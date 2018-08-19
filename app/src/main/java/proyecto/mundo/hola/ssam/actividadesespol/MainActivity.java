package proyecto.mundo.hola.ssam.actividadesespol;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.BufferedReader;
import java.text.Normalizer;

public class MainActivity extends AppCompatActivity {
    Button registrar;
    Button ingresar;
    TextView tv;
    AutoCompleteTextView autoCompleteTextView2;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        autoCompleteTextView2 = findViewById(R.id.autoCompleteTextView2);
        tv = findViewById(R.id.editText);


        registrar = (Button) findViewById(R.id.button);
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Formulario.class);
                startActivity(i);
                //if(!autoCompleteTextView2.getText().toString().equals("") && !tv.getText().toString().equals("")){
                //    if(autoCompleteTextView2.getText().toString().indexOf("@espol.edu.ec") != -1){
                //        createAccount(autoCompleteTextView2.getText().toString(), tv.getText().toString());
                //    }else
                //        Toast.makeText(MainActivity.this, "Ingrese correo Espol!",Toast.LENGTH_SHORT).show();
                //}else{
                //    Toast.makeText(MainActivity.this, "Ingrese correo y contraseña.",Toast.LENGTH_SHORT).show();
                //}
            }
        });


        ingresar = (Button) findViewById(R.id.button2);
        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!autoCompleteTextView2.getText().toString().equals("") && !tv.getText().toString().equals("")){
                    signIn(autoCompleteTextView2.getText().toString(), tv.getText().toString());
                }else{
                    Toast.makeText(MainActivity.this, "Ingrese correo y contraseña.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser, 1);
    }

    private void updateUI(FirebaseUser user, int a){
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();
            Intent i = null;
            if(a == 0){
                i = new Intent(MainActivity.this, Formulario.class);
            }else if(a == 1){
                i = new Intent(MainActivity.this, Noticias.class);
            }
            i.putExtra("name", name);
            i.putExtra("email", email);
            i.putExtra("photoUrl", photoUrl);
            startActivity(i);
        }
    }

    public void createAccount(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("", "createUserWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user, 0);
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("", "createUserWithEmail:failure", task.getException());
                    Toast.makeText(MainActivity.this, "Authentication failed.",Toast.LENGTH_SHORT).show();
                    updateUI(null, 0);
                }

                // ...
            }
        });
    }

    public void signIn(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("", "signInWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user, 1);
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("", "signInWithEmail:failure", task.getException());
                    Toast.makeText(MainActivity.this, "Authentication failed.",Toast.LENGTH_SHORT).show();
                    updateUI(null, 1);
                }
                // ...
            }
        });
    }
}
