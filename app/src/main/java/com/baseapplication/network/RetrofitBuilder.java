package com.baseapplication.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;

import com.baseapplication.BuildConfig;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.HttpException;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Keval on 20-Dec-16.
 * Build the client for api service.
 *
 * @author {@link 'https://github.com/kevalpatel2106'}
 */

public class RetrofitBuilder {
    private static final String TAG = "RetrofitBuilder";

    /**
     * Get the instance of the retrofit {@link APIService}.
     *
     * @return {@link APIService}
     */
    public static APIService getApiService() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BuildConfig.DEBUG ?
                HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        //Building retrofit
        final Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(APIService.BASE_URL)
                .client(client)
                .build();

        return retrofit.create(APIService.class);
    }

    /**
     * Make in api call on the separate thread.
     *
     * @param observable {@link Observable}
     * @param listener   {@link ResponseListener}.
     * @return {@link Disposable}
     * @see <a href="https://github.com/ReactiveX/RxJava/issues/4942">How to handle exceptions and errors?</a>
     */
    @Nullable
    public static Disposable makeApiCall(@NonNull Context context,
                                         @NonNull Observable observable,
                                         @NonNull final ResponseListener listener) {
        if (isNetworkAvailable(context)) {
            //noinspection unchecked
            return observable
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(new Consumer<BaseResult>() {
                        @Override
                        public void accept(@NonNull BaseResult response) throws Exception {
                            listener.onSuccess(response);
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(@NonNull Throwable throwable) throws Exception {
                            try {
                                if (throwable instanceof HttpException) { //Error frm the server.
                                    listener.onError("Something went wrong.");
                                } else if (throwable instanceof IOException) {  //Internet not available.
                                    listener.onError("Internet is not available. Please try again.");
                                }
                            } catch (Exception e1) {
                                listener.onError(throwable.getMessage());
                                e1.printStackTrace();
                            }
                        }
                    });
        } else {
            listener.onError("Internet is not available. Please try again.");
            return null;
        }
    }

    /**
     * Checks whether network (WIFI/mobile) is available or not.
     *
     * @param context application context.
     * @return true if network available,false otherwise.
     */
    private static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }
}
