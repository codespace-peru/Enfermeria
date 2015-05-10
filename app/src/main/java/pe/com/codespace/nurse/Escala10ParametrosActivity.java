package pe.com.codespace.nurse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import static pe.com.codespace.nurse.MyValues.*;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;


public class Escala10ParametrosActivity extends AppCompatActivity {

    TextView textViewTitle;
    TextView textViewResultado;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;
    TextView textView7;
    TextView textView8;
    TextView textView9;
    TextView textView10;
    TextView textViewNotas;
    TextView textViewItemEscala1;
    TextView textViewItemEscala2;
    TextView textViewItemEscala3;
    TextView textViewItemEscala4;
    TextView textViewItemEscala5;
    TextView textViewItemEscala6;
    TextView textViewItemEscala7;
    TextView textViewItemEscala8;
    TextView textViewItemEscala9;
    TextView textViewItemEscala10;
    Spinner dropdownItem1;
    Spinner dropdownItem2;
    Spinner dropdownItem3;
    Spinner dropdownItem4;
    Spinner dropdownItem5;
    Spinner dropdownItem6;
    Spinner dropdownItem7;
    Spinner dropdownItem8;
    Spinner dropdownItem9;
    Spinner dropdownItem10;
    int Param1, Param2, Param3, Param4, Param5, Param6, Param7, Param8, Param9, Param10;
    String[] items1, items2, items3, items4, items5, items6, items7, items8, items9, items10;
    int[] items1Values;
    int[] items2Values;
    int[] items3Values;
    int[] items4Values;
    int[] items5Values;
    int[] items6Values;
    int[] items7Values;
    int[] items8Values;
    int[] items9Values;
    int[] items10Values;
    int numeroEscala;
    String tipoEscala;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escala10parametros);
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        numeroEscala = getIntent().getExtras().getInt("numeroEscala");

        textViewTitle = (TextView) findViewById(R.id.textViewTitleEscala);
        textViewResultado = (TextView) findViewById(R.id.textViewResultadoEscala);
        textViewNotas = (TextView) findViewById(R.id.textViewEscalaNotas);
        textViewItemEscala1 = (TextView) findViewById(R.id.textViewItemEscala1);
        textViewItemEscala2 = (TextView) findViewById(R.id.textViewItemEscala2);
        textViewItemEscala3 = (TextView) findViewById(R.id.textViewItemEscala3);
        textViewItemEscala4 = (TextView) findViewById(R.id.textViewItemEscala4);
        textViewItemEscala5 = (TextView) findViewById(R.id.textViewItemEscala5);
        textViewItemEscala6 = (TextView) findViewById(R.id.textViewItemEscala6);
        textViewItemEscala7 = (TextView) findViewById(R.id.textViewItemEscala7);
        textViewItemEscala8 = (TextView) findViewById(R.id.textViewItemEscala8);
        textViewItemEscala9 = (TextView) findViewById(R.id.textViewItemEscala9);
        textViewItemEscala10 = (TextView) findViewById(R.id.textViewItemEscala10);
        textView1 = (TextView) findViewById(R.id.textViewVariable1);
        textView2 = (TextView) findViewById(R.id.textViewVariable2);
        textView3 = (TextView) findViewById(R.id.textViewVariable3);
        textView4 = (TextView) findViewById(R.id.textViewVariable4);
        textView5 = (TextView) findViewById(R.id.textViewVariable5);
        textView6 = (TextView) findViewById(R.id.textViewVariable6);
        textView7 = (TextView) findViewById(R.id.textViewVariable7);
        textView8 = (TextView) findViewById(R.id.textViewVariable8);
        textView9 = (TextView) findViewById(R.id.textViewVariable9);
        textView10 = (TextView) findViewById(R.id.textViewVariable10);
        dropdownItem1 = (Spinner)findViewById(R.id.spinnerVariable1);
        dropdownItem2 = (Spinner)findViewById(R.id.spinnerVariable2);
        dropdownItem3 = (Spinner)findViewById(R.id.spinnerVariable3);
        dropdownItem4 = (Spinner)findViewById(R.id.spinnerVariable4);
        dropdownItem5 = (Spinner)findViewById(R.id.spinnerVariable5);
        dropdownItem6 = (Spinner)findViewById(R.id.spinnerVariable6);
        dropdownItem7 = (Spinner)findViewById(R.id.spinnerVariable7);
        dropdownItem8 = (Spinner)findViewById(R.id.spinnerVariable8);
        dropdownItem9 = (Spinner)findViewById(R.id.spinnerVariable9);
        dropdownItem10 = (Spinner)findViewById(R.id.spinnerVariable10);
        prepararData(numeroEscala);
        textViewItemEscala1.setText(String.valueOf(Param1));
        textViewItemEscala2.setText(String.valueOf(Param2));
        textViewItemEscala3.setText(String.valueOf(Param3));
        textViewItemEscala4.setText(String.valueOf(Param4));
        textViewItemEscala5.setText(String.valueOf(Param5));
        textViewItemEscala6.setText(String.valueOf(Param6));
        textViewItemEscala7.setText(String.valueOf(Param7));
        textViewItemEscala8.setText(String.valueOf(Param8));
        textViewItemEscala9.setText(String.valueOf(Param9));
        textViewItemEscala10.setText(String.valueOf(Param10));
        UpdateRespuesta();

        AdapterSpinner adapter1 = new AdapterSpinner(this,R.layout.spinner_item, items1);
        dropdownItem1.setAdapter(adapter1);

        AdapterSpinner adapter2 = new AdapterSpinner(this,R.layout.spinner_item, items2);
        dropdownItem2.setAdapter(adapter2);

        AdapterSpinner adapter3 = new AdapterSpinner(this,R.layout.spinner_item, items3);
        dropdownItem3.setAdapter(adapter3);

        AdapterSpinner adapter4 = new AdapterSpinner(this,R.layout.spinner_item, items4);
        dropdownItem4.setAdapter(adapter4);

        AdapterSpinner adapter5 = new AdapterSpinner(this,R.layout.spinner_item, items5);
        dropdownItem5.setAdapter(adapter5);

        AdapterSpinner adapter6 = new AdapterSpinner(this,R.layout.spinner_item, items6);
        dropdownItem6.setAdapter(adapter6);

        AdapterSpinner adapter7 = new AdapterSpinner(this,R.layout.spinner_item, items7);
        dropdownItem7.setAdapter(adapter7);

        AdapterSpinner adapter8 = new AdapterSpinner(this,R.layout.spinner_item, items8);
        dropdownItem8.setAdapter(adapter8);

        AdapterSpinner adapter9 = new AdapterSpinner(this,R.layout.spinner_item, items9);
        dropdownItem9.setAdapter(adapter9);

        AdapterSpinner adapter10 = new AdapterSpinner(this,R.layout.spinner_item, items10);
        dropdownItem10.setAdapter(adapter10);

        dropdownItem1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Param1 = items1Values[i];
                textViewItemEscala1.setText(String.valueOf(Param1));
                UpdateRespuesta();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        dropdownItem2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Param2 = items2Values[i];
                textViewItemEscala2.setText(String.valueOf(Param2));
                UpdateRespuesta();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        dropdownItem3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Param3 = items3Values[i];
                textViewItemEscala3.setText(String.valueOf(Param3));
                UpdateRespuesta();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        dropdownItem4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Param4 = items4Values[i];
                textViewItemEscala4.setText(String.valueOf(Param4));
                UpdateRespuesta();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        dropdownItem5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Param5 = items5Values[i];
                textViewItemEscala5.setText(String.valueOf(Param5));
                UpdateRespuesta();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        dropdownItem6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Param6 = items6Values[i];
                textViewItemEscala6.setText(String.valueOf(Param6));
                UpdateRespuesta();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        dropdownItem7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Param7 = items7Values[i];
                textViewItemEscala7.setText(String.valueOf(Param7));
                UpdateRespuesta();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        dropdownItem8.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Param8 = items8Values[i];
                textViewItemEscala8.setText(String.valueOf(Param8));
                UpdateRespuesta();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        dropdownItem9.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Param9 = items9Values[i];
                textViewItemEscala9.setText(String.valueOf(Param9));
                UpdateRespuesta();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        dropdownItem10.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Param10 = items10Values[i];
                textViewItemEscala10.setText(String.valueOf(Param10));
                UpdateRespuesta();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        //Agregar el adView
        AdView adView = (AdView)this.findViewById(R.id.adViewEscala10Param);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        //Analytics
        Tracker tracker = ((AnalyticsApplication)  getApplication()).getTracker(AnalyticsApplication.TrackerName.APP_TRACKER);
        String nameActivity = getApplicationContext().getPackageName() + "." + this.getClass().getSimpleName();
        tracker.setScreenName(nameActivity);
        tracker.enableAdvertisingIdCollection(true);
        tracker.send(new HitBuilders.AppViewBuilder().build());
    }

    private void UpdateRespuesta(){
        textViewResultado.setText(tipoEscala + " :  " + String.valueOf(Param1+Param2+Param3+Param4+Param5+Param6+Param7+Param8+Param9+Param10));
    }

    private void prepararData(int tipo){
        switch (tipo){
            case BARTHEL:
                textViewTitle.setText(getResources().getString(R.string.scale_barthel_title));
                textView1.setText(getResources().getString(R.string.label_barthel_alimentacion));
                textView2.setText(getResources().getString(R.string.label_barthel_banarse));
                textView3.setText(getResources().getString(R.string.label_barthel_arreglarse));
                textView4.setText(getResources().getString(R.string.label_barthel_vestirse));
                textView5.setText(getResources().getString(R.string.label_barthel_deposicion));
                textView6.setText(getResources().getString(R.string.label_barthel_miccion));
                textView7.setText(getResources().getString(R.string.label_barthel_retrete));
                textView8.setText(getResources().getString(R.string.label_barthel_sillon));
                textView9.setText(getResources().getString(R.string.label_barthel_deambulacion));
                textView10.setText(getResources().getString(R.string.label_barthel_movilidad));
                textViewNotas.setText(getResources().getString(R.string.descripcion_scale_barthel));
                textViewNotas.setVisibility(View.VISIBLE);
                Param1=10; Param2=5; Param3=5; Param4=10; Param5=10; Param6=10; Param7=10; Param8=15; Param9=10; Param10=10;
                items1 = new String[]{getResources().getString(R.string.opc_barthel_independiente),getResources().getString(R.string.opc_barthel_necesita_ayuda), getResources().getString(R.string.opc_barthel_dependiente)};
                items1Values = new int[]{10,5,0};
                items2 = new String[]{getResources().getString(R.string.opc_barthel_independiente), getResources().getString(R.string.opc_barthel_dependiente)};
                items2Values = new int[]{5,0};
                items3 = new String[]{getResources().getString(R.string.opc_barthel_independiente), getResources().getString(R.string.opc_barthel_dependiente)};
                items3Values = new int[]{5,0};
                items4 = new String[]{getResources().getString(R.string.opc_barthel_independiente),getResources().getString(R.string.opc_barthel_necesita_ayuda),getResources().getString(R.string.opc_barthel_dependiente)};
                items4Values = new int[]{10,5,0};
                items5 = new String[]{getResources().getString(R.string.opc_barthel_continente), getResources().getString(R.string.opc_barthel_incontinencia_ocasional), getResources().getString(R.string.opc_barthel_incontinencia_completa)};
                items5Values = new int[]{10,5,0};
                items6 = new String[]{getResources().getString(R.string.opc_barthel_continente), getResources().getString(R.string.opc_barthel_incontinencia_ocasional), getResources().getString(R.string.opc_barthel_incontinencia_completa)};
                items6Values = new int[]{10,5,0};
                items7 = new String[]{getResources().getString(R.string.opc_barthel_independiente), getResources().getString(R.string.opc_barthel_necesita_algo_ayuda), getResources().getString(R.string.opc_barthel_dependiente)};
                items7Values = new int[]{10,5,0};
                items8 = new String[]{getResources().getString(R.string.opc_barthel_independiente), getResources().getString(R.string.opc_barthel_minima_ayuda), getResources().getString(R.string.opc_barthel_gran_ayuda), getResources().getString(R.string.opc_barthel_dependiente)};
                items8Values = new int[]{15,10,5,0};
                items9 = new String[]{getResources().getString(R.string.opc_barthel_independiente_50m), getResources().getString(R.string.opc_barthel_ayuda_50m),getResources().getString(R.string.opc_barthel_independiente_silla), getResources().getString(R.string.opc_barthel_dependiente)};
                items9Values = new int[]{15,10,5,0};
                items10 = new String[]{getResources().getString(R.string.opc_barthel_independiente), getResources().getString(R.string.opc_barthel_necesita_algo_ayuda), getResources().getString(R.string.opc_barthel_dependiente)};
                items10Values = new int[]{10,5,0};
                tipoEscala = getResources().getString(R.string.label_barthel_tipoescala);
                break;

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.escala_glasgow, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }



}
