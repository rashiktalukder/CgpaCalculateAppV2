package com.rashik.cgpa.calculation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rashik.cgpa.GradeRepository;
import com.rashik.cgpa.R;
import com.rashik.cgpa.databinding.FragmentSecondBinding;
import com.rashik.cgpa.home.DataController;
import com.rashik.cgpa.model.Course;

import java.util.ArrayList;
import java.util.List;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    DataController controller;

    EditText creditText,gpaText;
    Button addCourseButton;
    TextView cgpaTextView;
    double totalCredit=0;
    double productOfGpaAndCredit=0;
    RecyclerView recyclerView;
    CourseRecyclerAdapter adapter;
    List<Course>myCourses=new ArrayList<>();
    FloatingActionButton fab;
    GradeRepository repository;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        controller=DataController.getInstance();

        repository=new GradeRepository(getActivity().getApplication());
        myCourses=repository.GetCourseById(controller.getCurrentSemester().getId());

        creditText=binding.getRoot().findViewById(R.id.editTextCredit);
        gpaText=binding.getRoot().findViewById(R.id.editTextGpa);
        addCourseButton=binding.getRoot().findViewById(R.id.addButton);
        cgpaTextView=binding.getRoot().findViewById(R.id.cgpaTextView);

        recyclerView=binding.getRoot().findViewById(R.id.courseRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter=new CourseRecyclerAdapter(myCourses);

        recyclerView.setAdapter(adapter);

        fab= binding.fabCourseFragment;



        addCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(creditText.getText().toString().equals("") || gpaText.getText().toString().equals(""))
                {
                    Toast.makeText(getActivity(), "Please insert All Fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    CalculateCgpa(gpaText.getText().toString(),creditText.getText().toString());
                }

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getActivity()).setMessage("Do you want to save..?")
                        .setTitle("Warning!!")
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if(myCourses==null || myCourses.size()==0)
                                {
                                    Toast.makeText(getActivity(), "Add a course first", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    repository.InsertCourse(myCourses);
                                    Toast.makeText(getActivity(), "No Tension! Courses Saved", Toast.LENGTH_SHORT).show();
                                }

                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
            }
        });


        Toast.makeText(getActivity(), controller.getCurrentSemester().getSemesterName(), Toast.LENGTH_SHORT).show();

        return binding.getRoot();

    }


    private void CalculateCgpa(String gpa,String credit)
    {
        double gpaValue=Double.parseDouble(gpa);
        double creditValue=Double.parseDouble(credit);
        productOfGpaAndCredit+=(gpaValue*creditValue);
        totalCredit+=creditValue;
        double cgpa=productOfGpaAndCredit/totalCredit;
        cgpaTextView.setText(String.format("CGPA: %.2f",cgpa));

        Course course=new Course(gpaValue,creditValue,controller.getCurrentSemester().getId());
        myCourses.add(course);
        adapter.notifyDataSetChanged();


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}