package org.example.Commands.Utilites;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.example.Commands.ACommand;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class userinfo extends ACommand {
    public userinfo(HashMap<String, ACommand> hashMap) {
        super(hashMap);
    }

    @Override
    public void doSomething(@NotNull SlashCommandInteractionEvent event) {

    }
}
