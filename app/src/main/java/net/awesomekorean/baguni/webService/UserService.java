package net.awesomekorean.baguni.webService;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {
    @GET("users/email/{userEmail}")
    Call<User> getUserByEmail(@Path("userEmail") String email);

    @POST("users")
    Call<User> createUser(@Body User user);

    @PATCH("users/login/{userEmail}/{userPassword}")
    Call<User> logInCheck(@Path("userEmail") String email, @Path("userPassword") String password);
}
