package math.uni.lodz.pl.pokedexrestapi;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
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
import java.util.Arrays;

public class Types extends Activity {

    private ArrayList<String> arrayList;
    private ArrayAdapter<String> adapter;
    public EditText txtInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_types);

        ListView listView = (ListView)findViewById(R.id.listview);
        String[] items = {"Pokemon Types:"};
        arrayList = new ArrayList<>(Arrays.asList(items));
        adapter = new ArrayAdapter<String>(this,R.layout.list_view_item,R.id.textViewListItem,arrayList);
        listView.setAdapter(adapter);


        //String newItem = "mojItem";
        //arrayList.add(newItem);
        //adapter.notifyDataSetChanged();

        //dodawanie
        //String newItem = txtInput.getText().toString();
        //arrayList.add(newItem);
        //adapter.notifyDataSetChanged();


        new WebServiceHandler().execute("https://pokeapi.co/api/v2/type/");
    }

    private class WebServiceHandler extends AsyncTask<String, Void, String> {

        // okienko dialogowe, które każe użytkownikowi czekać
        private ProgressDialog dialog = new ProgressDialog(Types.this);

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
                Log.d(OnePokemon.class.getSimpleName(), e.toString());
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
                    arrayList.add(name);
                    adapter.notifyDataSetChanged();

                    //JSONObject phone = c.getJSONObject("sprites");
                    //String sprite = phone.getString("front_default");


                    //((TextView) findViewById(R.id.response_id)).setText("id: " + sprite);
                    //((TextView) findViewById(R.id.response_name)).setText(name);

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
                Log.d(OnePokemon.class.getSimpleName(), e.toString());
            }
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
            Log.d(OnePokemon.class.getSimpleName(), e.toString());
        }

        return stringBuilder.toString();
    }
}
