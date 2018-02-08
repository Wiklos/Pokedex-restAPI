package math.uni.lodz.pl.pokedexrestapi;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import java.net.URL;
import java.net.URLConnection;



public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    }



    public void pokedex(View view) {
        //Toast.makeText(getApplication(),"Toast", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), AllPokemons.class));
    }
    public void onePokemon(View view) {
        //Toast.makeText(getApplication(),"Toast", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), OnePokemon.class));
    }
    public void types(View view) {
        //Toast.makeText(getApplication(),"Toast", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), Types.class));
    }
    public void exit(View view) {
        finish();
        System.exit(0);
    }
}

