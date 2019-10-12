package net.awesomekorean.baguni.webService;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {

    @POST("users")
    Call<User> createUser(@Body User user);

    @GET("users/email/{userEmail}")
    Call<User> getByEmail(@Path("userEmail") String email);
}
