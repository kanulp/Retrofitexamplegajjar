package com.kanu_lp.retroexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kanu_lp.retroexample.NameData.Datum;
import com.kanu_lp.retroexample.NameData.Names;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    Button submit,refresh;
    EditText name,role;
    TextView data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submit = (Button)findViewById(R.id.register);
        refresh = (Button)findViewById(R.id.refresh);

        name = (EditText)findViewById(R.id.name);
        role = (EditText)findViewById(R.id.role);
        data = (TextView)findViewById(R.id.data);


        getdatafromserver();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                registerdata(name.getText().toString(),role.getText().toString());

            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getdatafromserver();
            }
        });


    }

    private void registerdata(String name,String role){

        GetApi service = RetrofitClient.getClient().create(GetApi.class);

        Call<ResponseBody> call = service.register_user(name,role);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONObject obj = new JSONObject(response.body().toString());
                    String result = obj.getString("result");
                    Log.d("registerresponse",response.body()+" \nresult:"+result);
                    Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("register response","failed");


            }
        });

    }

    private void getdatafromserver() {

        GetApi service = RetrofitClient.getClient().create(GetApi.class);

        Call<Names> call = service.getNames();

        call.enqueue(new Callback<Names>() {
                         @Override
                         public void onResponse(Call<Names> call, Response<Names> response) {

                             Log.d("namesdata11",response.body().getResult()+" "+response.body().getMessage());

                             List<Datum> list = response.body().getData();
                             data.setText("");

                             for (int i = 0; i < list.size(); i++) {
                                 Log.d("namesdata", list.get(i).getId() + " ");
                                 Log.d("namesdata1", list.get(i).getName() + " ");
                                 Log.d("namesdata2", list.get(i).getRole()+ " ");
                                 data.append("\nname : "+list.get(i).getName()+" Role : "+list.get(i).getRole());
                             }

                         }
                         @Override
                         public void onFailure(Call<Names> call, Throwable t) {

                         }
                     });
    }
}
