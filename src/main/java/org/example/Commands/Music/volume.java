package org.example.Commands.Music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.managers.AudioManager;
import org.example.Commands.ACommand;
import org.example.MusicPlayer.GuildMusicManager;
import org.example.MusicPlayer.PlayerManager;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class volume extends Music{
    public volume(HashMap<String, ACommand> hashMap){
        super(hashMap);
        slashCommandData=Commands.slash("volume", "Ses seviyesini ayarlar").addOption(OptionType.INTEGER,"volume","Kardeşşş çok açma",true);
    }
    @Override
    public void doSomething(@NotNull SlashCommandInteractionEvent event) {
        GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());
        AudioManager audioManager = event.getGuild().getAudioManager();
        AudioPlayer audioPlayer = musicManager.audioPlayer;
        int volume = event.getOptions().get(0).getAsInt();
        if (!event.getGuild().getMemberById(botID).getVoiceState().inAudioChannel()) {
            event.reply("Ses kanalında değilim la").queue();

        }
        else if (!event.getMember().getVoiceState().inAudioChannel()) {
            event.reply("Kardeşşşş ses kanalında değilsin").queue();
        }else {
            audioPlayer.setVolume(volume);
            event.replyEmbeds(new EmbedBuilder()
                    .setThumbnail("https://media.discordapp.net/attachments/984469722500329474/1076536703365435522/image.png")
                    .setFooter(event.getUser().getName(),event.getUser().getAvatarUrl())
                    .setTitle( event.getUser().getName()+ " şarkı seviyesini "+ volume+"% e ayarlardı").build()).queue();
        }



    }
}
