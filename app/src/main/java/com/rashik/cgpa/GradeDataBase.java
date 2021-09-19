package com.rashik.cgpa;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.rashik.cgpa.model.Course;
import com.rashik.cgpa.model.Semester;
@Database(entities = {Course.class,Semester.class},version = 1,exportSchema = false)
public abstract class GradeDataBase extends RoomDatabase {

    public abstract CourseDao courseDao();
    public abstract SemesterDao semesterDao();
    public static volatile GradeDataBase INSTANCE;

    static GradeDataBase getDatabase(final Context context)
    {
        if(INSTANCE==null)
        {
            INSTANCE= Room.databaseBuilder(context.getApplicationContext(),GradeDataBase.class,
                    "GRADEDATABASE").fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }


}
