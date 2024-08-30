package naointendorip.example.naointendorip;

import java.util.ArrayList;
import java.util.List;

public class GameDonwload {
    String name;
    String img;
    String table;
    String features;
    String review;
    List<String> downloadName;
    List<String> downloadLink;

    public GameDonwload(){

    }


    public GameDonwload(String name, String img, String table, String features, String review, List<String> downloadName, List<String> downloadLink) {
        this.name = name;
        this.img = img;
        this.table = table;
        this.features = features;
        this.review = review;
        this.downloadName = new ArrayList<>();
        this.downloadLink = new ArrayList<>();
    }

    public Object getTable() {
        return table;
    }

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }

    public String getFeatures() {
        return features;
    }

    public String getReview() {
        return review;
    }

    public List getDownloadName() {
        return downloadName;
    }

    public List getDownloadLink() {
        return downloadLink;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public void setDownloadName(List downloadName) {
        this.downloadName = downloadName;
    }

    public void setDownloadLink(List downloadLink) {
        this.downloadLink = downloadLink;
    }
}
