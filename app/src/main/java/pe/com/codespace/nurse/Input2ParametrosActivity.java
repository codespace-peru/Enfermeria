package pe.com.codespace.nurse;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.os.Bundle;
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


public class Input2ParametrosActivity extends AppCompatActivity {
    double Param1, Param2;
    double resultado;
    String label1="", label2="", unidades="", descripcion="";
    int tipo = -1;
    Menu opMenu;
    SharedPreferences sharedPreferences;
    boolean sistema_metrico_flag;
    EditText editText1 = null;
    EditText editText2 = null;
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
        setContentView(R.layout.activity_input2param);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        sistema_metrico_flag = PrefSingleton.getInstance().getPreference(MyValues.SISTEMA_METRICO,true);

        textViewResultado1 = (TextView) findViewById(R.id.tvResultado1);
        textViewResultado2 = (TextView) findViewById(R.id.tvResultado2);
        textViewDescription = (TextView) findViewById(R.id.tvDescription);
        TextView tvTitleFormula = (TextView) findViewById(R.id.tvTitleFormula);
        TextView tvParam1 = (TextView) findViewById(R.id.tvInput1);
        TextView tvParam2 = (TextView) findViewById(R.id.tvInput2);
        editText1 = (EditText) findViewById(R.id.etInput1);
        editText2 = (EditText) findViewById(R.id.etInput2);
        Intent intent = getIntent();
        tipo = intent.getExtras().getInt("formula");

        switch (tipo){
            case IMC://IMC
                tvTitleFormula.setText(getResources().getString(R.string.formula_imc_title));
                if(sistema_metrico_flag){
                    tvParam1.setText(getResources().getString(R.string.label_peso)  + " (" + MyValues.MASA_METRICO + "):");
                    tvParam2.setText(getResources().getString(R.string.label_talla) + " (" + MyValues.LONG_METRICO + "):");
                }
                else {
                    tvParam1.setText(getResources().getString(R.string.label_peso) + " (" + MyValues.MASA_INGLES + "):");
                    tvParam2.setText(getResources().getString(R.string.label_talla) + " (" + MyValues.LONG_INGLES + "):");
                }
                descripcion= getResources().getString(R.string.descripcion_imc);
                break;
            case PERDIDAS://Perdidas Insensibles
                tvTitleFormula.setText(getResources().getString(R.string.formula_perdidas_insensibles_title));
                if(sistema_metrico_flag){
                    tvParam1.setText(getResources().getString(R.string.label_peso)  + " (" + MyValues.MASA_METRICO + "):");
                }
                else {
                    tvParam1.setText(getResources().getString(R.string.label_peso) + " (" + MyValues.MASA_INGLES + "):");
                }
                tvParam2.setText(getResources().getString(R.string.label_numero_horas) + ":");
                descripcion="";
                break;
            case SC_NINOS://SC en niños
                tvTitleFormula.setText(getResources().getString(R.string.formula_superficie_corporal_ninos_title));
                if(sistema_metrico_flag){
                    tvParam1.setText(getResources().getString(R.string.label_peso)  + " (" + MyValues.MASA_METRICO + "):");
                    tvParam2.setText(getResources().getString(R.string.label_talla) + " (" + MyValues.LONG_METRICO + "):");
                }
                else {
                    tvParam1.setText(getResources().getString(R.string.label_peso) + " (" + MyValues.MASA_INGLES + "):");
                    tvParam2.setText(getResources().getString(R.string.label_talla) + " (" + MyValues.LONG_INGLES + "):");
                }
                descripcion= getResources().getString(R.string.descripcion_sc_ninos);
                break;
            case SC_ADULTOS://SC en adultos
                tvTitleFormula.setText(getResources().getString(R.string.formula_superficie_corporal_adultos_title));
                if(sistema_metrico_flag){
                    tvParam1.setText(getResources().getString(R.string.label_peso)  + " (" + MyValues.MASA_METRICO + "):");
                    tvParam2.setText(getResources().getString(R.string.label_talla) + " (" + MyValues.LONG_METRICO + "):");
                }
                else {
                    tvParam1.setText(getResources().getString(R.string.label_peso) + " (" + MyValues.MASA_INGLES + "):");
                    tvParam2.setText(getResources().getString(R.string.label_talla) + " (" + MyValues.LONG_INGLES + "):");
                }
                descripcion=getResources().getString(R.string.descripcion_sc_adultos);
                break;
            case VELOCIDADGOTEO://Velocidad de Goteo
                tvTitleFormula.setText(getResources().getString(R.string.formula_velocidad_goteo_title));
                tvParam1.setText(getResources().getString(R.string.label_volumen_infusion) + ":");
                tvParam2.setText(getResources().getString(R.string.label_numero_horas) + ":");
                descripcion=getResources().getString(R.string.descripcion_velocidad_goteo);
                break;
            case VOLUMENINFUSION://
                tvTitleFormula.setText(getResources().getString(R.string.formula_volumen_infusion_title));
                tvParam1.setText(getResources().getString(R.string.formula_velocidad_goteo_title) + " (gts/min): ");
                tvParam2.setText(getResources().getString(R.string.label_numero_horas) + ":");
                descripcion = getResources().getString(R.string.descripcion_volumen_infusion);
                break;
            case TIEMPOINFUSION://
                tvTitleFormula.setText(getResources().getString(R.string.formula_tiempo_infusion_title));
                tvParam1.setText(getResources().getString(R.string.label_volumen_infusion) + ":");
                tvParam2.setText(getResources().getString(R.string.formula_velocidad_goteo_title) + " (gts/min): ");
                descripcion=getResources().getString(R.string.descripcion_tiempo_infusion);
                break;
            case VOLUMEN_FARMACOS_UCI://
                tvTitleFormula.setText(getResources().getString(R.string.formula_volumen_inotropicos_title));
                if(sistema_metrico_flag){
                    tvParam1.setText(getResources().getString(R.string.label_peso_paciente)  + " (" + MyValues.MASA_METRICO + "):");
                }
                else {
                    tvParam1.setText(getResources().getString(R.string.label_peso_paciente) + " (" + MyValues.MASA_INGLES + "):");
                }
                tvParam2.setText(getResources().getString(R.string.label_cantidad_farmaco) + ":");
                descripcion=getResources().getString(R.string.descripcion_volumen_farmacos_uci);
                break;
        }
        textViewDescription.setText(descripcion);

        //Agregar el adView
        AdView adView = (AdView)this.findViewById(R.id.adViewInput2Param);
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
        opMenu = menu;
        getMenuInflater().inflate(R.menu.input, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_calculate:
                String s1 = editText1.getText().toString();
                String s2 = editText2.getText().toString();

                if(!Tools.isNumeric(s1) || ! Tools.isNumeric(s2)){
                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.toast_ingrese_valores), Toast.LENGTH_LONG).show();
                    return false;
                }
                Param1 = Double.parseDouble(editText1.getText().toString());
                Param2 = Double.parseDouble(editText2.getText().toString());

                switch (tipo){
                    case IMC://Indice de Masa Corporal
                        label1 = getResources().getString(R.string.label_imc);
                        if(!sistema_metrico_flag){
                            Param1 = Tools.LibrasToKilos(Param1);
                            Param2 = Tools.InchesToCentimetros(Param2);
                        }
                        if(Param1 > MyValues.PESO_MAXIMO || Param2 > MyValues.TALLA_MAXIMA){
                            Toast.makeText(getApplicationContext(),getResources().getString(R.string.toast_error_medidas), Toast.LENGTH_LONG).show();
                            onOptionsItemSelected(opMenu.findItem(R.id.action_clean));
                            break;
                        }
                        else {
                            resultado = Formulas.IMC(Param1, Param2);
                            unidades="";
                            textViewResultado1.setText(label1 + " : " + resultado + unidades);
                            textViewResultado1.setVisibility(View.VISIBLE);
                        }
                        break;
                    case PERDIDAS://Perdidas Insensibles
                        label1 = getResources().getString(R.string.label_perdidas_insensibles);
                        label2 = getResources().getString(R.string.label_agua_oxidacion);
                        if(!sistema_metrico_flag){
                            Param1 = Tools.LibrasToKilos(Param1);
                        }
                        if(Param1 > MyValues.PESO_MAXIMO){
                            Toast.makeText(getApplicationContext(),getResources().getString(R.string.toast_error_medidas), Toast.LENGTH_LONG).show();
                            onOptionsItemSelected(opMenu.findItem(R.id.action_clean));
                            break;
                        }
                        else {
                            resultado = Formulas.PerdidaInsensibleAdultoNormotermo(Param1, Param2);
                            double agua = (double) Math.round((resultado/3)*100)/100;
                            unidades = " ml.";
                            textViewResultado1.setText(label1 + " : " + resultado + unidades);
                            textViewResultado1.setVisibility(View.VISIBLE);
                            textViewResultado2.setText(label2 + " : " + agua + unidades);
                            textViewResultado2.setVisibility(View.VISIBLE);
                        }
                        break;
                    case SC_NINOS://Superficie corporal en Niños
                        if(!sistema_metrico_flag){
                            Param1 = Tools.LibrasToKilos(Param1);
                            Param2 = Tools.InchesToCentimetros(Param2);
                        }
                        if(Param1 > MyValues.PESO_MAXIMO || Param2 > MyValues.TALLA_MAXIMA){
                            Toast.makeText(getApplicationContext(),getResources().getString(R.string.toast_error_medidas), Toast.LENGTH_LONG).show();
                            onOptionsItemSelected(opMenu.findItem(R.id.action_clean));
                            break;
                        }
                        else {
                            resultado = Formulas.ASCbyHaycock(Param1, Param2);
                            label1 = getResources().getString(R.string.label_superficie_corporal);
                            unidades=" m2";
                            textViewResultado1.setText(label1 + " : " + resultado + unidades);
                            textViewResultado1.setVisibility(View.VISIBLE);
                        }
                        break;
                    case SC_ADULTOS://Superficie corporal en Adultos
                        if(!sistema_metrico_flag){
                            Param1 = Tools.LibrasToKilos(Param1);
                            Param2 = Tools.InchesToCentimetros(Param2);
                        }
                        if(Param1 > MyValues.PESO_MAXIMO || Param2 > MyValues.TALLA_MAXIMA){
                            Toast.makeText(getApplicationContext(),getResources().getString(R.string.toast_error_medidas), Toast.LENGTH_LONG).show();
                            onOptionsItemSelected(opMenu.findItem(R.id.action_clean));
                            break;
                        }
                        else {
                            resultado = Formulas.ASCbyMollester(Param1, Param2);
                            label1 = getResources().getString(R.string.label_superficie_corporal);
                            unidades=" m2";
                            textViewResultado1.setText(label1 + " : " + resultado + unidades);
                            textViewResultado1.setVisibility(View.VISIBLE);
                        }
                        break;
                    case VELOCIDADGOTEO://Velocidad de Goteo
                        resultado = Formulas.VelocidadGoteo(Param1, Param2);
                        label1 = getResources().getString(R.string.formula_velocidad_goteo_title);
                        unidades=" gts/min";
                        textViewResultado1.setText(label1 + " : " + resultado + unidades);
                        textViewResultado1.setVisibility(View.VISIBLE);
                        break;
                    case VOLUMENINFUSION://
                        resultado = Formulas.VolumenInfusion(Param1, Param2);
                        label1 = getResources().getString(R.string.formula_volumen_infusion_title);
                        unidades=" ml";
                        textViewResultado1.setText(label1 + " : " + resultado + unidades);
                        textViewResultado1.setVisibility(View.VISIBLE);
                        break;
                    case TIEMPOINFUSION://
                        resultado = Formulas.TiempoInfusion(Param1, Param2);
                        label1 = getResources().getString(R.string.formula_tiempo_infusion_title);
                        unidades=" hr";
                        textViewResultado1.setText(label1 + " : " + resultado + unidades);
                        textViewResultado1.setVisibility(View.VISIBLE);
                        break;
                    case VOLUMEN_FARMACOS_UCI://
                        if(!sistema_metrico_flag){
                            Param1 = Tools.LibrasToKilos(Param1);
                        }
                        if(Param1 > MyValues.PESO_MAXIMO){
                            Toast.makeText(getApplicationContext(),getResources().getString(R.string.toast_error_medidas), Toast.LENGTH_LONG).show();
                            onOptionsItemSelected(opMenu.findItem(R.id.action_clean));
                            break;
                        }
                        else {
                            resultado = Formulas.VolumenDopamina(Param1, Param2);
                            label1 = getResources().getString(R.string.label_volumen_total);
                            unidades=" ml";
                            textViewResultado1.setText(label1 + " : " + resultado + unidades);
                            textViewResultado1.setVisibility(View.VISIBLE);
                        }
                        break;
                }

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
                editText1.requestFocus();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
