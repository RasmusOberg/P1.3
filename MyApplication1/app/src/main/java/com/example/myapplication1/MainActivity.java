package com.example.myapplication1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.simple.parser.*;
import org.json.simple.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView tvTemp, tvWeather, tvWind, tvPcat;
    private static final String TAG = "MainActivity";
    private LocationManager locationManager;
    private LocationListener locationListener;
    private String id;
//    String apiurl = "http://opendata-download-metobs.smhi.se/api/version/1.0/parameter/1/station/";
    String apiurl = "http://opendata-download-metobs.smhi.se/api/version/1.0/parameter/1/station/52350/period/latest-hour/data.json";
    String pcat = "http://opendata-download-metfcst.smhi.se/api/category/pmp3g/version/2/geotype/point/lon/13.0038/lat/55.6050/data.json";
    int nederbörd, väder;
    double mm, wind;
    double temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComps();
        
        DownloadTask task = new DownloadTask();
        task.execute(apiurl); //länk till API

        FetchWeatherInformation weatherInformation = new FetchWeatherInformation();
        weatherInformation.execute(pcat);

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
//                Log.i("Location",location.toString());
                double lat = location.getLatitude();
                double lon = location.getLongitude();
                id = nearestStation(lat,lon);
//                apiurl += id;                               //remove
//                apiurl += "/period/latest-hour/data.json";  //remove
//                Log.d(TAG, "onCreate: "+apiurl);      //remove
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            }
        }
    }


    public String nearestStation(double myLatitude, double myLongitude) {
        String nearest = "";
        double minDistance = Double.MAX_VALUE;

        for(int i =0; i<Stations.ID.length;i++) {
            String id = Stations.ID[i];
            double longitude = Double.parseDouble(Stations.LONGITUDE[i]);
            double latitude = Double.parseDouble(Stations.LATITUDE[i]);
            double a = myLatitude-latitude;
            double b = myLongitude-longitude;
            double distance = Math.sqrt((Math.pow(a, 2)) + Math.pow(b, 2));
            if(distance<minDistance) {
                minDistance = distance;
                nearest = id;
            }
        }
        return nearest;
    }
    
    class FetchWeatherInformation extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;
            
            try{
                Log.d(TAG, "doInBackground: Waddap");
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection)url.openConnection();
                InputStream is = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(is);
                int data = reader.read();
                int charcounter = 0;
                
                while(data != -1){
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                    charcounter += 1;
                }
                Log.d(TAG, "doInBackground: KLAR " + charcounter);
                return result;
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            
            try {
                JSONArray array1 = new JSONArray(new JSONObject(s).getString("timeSeries"));
                Log.d(TAG, "onPostExecute: " + array1.toString());
                Log.d(TAG, "onPostExecute: antal items = " + array1.length());
                JSONObject obj = array1.getJSONObject(0);
                Log.d(TAG, "onPostExecute: " + obj.toString());

                String str = obj.getString("parameters");
                Log.d(TAG, "onPostExecute: " + str);

                JSONArray array3 = new JSONArray(str);

                JSONObject jsonObject;
                for(int i = 0; i < array3.length(); i++){
                    jsonObject = (JSONObject) array3.get(i);
                    Log.d(TAG, "onPostExecute: " + i + jsonObject.toString());
                }

                JSONObject precipitation = (JSONObject) array3.get(15);
                JSONObject precipitationMedian = (JSONObject) array3.get(5);
                JSONObject gust = (JSONObject) array3.get(11);
                JSONObject weatherSymbol = (JSONObject) array3.get(18);
;
                nederbörd = precipitation.getJSONArray("values").getInt(0);
                väder = weatherSymbol.getJSONArray("values").getInt(0);
                mm = precipitationMedian.getJSONArray("values").getDouble(0);
                wind = gust.getJSONArray("values").getDouble(0);

                Log.d(TAG, "onPostExecute: " +
                        "Nederbördstyp: " + nederbörd + ", " +
                        "vädertyp: " + väder + ", " +
                        "nederbördsmängd: " + mm + "mm/h, " +
                        "vindhastighet: " + wind + "m/s");

                setValues(nederbörd, väder, mm, wind);


            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    class DownloadTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream is = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(is);
                int data = reader.read();

                while(data != -1){
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }
                return result;
            }catch(Exception e){
                Log.d(TAG, "doInBackground: FUCK");
                e.printStackTrace();
                return null;
            }
        }

        protected void onPostExecute(String s){
            Log.d(TAG, "onPostExecute: testing" );
            super.onPostExecute(s);

            try{
                JSONObject jsonObject = new JSONObject(s);
                String weatherInfo = jsonObject.getString("value"); //argument ska vara json-objektets key:value
                JSONArray array = new JSONArray(weatherInfo);
                for(int i = 0; i < array.length(); i++){
                    JSONObject jsonpart = array.getJSONObject(i);
                    String one = jsonpart.getString("value"); //argument ska vara json-objekts key:value
                    temp = Double.parseDouble(one);
//                    String two = jsonpart.getString(""); //argument ska vara json-objekts key:value
                    Log.d("TAG", "onPostExecute:  " + one);
                    Log.d("TAG", "onPostExecute: ");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void setValues(int nederbörd, int väder, double mm, double wind) {
        String nederbördstyp = "";
        //0=ingen, 1=snö, 2=snöblandat, 3=regn, 4=duggregn, 5=freezing rain, 6=freezing duggregn
        switch (nederbörd) {
            case 0:
                nederbördstyp += "Ingen nederbörd";
                break;
            case 1:
                nederbördstyp += "Snö";
                break;
            case 2:
                nederbördstyp += "Snöblandat regn";
                break;
            case 3:
                nederbördstyp += "Regn";
                break;
            case 4:
                nederbördstyp += "Duggregn";
                break;
            case 5:
                nederbördstyp += "Underkylt regn";
                break;
            case 6:
                nederbördstyp += "Underkylt duggregn";
                break;
        }
        tvPcat.setText("Nederbördstyp: " + nederbördstyp);
        tvWind.setText(wind + "m/s");

        String vädertyp = "";
        switch (väder){
            case 1:
                 vädertyp += "Klart"; break;
            case 2:
                vädertyp += "Halvklart"; break;
            case 3:
                vädertyp += "Lite molnigt"; break;
            case 4:
                vädertyp += "Halvmolnigt"; break;
            case 5:
                vädertyp += "Molnigt"; break;
            case 6:
                vädertyp += "Mulet"; break;
            case 7:
                vädertyp += "Dimmigt"; break;
            case 8:
                vädertyp += "Lätta regnskurar"; break;
            case 9:
                vädertyp += "Milda regnskurar"; break;
            case 10:
                vädertyp += "Kraftiga regnskurar"; break;
            case 11:
                vädertyp += "Åska"; break;
        }
        tvWeather.setText("Väderlek: " + vädertyp);
        tvTemp.setText(temp + " grader");


    }

    public void initComps(){
        tvTemp = findViewById(R.id.tvTemp);
        tvWeather = findViewById(R.id.tvWeather);
        tvWind = findViewById(R.id.tvWind);
        tvPcat = findViewById(R.id.tvPcat);
    }
}
