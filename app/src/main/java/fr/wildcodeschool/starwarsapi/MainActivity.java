package fr.wildcodeschool.starwarsapi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.util.Log.d;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "VolleyDebug";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://swapi.co/api/planets/";

        RecyclerView planetList = findViewById(R.id.planetList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        planetList.setLayoutManager(layoutManager);

        final List<PlanetModel> planets = new ArrayList<>();


        RecyclerTouchListener listener = new RecyclerTouchListener(MainActivity.this, planetList, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                PlanetModel planet = planets.get(position);
                Toast.makeText(MainActivity.this, planet.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        });
        planetList.addOnItemTouchListener(listener);


        final PlanetAdapter adapter = new PlanetAdapter(planets);
        planetList.setAdapter(adapter);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray results = response.getJSONArray("results");

                            for (int i = 0 ; i < results.length(); i++) {
                                JSONObject planet = results.getJSONObject(i);
                                String name = planet.getString("name");
                                String climate = planet.getString("climate");
                                int diameter = Integer.parseInt(planet.getString("diameter"));

                                PlanetModel planetModel = new PlanetModel(name, climate, diameter);

                                planets.add(planetModel);
                            }
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, error.getMessage());
                    }
                });


// Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);
    }
}
