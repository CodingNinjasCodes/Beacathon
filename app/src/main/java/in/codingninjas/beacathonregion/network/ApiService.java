package in.codingninjas.beacathonregion.network;

import java.util.ArrayList;

import in.codingninjas.beacathonregion.network.responses.ApiResponse;
import in.codingninjas.beacathonregion.network.responses.UserResponse;
import in.codingninjas.beacathonregion.network.responses.UsersResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Rohan on 2/2/2016.
 *
 *
 */

public interface ApiService {

    @POST(URLConstants.SIGN_UP)
    Call<UserResponse> signUp(@Query("email") String email, @Query("name") String name,@Query("profile_pic_url") String profilePicURL);

    @POST(URLConstants.ADD_USER_IN_REGION)
    Call<ApiResponse> addUserInRegion(@Query("beacon_ssn")  String beaconSSN);

    @POST(URLConstants.REMOVE_USER_FROM_REGION)
    Call<ApiResponse> removeUserFromRegion(@Query("beacon_ssn")  String beaconSSN);

    @POST(URLConstants.UPDATE_USER_IN_REGIONS)
    Call<ApiResponse> updateUserInRegions(@Query("list_beacon_ssn") String list_beaconSSN);

    @GET(URLConstants.GET_USERS_FOR_REGION)
    Call<UsersResponse> getUsersForRegion(@Query("beacon_ssn") String beaconSSN);

    @GET(URLConstants.LOGOUT)
    Call<ApiResponse> logout();

}