package org.example.Commands.Music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.example.Commands.ACommand;
import org.example.MusicPlayer.GuildMusicManager;
import org.example.MusicPlayer.PlayerManager;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.concurrent.BlockingQueue;

public class skip extends Music{
    public skip(HashMap<String, ACommand> hashMap){
        super(hashMap);
        slashCommandData= Commands.slash("skip", "Şarkıyı geçer");
    }
    @Override
    public void doSomething(@NotNull SlashCommandInteractionEvent event) {
        GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());
        AudioPlayer audioPlayer = musicManager.audioPlayer;
        BlockingQueue<AudioTrack> queue = musicManager.scheduler.queue;
        if (!event.getGuild().getMemberById(botID).getVoiceState().inAudioChannel()) {
            event.reply("Ses kanalında değilim la").queue();

        } else if (audioPlayer.getPlayingTrack() == null) {
            event.replyEmbeds(new EmbedBuilder().setDescription("Şuan herhangi bir şarkı çalmıyor").build()).queue();
        } else if (!event.getMember().getVoiceState().inAudioChannel()) {
            event.reply("Kardeşşşş ses kanalında değilsin").queue();
        }else if (queue.isEmpty()){
            event.reply("Çalma listesi boş").queue();
        }
        else {
            musicManager.scheduler.nextTrack();
            event.reply("Sıradaki şarkıya geçildi.").queue();

        }
    }
}
