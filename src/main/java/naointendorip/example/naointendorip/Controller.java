package naointendorip.example.naointendorip;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController()
public class Controller {
    String url = "https://switchromz.com";

    @GetMapping("/game")
    public List<Game> recentgames() throws IOException {

        List<Game>result = new ArrayList<>();
        Document doc = Jsoup.connect(url).timeout(90000).get();
        Elements table = doc.select("#post-273 > div > div > div.wp-block-kadence-column.kadence-column273_7d54f2-a4 > div > div > div");
        Elements imgs = table.select("img");
        Elements links = table.select("a");



        System.out.println(links);

        for(int i = 0; i < imgs.size();i++){
            Game game = new Game();
            game.id = i;
            if(i % 2 != 0){
                game.img = imgs.get(i).attr("srcset");
                game.name = imgs.get(i).attr("title");
                game.link = links.get(i).attr("href");
                result.add(game);
            }

        }

        return result;
    }


}
