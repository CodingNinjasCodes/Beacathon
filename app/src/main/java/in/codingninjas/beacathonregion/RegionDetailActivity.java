package in.codingninjas.beacathonregion;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import in.codingninjas.beacathonregion.network.ApiClient;
import in.codingninjas.beacathonregion.network.NetworkDataManager;
import in.codingninjas.beacathonregion.network.responses.UsersResponse;
import retrofit2.Call;

public class RegionDetailActivity extends AppCompatActivity {

    List<User> mUsers;
    String beaconSSN;
    UsersAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_region_detail);

        mUsers = new ArrayList<>();


        recyclerView = (RecyclerView)findViewById(R.id.user_recycler);
        adapter = new UsersAdapter(this,mUsers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        Intent i = getIntent();
        if(i.hasExtra("beacon_ssn")){
            beaconSSN = i.getStringExtra("beacon_ssn");
            fetchUsers();
        }
        if(i.hasExtra("name")){
            String name = i.getStringExtra("name");
            getSupportActionBar().setTitle(name);
            TextView textView = (TextView)findViewById(R.id.region_detail_text_view);
            textView.setText("Users Inside " + name);
        }

    }

    private void fetchUsers() {
        Call<UsersResponse> call = ApiClient.authorizedApiService().getUsersForRegion(beaconSSN);
        NetworkDataManager<UsersResponse> manager = new NetworkDataManager<>();
        NetworkDataManager.NetworkResponseListener listener = manager.new NetworkResponseListener() {
            @Override
            public void onSuccessResponse(UsersResponse response) {
                mUsers.clear();
                mUsers.addAll(response.getData().getUsers());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(int code, String message) {
                Snackbar.make(recyclerView,"Failed to fetch Users",Snackbar.LENGTH_LONG).show();
            }
        };
        manager.execute(call,listener);
    }


}
