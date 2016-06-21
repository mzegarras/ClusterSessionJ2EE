package net.msonic.testsessionws;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by manuelzegarra on 18/06/16.
 */
public interface ISessionService {



    @POST("test/doTest2")
    Call<User> createUser(@Body String test);

}
