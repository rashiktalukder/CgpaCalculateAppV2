package com.rashik.cgpa.calculation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.rashik.cgpa.R;
import com.rashik.cgpa.databinding.FragmentSecondBinding;
import com.rashik.cgpa.home.DataController;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    DataController controller;

    EditText creditText,gpaText;
    Button addCourseButton;
    TextView cgpaTextView;
    double totalCredit=0;
    double productOfGpaAndCredit=0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        controller=DataController.getInstance();

        creditText=binding.getRoot().findViewById(R.id.editTextCredit);
        gpaText=binding.getRoot().findViewById(R.id.editTextGpa);
        addCourseButton=binding.getRoot().findViewById(R.id.addButton);
        cgpaTextView=binding.getRoot().findViewById(R.id.cgpaTextView);


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


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}