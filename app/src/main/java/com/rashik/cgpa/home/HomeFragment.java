package com.rashik.cgpa.home;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.rashik.cgpa.GradeRepository;
import com.rashik.cgpa.R;
import com.rashik.cgpa.databinding.FragmentHomeBinding;
import com.rashik.cgpa.model.Semester;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements HomeFragmentInterface{

    private FragmentHomeBinding binding;
    GradeRepository repository;
    RecyclerView recyclerView;
    HomeRecyclerAdapter homeRecyclerAdapter;
    List<Semester>allSemesters = new ArrayList<>();
    DataController controller;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        repository=new GradeRepository(getActivity().getApplication());

        recyclerView=binding.getRoot().findViewById(R.id.home_RecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        allSemesters=repository.GetAllSemesters();

        /*for (int i=0;i<100;i++)
        {
            allSemesters.add(new Semester("Semester Name"+i,00.00));
        }*/

        homeRecyclerAdapter=new HomeRecyclerAdapter(allSemesters);
        recyclerView.setAdapter(homeRecyclerAdapter);
        controller=DataController.getInstance();
        controller.setHomeFragmentInterface(this);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog=new Dialog(getActivity());
                dialog.setContentView(R.layout.custom_user_input_dialog);
                EditText semesterNameEditText=dialog.findViewById(R.id.dialog_semesterName_edittext);
                Button createButton=dialog.findViewById(R.id.dialog_create_button);
                createButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (semesterNameEditText.getText().toString().equals(""))
                        {
                            Toast.makeText(getActivity(), "Please Insert Semester Name", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            String semesterName=semesterNameEditText.getText().toString();
                            Toast.makeText(getActivity(), semesterName+" Is your semester name", Toast.LENGTH_LONG).show();
                            insertSemester(semesterName);
                            dialog.dismiss();
                        }
                    }
                });

                dialog.show();
            }
        });

        return binding.getRoot();

    }

    private void insertSemester(String semesterName)
    {
        Semester tempSemester=new Semester(semesterName,0.0);
        allSemesters.add(tempSemester);
        homeRecyclerAdapter.notifyDataSetChanged();
        repository.InsertSemester(tempSemester);


    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(HomeFragment.this)
//                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
//            }
//        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    @Override
    public void onSemesterItemClicked(Semester semester) {
       // Toast.makeText(getActivity(), "Name: "+semester.getSemesterName(), Toast.LENGTH_SHORT).show();
        controller.setCurrentSemester(semester);
        NavHostFragment.findNavController(HomeFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);

    }
}