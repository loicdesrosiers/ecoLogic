package Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import Entites.User;
import info.iut.ecologic.R;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{

    private Context mCtx;
    private List<User> userList;

    public UserAdapter(Context mCtx, List<User> userList) {
        this.mCtx = mCtx;
        this.userList = userList;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_list_user, null);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        User user = userList.get(position);
        holder.TVEmail.setText(user.getEmail()+"");
        holder.TVPseudo.setText(user.getPseudo()+"");
        holder.TVMdp.setText(user.getPassword()+"");
        holder.TVScore.setText(user.getScore()+"");
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{

        TextView TVEmail, TVPseudo, TVMdp, TVScore;

        public UserViewHolder(View itemView) {
            super(itemView);
            TVEmail = itemView.findViewById(R.id.TVEmail);
            TVPseudo = itemView.findViewById(R.id.TVPseudo);
            TVMdp = itemView.findViewById(R.id.TVMdp);
            TVScore = itemView.findViewById(R.id.TVScore);
        }
    }

}
