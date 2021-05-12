package retrofit.com;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.service.controls.actions.BooleanAction;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import retrofit.com.api.CepService;
import retrofit.com.model.CEP;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Button botao;
    private TextView texto;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botao = findViewById(R.id.botao1);
        texto = findViewById(R.id.text);

        retrofit = new  Retrofit.Builder()
                .baseUrl("https://viacep.com.br/ws/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recuperaCEP();
            }
        });
    }

    public void recuperaCEP(){
        CepService cepService = retrofit.create( CepService.class);
        Call<CEP> call = cepService.recuperaCEP();

        call.enqueue(new Callback<CEP>() {
            @Override
            public void onResponse(Call<CEP> call, Response<CEP> response) {
                if (response.isSuccessful()){
                    CEP cep = response.body();
                    texto.setText(cep.getCep() + "-"+ cep.getLogradouro() + "-" + cep.getBairro());
                }

            }

            @Override
            public void onFailure(Call<CEP> call, Throwable t) {

            }
        });
    }
}