package proyecto.mundo.hola.ssam.actividadesespol;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class Formulario extends AppCompatActivity {
    Button regresar;
    Button siguiente;
    CheckBox tecnologia;
    CheckBox checkBox2;
    CheckBox fotografia;
    CheckBox hacking;
    List<TextView> lista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        regresar = (Button)findViewById(R.id.regresar);
        siguiente = (Button)findViewById(R.id.siguiente);
        tecnologia = (CheckBox)findViewById(R.id.checkBox);
        fotografia = (CheckBox)findViewById(R.id.checkBox3);
        hacking = (CheckBox)findViewById(R.id.checkBox4);
        lista = new ArrayList<>();

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
                String contenido = checked();

                i.putExtra("name", name);
                i.putExtra("email", email);
                i.putExtra("photoUrl", imageUri);
                i.putExtra("contenido", contenido);
                startActivity(i);
            }
        });
    }



    public String checked(){
        StringBuilder sb = new StringBuilder();
        if(tecnologia.isChecked()){
            sb.append("tecnologia");
            sb.append(",");
        }
        if(hacking.isChecked()){
            sb.append("hacking");
            sb.append(",");
        }
        if(fotografia.isChecked()){
            sb.append("fotografia");
            sb.append(",");
        }

        Toast.makeText(Formulario.this, sb.toString(),Toast.LENGTH_SHORT).show();
        return sb.toString();
    }
}
