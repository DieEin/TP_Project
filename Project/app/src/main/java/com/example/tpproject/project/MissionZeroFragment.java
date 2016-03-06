package com.example.tpproject.project;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by Tomi on 6.3.2016 г..
 */
public class MissionZeroFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder theDialog = new AlertDialog.Builder(getActivity());

        theDialog.setTitle("Sample Dialog");
        theDialog.setMessage("Hello It's me...the Dialog");
        theDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "Clicked OK", Toast.LENGTH_SHORT).show();
            }
        });
        theDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "Clicked CANCEL", Toast.LENGTH_SHORT).show();
            }
        });

        return theDialog.create();
    }
}
