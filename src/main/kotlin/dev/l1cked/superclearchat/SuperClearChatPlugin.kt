package dev.l1cked.superclearchat

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.plugin.java.JavaPlugin

class SuperClearChatPlugin : JavaPlugin() {
    override fun onEnable() {
        val command = requireNotNull(getCommand(COMMAND_NAME)) {
            "Command '$COMMAND_NAME' is missing from plugin.yml"
        }

        command.setExecutor(SuperClearChatCommand)
        command.tabCompleter = SuperClearChatCommand
    }

    private object SuperClearChatCommand : TabExecutor {
        override fun onCommand(
            sender: CommandSender,
            command: Command,
            label: String,
            args: Array<out String>,
        ): Boolean {
            if (!sender.hasPermission(PERMISSION)) {
                sender.sendMessage(NO_PERMISSION_MESSAGE)
                return true
            }

            if (args.isNotEmpty()) {
                sender.sendMessage(Component.text("Usage: /$label", NamedTextColor.RED))
                return true
            }

            val onlinePlayers = Bukkit.getOnlinePlayers()
            for (player in onlinePlayers) {
                for (line in CLEAR_LINES) {
                    player.sendMessage(line)
                }
            }

            sender.sendMessage(CLEARED_MESSAGE)
            return true
        }

        override fun onTabComplete(
            sender: CommandSender,
            command: Command,
            alias: String,
            args: Array<out String>,
        ): List<String> = emptyList()
    }

    private companion object {
        private const val COMMAND_NAME = "clearchat"
        private const val PERMISSION = "clearchat.use"

        private val NO_PERMISSION_MESSAGE: Component =
            Component.text("You do not have permission to use this command.", NamedTextColor.RED)

        private val CLEARED_MESSAGE: Component =
            Component.text("Chat cleared.", NamedTextColor.GREEN)

        private val CLEAR_LINES: Array<String> = Array(500) { index ->
            INVISIBLE_CHAT_FILL.toString().repeat(index + 1)
        }

        private const val INVISIBLE_CHAT_FILL: Char = '\u00A0'
    }
}
