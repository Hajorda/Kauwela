package org.example.Commando;

import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

import org.example.Commands.ACommand;

import org.example.Commands.Core.*;
import org.example.Commands.Fun.*;
import org.example.Commands.Music.*;
import org.example.Commands.Utilites.*;
import org.jetbrains.annotations.NotNull;


import java.io.IOException;

import java.util.*;


public class CommandManager extends ListenerAdapter {
    private HashMap<String, ACommand> hasmap ;
    public static TextChannel textChannel;
    public static String url;


    public CommandManager() {
        hasmap = new HashMap<>();
        //Core
        credits credits = new credits(hasmap);
        help help1=new help(hasmap);
        invite invite1 = new invite(hasmap);
        status status1=new status(hasmap);
        support support1 = new support(hasmap);
        uptime uptime1=new uptime(hasmap);
        //Fun
        activity activiy1 = new activity(hasmap);
        animerush animerush = new animerush(hasmap);
        cutekedy cutekedy1 = new cutekedy(hasmap);
        gulle8 gulle81 = new gulle8(hasmap);
        hug hug1=new hug(hasmap);
        kedy kedy1=new kedy(hasmap);
        kedysearch kedysearch1=new kedysearch(hasmap);
        randomdog randomdog1 = new randomdog(hasmap);
        randomgpt randomgpt1 = new randomgpt(hasmap);
        randommeme randommeme1 = new randommeme(hasmap);
        rimage rimage1 = new rimage(hasmap);
        soundboard soundboard1 =new soundboard(hasmap);
        trmeme trmeme1 = new trmeme(hasmap);
        waifu waifu1 = new waifu(hasmap);
        //Music
        leave leave1 = new leave(hasmap);
        nowplaying nowplaying1 = new nowplaying(hasmap);
        pause pause1 = new pause(hasmap);
        play play1 = new play(hasmap);
        queue queue1 = new queue(hasmap);
        resume resume1 = new resume(hasmap);
        skip skip1 = new skip(hasmap);
        volume volume1 = new volume(hasmap);
        //Utilites
        clear clear1 = new clear(hasmap);
        feedback feedback1 = new feedback(hasmap);
        serverinfo serverinfo1 = new serverinfo(hasmap);
       // userinfo userinfo1 = new userinfo(hasmap);





    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        String command = event.getName();
        textChannel = event.getChannel().asTextChannel();
        hasmap.get(command).doSomething(event);

    }


  /* @Override
    public void onReady(@NotNull ReadyEvent event) {
        List<CommandData> commandData = new ArrayList<CommandData>();
        commandData.add(Commands.slash("credits", "Kredi skorunu gösterir"));
        event.getJDA().updateCommands().addCommands(commandData).queue();

    }*/


    /*Test için ideal*/
    @Override
    public void onGuildReady(GuildReadyEvent event) {
        List<CommandData> commandData = new ArrayList<CommandData>();
        for (Map.Entry<String,ACommand>entry: hasmap.entrySet()){

           commandData.add(entry.getValue().getSlashCommandData());
        }


        commandData.add(Commands.context(Command.Type.USER, "Get user avatar"));
        commandData.add(Commands.message("Count words"));

        event.getGuild().updateCommands().addCommands(commandData).queue();
    }


}


