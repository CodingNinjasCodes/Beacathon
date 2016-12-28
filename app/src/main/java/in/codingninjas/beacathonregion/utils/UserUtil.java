package in.codingninjas.beacathonregion.utils;

import in.codingninjas.beacathonregion.User;

/**
 * Created by rohanarora on 22/12/16.
 */

public class UserUtil {


    public static boolean isUserLoggedIn() {
        return  getToken() != null && getToken().length() != 0;
    }

    public static void saveUser(User user) {
        if (user != null) {
            if (user.getToken() != null && user.getToken().length() != 0) {
                SharedPreferencesUtil.storeStringValue(SharedPreferencesUtil.USER_ACCESS_TOKEN, user.getToken());
            }

            if (user.getEmail() != null && user.getEmail().length() != 0) {
                SharedPreferencesUtil.storeStringValue(SharedPreferencesUtil.USER_EMAIL, user.getEmail());
            }


            if (user.getName() != null && user.getName().length() != 0) {
                SharedPreferencesUtil.storeStringValue(SharedPreferencesUtil.USER_NAME, user.getName());
            }

            if (user.getId() >= 0) {
                SharedPreferencesUtil.storeLongValue(SharedPreferencesUtil.USER_SERVER_ID, user.getId());
            }

            if(user.getProfilePicURL() != null && user.getProfilePicURL().length() != 0){
                SharedPreferencesUtil.storeStringValue(SharedPreferencesUtil.USER_PROFILE_PIC_URL, user.getProfilePicURL());
            }

        }
    }

    public static String getProfilePicURL(){
        return SharedPreferencesUtil.retrieveStringValue(SharedPreferencesUtil.USER_PROFILE_PIC_URL, "");
    }


    public static String getName() {
        return SharedPreferencesUtil.retrieveStringValue(SharedPreferencesUtil.USER_NAME, "");
    }

    public static String getEmail(){
        return SharedPreferencesUtil.retrieveStringValue(SharedPreferencesUtil.USER_EMAIL,"");
    }


    public static String getToken() {
        return SharedPreferencesUtil.retrieveStringValue(SharedPreferencesUtil.USER_ACCESS_TOKEN, null);
    }

    public static long getId() {
        return SharedPreferencesUtil.retrieveLongValue(SharedPreferencesUtil.USER_SERVER_ID, -1);
    }



    public static void logout(){
        SharedPreferencesUtil.removeKey(SharedPreferencesUtil.USER_ACCESS_TOKEN);
        SharedPreferencesUtil.removeKey(SharedPreferencesUtil.USER_EMAIL);
        SharedPreferencesUtil.removeKey(SharedPreferencesUtil.USER_NAME);
        SharedPreferencesUtil.removeKey(SharedPreferencesUtil.USER_SERVER_ID);
        SharedPreferencesUtil.removeKey(SharedPreferencesUtil.USER_PROFILE_PIC_URL);
    }

}
