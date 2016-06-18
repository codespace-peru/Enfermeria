package pe.com.codespace.nurse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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


public class Escala6ParametrosActivity extends AppCompatActivity {

    TextView textViewResultado,textView1, textView2, textView3,textView4, textView5, textView6, textViewNotas;
    TextView textViewItemEscala1, textViewItemEscala2, textViewItemEscala3,textViewItemEscala4, textViewItemEscala5, textViewItemEscala6;
    Spinner dropdownItem1,dropdownItem2,dropdownItem3,dropdownItem4,dropdownItem5, dropdownItem6;
    int Param1, Param2, Param3, Param4, Param5, Param6;
    String[] items1, items2, items3, items4, items5, items6;
    int[] items1Values, items2Values, items3Values, items4Values, items5Values, items6Values;
    int numeroEscala;
    String tipoEscala;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escala6parametros);

        Toolbar toolbar = (Toolbar) findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }

        numeroEscala = getIntent().getExtras().getInt(TIPO_ESCALAS);
        textViewResultado = (TextView) findViewById(R.id.textViewResultadoEscala);
        textViewNotas = (TextView) findViewById(R.id.textViewEscalaNotas);
        textViewItemEscala1 = (TextView) findViewById(R.id.textViewItemEscala1);
        textViewItemEscala2 = (TextView) findViewById(R.id.textViewItemEscala2);
        textViewItemEscala3 = (TextView) findViewById(R.id.textViewItemEscala3);
        textViewItemEscala4 = (TextView) findViewById(R.id.textViewItemEscala4);
        textViewItemEscala5 = (TextView) findViewById(R.id.textViewItemEscala5);
        textViewItemEscala6 = (TextView) findViewById(R.id.textViewItemEscala6);
        textView1 = (TextView) findViewById(R.id.textViewVariable1);
        textView2 = (TextView) findViewById(R.id.textViewVariable2);
        textView3 = (TextView) findViewById(R.id.textViewVariable3);
        textView4 = (TextView) findViewById(R.id.textViewVariable4);
        textView5 = (TextView) findViewById(R.id.textViewVariable5);
        textView6 = (TextView) findViewById(R.id.textViewVariable6);
        dropdownItem1 = (Spinner)findViewById(R.id.spinnerVariable1);
        dropdownItem2 = (Spinner)findViewById(R.id.spinnerVariable2);
        dropdownItem3 = (Spinner)findViewById(R.id.spinnerVariable3);
        dropdownItem4 = (Spinner)findViewById(R.id.spinnerVariable4);
        dropdownItem5 = (Spinner)findViewById(R.id.spinnerVariable5);
        dropdownItem6 = (Spinner)findViewById(R.id.spinnerVariable6);
        prepararData(numeroEscala);
        textViewItemEscala1.setText(String.valueOf(Param1));
        textViewItemEscala2.setText(String.valueOf(Param2));
        textViewItemEscala3.setText(String.valueOf(Param3));
        textViewItemEscala4.setText(String.valueOf(Param4));
        textViewItemEscala5.setText(String.valueOf(Param5));
        textViewItemEscala6.setText(String.valueOf(Param6));
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

        //Agregar el adView
        AdView adView = (AdView)this.findViewById(R.id.adViewEscala6Param);
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
        textViewResultado.setText(tipoEscala + " :  " + String.valueOf(Param1+Param2+Param3+Param4+Param5+Param6));
    }

    private void prepararData(int tipo){
        switch (tipo){
            case SOFA:
                if(getSupportActionBar()!=null) {
                    getSupportActionBar().setTitle(getResources().getString(R.string.score_sofa_title));
                }
                textView1.setText(getResources().getString(R.string.label_sofa_respiracion));
                textView2.setText(getResources().getString(R.string.label_sofa_coagulacion));
                textView3.setText(getResources().getString(R.string.label_sofa_higado));
                textView4.setText(getResources().getString(R.string.label_sofa_cardiovascular));
                textView5.setText(getResources().getString(R.string.label_sofa_snc));
                textView6.setText(getResources().getString(R.string.label_sofa_renal));
                textViewNotas.setText(getResources().getString(R.string.descripcion_scale_sofa));
                textViewNotas.setVisibility(View.VISIBLE);
                Param1=0; Param2=0; Param3=0; Param4=0; Param5=0; Param6=0;
                items1 = new String[]{"> 400 mmHg","<= 400 mmHg", "<= 300 mmHg", "<= 200 mmHg", "<= 100 mmHg"};
                items1Values = new int[]{0,1,2,3,4};
                items2 = new String[]{"> 150,000 mm3","<= 150,000 mm3","<= 100,000 mm3","<= 50,000 mm3","<= 20,000 mm3"};
                items2Values = new int[]{0,1,2,3,4};
                items3 = new String[]{"< 1.2 mg/dl","1.2 - 1.9 mg/dl","2.0 - 5.9 mg/dl","6.0 - 11.9 mg/dl","> 12.0 mg/dl"};
                items3Values = new int[]{0,1,2,3,4};
                items4 = new String[]{">= 70 mmHg"," < 70 mmHg",getResources().getString(R.string.opc_sofa_cardio1),getResources().getString(R.string.opc_sofa_cardio2),getResources().getString(R.string.opc_sofa_cardio3)};
                items4Values = new int[]{0,1,2,3,4};
                items5 = new String[]{"15","13 - 14","10 - 12","6 - 9","< 6"};
                items5Values = new int[]{0,1,2,3,4};
                items6 = new String[]{"< 1.2 mg/dl (<=109 µmol/L)","1.2-1.9 mg/dl (110-170µmol/L)","2-3.4 mg/dl (171-299µmol/L)","3.5-4.9 mg/dl (300-439µmol/L)","> 5 mg/dl (>=440µmol/L)"};
                items6Values = new int[]{0,1,2,3,4};
                tipoEscala="SOFA";
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
