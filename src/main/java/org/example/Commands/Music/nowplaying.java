package org.example.Commands.Music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.managers.AudioManager;
import org.example.Commands.ACommand;
import org.example.MusicPlayer.GuildMusicManager;
import org.example.MusicPlayer.PlayerManager;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class nowplaying extends Music{
    public nowplaying(HashMap<String, ACommand> hashMap){
        super(hashMap);
        slashCommandData=Commands.slash("nowplaying", "Çalan şarkıyı gösterir.");
    }
    @Override
    public void doSomething(@NotNull SlashCommandInteractionEvent event) {
        if (!event.getGuild().getMemberById(botID).getVoiceState().inAudioChannel()) {
            event.reply("Ses kanalında değilim la").queue();

        } else if (!event.getMember().getVoiceState().inAudioChannel()) {
            event.reply("Kardeşşşş ses kanalında değilsin").queue();
        }
        GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());
        AudioManager audioManager = event.getGuild().getAudioManager();
        AudioPlayer audioPlayer = musicManager.audioPlayer;
        AudioTrack track = audioPlayer.getPlayingTrack();
        if (track == null) {
            event.reply("Herhangi müzik çalmıyor").queue();

        }else {
            AudioTrackInfo info = track.getInfo();
            long[] times = calculateTime(info.length);
            EmbedBuilder builder = new EmbedBuilder()
                    .setAuthor(info.author)
                    .setDescription(info.title)
                    .addField("",times[0]+":"+times[1]+":"+times[2]+":"+times[3],false)
                    .setThumbnail(thumbnail(info.uri));
            event.replyEmbeds(builder.build()).queue();

        }

    }
}
