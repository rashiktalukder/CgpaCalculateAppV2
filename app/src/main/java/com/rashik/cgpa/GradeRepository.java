package com.rashik.cgpa;

import android.app.Application;
import android.os.AsyncTask;

import com.rashik.cgpa.model.Course;
import com.rashik.cgpa.model.Semester;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class GradeRepository {
    private CourseDao courseDao;
    private SemesterDao semesterDao;
    List<Semester> mySemesterList=new ArrayList<>();

    public GradeRepository(Application application)
    {
        GradeDataBase db=GradeDataBase.getDatabase(application);
        courseDao= db.courseDao();
        semesterDao= db.semesterDao();

    }
    public void InsertSemester(Semester semester)
    {
        new InsertTask(semesterDao).execute(semester);

    }

    public List<Semester> GetAllSemesters()
    {
        try {
            mySemesterList= new GetAllSemesterTask(semesterDao).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mySemesterList;

    }









    //Background task; Its work on BackGround
    private static class InsertTask extends AsyncTask<Semester,Void,Void>
    {
        private SemesterDao dao;
        InsertTask(SemesterDao semesterDao)
        {
            dao=semesterDao;
        }

        @Override
        protected Void doInBackground(Semester... semesters) {
            dao.InsertSemester(semesters[0]);

            return null;
        }
    }
    private static class GetAllSemesterTask extends AsyncTask<Void,Void,List<Semester>>
    {
        SemesterDao dao;
        GetAllSemesterTask(SemesterDao semesterDao)
        {
            dao=semesterDao;
        }

        @Override
        protected List<Semester> doInBackground(Void... voids) {

            return dao.GetAllSemesters();
        }
    }

}
