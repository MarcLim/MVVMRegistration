package com.example.marcivanlim.mvvmregistration.API;



import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by pc on 7/22/2018.
 */

public interface APIInterface {


    @FormUrlEncoded
    @POST("insert_user.php")
    Observable<String> registerUser(
            @Field("username") String username,
            @Field("password") String password,
            @Field("firstname") String firstname,
            @Field("lastname") String lastname,
            @Field("email") String email,
            @Field("authorized") String authorized);


}
