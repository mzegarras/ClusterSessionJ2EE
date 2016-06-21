package net.msonic.testsessionws;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);


        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.cookieJar(new JavaNetCookieJar(new MyCookieManager()));

        builder.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request.Builder requestBuilder = chain.request().newBuilder();
                requestBuilder.header("Content-Type", "application/json");
               // requestBuilder.header("Cache-Control", "no-cache=\"set-cookie, set-cookie2\"");
                return chain.proceed(requestBuilder.build());
            }
        });

        builder.addInterceptor(new DCB_AddCookiesInterceptor());
        builder.addInterceptor(logging);  // <-- this is the important line!

        //builder.interceptors().add(new DCB_AddCookiesInterceptor());
        //builder.interceptors().add(new DCB_ReceivedCookiesInterceptor());


        final OkHttpClient httpClient = builder.build();




        FloatingActionButton fab0 = (FloatingActionButton) findViewById(R.id.fab0);
        fab0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.0.14:9080/gc/")
                        .client(httpClient)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ISessionService apiService =  retrofit.create(ISessionService.class);

                Call<User> call = apiService.createUser("");

                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Log.d(MainActivity.class.getCanonicalName(),"onResponse");
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.d(MainActivity.class.getCanonicalName(),"onFailure");
                    }
                });

            }
        });


        FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.0.14:9080/gc1/")
                        .client(httpClient)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ISessionService apiService =  retrofit.create(ISessionService.class);

                Call<User> call = apiService.createUser("");

                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Log.d(MainActivity.class.getCanonicalName(),"onResponse");
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.d(MainActivity.class.getCanonicalName(),"onFailure");
                    }
                });

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
