package com.example.aly.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class MainActivity extends AppCompatActivity {
     FirebaseAuth mAuth;
    FirebaseAuth auth;
    private EditText emailin,passwordin,nameup,emailup,passwordup,repasswordup;
    private static final String TAG = "MainActivity";
    private String email,password,fucname ,fucemail,fucpassword,fucrepassword;
    private Button signin1,signin22 ,signup,rsignup,crenew,alrhave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        auth = FirebaseAuth.getInstance();
        emailin = (EditText)findViewById(R.id.emailin);
        nameup = (EditText)findViewById(R.id.nameup2);
        emailup = (EditText)findViewById(R.id.emailup2);
        passwordup = (EditText)findViewById(R.id.passwordup2);
        repasswordup = (EditText)findViewById(R.id.repasswordup2);
        crenew = (Button)findViewById(R.id.button8) ;
        alrhave = (Button)findViewById(R.id.button6) ;
        passwordin = (EditText)findViewById(R.id.passwordin);
        emailin.setVisibility(View.INVISIBLE);
        passwordin.setVisibility(View.INVISIBLE);
        nameup.setVisibility(View.INVISIBLE);
        emailup.setVisibility(View.INVISIBLE);
        passwordup.setVisibility(View.INVISIBLE);
        repasswordup.setVisibility(View.INVISIBLE);
        signin1=(Button)findViewById(R.id.button3);
        signin22=(Button)findViewById(R.id.button7);
        signup=(Button)findViewById(R.id.button2);
        rsignup=(Button)findViewById(R.id.rsignup);
        signin22.setVisibility(View.INVISIBLE);
        rsignup.setVisibility(View.INVISIBLE);
        crenew.setVisibility(View.INVISIBLE);
        alrhave.setVisibility(View.INVISIBLE);





    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }


    public void signin(View view) {
        emailin.setVisibility(View.VISIBLE);
        passwordin.setVisibility(View.VISIBLE);
        signin22.setVisibility(View.VISIBLE);
        signup.setVisibility(View.INVISIBLE);
        signin1.setVisibility(View.INVISIBLE);
        crenew.setVisibility(View.VISIBLE);



    }

    public void signup(View view) {

        signup.setVisibility(View.INVISIBLE);
        signin1.setVisibility(View.INVISIBLE);
        nameup.setVisibility(View.VISIBLE);
        emailup.setVisibility(View.VISIBLE);
        passwordup.setVisibility(View.VISIBLE);
        repasswordup.setVisibility(View.VISIBLE);
        rsignup.setVisibility(View.VISIBLE);
        alrhave.setVisibility(View.VISIBLE);

    }
    private void updateUI(FirebaseUser user) {

        if (user != null) {
            Intent intent = new Intent(this,SigninActivity.class);
            startActivity(intent);


        } else {


        }
    }

    public void signin2(View view) {
        email=emailin.getText().toString();
        password=passwordin.getText().toString();
        if (email.equals("")||password.equals("")){
            Toast.makeText(MainActivity.this, "ops \n could you tell us your email and password",
                    Toast.LENGTH_LONG).show();
        }
        else {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(MainActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                updateUI(null);
                            }
                        }
                    });
        }

    }

    public void signup2(View view) {
        fucname =nameup.getText().toString();
        fucemail =emailup.getText().toString();
        fucpassword =passwordup.getText().toString();
        fucrepassword =repasswordup.getText().toString();
        if (fucname.equals("")||fucemail.equals("")||fucpassword.equals("")||
                fucrepassword.equals("")||!(fucpassword.equals(fucrepassword))){
            Toast.makeText(MainActivity.this, "opps \n is it not clear ?",
                    Toast.LENGTH_LONG).show();
        }
        else {
            auth.createUserWithEmailAndPassword(fucemail,fucpassword)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){

                                Toast.makeText(MainActivity.this, "good",
                                        Toast.LENGTH_LONG).show();
                                FirebaseUser user = auth.getCurrentUser();
                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(fucname).build();
                                user.updateProfile(profileUpdates);
                                go();

                            }
                            else{Toast.makeText(MainActivity.this, "bad",
                                    Toast.LENGTH_LONG).show();}

                        }
                    });


        }

    }
    public void go(){
        Intent intent = new Intent(this,SigninActivity.class);
        startActivity(intent);
    }

    public void already(View view) {
        emailin.setVisibility(View.VISIBLE);
        passwordin.setVisibility(View.VISIBLE);
        signin22.setVisibility(View.VISIBLE);
        signup.setVisibility(View.INVISIBLE);
        signin1.setVisibility(View.INVISIBLE);
    }

    public void createnew(View view) {
        signup.setVisibility(View.INVISIBLE);
        signin1.setVisibility(View.INVISIBLE);
        nameup.setVisibility(View.VISIBLE);
        emailup.setVisibility(View.VISIBLE);
        passwordup.setVisibility(View.VISIBLE);
        repasswordup.setVisibility(View.VISIBLE);
        rsignup.setVisibility(View.VISIBLE);
    }
}
