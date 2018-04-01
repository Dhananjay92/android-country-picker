package com.pavanpathro.androidcountrypicker;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import java.util.List;

/**
 * Created by Pavan on 11/03/18.
 */

public class DiffUtilCallback extends DiffUtil.Callback {

    private List<Country> oldCountriesList;
    private List<Country> newCountriesList;

    public DiffUtilCallback(List<Country> newCountriesList, List<Country> oldCountriesList) {
        this.newCountriesList = newCountriesList;
        this.oldCountriesList = oldCountriesList;
    }

    @Override
    public int getOldListSize() {
        return oldCountriesList.size();
    }

    @Override
    public int getNewListSize() {
        return newCountriesList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldCountriesList.get(oldItemPosition).getCode().equalsIgnoreCase(newCountriesList.get(newItemPosition).getCode());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldCountriesList.get(oldItemPosition).equals(newCountriesList.get(newItemPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        //you can return particular field for changed item.
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
