package networking;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestConfig
{





    private static Retrofit retrofit;
    private static final String BASE_URL = "https://github-trending-api.now.sh/";
    public static OkHttpClient.Builder okkk = new OkHttpClient.Builder().connectTimeout(30000, TimeUnit.MILLISECONDS).readTimeout(30000,TimeUnit.MILLISECONDS);

    public static Retrofit getRetrofitInstance()
    {
        okkk.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));

        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL).addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create()).client(okkk.build())
                    .build();
        }


        return retrofit;
    }


}
