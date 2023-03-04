package org.example.Commands.Fun;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.example.Commands.ACommand;
import org.example.RandomCat.RandomCuteKedy;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;

public class kedysearch extends ACommand {
    private final RandomCuteKedy randomCuteKedy = new RandomCuteKedy();
    public kedysearch(HashMap<String,ACommand> hashMap){
        super(hashMap);

        try {
            optionData = new OptionData(OptionType.STRING, "tag", "kedyler çok sevimli", true)

                    .addChoices(randomCuteKedy.getChoices(10));
            slashCommandData =Commands.slash("kedysearch", "Kedy Arama Motoru").addOptions(optionData);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void doSomething(@NotNull SlashCommandInteractionEvent event) {
        try {
            String prompt = event.getOptions().get(0).getAsString();
            RandomCuteKedy cat = new RandomCuteKedy();
            EmbedBuilder randomkedi = new EmbedBuilder()
                    .setDescription(prompt)
                    .setImage(cat.getImage(prompt));
            // System.out.println(url);
            event.replyEmbeds(randomkedi.build()).queue();

        } catch (IOException e) {
            event.replyEmbeds(new EmbedBuilder().setDescription("Bulunamadı").build()).queue();
            throw new RuntimeException(e);

        }


    }
}
