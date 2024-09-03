package naointendorip.example.naointendorip;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.*;
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

    @CrossOrigin("*")
    @GetMapping("/game")
    public List<Game> recentGames() throws IOException {
        Document doc = Jsoup.connect(url).timeout(90000).get();
        List<Game> result = new ArrayList<>();
        Elements table = doc.select("#post-273 > div > div > div.wp-block-kadence-column.kadence-column273_7d54f2-a4 > div > div > div");
        Elements imgs = table.select(".kadence-post-image-inner-intrisic > a > img.attachment-large");
        Elements links = table.select("a.kadence-post-image-inner-wrap");

        System.out.println("Imagens encontradas: " + imgs.size());
        System.out.println("Links encontrados: " + links.size());


        for (int i = 0; i < 18; i++) {
            Game game = new Game();
            game.id = i;
            game.img = imgs.get(i).attr("data-lazy-srcset");
            game.name = imgs.get(i).attr("title");
            game.link = links.get(i).attr("href");
            //System.out.println(imgs);
            result.add(game);
        }

        return result;
    }

    @CrossOrigin("*")
    @GetMapping("/search/{name}")
    @ResponseBody
    public List<Game> search(@PathVariable("name") String name) throws IOException {
        Document doc = Jsoup.connect(url + "?s=" + name).timeout(90000).get();
        List<Game> games = new ArrayList<>();


        Elements links = doc.select("article.entry a.post-thumbnail");
        Elements imgs = doc.select("article.entry a.post-thumbnail div.post-thumbnail-inner img");
        System.out.println(links);
        for (int i = 0; i < links.size(); i++) {
            Game game = new Game();
            game.setId(i);
            game.setLink(links.get(i).attr("href"));
            game.setName(imgs.get(i).attr("alt"));
            game.setImg(imgs.get(i).attr("srcset"));
            games.add(game);
        }

        return games;
    }

    @CrossOrigin("*")
    @GetMapping("/download")
    @ResponseBody
    public GameDonwload download(@RequestParam String link) throws IOException {
        //url = link.replaceAll("%", "/");
        System.out.println(link);
        //System.out.println(url);

        Document doc = Jsoup.connect(link).timeout(90000).get();


        Elements paragraphs = doc.select("p");
        Elements features = new Elements(paragraphs.subList(0, Math.min(3, paragraphs.size())));
        Elements downloads = doc.select("div > div.wp-block-kadence-column > div.kt-inside-inner-col > div.wp-block-kadence-advancedbtn a > span");
        Elements dLinks = doc.select("div > div.wp-block-kadence-column > div.kt-inside-inner-col > div.wp-block-kadence-advancedbtn a");
        GameDonwload gameDonwload = new GameDonwload();
        gameDonwload.downloadName = new ArrayList<>();
        gameDonwload.downloadLink = new ArrayList<>();
        gameDonwload.setName(doc.select("h1.entry-title").text());
        gameDonwload.setImg(doc.select(".post-thumbnail-inner img").attr("srcset"));
        gameDonwload.setTable(doc.select("figure.wp-block-table table").toString());
        gameDonwload.setFeatures(features.text());
        gameDonwload.setReview(paragraphs.get(3).text());

        for (int i = 0; i < downloads.size(); i++) {
            gameDonwload.downloadName.add(downloads.get(i).text());
            gameDonwload.downloadLink.add(dLinks.get(i).attr("href"));
        }


        System.out.println(gameDonwload.getName() + gameDonwload.getReview());


        return gameDonwload;

    }

}
