package in.codingninjas.beacathonregion.network;

import com.google.gson.JsonSyntaxException;

import in.codingninjas.beacathonregion.network.responses.ApiResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by asingla on 23/02/16.
 * Abstract Retrofit call manager for handling common error responses
 */
public class NetworkDataManager<T extends ApiResponse> {

    public static final int SUCCESS_RESPONSE_CODE = 200;

    public static final int INTERNAL_SERVER_ERROR_CODE = 500;
    public static final String INTERNAL_SERVER_ERROR_MESSAGE = "Something went wrong. Contact Coding Ninjas Team";

    public static final int NETWORK_FAILURE_ERROR_CODE = 511;
    public static final String NETWORK_FAILURE_ERROR_MESSAGE = "Network Failure. Please try after some time";

    public abstract class NetworkResponseListener {
        public abstract void onSuccessResponse(T response);
        public abstract void onFailure(int code, String message);
    }

    public NetworkDataManager() {

    }

    public void execute(Call<T> call, final NetworkResponseListener listener) {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful()) {
                    if (response.body().getError() != null && response.body().getError().length() > 0) {
                        listener.onFailure(SUCCESS_RESPONSE_CODE, response.body().getError());
                    } else {
                        listener.onSuccessResponse(response.body());
                    }
                } else {
                    listener.onFailure(INTERNAL_SERVER_ERROR_CODE, INTERNAL_SERVER_ERROR_MESSAGE);
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                if (t instanceof JsonSyntaxException) {
                    listener.onFailure(INTERNAL_SERVER_ERROR_CODE, INTERNAL_SERVER_ERROR_MESSAGE);
                } else {
                    listener.onFailure(NETWORK_FAILURE_ERROR_CODE, NETWORK_FAILURE_ERROR_MESSAGE);
                }
            }
        });
    }

}
