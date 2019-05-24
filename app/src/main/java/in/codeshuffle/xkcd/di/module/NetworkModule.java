package in.codeshuffle.xkcd.di.module;

import android.util.Log;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import in.codeshuffle.xkcd.BuildConfig;
import in.codeshuffle.xkcd.MyApp;
import in.codeshuffle.xkcd.data.network.ApiHelper;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    Cache provideHttpCache(MyApp application) {
        int cacheSize = 10 * 1024 * 1024;
        return new Cache(application.getCacheDir(), cacheSize);
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Cache cache) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(chain -> {
                    Request original = chain.request();

                    // Customize the request
                    Request request = original.newBuilder()
                            .header("Content-Type", "application/json")
                            .removeHeader("Pragma")
                            .build();

                    Response response = chain.proceed(request);
                    response.cacheResponse();
                    // Customize or return the response
                    Log.d("API", "HomePresenter: " + response.toString());
                    return response;
                })
                .cache(cache)
                .build();


        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(provideGSonConverterFactory())
                .addCallAdapterFactory(provideRxJavaCallAdapterFactory())
                .build();
    }

    @Provides
    @Singleton
    ApiHelper provideApiClient(
            Retrofit retrofit) {
        return retrofit.create(ApiHelper.class);
    }

    @Provides
    @Singleton
    Converter.Factory provideGSonConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    CallAdapter.Factory provideRxJavaCallAdapterFactory() {
        return RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io());
    }

}
