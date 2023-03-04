package org.example.Commands.Fun;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.example.Commands.ACommand;
import org.example.RandomWaifu.RandomWaifu;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class waifu extends ACommand {
    public waifu(HashMap<String,ACommand> hashMap){
        super(hashMap);
        slashCommandData=Commands.slash("waifu", "Keşke anime kızları gerçek olsa");
    }
    @Override
    public void doSomething(@NotNull SlashCommandInteractionEvent event) {
        event.replyEmbeds(new EmbedBuilder().setImage(new RandomWaifu("sfw", "waifu").getImageUrl()).build()).queue();
    }
}
