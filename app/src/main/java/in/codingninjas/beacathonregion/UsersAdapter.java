package in.codingninjas.beacathonregion;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import in.codingninjas.beacathonregion.utils.RoundedImageView;

/**
 * Created by rohanarora on 23/12/16.
 *
 */

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    Context mContext;
    List<User> mUsers;

    public UsersAdapter(Context context, List<User> users){
        mContext = context;
        mUsers = users;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.layout_user_item,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = mUsers.get(position);
        String profileURL = user.getProfilePicURL();
        if(profileURL!=null && !profileURL.isEmpty()){
            Picasso.with(mContext).load(profileURL).placeholder(R.drawable.profile_placeholder)
                    .error(R.drawable.profile_placeholder).into(holder.profileImageView);
        }
        holder.nameTextView.setText(user.getName());
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        RoundedImageView profileImageView;
        TextView nameTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            profileImageView = (RoundedImageView)itemView.findViewById(R.id.layout_item_profile_image_view);
            nameTextView = (TextView)itemView.findViewById(R.id.layout_item_name_text_view);
        }
    }
}
