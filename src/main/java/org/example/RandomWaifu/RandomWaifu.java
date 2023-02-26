package org.example.RandomWaifu;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class RandomWaifu {
    private String ImageUrl;

    public String getImageUrl() {
        return ImageUrl;
    }

    public RandomWaifu(String type, String category){
        try{
            URL urlOfFact = new URL("https://api.waifu.pics/"+type+"/"+category);
            Scanner sc = new Scanner(urlOfFact.openStream());
            StringBuffer sb = new StringBuffer();
            while(sc.hasNext()) {
                sb.append(sc.nextLine());

            }
            String result = sb.toString();
            result = result.replaceAll("<[^>]*>", "");
            ImageUrl = result.substring(result.indexOf("https"),result.indexOf("\"}"));

            // System.out.println(fact);

        }catch (Exception e){
            System.out.println(e.getMessage());

        }

    }

}
