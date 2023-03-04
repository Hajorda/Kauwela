package org.example.Commands.Core;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.example.Commands.ACommand;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class invite extends ACommand {
    public invite(HashMap<String,ACommand> hashMap){
        super(hashMap);
        slashCommandData= Commands.slash("invite", "İnviting for Kauwela Bot");
    }
    @Override
    public void doSomething(@NotNull SlashCommandInteractionEvent event) {
        event.reply("Click the button for Invite Kauwela Bot to your server").addActionRow(Button.link("https://ptb.discord.com/api/oauth2/authorize?client_id=984469828008026192&permissions=8&scope=bot%20applications.commands", "Invite")).queue();
    }
}
