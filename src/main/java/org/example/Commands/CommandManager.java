package org.example.Commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.example.RandomCat.RandomCat;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CommandManager extends ListenerAdapter {
    private List<EmbedBuilder> embedBuilders ;
    private Random random;
    private int randomNumber;

    public CommandManager(){
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
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        String command = event.getName();

        if (command.equals("8top")){
            randomNumber = random.nextInt(0,embedBuilders.size());

            EmbedBuilder soru = new EmbedBuilder().setDescription(event.getOptions().get(0).getAsString()).setTitle("Soru");
            event.replyEmbeds(soru.build(),embedBuilders.get(randomNumber).setColor(Color.red).build()).queue();

        } else if (command.equals("kedy")) {
            RandomCat kedy = new RandomCat();
            System.out.println(kedy.getFact());
            EmbedBuilder randomkedi = new EmbedBuilder().setDescription(kedy.getFact()).setImage(kedy.getImageURL());
            event.replyEmbeds(randomkedi.build()).queue();

        }
    }

  /*  @Override
    public void onReady(@NotNull ReadyEvent event) {
        List<CommandData> commandData = new ArrayList<CommandData>();
        commandData.add(Commands.slash("8top","Anneni sor").addOption(OptionType.STRING,"soru","Sorunu sor bakem",true));
        event.getJDA().updateCommands().addCommands(commandData).queue();
    }*/


    /*Test için ideal*/
    @Override
    public void onGuildReady(GuildReadyEvent event) {
        List<CommandData> commandData = new ArrayList<CommandData>();

        commandData.add(Commands.slash("8top","Anneni sor").addOption(OptionType.STRING,"soru","Sorunu sor bakem",true));
        commandData.add(Commands.slash("kedy","Günlük kedy dozunu karşılar"));
        event.getGuild().updateCommands().addCommands(commandData).queue();



    }
}
