package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import org.apache.http.client.fluent.Request;

public class Main {
    public static void main(String[] args) throws IOException {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players";
        
        String bodyText = Request.Get(url).execute().returnContent().asString();
                
//        System.out.println("json-muotoinen data:");
//        System.out.println( bodyText );

        Gson mapper = new Gson();
        Player[] players = mapper.fromJson(bodyText, Player[].class);
        ArrayList<Player> suomi = new ArrayList<>();
        for (Player player : players) {
            if (player.getNationality().contains("FIN")) {
                suomi.add(player);
            }
        }
        Collections.sort(suomi);
        System.out.println("Pelaajat:");
        for (Player player : suomi) {
            System.out.println(player);
        }   
    }
  
}
