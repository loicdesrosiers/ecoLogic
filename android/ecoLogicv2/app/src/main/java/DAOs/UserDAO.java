package DAOs;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import Entites.User;

@Dao
public interface UserDAO {

    @Query("SELECT * FROM users")
    List<User> getAllUsers();

    @Query("SELECT * FROM users WHERE email LIKE :email AND password LIKE :password")
    User getUser(String email, String password);

    @Query("SELECT * FROM users WHERE email LIKE :email")
    User checkUserEmail(String email);

    @Insert
    void insertAll(User ... users);

    @Update
    void updateUsers(User... users);

    @Delete
    void delete(User user);
}
