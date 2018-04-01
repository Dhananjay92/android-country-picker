package com.pavanpathro.androidcountrypicker;

import android.content.Context;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Pavan on 11/03/18.
 */

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {

    private Context context;
    private List<Country> listOfCountries;
    private OnItemClickListener onItemClickListener;

    CountryAdapter(Context context, List<Country> listOfCountries) {
        this.context = context;
        this.listOfCountries = listOfCountries;
    }

    void refreshItems(List<Country> newListOfCountries) {
        final DiffUtilCallback diffCallback = new DiffUtilCallback(newListOfCountries, this.listOfCountries);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.listOfCountries.clear();
        this.listOfCountries.addAll(newListOfCountries);
        diffResult.dispatchUpdatesTo(this);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_item_country_code, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Country country = listOfCountries.get(position);
        if (country != null) {
            holder.imageViewIcon.setImageResource(country.getFlag());
            holder.textViewCountryName.setText(country.getName());
        }
    }

    @Override
    public int getItemCount() {
        return listOfCountries.size();
    }

    void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewIcon;
        TextView textViewCountryName;

        ViewHolder(final View itemView) {
            super(itemView);
            imageViewIcon = itemView.findViewById(R.id.imageViewIcon);
            textViewCountryName = itemView.findViewById(R.id.textViewCountryName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(listOfCountries.get(getAdapterPosition()));
                }
            });
        }
    }


}
