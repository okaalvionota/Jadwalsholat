package oka.jadwalsholat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os. AsyncTask;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

public class MainActivity extends AppCompatActivity{
    private static String urlString;
    ImageView loader;
    TextView tvImsak, tvShubuh, tvTerbit, tvDzuhur, tvAshar, tvMaghrib, tvIsya,
        tvImsakTime, tvShubuhTime, tvTerbitTime, tvDzuhurTime, tvAsharTime, tvMaghribTime, tvIsyaTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Initialize();
        final TextView tv = (TextView) findViewById(R.id.date);
        tv.setText("Please Wait..");
        urlString = "http://weirdo.tk/api.php";
        new ProcessJSON().execute(urlString);
    }
    private void Initialize(){
        loader = (ImageView) findViewById(R.id.imageView);

        tvImsak = (TextView) findViewById(R.id.txImsak);
        tvImsakTime = (TextView) findViewById(R.id.txImsakTime);
        tvShubuh = (TextView) findViewById(R.id.txSubuh);
        tvShubuhTime = (TextView) findViewById(R.id.txSubuhTime);
        tvTerbit = (TextView) findViewById(R.id.txTerbit);
        tvTerbitTime = (TextView) findViewById(R.id.txTerbitTime);
        tvDzuhur = (TextView) findViewById(R.id.txDzuhur);
        tvDzuhurTime = (TextView) findViewById(R.id.txDzuhurTime);
        tvAshar = (TextView) findViewById(R.id.txAshar);
        tvAsharTime = (TextView) findViewById(R.id.txAsharTime);
        tvMaghrib = (TextView) findViewById(R.id.txMaghrib);
        tvMaghribTime = (TextView) findViewById(R.id.txMaghribTime);
        tvIsya = (TextView) findViewById(R.id.txIsya);
        tvIsyaTime = (TextView) findViewById(R.id.txIsyaTime);

        tvImsak.setText("");
        tvImsakTime.setText("");
        tvShubuh.setText("");
        tvShubuhTime.setText("");
        tvTerbit.setText("");
        tvTerbitTime.setText("");
        tvDzuhur.setText("");
        tvDzuhurTime.setText("");
        tvAshar.setText("");
        tvAsharTime.setText("");
        tvMaghrib.setText("");
        tvMaghribTime.setText("");
        tvIsya.setText("");
        tvIsyaTime.setText("");
    }
    private class ProcessJSON extends AsyncTask<String, Void, String>{
        protected String doInBackground(String... strings){
            String stream = null;
            String urlString = strings[0];

            HTTPDataHandler hh = new HTTPDataHandler();
            stream = hh.GetHTTPData(urlString);

            return stream;
        }

        protected void onPostExecute(String stream){
            TextView tvTitle = (TextView) findViewById(R.id.Title);
            TextView tv = (TextView) findViewById(R.id.date);

            if(stream !=null){
                try{
                    // Get the full HTTP Data as JSONObject
                    JSONObject reader= new JSONObject(stream);

                    JSONObject coord = reader.getJSONObject("api");
                    String lon = coord.getString("tanggal");

                    loader.setVisibility(View.INVISIBLE);

                    tv.setText(lon);

                    tvImsak.setText("Imsak");
                    tvShubuh.setText("Shubuh");
                    tvTerbit.setText("Terbit");
                    tvDzuhur.setText("Dzuhur");
                    tvAshar.setText("Ashar");
                    tvMaghrib.setText("Maghrib");
                    tvIsya.setText("Isya");

                    tvImsakTime.setText(coord.getString("imsak"));
                    tvShubuhTime.setText(coord.getString("subuh"));
                    tvTerbitTime.setText(coord.getString("terbit"));
                    tvDzuhurTime.setText(coord.getString("dzuhur"));
                    tvAsharTime.setText(coord.getString("ashar"));
                    tvMaghribTime.setText(coord.getString("maghrib"));
                    tvIsyaTime.setText(coord.getString("isya"));

                }catch(JSONException e){
                    e.printStackTrace();
                }

            } // if statement end
        } // onPostExecute() end
    } // ProcessJSON class end
}

