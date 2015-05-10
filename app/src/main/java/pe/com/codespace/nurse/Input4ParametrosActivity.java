package pe.com.codespace.nurse;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import static pe.com.codespace.nurse.MyValues.*;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;


public class Input4ParametrosActivity extends AppCompatActivity {
    double Param1, Param2, Param3, Param4;
    double resultado;
    String label1="", unidades="", descripcion="";
    int tipo = -1;
    SharedPreferences sharedPreferences;
    boolean sistema_metrico_flag;
    EditText editText1 = null;
    EditText editText2 = null;
    EditText editText3 = null;
    EditText editText4 = null;
    TextView textViewResultado1 = null;
    TextView textViewResultado2 = null;
    TextView textViewDescription = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setIcon(R.drawable.ic_launcher);
        }
        setContentView(R.layout.activity_input4param);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        sistema_metrico_flag = PrefSingleton.getInstance().getPreference(MyValues.SISTEMA_METRICO,true);

        textViewResultado1 = (TextView) findViewById(R.id.tvResultado1);
        textViewResultado2 = (TextView) findViewById(R.id.tvResultado2);
        textViewDescription = (TextView) findViewById(R.id.tvDescription);
        TextView tvTitleFormula = (TextView) findViewById(R.id.tvTitleFormula);
        TextView tvParam1 = (TextView) findViewById(R.id.tvInput1);
        TextView tvParam2 = (TextView) findViewById(R.id.tvInput2);
        TextView tvParam3 = (TextView) findViewById(R.id.tvInput3);
        TextView tvParam4 = (TextView) findViewById(R.id.tvInput4);
        editText1 = (EditText) findViewById(R.id.etInput1);
        editText2 = (EditText) findViewById(R.id.etInput2);
        editText3 = (EditText) findViewById(R.id.etInput3);
        editText4 = (EditText) findViewById(R.id.etInput4);
        Intent intent = getIntent();
        tipo = intent.getExtras().getInt("formula");

        switch (tipo){
            case GOTEO_FARMACOS_UCI:
                tvTitleFormula.setText(getResources().getString(R.string.formula_velocidad_inotropicos_title));
                tvParam1.setText(getResources().getString(R.string.label_dosis_inotropicos) + ":");
                if(sistema_metrico_flag){
                    tvParam2.setText(getResources().getString(R.string.label_peso_paciente) + " (" + MyValues.MASA_METRICO + "):");
                }else
                {
                    tvParam2.setText(getResources().getString(R.string.label_peso_paciente) + " (" + MyValues.MASA_INGLES + "):");
                }
                tvParam3.setText(getResources().getString(R.string.label_cantidad_farmaco) +  ":");
                tvParam4.setText(getResources().getString(R.string.label_volumen_total) + " (ml):");
                descripcion = getResources().getString(R.string.descripcion_farmacos_uci);
                textViewDescription.setText(descripcion);
                break;
            case 16:
                break;
        }

        //Agregar el adView
        AdView adView = (AdView)this.findViewById(R.id.adViewInput4Param);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        //Analytics
        Tracker tracker = ((AnalyticsApplication)  getApplication()).getTracker(AnalyticsApplication.TrackerName.APP_TRACKER);
        String nameActivity = getApplicationContext().getPackageName() + "." + this.getClass().getSimpleName();
        tracker.setScreenName(nameActivity);
        tracker.enableAdvertisingIdCollection(true);
        tracker.send(new HitBuilders.AppViewBuilder().build());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.input, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_calculate:
                String s1 = editText1.getText().toString();
                String s2 = editText2.getText().toString();
                String s3 = editText3.getText().toString();
                String s4 = editText4.getText().toString();

                if(!Tools.isNumeric(s1) || !Tools.isNumeric(s2) || !Tools.isNumeric(s3) || !Tools.isNumeric(s4)) {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.toast_ingrese_valores), Toast.LENGTH_SHORT).show();
                    return false;
                }
                Param1 = Double.parseDouble(editText1.getText().toString());//dosis
                Param2 = Double.parseDouble(editText2.getText().toString());//peso
                Param3 = Double.parseDouble(editText3.getText().toString());//cantidad
                Param4 = Double.parseDouble(editText4.getText().toString());//volumen
                switch (tipo){
                    case GOTEO_FARMACOS_UCI://Goteo de Dopamina
                        if(!sistema_metrico_flag){
                            Param2 = Tools.LibrasToKilos(Param2);
                        }
                        resultado = Formulas.GoteoDopamina(Param1, Param2, Param3, Param4);
                        label1 = getResources().getString(R.string.label_velocidad);
                        unidades=" ml/hr";
                        break;
                }
                textViewResultado1.setText(label1 + " : " + resultado + unidades);
                textViewResultado1.setVisibility(View.VISIBLE);
                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if(getCurrentFocus()!=null){
                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                }
                break;
            case R.id.action_clean:
                textViewResultado1.setVisibility(View.INVISIBLE);
                textViewResultado2.setVisibility(View.GONE);
                editText1.setText("");
                editText2.setText("");
                editText3.setText("");
                editText4.setText("");
                editText1.requestFocus();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
