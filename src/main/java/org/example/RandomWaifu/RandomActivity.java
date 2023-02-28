package org.example.RandomWaifu;

import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RandomActivity {
   // https://www.boredapi.com/api/activity

    public static EmbedBuilder randomActivityGenerator(){
        try{

                HttpRequest post = HttpRequest.newBuilder()

                        .uri(new URI("https://www.boredapi.com/api/activity"))
                        .GET()
                        .build();

                HttpClient httpClient = HttpClient.newHttpClient();
                HttpResponse<String> postResponse = httpClient.send(post, HttpResponse.BodyHandlers.ofString());
                String response = postResponse.body();
                System.out.println(response);





            String title = response.substring(response.indexOf("activity")+11,response.indexOf("type")-3);
            String type = response.substring(response.indexOf("type")+7,response.indexOf("participants")-3);

            EmbedBuilder embedMeme = new EmbedBuilder()
                    .setFooter("KauwelaBot","https://media.discordapp.net/attachments/984469722500329474/1076536703365435522/image.png")
                    .setDescription("Type: "+type)
                    .setColor(Color.yellow)
                    .setThumbnail("https://media.discordapp.net/attachments/808584895516508200/1059121585707040858/MemeFeedBot.gif")
                    .setAuthor(title);

            return embedMeme;
        }catch (Exception e){

            System.out.println(e.getMessage());
            return null;
        }
    }
}
