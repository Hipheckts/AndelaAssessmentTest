package com.challenge.andela.jdl;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by mokeam on 3/4/17.
 */
//This class handles the Github Api endpoint for retrieving the users.
public interface EndPoint {

    @GET("search/users")
    Call<Developer> getJavaDevelopersInLagos(@Query("q") String query);

}
