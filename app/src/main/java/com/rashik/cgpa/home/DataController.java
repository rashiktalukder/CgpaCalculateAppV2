package com.rashik.cgpa.home;

import android.provider.ContactsContract;

import com.rashik.cgpa.model.Semester;

public class DataController {

    static DataController instance;

    public static DataController getInstance()
    {
        if(instance==null)
        {
            instance=new DataController();

        }
        return instance;

    }
    HomeFragmentInterface homeFragmentInterface;
    Semester currentSemester;


//getter setter Func.

    public HomeFragmentInterface getHomeFragmentInterface() {
        return homeFragmentInterface;
    }

    public void setHomeFragmentInterface(HomeFragmentInterface homeFragmentInterface) {
        this.homeFragmentInterface = homeFragmentInterface;
    }

    public Semester getCurrentSemester() {
        return currentSemester;
    }

    public void setCurrentSemester(Semester currentSemester) {
        this.currentSemester = currentSemester;
    }
}
