# SuperClearChat

SuperClearChat is a small Paper plugin that clears visible player chat with one command.

## Features

- `/clearchat` clears visible chat for all online players.
- `clearchat.use` controls access to the command.
- Sends 500 precomputed blank chat lines using no-break spaces (`U+00A0`) to reduce normal-space normalization while keeping the output invisible in normal clients.
- No config, listeners, storage, or external runtime dependencies.

## Requirements

- Paper 1.21.x
- Java 21 or newer

The plugin is compiled against Paper API `1.21.8-R0.1-SNAPSHOT` and uses stable Bukkit/Paper command and message APIs.

## Build

```powershell
.\gradlew.bat clean build --warning-mode all
```

The plugin jar is created at:

```text
build/libs/SuperClearChat-1.0.0.jar
```

## Install

1. Stop the server.
2. Copy `build/libs/SuperClearChat-1.0.0.jar` into the server `plugins` folder.
3. Start the server.
4. Grant `clearchat.use` to the staff group that should use the command.

## Command

| Command | Permission | Description |
| --- | --- | --- |
| `/clearchat` | `clearchat.use` | Clears visible chat for online players. |

## Permission

```yaml
clearchat.use:
  default: op
```

Operators can use the command by default. Permission plugins can grant or revoke `clearchat.use` normally.

## Notes

Clear-chat commands move old messages out of the visible scrollback by sending blank lines. They do not delete signed chat history or moderate previous messages.
