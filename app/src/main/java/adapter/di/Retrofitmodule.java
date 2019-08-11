package adapter.di;


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import networking.RestInterface;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



@Module
public class Retrofitmodule {



    @Provides
    public HttpLoggingInterceptor getIntyerceptor()
    {


        return  new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS);


    }



    @Provides
    public OkHttpClient GiveOkkhttp(HttpLoggingInterceptor httplog) {
        return new OkHttpClient.Builder().connectTimeout(30000, TimeUnit.MILLISECONDS).readTimeout(30000, TimeUnit.MILLISECONDS).addInterceptor(httplog).build();


    }


    @Provides
    public Retrofit GiveRetrofit( OkHttpClient okki) {

        return new retrofit2.Retrofit.Builder()
                .baseUrl("https://github-trending-api.now.sh/").addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).client(okki)
                .build();


    }



    @Singleton
    @Provides
    public RestInterface gitRetrofitService(Retrofit retof)
    { return  retof.create(RestInterface.class);
    }








}
