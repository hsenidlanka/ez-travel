package com.example.hsenid.taxiapp;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class DialogBoxActivity extends AppCompatDialogFragment {
    private EditText currentEmail;
    private EditText currentPassword;
    private EditText newPassword;
    private DialogListener listener;

    private String url;



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater= getActivity().getLayoutInflater();
        View view= inflater.inflate(R.layout.dialog_login,null);

        mBuilder.setView(view)
                .setTitle("Update Password")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String email= currentEmail.getText().toString();
                        String cPwd =currentPassword.getText().toString();
                        String nPwd =newPassword.getText().toString();

                        listener.updatePassword(email,cPwd,nPwd);

                    }
                });
        currentEmail = (EditText) view.findViewById(R.id.current_email_text);
        currentPassword =(EditText) view.findViewById(R.id.current_pw_text);
        newPassword = (EditText) view.findViewById(R.id.new_pw_text);

        return mBuilder.create();

    }

    public void onAttach(Context context){
        super.onAttach(context);

        try {
            listener =(DialogListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() + "must implement DialogListener");
        }
    }

    public interface  DialogListener{
        void updatePassword(String email,String currentPw,String newPw);
    }

}
