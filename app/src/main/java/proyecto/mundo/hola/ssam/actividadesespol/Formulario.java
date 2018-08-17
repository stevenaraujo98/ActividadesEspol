package proyecto.mundo.hola.ssam.actividadesespol;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Formulario extends AppCompatActivity {
    Button regresar;
    Button siguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        regresar = findViewById(R.id.regresar);
        siguiente = findViewById(R.id.siguiente);

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent in = new Intent(Formulario.this, MainActivity.class);
                startActivity(in);
            }
        });

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Formulario.this, Resultado.class);
                String name = getIntent().getStringExtra("name");
                String email = getIntent().getStringExtra("email");
                Uri imageUri = getIntent().getParcelableExtra("photoUrl");

                i.putExtra("name", name);
                i.putExtra("email", email);
                i.putExtra("photoUrl", imageUri);
                startActivity(i);
            }
        });


    }
}
