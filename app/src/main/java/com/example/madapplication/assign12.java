package com.example.madapplication;



import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class assign12 extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign12);

        // Retrieve the Student object passed from MainActivity
        Student2 student = (Student2) getIntent().getSerializableExtra("key");

        // Extract the Student details
        String name = "Faculty Name: <i>" + student.Name + "</i>";
        String state = "Designation: <i>" + student.State + "</i>";
        String gender = "Gender: <i>" + student.Gender + "</i>";
        String dob = "Date of Join: <i>" + student.DOB + "</i>";

        // Calculate experience
        String experience = calculateExperience(student.DOB);

        // Show a toast greeting the user
        Toast.makeText(this, "Hello, " + student.Name + "!", Toast.LENGTH_SHORT).show();

        // Set the details into respective TextViews
        TextView textViewName = findViewById(R.id.textViewName);
        textViewName.setText(Html.fromHtml(name));

        TextView textViewGender = findViewById(R.id.textViewGender);
        textViewGender.setText(Html.fromHtml(gender));

        TextView textViewDOB = findViewById(R.id.textViewDOB);
        textViewDOB.setText(Html.fromHtml(dob));

        TextView textViewState = findViewById(R.id.textViewState);
        textViewState.setText(Html.fromHtml(state));

        // Display the calculated experience
        TextView textViewExperience = findViewById(R.id.experience);
        textViewExperience.setText(Html.fromHtml("Experience: <i>" + experience + "</i>"));
    }

    // Method to calculate experience
    private String calculateExperience(String dob) {
        try {
            // Parse the date of joining (assumed format: "DD-MM-YYYY")
            String[] dateParts = dob.split("-");
            int day = Integer.parseInt(dateParts[0]);
            int month = Integer.parseInt(dateParts[1]) - 1; // Months are 0-based in Calendar
            int year = Integer.parseInt(dateParts[2]);

            // Current date
            Calendar today = Calendar.getInstance();

            // Joining date
            Calendar joiningDate = Calendar.getInstance();
            joiningDate.set(year, month, day);

            // Calculate the difference in years, months, and days
            int diffYear = today.get(Calendar.YEAR) - joiningDate.get(Calendar.YEAR);
            int diffMonth = today.get(Calendar.MONTH) - joiningDate.get(Calendar.MONTH);
            int diffDay = today.get(Calendar.DAY_OF_MONTH) - joiningDate.get(Calendar.DAY_OF_MONTH);

            // Adjust for negative months or days
            if (diffDay < 0) {
                diffMonth--;
                diffDay += joiningDate.getActualMaximum(Calendar.DAY_OF_MONTH);
            }
            if (diffMonth < 0) {
                diffYear--;
                diffMonth += 12;
            }

            // Return formatted experience
            return diffYear + " years, " + diffMonth + " months, " + diffDay + " days";

        } catch (Exception e) {
            e.printStackTrace();
            return "Invalid Date of Join";
        }
    }
}