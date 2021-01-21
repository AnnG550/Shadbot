package com.shadorc.shadbot.command.owner;

import com.shadorc.shadbot.command.CommandException;
import com.shadorc.shadbot.core.command.*;
import com.shadorc.shadbot.object.Emoji;
import discord4j.discordjson.json.ApplicationCommandOptionData;
import discord4j.discordjson.json.ApplicationCommandRequest;
import discord4j.discordjson.json.ImmutableApplicationCommandRequest;
import discord4j.rest.util.ApplicationCommandOptionType;
import reactor.core.publisher.Mono;

import static com.shadorc.shadbot.Shadbot.DEFAULT_LOGGER;

public class EnableCommandCmd extends BaseCmd {

    public EnableCommandCmd() {
        super(CommandCategory.OWNER, CommandPermission.OWNER, "enable_command", "Enable/disable a command");
    }

    @Override
    public ApplicationCommandRequest build(ImmutableApplicationCommandRequest.Builder builder) {
        return builder
                .addOption(ApplicationCommandOptionData.builder()
                        .name("command")
                        .description("The command to enable/disable")
                        .type(ApplicationCommandOptionType.STRING.getValue())
                        .required(true)
                        .build())
                .addOption(ApplicationCommandOptionData.builder()
                        .name("enabled")
                        .description("True to enable, false to disable")
                        .type(ApplicationCommandOptionType.BOOLEAN.getValue())
                        .required(true)
                        .build())
                .build();
    }

    @Override
    public Mono<?> execute(Context context) {
        final String commandName = context.getOption("command").orElseThrow();
        final BaseCmd cmd = CommandManager.getInstance().getCommand(commandName);
        if (cmd == null) {
            throw new CommandException(String.format("Command `%s` not found.", commandName));
        }

        final boolean enabled = context.getOptionAsBool("enabled").orElseThrow();
        cmd.setEnabled(enabled);

        DEFAULT_LOGGER.info("Command {} {}", cmd.getName(), enabled ? "enabled" : "disabled");

        return context.createFollowupMessage(Emoji.CHECK_MARK + " Command `%s` %s",
                commandName, enabled ? "enabled" : "disabled");
    }

}
