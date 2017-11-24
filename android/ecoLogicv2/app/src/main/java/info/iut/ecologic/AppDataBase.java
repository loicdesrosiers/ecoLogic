package info.iut.ecologic;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import java.io.Serializable;

import DAOs.QCMUniqueSolutionDAO;
import DAOs.UserDAO;
import Entites.QCMUniqueSolution;
import Entites.User;

@Database(entities = {User.class, QCMUniqueSolution.class}, version = 3)
public abstract class AppDataBase extends RoomDataBase{

    public abstract UserDAO userDAO();

    public abstract QCMUniqueSolutionDAO qcmUniqueSolutionDAO();

}
