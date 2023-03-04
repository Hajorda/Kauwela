package org.example.Commands.Fun;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.example.ChatGPT.DallE;
import org.example.Commando.CommandManager;
import org.example.Commands.ACommand;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.HashMap;

public class rimage extends ACommand {
    public rimage(HashMap<String,ACommand> hashMap){
        super(hashMap);
        slashCommandData= Commands.slash("rimage", "Resim yap enayi").addOption(OptionType.STRING, "metin", "Nasıl bir şey olsun bacım", true);
    }

    @Override
    public void doSomething(@NotNull SlashCommandInteractionEvent event) {

        String prompt = event.getOptions().get(0).getAsString();
        event.deferReply().queue();


        try {
            url = DallE.ImageCreaterRandomizer(prompt);
            CommandManager.url=url;
            event.getHook().sendMessageEmbeds(new EmbedBuilder().setImage(url).setFooter(event.getUser().getName(), event.getUser().getAvatarUrl()).setAuthor(prompt).setColor(Color.magenta).build()).addActionRow(Button.primary("rimage_save", "Save")).queue();
        } catch (Exception e) {
            event.getHook().sendMessage("API de sıkıntı çıktı").queue();
        }
    }
}
