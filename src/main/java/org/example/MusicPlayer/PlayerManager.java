package org.example.MusicPlayer;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;

import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerManager {
    private static PlayerManager INSTANCE;

    private final Map<Long, GuildMusicManager> musicManagers;
    private final AudioPlayerManager audioPlayerManager;



    public PlayerManager() {
        this.musicManagers = new HashMap<>();
        this.audioPlayerManager = new DefaultAudioPlayerManager();

        AudioSourceManagers.registerRemoteSources(this.audioPlayerManager);
        AudioSourceManagers.registerLocalSource(this.audioPlayerManager);
    }

    private String thumbnail(String url)  {
        if (url.contains("youtube")){


           return   "http://img.youtube.com/vi/"+ url.substring(url.indexOf("v=")+2)+"/0.jpg";
        }
        else if(url.contains("youtube") && url.contains("&index")){
            return   "http://img.youtube.com/vi/"+ url.substring(url.indexOf("v=")+2,url.indexOf("&index"))+"/0.jpg";
        }
        return null;
    }

    private long[] calculateTime(long time){
        long[] duration = new long[4];
       duration[0]= time / (1000 * 60 * 60);
        time = time % (1000 * 60 * 60);
        duration[1]= time / (1000 * 60);
        time = time % (1000 * 60);
        duration[2]= time / 1000;
        time = time % 1000;
        duration[3] = time;
        return  duration;


    }




    public GuildMusicManager getMusicManager(Guild guild) {
        return this.musicManagers.computeIfAbsent(guild.getIdLong(), (guildId) -> {
            final GuildMusicManager guildMusicManager = new GuildMusicManager(this.audioPlayerManager);

            guild.getAudioManager().setSendingHandler(guildMusicManager.getSendHandler());

            return guildMusicManager;
        });
    }

    public void loadAndPlay(TextChannel channel, String trackUrl) {
        final GuildMusicManager musicManager = this.getMusicManager(channel.getGuild());

        this.audioPlayerManager.loadItemOrdered(musicManager, trackUrl, new AudioLoadResultHandler() {
            @Override
            public void trackLoaded(AudioTrack track) {
                musicManager.scheduler.queue(track);
                AudioTrackInfo  audioTrackInfo = track.getInfo();
                long[] times = calculateTime(audioTrackInfo.length);
                EmbedBuilder embedBuilder = new EmbedBuilder();
                System.out.println("çalıştı");
                embedBuilder.setTitle(audioTrackInfo.author)
                        .addField("",audioTrackInfo.title,false)
                        .addField("",times[0]+":"+times[1]+":"+times[2]+":"+times[3],false)
                        .setThumbnail(thumbnail(audioTrackInfo.uri));

                System.out.println(audioTrackInfo.title);
                channel.sendMessageEmbeds(embedBuilder.build()).queue();


            }

            @Override
            public void playlistLoaded(AudioPlaylist playlist) {
                final List<AudioTrack> tracks = playlist.getTracks();



                for (final AudioTrack track : tracks) {
                    musicManager.scheduler.queue(track);

                }
                AudioTrackInfo  audioTrackInfo = tracks.get(0).getInfo();
                long[] times = calculateTime(audioTrackInfo.length);
                EmbedBuilder embedBuilder = new EmbedBuilder();
                System.out.println("çalıştı");
                embedBuilder.setTitle(audioTrackInfo.author)
                        .addField("",audioTrackInfo.title,false)
                        .addField("",times[0]+":"+times[1]+":"+times[2]+":"+times[3],false)
                        .setThumbnail(thumbnail(audioTrackInfo.uri));

                System.out.println(audioTrackInfo.title);
                channel.sendMessageEmbeds(embedBuilder.build()).queue();

            }

            @Override
            public void noMatches() {
                //
            }

            @Override
            public void loadFailed(FriendlyException exception) {
                //
            }
        });
    }

    public static PlayerManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PlayerManager();
        }

        return INSTANCE;
    }

}
