package org.example.Commands.Fun;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.example.Commands.ACommand;
import org.example.RandomWaifu.RandomActivity;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class activity extends ACommand {
    public activity(HashMap<String,ACommand> hashMap){
        super(hashMap);
        slashCommandData=Commands.slash("activity", "Canın mı sıkıldı");
    }
    @Override
    public void doSomething(@NotNull SlashCommandInteractionEvent event) {
        event.replyEmbeds(RandomActivity.randomActivityGenerator().build()).queue();
    }
}
