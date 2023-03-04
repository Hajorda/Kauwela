package org.example.Commands.Core;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.example.Commands.ACommand;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.HashMap;

public class help extends ACommand {
    public help(HashMap<String,ACommand> hashMap){
        super(hashMap);
        slashCommandData= Commands.slash("help", "Command list");
    }

    @Override
    public void doSomething(@NotNull SlashCommandInteractionEvent event) {
        EmbedBuilder embedHelp = new EmbedBuilder()
                .setAuthor("Kauwela Bot", "https://ptb.discord.com/api/oauth2/authorize?client_id=984469828008026192&permissions=8&scope=bot%20applications.commands")
                .setColor(Color.YELLOW)
                .setThumbnail("https://media.discordapp.net/attachments/984469722500329474/1076536703365435522/image.png")
                .setFooter("Kauwela Bot", "https://media.discordapp.net/attachments/984469722500329474/1076536703365435522/image.png")
                .setDescription("Kauwela Bot is a multipurpose disocrd managment and fun bot. It's writen in Java using JDA and  it's curently developing")
                .addField(" \uD83D\uDCC1 Core Commands", """
                            `/help`  For command list\s
                            `/invite`  Bot's imvite link\s
                            `/support`  Bot's support server invite link
                            `/credits`  People behind the Bot
                            `/uptime`  How long the bot has been running
                            `/status`  Stats of Bot""", true)
                .addField("\uD83C\uDFB5 Music Commands", """
                            `/play`  Playing music
                            `/skip`  Skip the music
                            `/pause`  Pausing the music
                            `/leave`  Bot leaves the channel
                            `/nowplaying`  Shows the current song
                            `/queue`  Shows the queuueueueu""", true)
                .addField("ℹ️ Info Commands", """
                            `/userinfo`  User's info
                            `/serverinfo`  Server's info
                            `/clear`  Cleans the chat
                            `/feedback`  Any feedback for Bot""", true)
                .addField("\uD83C\uDF88 Fun Commands", """
                            `/8top` Ask Yes No questions
                            `/kedy`  Random cat photos and fun facts
                            `/soundboard`  Generates the soundboard
                            `/randomgpt`  Chat with GPT3 but random settings
                            `/rimage`  Generates images with Dale2
                            `/randommeme`  Gives you memes
                            `/trmeme`  Gives you turkish memes
                            `/waifu`  Gives you a random waifu picture
                            `/cutekedy`  Gives you cute kedys
                            `/kedysearch`  Biggest kedy searcing engine system in discord
                            `/activity`  Are you bored? This commands give you random activity
                            `/hug`  Yalnız mısın hiç arkadaşın yok mu bu komut tam sana göre kısaca e-sarılma komudu
                            `/randomdog`  Random Dogs""", true)
                .addField("", "Also When you right click a user from menu -> apps, you can get the users profile image`\n" +
                        "\uD83E\uDD16 Ayrıca botu etiketleyip bot ile GPT3 kullanarak sohbet edebilirsin.", false);


        event.replyEmbeds(embedHelp.build()).addActionRow(net.dv8tion.jda.api.interactions.components.buttons.Button.link("https://ptb.discord.com/api/oauth2/authorize?client_id=984469828008026192&permissions=8&scope=bot%20applications.commands", "Invite"),
                Button.link("https://discord.gg/jXpT9rtHMN", "Support Server")).queue();
    }
}
