package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    EditText edt_input_reg_id,edt_input_reg_pass,edt_input_reg_pass_check,edt_input_reg_email,edt_input_reg_date;
    Button btn_Checkid,btn_back,btn_register;
    FirebaseFirestore firestore;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        firestore=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btn_Checkid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=edt_input_reg_id.getText().toString().trim();
                firestore.collection("user")
                        .whereEqualTo("id",id)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()&& !task.getResult().isEmpty()){
                                    Toast.makeText(Register.this, "Id đã tồn tại", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=edt_input_reg_id.getText().toString().trim();
                String pass=edt_input_reg_pass.getText().toString().trim();
                String pass_check=edt_input_reg_pass_check.getText().toString().trim();
                String email=edt_input_reg_email.getText().toString().trim();
                String date=edt_input_reg_date.getText().toString().trim();

                if (id.isEmpty()){
                    edt_input_reg_id.setError("id is required!");
                    edt_input_reg_id.requestFocus();
                    return;
                }else if (pass.isEmpty()){
                    edt_input_reg_pass.setError("pass is required!");
                    edt_input_reg_pass.requestFocus();
                    return;
                }
                else if (pass.length()!=6){
                    edt_input_reg_pass.setError("pass is required!");
                    edt_input_reg_pass.requestFocus();
                    return;
                }else if (pass_check.isEmpty()){
                    edt_input_reg_pass_check.setError("pass check is required!");
                    edt_input_reg_pass_check.requestFocus();
                    return;
                }
                else if (!pass_check.equals(pass)){
                    edt_input_reg_pass_check.setError("pass and pass check do not match!");
                    edt_input_reg_pass_check.requestFocus();
                    return;
                }
                else if (email.isEmpty()){
                    edt_input_reg_email.setError("email is required!");
                    edt_input_reg_email.requestFocus();
                    return;
                }
                else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    edt_input_reg_email.setError("wrong email format!");
                    edt_input_reg_email.requestFocus();
                    return;
                }
                else if (date.isEmpty()){
                    edt_input_reg_date.setError("birthday is required!");
                    edt_input_reg_date.requestFocus();
                    return;
                }
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                    builder.setCancelable(false); // if you want user to wait for some process to finish,
                    builder.setView(R.layout.dialog_progress);
                    AlertDialog dialog = builder.create();
                    dialog.show();

                    firestore.collection("user")
                            .whereEqualTo("id",id)
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()&& !task.getResult().isEmpty()){
                                        dialog.dismiss();
                                        Toast.makeText(Register.this, "Id đã tồn tại", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        auth.createUserWithEmailAndPassword(email,pass)
                                                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                                        if (task.isSuccessful()){
                                                            String uid=auth.getCurrentUser().getUid();
                                                            Map<String, Object> user=new HashMap<>();
                                                            user.put("id",id);
                                                            user.put("pass",pass);
                                                            user.put("email",email);
                                                            user.put("birthday",date);
                                                            firestore.collection("user").document(uid).set(user)
                                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                        @Override
                                                                        public void onSuccess(Void unused) {
                                                                            Intent intent=new Intent(Register.this,Login.class);
                                                                            startActivity(intent);
                                                                            finish();
                                                                            dialog.dismiss();
                                                                        }
                                                                    });
                                                        }else {
                                                            dialog.dismiss();
                                                            Log.e("TAG", task.getException().getMessage() );
                                                            Toast.makeText(Register.this, "lỗi", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                });
                                    }
                                }
                            });



                }

            }
        });
    }
    private void init(){
        edt_input_reg_id=(EditText) findViewById(R.id.edt_input_reg_id);
        edt_input_reg_pass=(EditText) findViewById(R.id.edt_input_reg_pass);
        edt_input_reg_pass_check=(EditText) findViewById(R.id.edt_input_reg_pass_check);
        edt_input_reg_email=(EditText) findViewById(R.id.edt_input_reg_email);
        edt_input_reg_date=(EditText) findViewById(R.id.edt_input_reg_date);
        btn_Checkid=(Button) findViewById(R.id.btn_Checkid);
        btn_back=(Button) findViewById(R.id.btn_back);
        btn_register=(Button) findViewById(R.id.btn_register);
    }
}