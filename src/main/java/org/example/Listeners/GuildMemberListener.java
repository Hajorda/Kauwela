package org.example.Listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.events.guild.voice.GenericGuildVoiceEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.events.user.UserActivityStartEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;
import org.example.MusicPlayer.GuildMusicManager;
import org.example.MusicPlayer.PlayerManager;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class GuildMemberListener extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {

        String avatarUrl = event.getUser().getEffectiveAvatarUrl();
        String name = event.getUser().getName();
        System.out.println("dadassssssssssssssss");
        System.out.println(name+" sunucuya katıldı!");

        event.getGuild().getDefaultChannel().asTextChannel().sendMessage(name+" katıldı").queue();

        EmbedBuilder joinEmbed = new EmbedBuilder()
                .setColor(new Color(10, 238, 13))
                .setThumbnail(avatarUrl)
                .setFooter("Kauwela Bot", "https://media.discordapp.net/attachments/984469722500329474/1076536703365435522/image.png")
                .setAuthor("Sunucuya yeni biri katıldı!")
                .addField(name+" adlı kullanıcı sunucuya katıldı!", "", false);

        event.getJDA().getGuildById("984469721455919174").getTextChannelById("984473410044305459").sendMessageEmbeds(joinEmbed.build()).queue();
    }

    @Override
    public void onGuildMemberRemove(GuildMemberRemoveEvent event) {
        String avatarUrl = event.getUser().getEffectiveAvatarUrl();
        String name = event.getUser().getName();

        event.getGuild().getDefaultChannel().asTextChannel().sendMessage(name+" ayrıldı").queue();

        EmbedBuilder leaveEmbed = new EmbedBuilder()
                .setColor(new Color(231, 9, 9))
                .setThumbnail(avatarUrl)
                .setFooter("Kauwela Bot", "https://media.discordapp.net/attachments/984469722500329474/1076536703365435522/image.png")
                .setAuthor("Sunucuya biri ayrıldı")
                .addField(name+" adlı kullanıcı sunucuya terk etti!", "", false);

        event.getJDA().getGuildById("984469721455919174").getTextChannelById("984473410044305459").sendMessageEmbeds(leaveEmbed.build()).queue();

    }

    @Override
    public void onGuildVoiceUpdate(GuildVoiceUpdateEvent event) {
        System.out.println("deneme1111");
       if (event.getChannelJoined() !=null){
           if (event.getMember().getUser().getId().equals("362965641357033472")){
               AudioManager audioManager = event.getGuild().getAudioManager();
               VoiceChannel memberchanne = event.getMember().getVoiceState().getChannel().asVoiceChannel();
               audioManager.openAudioConnection(memberchanne);
               PlayerManager.getInstance().loadAndPlayNonEmbed(event.getGuild().getTextChannels().get(0),"https://www.youtube.com/watch?v=unPQQQ8RDKw");

           }

       }
        if (event.getChannelJoined() !=null) {
            if (!(event.getMember().getUser().getId().equals("984469828008026192"))) {

                AudioManager audioManager = event.getGuild().getAudioManager();
                VoiceChannel memberchanne = event.getMember().getVoiceState().getChannel().asVoiceChannel();
                audioManager.openAudioConnection(memberchanne);
                PlayerManager.getInstance().loadAndPlayNonEmbed(event.getGuild().getTextChannels().get(0), "https://www.youtube.com/watch?v=2kwA5T7AjXY");

                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        audioManager.closeAudioConnection();
                    }
                };
                timer.schedule(task, PlayerManager.getInstance().getDuration() + 2000);
            }
        }

    }


}
