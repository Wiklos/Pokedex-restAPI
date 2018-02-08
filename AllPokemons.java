package math.uni.lodz.pl.pokedexrestapi;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class AllPokemons extends Activity {

    GridView simpleList;
    ArrayList birdList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_pokemons);

        new WebServiceHandler().execute("https://pokeapi.co/api/v2/pokemon/?limit=151");

        simpleList = (GridView) findViewById(R.id.simpleGridView);

    }

    private class WebServiceHandler extends AsyncTask<String, Void, String> {

        // okienko dialogowe, które każe użytkownikowi czekać
        private ProgressDialog dialog = new ProgressDialog(AllPokemons.this);

        // metoda wykonywana jest zaraz przed główną operacją (doInBackground())
        // mamy w niej dostęp do elementów UI
        @Override
        protected void onPreExecute() {
            // wyświetlamy okienko dialogowe każące czekać
            dialog.setMessage("Czekaj...");
            dialog.show();
        }

        // główna operacja, która wykona się w osobnym wątku
        // nie ma w niej dostępu do elementów UI
        @Override
        protected String doInBackground(String... urls) {

            try {
                // zakładamy, że jest tylko jeden URL
                URL url = new URL(urls[0]);
                URLConnection connection = url.openConnection();

                // pobranie danych do InputStream
                InputStream in = new BufferedInputStream(connection.getInputStream());

                // konwersja InputStream na String
                // wynik będzie przekazany do metody onPostExecute()
                return streamToString(in);

            } catch (Exception e) {
                // obsłuż wyjątek
                Log.d(AllPokemons.class.getSimpleName(), e.toString());
                return null;
            }

        }

        // metoda wykonuje się po zakończeniu metody głównej,
        // której wynik będzie przekazany;
        // w tej metodzie mamy dostęp do UI
        @Override
        protected void onPostExecute(String result) {

            // chowamy okno dialogowe
            dialog.dismiss();

            try {
                // reprezentacja obiektu JSON w Javie
                JSONObject json = new JSONObject(result);
                JSONArray forms = json.getJSONArray("results");
                for (int i = 0; i < forms.length(); i++) {
                    JSONObject c = forms.getJSONObject(i);

                    //String url = c.getString("url");
                    String name = c.getString("name");


                    //JSONObject phone = c.getJSONObject("sprites");
                    //String sprite = phone.getString("front_default");


                    //((TextView) findViewById(R.id.response_id)).setText("id: " + sprite);
                    //((TextView) findViewById(R.id.response_name)).setText(name);

                    //loadImageFromUrl("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png");
                    birdList.add(new Item(name,R.drawable.p));//gridview

                    //Toast.makeText(getApplication(),"Toast"+name, Toast.LENGTH_SHORT).show();
                    //sprite
                    //JSONObject nested= json.getJSONObject("sprites");
                    //Log.d("TAG","flag value "+nested.getString("front_default"));
                    //String SpriteUrl = nested.getString("front_default");
                    //((TextView) findViewById(R.id.response_name2)).setText(SpriteUrl);
                    //end sprite

                    //wyswietlanie obrazka
                    //String url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png";
                    //ImageView imageView = (ImageView)findViewById(R.id.imageView2);
                    //loadImageFromUrl(SpriteUrl);

                }
                // pobranie pól obiektu JSON i wyświetlenie ich na ekranie
                //((TextView) findViewById(R.id.response_id)).setText("id: " + json.optString("ulr"));
                //((TextView) findViewById(R.id.response_name)).setText("name: " + json.optString("name"));

            } catch (Exception e) {
                // obsłuż wyjątek
                Log.d(MainActivity.class.getSimpleName(), e.toString());
            }
            MyAdapter myAdapter=new MyAdapter(getApplicationContext(),R.layout.grid_view_items,birdList);
            simpleList.setAdapter(myAdapter);
        }

    }

    // konwersja z InputStream do String
    public static String streamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;

        try {

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }

            reader.close();

        } catch (IOException e) {
            // obsłuż wyjątek
            Log.d(MainActivity.class.getSimpleName(), e.toString());
        }

        return stringBuilder.toString();
    }
    private void loadImageFromUrl(String url) {
        ImageView imageView = (ImageView)findViewById(R.id.imageView2);
        Picasso.with(this).load(url).placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(imageView, new com.squareup.picasso.Callback(){

                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                    }
                });
    }

}
