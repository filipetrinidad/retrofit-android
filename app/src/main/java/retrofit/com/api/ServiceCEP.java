package retrofit.com.api;

import retrofit.com.model.CEP;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ServiceCEP {

    @GET("01001000/json/")
    Call<CEP> obtemCEP();
}
