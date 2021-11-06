package personal.app.ejercicio_guia_7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static SharedPreferences sharedPreferences;
    public static String NAME_FILE = "configuration";
    public static int randomNum;
    public static int puntaje;
    public static int intentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        centerTitle();
        newRandomNumber();
        sharedPreferences = getSharedPreferences(NAME_FILE, MODE_PRIVATE);
        if(!sharedPreferences.getString("USER", "").isEmpty()){
            puntaje = Integer.parseInt(sharedPreferences.getString("PUNTAJE", ""));
            intentos = Integer.parseInt(sharedPreferences.getString("INTENTOS", ""));
        }else{
            puntaje = 0;
            intentos = 0;
        }
    }

    public void onClickJugar(View view) {
        if(!sharedPreferences.getString("USER", "").isEmpty()) {
            Intent intent = new Intent(MainActivity.this, JugarActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(MainActivity.this, "Debe registrar un usuario", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickPuntaje(View view) {
        if(!sharedPreferences.getString("USER", "").isEmpty()) {
            Intent intent = new Intent(MainActivity.this, PuntajeActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(MainActivity.this, "Debe registrar un usuario", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickVerRespuesta(View view) {
        if(!sharedPreferences.getString("USER", "").isEmpty()) {
            Intent intent = new Intent(MainActivity.this, RespuestaActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(MainActivity.this, "Debe registrar un usuario", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickConfiguracion(View view) {
        Intent intent = new Intent(MainActivity.this, ConfiguracionActivity.class);
        startActivity(intent);
    }

    public void onClickDatos(View view) {
        Intent intent = new Intent(MainActivity.this, DatosActivity.class);
        startActivity(intent);
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