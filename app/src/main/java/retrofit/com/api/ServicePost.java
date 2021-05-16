package retrofit.com.api;

import retrofit.com.model.JsonPlace;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ServicePost {

     @POST("/posts")
    Call<JsonPlace> realizarPost(@Body JsonPlace jsonPlace);

     @PUT("/posts/{id}")
    Call<JsonPlace> atualizarPut(@Path("id") int id);

     @DELETE("/posts/{id}")
    Call<Void> excluirDelete(@Path("Id") int id );
}
