package com.jivan.myapp.dialog;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jivan.myapp.R;
import com.jivan.myapp.helpers.DBHandler;

import java.util.Objects;


public class DeleteCourseDialogFragment extends DialogFragment {

    private Button btnDeleteCourse;
    private EditText etCourseId;
    private DBHandler dbHandler;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_delete_course_dialog, null);
        builder.setView(view);

        etCourseId = view.findViewById(R.id.etCourseId);
        btnDeleteCourse = view.findViewById(R.id.btnDeleteCourse);

        dbHandler = new DBHandler(requireContext());

        btnDeleteCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int id = Integer.parseInt(etCourseId.getText().toString());
                    int deleteRow = dbHandler.deleteCourse(id);
                    if (deleteRow > 0) {
                        Toast.makeText(getActivity(), "Course of id " + id + " deleted ", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getActivity(), "Course of id " + id + " not valid", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception ex) {
                    Log.d("Error ", Objects.requireNonNull(ex.getMessage()));
                }
            }
        });


        return builder.create();
    }


}