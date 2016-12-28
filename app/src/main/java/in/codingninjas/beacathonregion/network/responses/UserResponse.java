package in.codingninjas.beacathonregion.network.responses;

import com.google.gson.annotations.SerializedName;

import in.codingninjas.beacathonregion.User;

/**
 * Created by rohanarora on 22/12/16.
 * Retrofit response class for user
 */

public class UserResponse extends ApiResponse {
    @SerializedName("data")
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }


    public class Data{
        @SerializedName("user")
        private User user;
        public User getUser() {
            return user;
        }
        public void setUser(User user) {
            this.user = user;
        }

    }
}
