package com.rashik.cgpa;

import android.app.Application;
import android.os.AsyncTask;

import com.rashik.cgpa.model.Course;
import com.rashik.cgpa.model.Semester;

public class GradeRepository {
    private CourseDao courseDao;
    private SemesterDao semesterDao;

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

}
