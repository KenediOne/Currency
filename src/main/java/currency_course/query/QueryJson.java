package currency_course.query;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QueryJson {
    private static QueryJson mInstance;
    private Retrofit mRetrofit;

    private QueryJson(String BASE_URL) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .addInterceptor(interceptor);

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build();
    }

    public static QueryJson getInstance(String BASE_URL) {
        if (mInstance == null) {
            mInstance = new QueryJson(BASE_URL);
        }
        return mInstance;
    }

    public Queries getJSONApi() {
        return mRetrofit.create(Queries.class);
    }
}
