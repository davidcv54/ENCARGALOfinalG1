package mx.com.encargalo;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class activity_is_actcrearunacuenta extends AppCompatActivity {
Button btncrearcuenta;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_is_actcrearunacuenta);

        btncrearcuenta=(Button)findViewById(R.id.is_ispbtnRegistrarse);
        btncrearcuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent loguearfb = new Intent(activity_is_actcrearunacuenta.this, activity_is_actverificacioncodigo.class);
                startActivity(loguearfb);
            }
        });

        }
}
