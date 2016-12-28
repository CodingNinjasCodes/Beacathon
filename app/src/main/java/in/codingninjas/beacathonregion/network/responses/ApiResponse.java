package in.codingninjas.beacathonregion.network.responses;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Rohan on 2/2/2016.
 *
 * Basic Response model
 */
public class ApiResponse {

    @SerializedName("update")
    private boolean update;

    @SerializedName("status")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("error")
    private String error;

    public String getMessage() {
        return message;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public boolean isUpdate() {
        return update;
    }

    public int getCode() {
        return code;
    }

    public String getError() {
        return error;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
