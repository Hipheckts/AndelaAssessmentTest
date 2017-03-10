package com.challenge.andela.jdl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.roger.catloadinglibrary.CatLoadingView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mokeam on 3/4/17.
 */
//This class is responsible for handling the api call,retrieves the result and populates the recyclerview developer adapter.


public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "https://api.github.com/";
    private static final String TAG = "MainActivity";

    private RecyclerView recyclerView;
    private DeveloperAdapter mAdapter;
    List<Developer.ItemsEntity> Users;
    private RecyclerView.LayoutManager mLayoutManager;
    CatLoadingView mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mView = new CatLoadingView();
        mView.show(getSupportFragmentManager(),"Loading...");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        OkHttpClient okClient = new OkHttpClient.Builder()
                .addInterceptor(
                        new Interceptor() {
                            @Override
                            public Response intercept(Interceptor.Chain chain) throws IOException {
                                Request request = chain.request().newBuilder()
                                        .addHeader("Accept", "Application/JSON").build();
                                return chain.proceed(request);
                            }
                        }).build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        EndPoint service = retrofit.create(EndPoint.class);

        Call<Developer> call = service.getJavaDevelopersInLagos("language:java location:lagos");
        call.enqueue(new Callback<Developer>() {
            @Override
            public void onResponse(Call<Developer> call, retrofit2.Response<Developer> response) {
                Log.d(TAG, "Status Code = " + response.code());

                if (response.isSuccessful()) {
                    // request successful (status code 200, 201)
                    Users = new ArrayList<>();
                    Developer result = response.body();
                    Log.d(TAG, "response = " + new Gson().toJson(result));
                    Users = result.getItems();
                    Log.d(TAG, "Items = " + Users.size());

                    // This is where data loads
                    mAdapter = new DeveloperAdapter(Users);


                    //attach to recyclerview
                    mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(mAdapter);
                    mView.dismiss();
                }
                else{
                    Log.d(TAG,"An error occured");
                }
            }

            @Override
            public void onFailure(Call<Developer> call, Throwable t) {

            }
        });


    }
}
