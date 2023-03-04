package org.example.Commands.Fun;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.example.Commands.ACommand;
import org.example.RandomCat.RandomCat;
import org.example.RandomCat.RandomCuteKedy;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;

public class cutekedy extends ACommand {
    public cutekedy(HashMap<String,ACommand> hashMap){
        super(hashMap);
        slashCommandData=Commands.slash("cutekedy", "Tatliş kediler gönderir.");
    }
    @Override
    public void doSomething(@NotNull SlashCommandInteractionEvent event) {
        RandomCat kedy = new RandomCat();
        try {
            RandomCuteKedy cat = new RandomCuteKedy();
            EmbedBuilder randomkedi = new EmbedBuilder()
                    .setDescription("**Fact: **" + kedy.getFact())
                    .setImage(cat.getImage("cute"));
            // System.out.println(url);
            event.replyEmbeds(randomkedi.build()).queue();

        } catch (IOException e) {
            event.replyEmbeds(new EmbedBuilder().setDescription("API de sıkıntı var ").build()).queue();
            throw new RuntimeException(e);
        }

    }
}
