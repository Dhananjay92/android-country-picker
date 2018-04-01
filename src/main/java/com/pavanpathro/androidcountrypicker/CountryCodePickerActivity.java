package com.pavanpathro.androidcountrypicker;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.Timer;
import java.util.TimerTask;

public class CountryCodePickerActivity extends AppCompatActivity implements TextWatcher, OnItemClickListener {

    private String searchedText;
    private Timer timer;
    private CountryAdapter countryAdapter;
    private Country country;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picker);

        EditText editTextSearch = findViewById(R.id.editTextSearch);
        editTextSearch.addTextChangedListener(this);

        countryAdapter = new CountryAdapter(this, CountryCodePickerUtil.getListOfCountries());
        countryAdapter.setOnItemClickListener(this);

        recyclerView = findViewById(R.id.recyclerViewCountries);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(countryAdapter);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (charSequence.length() > 0) {
            searchedText = charSequence.toString();
        } else {
            resetList();
        }
        if (timer != null) {
            timer.cancel();
        }
    }

    private void filterList() {
        countryAdapter.refreshItems(CountryCodePickerUtil.getListOfFilteredCountries(searchedText.toLowerCase()));
    }

    private void resetList() {
        countryAdapter.refreshItems(CountryCodePickerUtil.getListOfCountries());
        recyclerView.scrollToPosition(0);
    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (searchedText.length() == 0) {
            resetList();
        }

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Handler mainHandler = new Handler(Looper.getMainLooper());
                Runnable myRunnable = new Runnable() {
                    @Override
                    public void run() {
                        filterList();
                    }
                };
                mainHandler.post(myRunnable);
            }
        }, 100);
    }

    @Override
    public void onItemClick(Country country) {
        this.country = country;

        Intent intent = new Intent();
        intent.putExtra(CountryCodePickerUtil.KEY_SELECTED_COUNTRY, country);
        setResult(CountryCodePickerUtil.REQUEST_CODE_COUNTRY_CODE_PICKER, intent);

        finish();
    }

}
