package com.jivan.myapp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

 public class MyDialogFragment extends DialogFragment{
     private String title = "Confirmation", message = "Are you sure you want to continue?", positiveMessage = "Yes";
     public MyDialogFragment(String title, String message, String positiveMessage){
         this.title = title;
         this.message = message;
         this.positiveMessage = positiveMessage;
     }
     public MyDialogFragment(){

     }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {


        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setTitle(title).setMessage(message)
                .setIcon(R.drawable.ic_launcher_background)
                .setPositiveButton(positiveMessage, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
//                        Toast.makeText(requireActivity(), "Continue...", Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                });
//                .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int i) {
//                        Toast.makeText(requireActivity(), "Cancelling...", Toast.LENGTH_LONG).show();
//                        dialog.dismiss();
//                    }
//                });

        return builder.create();
    }

}