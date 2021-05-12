package retrofit.com.api;

import retrofit.com.model.CEP;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CepService {

    @GET("01001000/json/")
    Call<CEP> recuperaCEP();
}
