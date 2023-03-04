package org.example.Commands.Core;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.example.Commands.ACommand;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.HashMap;

public class credits extends ACommand {
    public credits(HashMap<String,ACommand> hashMap){
        super(hashMap);
        slashCommandData=Commands.slash("credits", "Kredi skorunu gösterir");
    }
    @Override
    public void doSomething(@NotNull SlashCommandInteractionEvent event) {
        EmbedBuilder asda = new EmbedBuilder();
        asda.setTitle("asdadada");
        EmbedBuilder creditEmbed = new EmbedBuilder()
                .setAuthor("Credits")
                .setColor(Color.YELLOW)
                .setThumbnail("https://media.discordapp.net/attachments/984469722500329474/1076536703365435522/image.png")
                .setFooter("Kauwela Bot", "https://media.discordapp.net/attachments/984469722500329474/1076536703365435522/image.png")
                .addField("Developers", "Hajorda#6261 , Cicikuş#0309", false)
                .addField("Contact", "You can contact us for any feedback with using Discord", false)
                .addField("", "For additional information you can use the buttons.", false)
                .setImage("https://media.discordapp.net/attachments/984473352351670302/1080264390198177893/NdxQXD-Hg0i9-VDaC4UXx-transformed_Ozel.png");

        event.replyEmbeds(creditEmbed.build()).addActionRow(net.dv8tion.jda.api.interactions.components.buttons.Button.link("https://github.com/Hajorda", "Hajorda's GitHub"),
                Button.link("https://github.com/Cicikuss", "Cicikus's GitHub")).queue();
    }
}
