package in.codingninjas.beacathonregion;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rohanarora on 22/12/16.
 */

public class User {

    private long id;

    private String email;

    private String name;

    private String token;

    @SerializedName("profile_pic_url")
    private String profilePicURL;

    public User(){}

    public String getEmail() {
        return email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getProfilePicURL() {
        return profilePicURL;
    }

    public void setProfilePicURL(String profilePicURL) {
        this.profilePicURL = profilePicURL;
    }
}
