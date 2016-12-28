package in.codingninjas.beacathonregion.network.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import in.codingninjas.beacathonregion.User;

/**
 * Created by rohanarora on 22/12/16.
 * Retrofit response class for users list
 */

public class UsersResponse extends ApiResponse {
    @SerializedName("data")
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }


    public class Data{
        @SerializedName("users")
        private List<User> users;

        public List<User> getUsers() {
            return users;
        }

        public void setUsers(List<User> users) {
            this.users = users;
        }
    }
}
