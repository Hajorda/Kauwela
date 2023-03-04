package org.example.Commands.Music;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.example.Commands.ACommand;
import org.example.MusicPlayer.GuildMusicManager;
import org.example.MusicPlayer.PlayerManager;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class queue extends Music{
    public queue(HashMap<String, ACommand> hashMap){
        super(hashMap);
        slashCommandData=Commands.slash("queue", "Çalma listesini gösterir.");
    }
    @Override
    public void doSomething(@NotNull SlashCommandInteractionEvent event) {
        if (!event.getGuild().getMemberById(botID).getVoiceState().inAudioChannel()) {
            event.reply("Ses kanalında değilim la").queue();

        } else if (!event.getMember().getVoiceState().inAudioChannel()) {
            event.reply("Kardeşşşş ses kanalında değilsin").queue();
        }else {
            GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());
            BlockingQueue<AudioTrack> queue = musicManager.scheduler.queue;
            if (queue.isEmpty()){
                event.reply("Çalma listesi boş").queue();
            }
            else {
                int trackCount = Math.min(queue.size(), 20);
                List<AudioTrack> trackList = new ArrayList<>(queue);

                List<MessageEmbed> embeds = new ArrayList<>();
                EmbedBuilder embed = new EmbedBuilder()
                        .setTitle("Çalma listesi:");
                embeds.add(embed.build());

                for (int i = 0; i <  trackCount; i++) {
                    AudioTrack track = trackList.get(i);
                    AudioTrackInfo info = track.getInfo();
                    long[] times = calculateTime(info.length);
                    EmbedBuilder builder = new EmbedBuilder()
                            .setAuthor(info.author)
                            .setDescription(info.title)
                            .addField("",times[0]+":"+times[1]+":"+times[2]+":"+times[3],false)
                            .setThumbnail(thumbnail(info.uri));
                    embeds.add(builder.build());



                }
                event.replyEmbeds(embeds).queue();
            }


        }

    }
}
