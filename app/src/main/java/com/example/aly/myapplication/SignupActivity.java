package com.example.aly.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class SignupActivity extends AppCompatActivity {
    private static EditText nameup,emailup,passwordup,repasswordup;
    private static Button signupf;
    private FirebaseAuth auth;

    Intent intent;
    private static String name,email,password,repassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        nameup = (EditText)findViewById(R.id.nameup3);
        auth = FirebaseAuth.getInstance();

        emailup = (EditText)findViewById(R.id.emailup3);

        passwordup = (EditText)findViewById(R.id.passwordup3);
        repasswordup = (EditText)findViewById(R.id.repasswordup3);

        signupf = (Button)findViewById(R.id.signupf);
         //intent = new Intent(this,SigninActivity.class);



    }

    public void signupon(View view) {
        name =nameup.getText().toString();
        email =emailup.getText().toString();
        password =passwordup.getText().toString();
        repassword =repasswordup.getText().toString();
        if (name.equals("")||email.equals("")||password.equals("")||
                repassword.equals("")||!(password.equals(repassword))){
            Toast.makeText(SignupActivity.this, "opps \n is it not clear ?",
                    Toast.LENGTH_LONG).show();
        }
        else {
            auth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){

                                Toast.makeText(SignupActivity.this, "good",
                                        Toast.LENGTH_LONG).show();
                                FirebaseUser user = auth.getCurrentUser();
                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(name).build();
                                user.updateProfile(profileUpdates);
                                go();

                            }
                            else{Toast.makeText(SignupActivity.this, "bad",
                                    Toast.LENGTH_LONG).show();}

                        }
                    });


        }

    }
    public void go(){
        Intent intent = new Intent(this,SigninActivity.class);
        startActivity(intent);
    }
}
