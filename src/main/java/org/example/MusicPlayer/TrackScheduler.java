package org.example.MusicPlayer;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import net.dv8tion.jda.api.EmbedBuilder;
import org.example.Commands.CommandManager;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TrackScheduler extends AudioEventAdapter {
    public final AudioPlayer player;
    public final BlockingQueue<AudioTrack> queue;
    public boolean repeating = false;

    public TrackScheduler(AudioPlayer player) {
        this.player = player;
        this.queue = new LinkedBlockingQueue<>();
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
    private String thumbnail(String url)  {
        if (url.contains("youtube")){


            return   "http://img.youtube.com/vi/"+ url.substring(url.indexOf("v=")+2)+"/0.jpg";
        }
        else if(url.contains("youtube") && url.contains("&index")){
            return   "http://img.youtube.com/vi/"+ url.substring(url.indexOf("v=")+2,url.indexOf("&index"))+"/0.jpg";
        }
        return null;
    }

    public void queue(AudioTrack track) {
        if (!this.player.startTrack(track, true)) {
            this.queue.offer(track);
        }
    }

    public void nextTrack() {
        AudioTrackInfo audioTrackInfo = queue.element().getInfo();
        long[] times = calculateTime(audioTrackInfo.length);
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle(audioTrackInfo.author)
                .addField("",audioTrackInfo.title,false)
                .addField("",times[0]+":"+times[1]+":"+times[2]+":"+times[3],false)
                .setThumbnail(thumbnail(audioTrackInfo.uri));

        System.out.println(audioTrackInfo.title);
        CommandManager.textChannel.sendMessageEmbeds(embedBuilder.build()).queue();

        this.player.startTrack(this.queue.poll(), false);

    }

    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
        if (endReason.mayStartNext) {
            if (this.repeating) {
                this.player.startTrack(track.makeClone(), false);
                return;
            }

            nextTrack();
        }
    }
}
