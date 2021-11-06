package personal.app.ejercicio_guia_7;

import static personal.app.ejercicio_guia_7.MainActivity.NAME_FILE;
import static personal.app.ejercicio_guia_7.MainActivity.intentos;
import static personal.app.ejercicio_guia_7.MainActivity.puntaje;
import static personal.app.ejercicio_guia_7.MainActivity.sharedPreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;

public class ConfiguracionActivity extends AppCompatActivity {

    private EditText etNombreUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);
        centerTitle();
        setTitle("Configuración");
        etNombreUsuario = findViewById(R.id.etNombreUsuario);
    }

    public void onClickGuardarConfiguracion(View view) {
        sharedPreferences = getSharedPreferences(NAME_FILE, MODE_PRIVATE);
        SharedPreferences.Editor editorConfig = sharedPreferences.edit();
        editorConfig.putString("USER", etNombreUsuario.getText().toString());
        puntaje = 10;
        intentos = 0;
        editorConfig.putString("PUNTAJE", String.valueOf(puntaje));
        editorConfig.putString("INTENTOS", String.valueOf(intentos));
        editorConfig.commit();
        Toast.makeText(ConfiguracionActivity.this, sharedPreferences.getString("USER", ""), Toast.LENGTH_SHORT).show();
    }

    public void centerTitle() {
        ArrayList<View> textViews = new ArrayList<>();
        getWindow().getDecorView().findViewsWithText(textViews, getTitle(), View.FIND_VIEWS_WITH_TEXT);
        if(textViews.size() > 0) {
            AppCompatTextView appCompatTextView = null;
            if(textViews.size() == 1) {
                appCompatTextView = (AppCompatTextView) textViews.get(0);
            } else {
                for(View v : textViews) {
                    if(v.getParent() instanceof Toolbar) {
                        appCompatTextView = (AppCompatTextView) v;
                        break;
                    }
                }
            }
            if(appCompatTextView != null) {
                ViewGroup.LayoutParams params = appCompatTextView.getLayoutParams();
                params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                appCompatTextView.setLayoutParams(params);
                appCompatTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            }
        }
    }

}