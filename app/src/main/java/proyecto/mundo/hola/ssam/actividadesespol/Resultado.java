package proyecto.mundo.hola.ssam.actividadesespol;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Resultado extends AppCompatActivity {
    Button botonIR;
    TextView primera;
    //val mainFrag: Perfil = MainFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        botonIR = findViewById(R.id.botonIR);
        primera = findViewById(R.id.primera);

        botonIR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Resultado.this, Noticias.class);
                String name = getIntent().getStringExtra("name");
                String email = getIntent().getStringExtra("email");
                Uri imageUri = getIntent().getParcelableExtra("photoUrl");

                i.putExtra("name", name);
                i.putExtra("email", email);
                i.putExtra("photoUrl", imageUri);
                startActivity(i);
            }
        });

        primera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fg = new Perfil();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.contenidoNoticias,fg);
                ft.addToBackStack(null);
                ft.commit();

                //Intent i = new Intent(Resultado.this, Perfil.class);
                //String name = getIntent().getStringExtra("name");
                //String email = getIntent().getStringExtra("email");
                //Uri imageUri = getIntent().getParcelableExtra("photoUrl");

                //i.putExtra("name", name);
                //i.putExtra("email", email);
                //i.putExtra("photoUrl", imageUri);
                //startActivity(i);
            }
        });
    }
}
