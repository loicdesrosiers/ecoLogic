package info.iut.ecologic;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import Adapter.UserAdapter;
import Entites.User;

public class RecyclerViewUsers extends AppCompatActivity {

    AppDataBase dataBase;

    RecyclerView recyclerView;
    UserAdapter userAdapter;

    List<User> listUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_users);
        dataBase = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, "database")
                .allowMainThreadQueries()
                .build();
        listUsers = dataBase.userDAO().getAllUsers();

        recyclerView = findViewById(R.id.RecyclerView);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager( new LinearLayoutManager(this));
        userAdapter = new UserAdapter(this, listUsers);
        recyclerView.setAdapter(userAdapter);
    }
}
