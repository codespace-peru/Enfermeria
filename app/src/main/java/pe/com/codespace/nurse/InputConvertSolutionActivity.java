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
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.AdapterView.*;
import static pe.com.codespace.nurse.MyValues.*;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;


public class InputConvertSolutionActivity extends AppCompatActivity {
    double Param1, Param2, Param3, Param4;
    int resultado;
    String label1="", label2="", unidades="";
    int tipo = -1;
    SharedPreferences sharedPreferences;
    EditText editText1 = null;
    TextView textViewResultado1 = null;
    TextView textViewResultado2 = null;
    TextView tvDescription = null;
    Spinner dropdownBase, dropdownMix, dropdownTarget;
    Menu opMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setIcon(R.drawable.ic_launcher);
        }
        setContentView(R.layout.activity_convertsolution);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        Intent intent = getIntent();
        tipo = intent.getExtras().getInt("formula");


        dropdownBase = (Spinner)findViewById(R.id.spinnerBase);
        dropdownMix = (Spinner)findViewById(R.id.spinnerMix);
        dropdownTarget = (Spinner)findViewById(R.id.spinnerTarget);
        editText1 = (EditText) findViewById(R.id.etInput1);

        String[] itemsTarget=null, itemsBase=null, itemsMix=null;
        double[] itemsTargetValues=null, itemsBaseValues=null, itemsMixValues=null;
        String agua = getResources().getString(R.string.opc_agua_destilada);
        String dextrosa5 = getResources().getString(R.string.opc_dextrosa5);
        String dextrosa10 = getResources().getString(R.string.opc_dextrosa10);
        String dextrosa20 = getResources().getString(R.string.opc_dextrosa20);
        String dextrosa25 = getResources().getString(R.string.opc_dextrosa25);
        String dextrosa33 = getResources().getString(R.string.opc_dextrosa33);
        String dextrosa30 = getResources().getString(R.string.opc_dextrosa30);
        String dextrosa50 = getResources().getString(R.string.opc_dextrosa50);
        String dextrosa70 = getResources().getString(R.string.opc_dextrosa70);

        String cloruro45 = getResources().getString(R.string.opc_cloruro45);
        String cloruro15 = getResources().getString(R.string.opc_cloruro15);
        String cloruro3 = getResources().getString(R.string.opc_cloruro3);
        String cloruro9 = getResources().getString(R.string.opc_cloruro9);
        String cloruro10 = getResources().getString(R.string.opc_cloruro10);
        String cloruro117 = getResources().getString(R.string.opc_cloruro117);
        String cloruro20 = getResources().getString(R.string.opc_cloruro20);

        switch (tipo){
            case CONVERSIONDEXTROSA:
                itemsBase = new String[]{agua,dextrosa5,dextrosa10};
                itemsBaseValues = new double[]{0,5,10};
                itemsMix = new String[]{dextrosa5, dextrosa10, dextrosa25, dextrosa33, dextrosa50, dextrosa70};
                itemsMixValues = new double[]{5,10,25,33.3,50,70};
                itemsTarget = new String[]{dextrosa5, dextrosa10, dextrosa20, dextrosa25, dextrosa30, dextrosa50};
                itemsTargetValues = new double[]{5,10,20,25,30,50};
                break;
            case CONVERSIONCLORURO:
                itemsTarget = new String[]{cloruro45, cloruro15, cloruro3, cloruro10, cloruro117};
                itemsTargetValues = new double[]{0.45,1.5,3,10,11.7};
                itemsBase = new String[]{agua,cloruro9};
                itemsBaseValues = new double[]{0,0.9};
                itemsMix = new String[]{cloruro9, cloruro10, cloruro117, cloruro20};
                itemsMixValues = new double[]{0.9,10,11.7,20};
                break;
        }

        AdapterSpinner adapter1 = new AdapterSpinner(this,R.layout.spinner_item, itemsBase);
        dropdownBase.setAdapter(adapter1);
        AdapterSpinner adapter2 = new AdapterSpinner(this,R.layout.spinner_item, itemsMix);
        dropdownMix.setAdapter(adapter2);
        AdapterSpinner adapter3 = new AdapterSpinner(this,R.layout.spinner_item, itemsTarget);
        dropdownTarget.setAdapter(adapter3);

        final double[] finalItemsBaseValues = itemsBaseValues;
        final double[] finalItemsMixValues = itemsMixValues;
        final double[] finalItemsTargetValues = itemsTargetValues;
        dropdownBase.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (finalItemsBaseValues != null) {
                    Param1 = finalItemsBaseValues[i];
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });
        dropdownMix.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (finalItemsMixValues != null) {
                    Param2 = finalItemsMixValues[i];
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });
        dropdownTarget.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (finalItemsTargetValues != null) {
                    Param3 = finalItemsTargetValues[i];
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });


        TextView tvTitleFormula = (TextView) findViewById(R.id.tvTitleFormula1);
        TextView tvParam1 = (TextView) findViewById(R.id.tvTargetSolucion);
        TextView tvParam2 = (TextView) findViewById(R.id.tvBaseSolucion);
        TextView tvParam3 = (TextView) findViewById(R.id.tvMixSolucion);
        TextView tvParam4 = (TextView) findViewById(R.id.tvTargetVolumen);
        textViewResultado1 = (TextView) findViewById(R.id.tvResultado1);
        textViewResultado2 = (TextView) findViewById(R.id.tvResultado2);
        tvDescription = (TextView) findViewById(R.id.tvDescription);
        switch (tipo){
            case CONVERSIONDEXTROSA:
                tvTitleFormula.setText(getResources().getString(R.string.formula_conversion_dextrosa_title));
                tvParam1.setText(getResources().getString(R.string.label_solucion_target));
                tvParam2.setText(getResources().getString(R.string.label_solucion_base1));
                tvParam3.setText(getResources().getString(R.string.label_solucion_base2));
                tvParam4.setText((getResources().getString(R.string.label_volumen_total)) + " (ml)");
                tvDescription.setText(getResources().getString(R.string.descripcion_conversion_soluciones));
                break;
            case CONVERSIONCLORURO:
                tvTitleFormula.setText(getResources().getString(R.string.formula_conversion_cloruro_title));
                tvParam1.setText(getResources().getString(R.string.label_solucion_target));
                tvParam2.setText(getResources().getString(R.string.label_solucion_base1));
                tvParam3.setText(getResources().getString(R.string.label_solucion_base2));
                tvParam4.setText((getResources().getString(R.string.label_volumen_total)) + " (ml)");
                tvDescription.setText(getResources().getString(R.string.descripcion_conversion_soluciones));
                break;
        }

        //Agregar el adView
        AdView adView = (AdView)this.findViewById(R.id.adViewConvertSolution);
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
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_calculate:
                String s1 = dropdownBase.getSelectedItem().toString();
                String s2 = dropdownMix.getSelectedItem().toString();
                String s4 = editText1.getText().toString();

                if(!Tools.isNumeric(s4)) {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.toast_ingrese_valores), Toast.LENGTH_SHORT).show();
                    return false;
                }
                if(!((Param1<=Param3 && Param3<=Param2) || (Param2<=Param3 && Param3<=Param1))){
                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.toast_error_valores),Toast.LENGTH_SHORT).show();
                    onOptionsItemSelected(opMenu.findItem(R.id.action_clean));
                }
                else {
                    Param4 = Integer.parseInt(editText1.getText().toString());
                    double temp = 0;
                    switch (tipo){
                        case CONVERSIONDEXTROSA: case CONVERSIONCLORURO://Conversion de Dextrosa y NaCl
                            resultado = Formulas.ConversionSoluciones(Param1, Param2, Param3, Param4);
                            temp = Param4 - resultado;
                            unidades=" ml";
                            label1= s1 + " : ";
                            label2= s2 + " : ";
                            textViewResultado2.setVisibility(View.VISIBLE);
                            textViewResultado2.setText(label2 + resultado + unidades);
                            break;
                    }
                    textViewResultado1.setText(label1 + Math.round(temp) + unidades);
                    textViewResultado1.setVisibility(View.VISIBLE);
                    InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    if(getCurrentFocus()!=null){
                        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                    }

                }
                break;
            case R.id.action_clean:
                textViewResultado1.setVisibility(View.INVISIBLE);
                textViewResultado2.setVisibility(View.GONE);
                editText1.setText("");
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
