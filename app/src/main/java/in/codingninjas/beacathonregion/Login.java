package in.codingninjas.beacathonregion;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzi;

import in.codingninjas.beacathonregion.network.ApiClient;
import in.codingninjas.beacathonregion.network.NetworkDataManager;
import in.codingninjas.beacathonregion.network.responses.UserResponse;
import in.codingninjas.beacathonregion.utils.UserUtil;

public class Login extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private static final int RC_SIGN_IN = 100;
    GoogleApiClient mGoogleApiClient;

    ProgressBar progressBar;
    SignInButton signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (UserUtil.isUserLoggedIn()) {
            proceed();
        } else {
            setContentView(R.layout.activity_login);

            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .build();

            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                    .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                    .build();

            signInButton = (SignInButton) findViewById(R.id.sign_in_button);
            signInButton.setSize(SignInButton.SIZE_STANDARD);
            signInButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    signIn();
                }
            });

            progressBar = (ProgressBar)findViewById(R.id.progress_bar);
        }


    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void signIn() {
        if(isNetworkConnected()) {
            signInButton.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
            startActivityForResult(signInIntent, RC_SIGN_IN);
        }
        else {
            Toast.makeText(this,"Please connect to internet.",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                GoogleSignInAccount acct = result.getSignInAccount();
                // Get account information
                String name = acct.getGivenName();
                String email = acct.getEmail();
                String profilePicUrl = "";
                Uri photoURI = acct.getPhotoUrl();
                if(photoURI!=null){
                    profilePicUrl = photoURI.toString();
                }

                retrofit2.Call<UserResponse> call = ApiClient.authorizedApiService().signUp(email,name,profilePicUrl);
                NetworkDataManager<UserResponse> manager = new NetworkDataManager<>();
                NetworkDataManager.NetworkResponseListener listener = manager.new NetworkResponseListener() {
                    @Override
                    public void onSuccessResponse(UserResponse response) {
                        Toast.makeText(Login.this,"Sign Up success",Toast.LENGTH_LONG).show();
                        User user = response.getData().getUser();
                        UserUtil.saveUser(user);
                        proceed();
                    }

                    @Override
                    public void onFailure(int code, String message) {
                        progressBar.setVisibility(View.GONE);
                        signInButton.setVisibility(View.VISIBLE);
                        Toast.makeText(Login.this,"Sign Up Fail " + message,Toast.LENGTH_LONG).show();
                    }
                };
                manager.execute(call,listener);
                MyApp.getInstance().setUpBeacon();
            }
            else {
                progressBar.setVisibility(View.GONE);
                signInButton.setVisibility(View.VISIBLE);
                Toast.makeText(Login.this,"Google Sign Up Fail. Please try again later",Toast.LENGTH_LONG).show();
            }
        }
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

    private void proceed(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        this.finish();
    }

}
