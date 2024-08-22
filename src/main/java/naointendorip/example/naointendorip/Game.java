package naointendorip.example.naointendorip;

public class Game {
    int id;
    String name;
    String img;
    String link;

    public Game(int id,String name,String img,String link){

    }
    public Game(){

    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return img;
    }

}
