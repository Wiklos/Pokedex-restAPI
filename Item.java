package math.uni.lodz.pl.pokedexrestapi;

/**
 * Created by Wiklos1 on 2018-02-05.
 */

public class Item {

    String birdListName;
    int birdListImage;

    public Item(String birdName, int birdImage)
    {
        this.birdListImage=birdImage;
        this.birdListName=birdName;
    }
    public String getbirdName()
    {
        return birdListName;
    }
    public int getbirdImage()
    {
        return birdListImage;
    }
}