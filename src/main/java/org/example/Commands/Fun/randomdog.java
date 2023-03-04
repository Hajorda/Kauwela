package org.example.Commands.Fun;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.example.Commands.ACommand;
import org.example.RandomCat.RandomDog;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.HashMap;

public class randomdog extends ACommand {
    public randomdog(HashMap<String,ACommand> hashMap){
        super(hashMap);
        slashCommandData=Commands.slash("randomdog", "köpek ");
    }
    @Override
    public void doSomething(@NotNull SlashCommandInteractionEvent event) {
        String url = RandomDog.randomDogGeneator();
        System.out.println(url.substring(url.length()-3,url.length()));
        if(url.substring(url.length()-3,url.length()).equals("mp4")){
            event.reply("**A random dog video** "+url).queue();
        }
        else
            event.replyEmbeds(new EmbedBuilder().setImage(url).setColor(Color.YELLOW).setAuthor("A random dog picture").setFooter("KauwelaBot","https://cdn.discordapp.com/attachments/984469722500329474/1076536703365435522/image.png").build()).queue();

    }
}
