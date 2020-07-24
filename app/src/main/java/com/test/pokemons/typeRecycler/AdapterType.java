package com.test.pokemons.typeRecycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.pokemons.R;
import com.test.pokemons.models.Pokemon;

import java.util.List;

public class AdapterType extends RecyclerView.Adapter<AdapterType.ViewHolder> {
    private List<Pokemon.Types> types;

    public AdapterType(List<Pokemon.Types> types) {
        this.types = types;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = (View) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_type, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        View view = holder.itemView;
        TextView textView = (TextView) view.findViewById(R.id.text_item);
        textView.setText(types.get(position).type.name);
    }

    @Override
    public int getItemCount() {
        return types.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
