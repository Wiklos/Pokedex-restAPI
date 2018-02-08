package math.uni.lodz.pl.pokedexrestapi;


/**
 * Created by admin on 18-01-2017.
 */
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter<Item>  {
    int zmienna_globalna = 0;
    ArrayList<Item> birdList = new ArrayList<>();
    Context context;

    public MyAdapter(Context context, int textViewResourceId, ArrayList<Item> objects) {
        super(context, textViewResourceId, objects);

        birdList = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        zmienna_globalna++;
        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.grid_view_items, null);
        TextView textView = (TextView) v.findViewById(R.id.textView);
        ImageView imageView = (ImageView) v.findViewById(R.id.imageView);
        textView.setText(birdList.get(position).getbirdName());
        imageView.setImageResource(birdList.get(position).getbirdImage());

        Typeface tf = Typeface.createFromAsset(textView.getContext().getAssets(), "fonts/pokemonFont.ttf");
        textView.setTypeface(tf);

        //proby
        context = imageView.getContext();
        //String SpriteUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+zmienna_globalna+".png";
        //String SpriteUrl = "https://img.pokemondb.net/artwork/"+textView.getText()+".jpg"; //biale tlo
        String SpriteUrl = "https://img.pokemondb.net/sprites/black-white/normal/"+textView.getText()+".png"; //przezroczyste tlo

        Picasso.with(context).load(SpriteUrl).placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(imageView, new com.squareup.picasso.Callback() {

                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                    }
                });

        return v;

    }

}