package com.sneha.latticeinovations;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class table_contents extends AppCompatActivity {

    ApiInterface apiInterface;
    private List<user> users;
    private user_adapter user_adapter;
    RecyclerView recycler;
    EditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_contents);

        recycler = findViewById(R.id.reccycler);
        search = findViewById(R.id.search);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<user>> call = apiInterface.getData();
        call.enqueue(new Callback<List<user>>() {

            @Override
            public void onResponse(Call<List<user>> call, Response<List<user>> response) {

                users = response.body();
                user_adapter = new user_adapter(users, table_contents.this);
                recycler.setAdapter(user_adapter);
            }

            @Override
            public void onFailure(Call<List<user>> call, Throwable t) {

            }
        });

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                filter(s.toString().toLowerCase());
            }
        });

    }

    void filter(String text){
        List<user> temp = new ArrayList();
        for(user d: users){
            if(d.getName().toLowerCase().contains(text)){
                temp.add(d);
            }
        }
        user_adapter.updateList(temp);
    }


}