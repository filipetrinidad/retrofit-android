package retrofit.com;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import retrofit.com.api.ServiceCEP;
import retrofit.com.api.ServicePost;
import retrofit.com.model.CEP;
import retrofit.com.model.JsonPlace;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Button botao1, botao2;
    private TextView texto;
    private Retrofit retrofit;
    private ServicePost service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botao1 = findViewById(R.id.botao1);
        texto = findViewById(R.id.text);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(ServicePost.class);

        botao1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                excluir();
            }
        });

    }

    private void excluir(){

        Call<Void> call = service.excluirDelete(2);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    texto.setText("Status: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });

    }

    private void atualizar(){

        Call<JsonPlace> call = service.atualizarPut(2);
        call.enqueue(new Callback<JsonPlace>() {
            @Override
            public void onResponse(Call<JsonPlace> call, Response<JsonPlace> response) {
                if(response.isSuccessful()){
                    JsonPlace jsonPlace2 = response.body();
                    texto.setText(response.code() + " " + jsonPlace2.getId());
                }
            }

            @Override
            public void onFailure(Call<JsonPlace> call, Throwable t) {

            }
        });
    }

    private void postar(){

        JsonPlace jsonPlace = new JsonPlace("123", "aasasa", "asasasas");

        Call<JsonPlace> call = service.realizarPost( jsonPlace );

        call.enqueue(new Callback<JsonPlace>() {
            @Override
            public void onResponse(Call<JsonPlace> call, Response<JsonPlace> response) {
                if(response.isSuccessful()){
                    JsonPlace jsonPlace1 = response.body();
                    texto.setText("Codigo: " + response.code() + "id: " + jsonPlace1.getId());
                }
            }

            @Override
            public void onFailure(Call<JsonPlace> call, Throwable t) {

            }
        });
    }

   /* private  void recuperaCEP(){

        ServiceCEP service = retrofit.create(ServiceCEP.class);
        Call<CEP> call = service.obtemCEP();

        call.enqueue(new Callback<CEP>() {
            @Override
            public void onResponse(Call<CEP> call, Response<CEP> response) {
                if(response.isSuccessful()){
                    CEP cep = response.body();
                    texto.setText(cep.getBairro());
                }
            }

            @Override
            public void onFailure(Call<CEP> call, Throwable t) {

            }
        });
    }*/
}