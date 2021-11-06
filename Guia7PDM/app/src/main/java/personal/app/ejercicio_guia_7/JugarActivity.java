package personal.app.ejercicio_guia_7;

import static personal.app.ejercicio_guia_7.MainActivity.NAME_FILE;
import static personal.app.ejercicio_guia_7.MainActivity.intentos;
import static personal.app.ejercicio_guia_7.MainActivity.puntaje;
import static personal.app.ejercicio_guia_7.MainActivity.randomNum;
import static personal.app.ejercicio_guia_7.MainActivity.sharedPreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.Random;

public class JugarActivity extends AppCompatActivity {

    TextView tvNombreUsuarioJugar, tvPuntajeTotal, tvIntentosNumeroJugar;
    EditText etNumeroJugar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugar);
        centerTitle();
        setTitle("Jugar");
        tvNombreUsuarioJugar = findViewById(R.id.tvNombreUsuarioJugar);
        tvPuntajeTotal = findViewById(R.id.tvPuntajeTotal);
        tvIntentosNumeroJugar = findViewById(R.id.tvIntentosNumeroJugar);
        etNumeroJugar = findViewById(R.id.etNumeroJugar);
        sharedPreferences = getSharedPreferences(NAME_FILE, MODE_PRIVATE);
        tvNombreUsuarioJugar.setText(sharedPreferences.getString("USER", ""));
        tvPuntajeTotal.setText("Puntaje total: "+sharedPreferences.getString("PUNTAJE", ""));
        tvIntentosNumeroJugar.setText(sharedPreferences.getString("INTENTOS", ""));
    }

    public void onClickAdivinar(View view) {
        sharedPreferences = getSharedPreferences(NAME_FILE, MODE_PRIVATE);
        SharedPreferences.Editor editorConfig = sharedPreferences.edit();
        if (String.valueOf(randomNum).equals(etNumeroJugar.getText().toString())) {
            puntaje = puntaje + 10;
            intentos++;
            editorConfig.putString("PUNTAJE", String.valueOf(puntaje));
            editorConfig.putString("INTENTOS", String.valueOf(intentos));
            editorConfig.commit();
            newRandomNumber();
            Toast.makeText(JugarActivity.this, "Intento acertado", Toast.LENGTH_SHORT).show();
        } else {
            puntaje = puntaje - 1;
            intentos++;
            editorConfig.putString("PUNTAJE", String.valueOf(puntaje));
            editorConfig.putString("INTENTOS", String.valueOf(intentos));
            editorConfig.commit();
            Toast.makeText(JugarActivity.this, "Intento fallido", Toast.LENGTH_SHORT).show();
        }
        tvPuntajeTotal.setText("Puntaje total: " + sharedPreferences.getString("PUNTAJE", ""));
        tvIntentosNumeroJugar.setText(sharedPreferences.getString("INTENTOS", ""));
    }

    public void newRandomNumber(){
        Random rand = new Random();
        randomNum = rand.nextInt((10 - 1) + 1) + 1;
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