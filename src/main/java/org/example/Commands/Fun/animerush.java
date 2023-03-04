package org.example.Commands.Fun;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.example.Commands.ACommand;
import org.example.RSS.RssReader;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class animerush extends ACommand {
    public animerush(HashMap<String,ACommand> hashMap){
        super(hashMap);
        slashCommandData=Commands.slash("animerush", "Son çıkan animeyi gösterir");
    }
    @Override
    public void doSomething(@NotNull SlashCommandInteractionEvent event) {
        try {
            RssReader reader = new RssReader("https://www.animerush.tv/rss.xml");
               /* EmbedBuilder anime = new EmbedBuilder()
                        .setDescription(reader.getAnimeLink())
                        .setThumbnail("https/www.animerush.tv/anime-images-big/")
                        .setTitle(reader.getLatestAnime());*/

            event.reply(reader.getAnimeLink()).queue();

        } catch (Exception e) {
            event.reply("Hata baby").queue();
            throw new RuntimeException(e);
        }


    }
}
