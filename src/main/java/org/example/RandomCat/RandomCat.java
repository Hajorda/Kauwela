package org.example.RandomCat;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class RandomCat {
    private String fact;
    private  String ImageURL;

    public RandomCat() {
        try{
            URL urlOfFact = new URL("https://catfact.ninja/fact");
            Scanner sc = new Scanner(urlOfFact.openStream());
            StringBuffer sb = new StringBuffer();
            while(sc.hasNext()) {
                sb.append(sc.nextLine());

            }
            String result = sb.toString();
            result = result.replaceAll("<[^>]*>", "");
            byte[] bytes = result.substring(result.indexOf("fact")+7,result.indexOf("length")-3).getBytes(StandardCharsets.UTF_8);
            fact =  new String(bytes, StandardCharsets.ISO_8859_1);
           // System.out.println(fact);

        }catch (Exception e){
            System.out.println(e.getMessage());
            fact = "Sistemde bir hata olmalı";
        }


        try{
            URL urlOfRandomIMage = new URL("https://api.thecatapi.com/v1/images/search");
            Scanner scn = new Scanner(urlOfRandomIMage.openStream());
            StringBuffer bs = new StringBuffer();
            while (scn.hasNext()){
                bs.append(scn.next());
            }
            String result2 = bs.toString();
            result2 = result2.replaceAll("<[^>]*>", "");
            ImageURL = new String(result2.substring(result2.indexOf("https"),result2.indexOf("jpg")+3));
            //System.out.println(ImageURL);

        }catch (Exception e){
            System.out.println(e.getMessage());

        }



    }

    public String getFact() {
        return fact;
    }

    public String getImageURL() {
        return ImageURL;
    }
}
