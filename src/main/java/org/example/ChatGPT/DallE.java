package org.example.ChatGPT;

import com.theokanning.openai.image.CreateImageRequest;
import com.theokanning.openai.service.OpenAiService;
import io.github.cdimascio.dotenv.Dotenv;

import java.time.Duration;
import java.util.Random;

public class DallE {
    public static String ImageCreaterRandomizer(String prompt){
        OpenAiService openAiService = new OpenAiService(Dotenv.load().get("APIKEY"), Duration.ofMillis(1000000000));
        CreateImageRequest createImageRequest = CreateImageRequest.builder()
                .prompt(prompt)
                .n(new Random().nextInt(10)+1)
                .size("1024x1024")
                .build();

        return openAiService.createImage(createImageRequest).getData().get(0).getUrl();


    }
}
