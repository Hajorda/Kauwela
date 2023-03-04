package org.example.Commands.Core;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.example.Commands.ACommand;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class support extends ACommand {
    public support(HashMap<String,ACommand> hashMap){
        super(hashMap);
        slashCommandData= Commands.slash("support", "Kauwela Bot support server");
    }
    @Override
    public void doSomething(@NotNull SlashCommandInteractionEvent event) {
        event.reply("Click the button for **Offical Kauwela Bot Support Server**").addActionRow(Button.link("https://discord.gg/jXpT9rtHMN", "Invite")).queue();
    }
}
