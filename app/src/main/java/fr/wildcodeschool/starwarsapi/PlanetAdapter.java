package fr.wildcodeschool.starwarsapi;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class PlanetAdapter extends RecyclerView.Adapter<PlanetAdapter.MyViewHolder> {

    private List<PlanetModel> planets;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, climate, diameter;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.planetName);
            climate = view.findViewById(R.id.planetClimate);
            diameter = view.findViewById(R.id.planetDiameter);
        }
    }


    public PlanetAdapter(List<PlanetModel> moviesList) {
        this.planets = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_planet, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PlanetModel planet = planets.get(position);
        holder.name.setText(planet.getName());
        holder.climate.setText(planet.getClimate());
        holder.diameter.setText(String.valueOf(planet.getDiameter()));
    }

    @Override
    public int getItemCount() {
        return planets.size();
    }
}
