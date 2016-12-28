package in.codingninjas.beacathonregion.network;


import java.security.PublicKey;

/**
 * Created by asingla on 10/02/16.
 *
 */
public class URLConstants {

    public static final String BASE_URL = "http://beacathon-test.herokuapp.com/";
    public static final String SIGN_UP = "sign_up.json";
    public static final String ADD_USER_IN_REGION = "add_user_in_region.json";
    public static final String REMOVE_USER_FROM_REGION = "remove_user_from_region.json";
    public static final String UPDATE_USER_IN_REGIONS = "update_user_in_regions.json";
    public static final String GET_USERS_FOR_REGION = "users_for_region.json";
    public static final String LOGOUT = "logout.json";

    public static String getBaseURL(){
        return BASE_URL;
    }


}

