package org.example.RandomCat;

import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RandomDog {
    public static String randomDogGeneator(){
        try{

            HttpRequest post = HttpRequest.newBuilder()

                    .uri(new URI("https://random.dog/woof.json"))
                    .GET()
                    .build();

            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> postResponse = httpClient.send(post, HttpResponse.BodyHandlers.ofString());
            String response = postResponse.body();
            System.out.println(response);





            String dogUrl = response.substring(response.indexOf("url")+6,response.length()-2);
            System.out.println(dogUrl);


            return dogUrl;
        }catch (Exception e){

            System.out.println(e.getMessage());
            return null;
        }
    }
}
