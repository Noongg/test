package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class Login extends AppCompatActivity {
    EditText edt_input_id,edt_input_pass;
    Button buttonLogin,buttonRegister;
    FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edt_input_id=(EditText) findViewById(R.id.edt_input_id);
        edt_input_pass=(EditText) findViewById(R.id.edt_input_pass);
        buttonLogin=(Button) findViewById(R.id.buttonLogin);
        buttonRegister=(Button) findViewById(R.id.buttonRegister);
        firestore=FirebaseFirestore.getInstance();
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=edt_input_id.getText().toString().trim();
                String pass=edt_input_pass.getText().toString().trim();
                if (id.isEmpty()){
                    edt_input_id.setError("id is required!");
                    edt_input_id.requestFocus();
                    return;
                }else if (pass.isEmpty()){
                    edt_input_pass.setError("pass is required!");
                    edt_input_pass.requestFocus();
                    return;
                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                    builder.setCancelable(false); // if you want user to wait for some process to finish,
                    builder.setView(R.layout.dialog_progress);
                    AlertDialog dialog = builder.create();
                    dialog.show();

                    firestore.collection("user")
                            .whereEqualTo("id",id)
                            .whereEqualTo("pass",pass)
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful() && task.getResult()!=null &&task.getResult().getDocuments().size()>0){
                                        Intent intent=new Intent(Login.this, Main_Page.class);
                                        startActivity(intent);
                                        finish();
                                        dialog.dismiss();
                                    }else {
                                        Toast.makeText(Login.this, "Wrong id or password", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    }
                                }
                            });
                }
            }
        });
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,Register.class);
                startActivity(intent);
            }
        });
    }
}