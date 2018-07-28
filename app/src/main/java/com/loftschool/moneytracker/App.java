package com.loftschool.moneytracker;

import android.app.Application;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loftschool.moneytracker.Api.Api;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private Api api;

    private static final String PREFS_NAME = "shared_prefs";
    private static final String KEY_TOKEN = "auth_token";

    @Override
    public void onCreate() {
        super.onCreate();

        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
        interceptor.setLevel(BuildConfig.DEBUG
                ? HttpLoggingInterceptor.Level.BODY
                : HttpLoggingInterceptor.Level.NONE);

        OkHttpClient client=new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(new AuthInterceptor())
                .build();

        Gson gson=new GsonBuilder()
                .setDateFormat("dd.MM.yyy HH:mm:ss")
                .create();

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        api=retrofit.create(Api.class);
    }

    public Api getApi(){
        return api;
    }

    public void saveAuthToken(String token){
        getSharedPreferences(PREFS_NAME,MODE_PRIVATE)
                .edit()
                .putString(KEY_TOKEN,token)
                .apply();
    }

    public String getAuthToken(){
        return getSharedPreferences(PREFS_NAME,MODE_PRIVATE).getString(KEY_TOKEN,null);
    }

    public boolean isAuthorized(){
        return !TextUtils.isEmpty(getAuthToken());
    }

    private class AuthInterceptor implements Interceptor{

        @Override
        public Response intercept(Chain chain) throws IOException {

            Request request = chain.request();
            HttpUrl.Builder urlBuilder = request.url().newBuilder();
            urlBuilder.addEncodedQueryParameter("auth-token", getAuthToken());
            return chain.proceed(request.newBuilder().url(urlBuilder.build()).build());
        }
    }
}
