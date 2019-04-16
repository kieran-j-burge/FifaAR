package FifaAR.Tools;


import FifaAR.Service.DataService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class HttpTools {

    private DataService dataService;


    public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36";

    @Autowired
    public HttpTools(DataService dataService) {
        this.dataService = dataService;
    }

    public String httpGetURL(String url_string){
        try {
            StringBuilder result = new StringBuilder();
            URL url = new URL(url_string);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
            return result.toString();

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    public void getPlayers(){

        Integer count = dataService.getLastPlayerCount() +1;
        //Fetch the page
        Document doc;
        Boolean end = Boolean.FALSE;
        Integer endCount = 0;

        while(end == Boolean.FALSE){

            try {
                doc = Jsoup.connect("https://www.futbin.com/19/player/"+count).userAgent(USER_AGENT).get();
                //Get player rating
                Elements ratingE = doc.select(".pcdisplay-rat");
                String rating = ratingE.first().text();
                System.out.println(rating);

                // Get players card name
                Elements cardNameE = doc.select(".pcdisplay-name");
                System.out.println(cardNameE.text());
                //Check if all players have been retreived
                if (!cardNameE.isEmpty()){
                    endCount = 0;
                    String cardName = cardNameE.first().text();
                    String longName = "l_name";
                    // Get player long name
                    Elements playerInfo = doc.select(".table-info tr");
                    for (Element e : playerInfo){
                        if(e.select("th").text().toLowerCase().contains("name")){
                            longName = e.select("td").text();
                        }

                    }
                    System.out.println(count+" "+cardName+" "+longName);
                    dataService.storePlayer(count,cardName,longName,rating);

                }
                else{
                    //Set the while loop to finished - all players stored
                    end = Boolean.TRUE;
                }

            } catch (IOException e) {
                e.printStackTrace();
                endCount ++;
                if (endCount >=25){
                    end = Boolean.TRUE;
                }
            }
            count ++;
        }

    }

}
