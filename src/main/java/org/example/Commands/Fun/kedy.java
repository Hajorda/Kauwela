package org.example.Commands.Fun;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.example.Commands.ACommand;
import org.example.RandomCat.RandomCat;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class kedy extends ACommand {
    public kedy(HashMap<String,ACommand> hashMap){
        super(hashMap);
        slashCommandData=Commands.slash("kedy", "Günlük kedy dozunu karşılar");
    }
    @Override
    public void doSomething(@NotNull SlashCommandInteractionEvent event) {
        RandomCat kedy = new RandomCat();

        EmbedBuilder randomkedi = new EmbedBuilder()
                .setDescription("**Fact: **" + kedy.getFact())
                .setImage(kedy.getImageURL());
        System.out.println(kedy.getImageURL());
        event.replyEmbeds(randomkedi.build()).queue();
    }
}
