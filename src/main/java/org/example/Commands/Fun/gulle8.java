package org.example.Commands.Fun;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.example.Commands.ACommand;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class gulle8 extends ACommand {
    private final List<EmbedBuilder> embedBuilders;
    private final Random random;
    private int randomNumber;



    public gulle8(HashMap<String,ACommand> hashMap) {
        super(hashMap,"8top");
        commandName = "8top";
        slashCommandData= Commands.slash("8top", "Anneni sor").addOption(OptionType.STRING, "soru", "Sorunu sor bakem", true);
        embedBuilders = new ArrayList<>();
        random = new Random();
        embedBuilders.add(new EmbedBuilder().setDescription("Evet"));
        embedBuilders.add(new EmbedBuilder().setDescription("Hayır"));
        embedBuilders.add(new EmbedBuilder().setDescription("Belki"));
        embedBuilders.add(new EmbedBuilder().setDescription("Kesin bir şey diyemem"));
        embedBuilders.add(new EmbedBuilder().setDescription("Asla Enayi"));
        embedBuilders.add(new EmbedBuilder().setDescription("İnan bilmiyorum").setImage("https://galeri14.uludagsozluk.com/844/inan-bilmiyorum_2286860.jpg"));

    }

    @Override
    public void doSomething(@NotNull SlashCommandInteractionEvent event) {

        randomNumber = random.nextInt(0, embedBuilders.size());

        EmbedBuilder soru = new EmbedBuilder().setDescription(event.getOptions().get(0).getAsString()).setTitle("Soru");
        event.replyEmbeds(soru.build(), embedBuilders.get(randomNumber).setColor(Color.red).build()).queue();

    }
}
