package naointendorip.example.naointendorip;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController()
public class Controller {
    String url = "https://switchromz.com";


    public Elements getDocument(String selector, String url) throws IOException {
        Document doc = Jsoup.connect(url).timeout(90000).get();
        return doc.select(selector);
    }


    @GetMapping("/game")
    public List<Game> recentGames() throws IOException {
        Document doc = Jsoup.connect(url).timeout(90000).get();


        List<Game>result = new ArrayList<>();

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

    @GetMapping("/search/{name}")
    @ResponseBody
    public List<Game> search(@PathVariable("name") String name) throws IOException {
        Document doc = Jsoup.connect(url+"?s="+name).timeout(90000).get();
        List<Game> games = new ArrayList<>();


        Elements links = doc.select("article.entry a.post-thumbnail");
        Elements imgs = doc.select("article.entry a.post-thumbnail div.post-thumbnail-inner img");
        System.out.println(links);
        for(int i = 0; i < links.size(); i++ ){
            Game game = new Game();
            game.setId(i);
            game.setLink(links.get(i).attr("href"));
            game.setName(imgs.get(i).attr("alt"));
            game.setImg(imgs.get(i).attr("srcset"));
            games.add(game);
        }

        return games;
    }


}
