package org.example.RandomWaifu;

import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class RandomMeme {

   public static EmbedBuilder randomMemeGenerate() throws URISyntaxException {
    try{
       HttpRequest post = HttpRequest.newBuilder()

            .uri(new URI("https://meme-api.com/gimme"))
            .GET()
            .build();

       HttpClient httpClient = HttpClient.newHttpClient();
       HttpResponse<String> postResponse = httpClient.send(post, HttpResponse.BodyHandlers.ofString());
       String response = postResponse.body();
        System.out.println(response);


        String ImageUrl = response.substring(response.indexOf("url")+6,response.indexOf("nsfw")-3);
        String author = response.substring(response.indexOf("author")+9,response.indexOf("ups")-3);
        String title = response.substring(response.indexOf("title")+8,response.indexOf("url")-3);
        System.out.println("IMAGE URL : "+ImageUrl+"author "+author+"title:  "+title);
        EmbedBuilder embedMeme = new EmbedBuilder()
                .setImage(ImageUrl)
                .setFooter("KauwelaBot - u/"+author,"https://media.discordapp.net/attachments/984469722500329474/1076536703365435522/image.png")
                .setColor(Color.yellow)
                .setAuthor(title);
       return embedMeme;
    }catch (Exception e){

        System.out.println(e.getMessage());
       return null;
    }
   }
    public static EmbedBuilder trmemeGenerator() throws URISyntaxException {
        try{
            HttpRequest post1 = HttpRequest.newBuilder()

                    .uri(new URI("https://meme-api.com/gimme/TurkeyJerky"))
                    .GET()
                    .build();

            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> postResponse = httpClient.send(post1, HttpResponse.BodyHandlers.ofString());
            String response = postResponse.body();
            System.out.println(response);


            String ImageUrl = response.substring(response.indexOf("url")+6,response.indexOf("nsfw")-3);
            String author = response.substring(response.indexOf("author")+9,response.indexOf("ups")-3);
            String title = response.substring(response.indexOf("title")+8,response.indexOf("url")-3);
            System.out.println("IMAGE URL : "+ImageUrl+"author "+author+"title:  "+title);
            EmbedBuilder embedMeme1 = new EmbedBuilder()
                    .setImage(ImageUrl)
                    .setFooter("KauwelaBot - u/"+author,"https://media.discordapp.net/attachments/984469722500329474/1076536703365435522/image.png")
                    .setColor(Color.yellow)
                    .setAuthor(title);
            return embedMeme1;
        }catch (Exception e){

            System.out.println(e.getMessage());
            return null;
        }
    }
    public static EmbedBuilder floodGenerator() throws URISyntaxException {
        try{
            HttpRequest post1 = HttpRequest.newBuilder()

                    .uri(new URI("https://meme-api.com/gimme/TurkeyJerky"))
                    .GET()
                    .build();

            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> postResponse = httpClient.send(post1, HttpResponse.BodyHandlers.ofString());
            String response = postResponse.body();
            System.out.println(response);


            String ImageUrl = response.substring(response.indexOf("url")+6,response.indexOf("nsfw")-3);
            String author = response.substring(response.indexOf("author")+9,response.indexOf("ups")-3);
            String title = response.substring(response.indexOf("title")+8,response.indexOf("url")-3);
            System.out.println("IMAGE URL : "+ImageUrl+"author "+author+"title:  "+title);
            EmbedBuilder embedMeme1 = new EmbedBuilder()
                    .setImage(ImageUrl)
                    .setFooter("KauwelaBot - u/"+author,"https://media.discordapp.net/attachments/984469722500329474/1076536703365435522/image.png")
                    .setColor(Color.yellow)
                    .setAuthor(title);
            return embedMeme1;
        }catch (Exception e){

            System.out.println(e.getMessage());
            return null;
        }
    }
}
