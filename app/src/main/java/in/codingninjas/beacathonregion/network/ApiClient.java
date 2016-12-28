package in.codingninjas.beacathonregion.network;

import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.concurrent.TimeUnit;

import in.codingninjas.beacathonregion.utils.SharedPreferencesUtil;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Rohan on 2/2/2016.
 *
 * Class to build singleton Retrofit client and ApiService
 */
public class ApiClient  {

    private static ApiService authorizedApiService;

    private ApiClient() {
    }



    private static void setUpAuthorizedRetrofitClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new AuthorizedOKHttpInterceptor())
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(90, TimeUnit.SECONDS).build();


        authorizedApiService = new Retrofit.Builder()
                .baseUrl(URLConstants.getBaseURL())
                .addConverterFactory(
                        GsonConverterFactory.create(new GsonBuilder()
                                .serializeNulls()
                                .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                                .create()))
                .client(client)
                .build().create(ApiService.class);
    }

    public static ApiService authorizedApiService() {
        if(authorizedApiService==null){
           setUpAuthorizedRetrofitClient();
        }
        return authorizedApiService;
    }

    /***
     *
     * Request Interceptor to add header in calls before sending final request
     */
    private static class AuthorizedOKHttpInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            String accessToken = SharedPreferencesUtil.retrieveStringValue(SharedPreferencesUtil.USER_ACCESS_TOKEN, null);
            if (accessToken == null) {
                accessToken = "";
            }
            request = request.newBuilder()
                    .addHeader("Authorization", "Token " + accessToken)
                    .build();
            return chain.proceed(request);
        }
    }


}
