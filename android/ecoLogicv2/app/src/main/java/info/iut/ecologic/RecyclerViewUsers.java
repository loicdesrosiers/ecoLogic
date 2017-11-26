package info.iut.ecologic;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.List;

import Adapter.UserAdapter;
import Entites.User;

public class RecyclerViewUsers extends AppCompatActivity {

    AppDataBase dataBase;
    Button btnRetour;
    User user;
    RecyclerView recyclerView;
    UserAdapter userAdapter;

    List<User> listUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_users);
        btnRetour = (Button) findViewById(R.id.BTNRetour);
        Intent i = getIntent();
        user = (User) i.getSerializableExtra("user");
        dataBase = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, "database")
                .allowMainThreadQueries()
                .build();
        listUsers = dataBase.userDAO().getAllUsers();

        recyclerView = findViewById(R.id.RecyclerView);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager( new LinearLayoutManager(this));
        userAdapter = new UserAdapter(this, listUsers);
        recyclerView.setAdapter(userAdapter);

        btnRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RecyclerViewUsers.this, MenuPrincipal.class);
                i.putExtra("user", user);
                startActivity(i);
                finish();
            }
        });
    }


}
