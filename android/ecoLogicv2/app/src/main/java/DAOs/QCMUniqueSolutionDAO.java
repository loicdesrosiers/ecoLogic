package DAOs;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import Entites.QCMUniqueSolution;

@Dao
public interface QCMUniqueSolutionDAO {

    @Query("SELECT * FROM qcmuniquesolution")
    List<QCMUniqueSolution> getAllQuestions();

    @Insert
    void insertAll(QCMUniqueSolution ... qcmUniqueSolutions);
}
