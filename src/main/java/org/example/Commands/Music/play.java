package org.example.Commands.Music;

import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.managers.AudioManager;
import org.example.Commands.ACommand;
import org.example.MusicPlayer.PlayerManager;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class play extends Music{
    public play(HashMap<String, ACommand> hashMap){super(hashMap);
        optionData = new OptionData(OptionType.STRING, "music", "Gece Vakti'ini açmazsan darılırım", true);
        slashCommandData = Commands.slash("play", "Hardalı dinle çok iyi grup").addOptions(optionData);
    }
    @Override
    public void doSomething(@NotNull SlashCommandInteractionEvent event) {
        if (!event.getMember().getVoiceState().inAudioChannel()) {

            event.reply("İlk önce ses kanalına girmelisin").queue();
        } else {
            AudioManager audioManager = event.getGuild().getAudioManager();
            VoiceChannel memberchanne = event.getMember().getVoiceState().getChannel().asVoiceChannel();
            audioManager.openAudioConnection(memberchanne);

            PlayerManager.getInstance().loadAndPlay(event.getChannel().asTextChannel(), event.getOptions().get(0).getAsString());

            textChannel = event.getChannel().asTextChannel();
            event.reply(event.getMember().getAsMention() + "tarafından listeye şarkı eklendi.").queue();


        }
    }
}
