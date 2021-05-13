package retrofit.com.api;

import retrofit.com.model.JsonPlace;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ServicePost {

     @POST("/posts")
    Call<JsonPlace> realizarPost(@Body JsonPlace jsonPlace);
}
