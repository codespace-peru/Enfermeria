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


public class Escala3ParametrosActivity extends AppCompatActivity {

    TextView textViewTitle,textViewResultado,textView1, textView2, textView3, textViewNotas;
    TextView textViewItemEscala1, textViewItemEscala2, textViewItemEscala3;
    Spinner dropdownItem1,dropdownItem2,dropdownItem3;
    int Param1, Param2, Param3;
    String[] items1, items2, items3;
    int[] items1Values, items2Values, items3Values;
    int numeroEscala;
    String tipoEscala;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_escala3parametros);
        numeroEscala= getIntent().getExtras().getInt("numeroEscala");
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }

        textViewTitle = (TextView) findViewById(R.id.textViewTitleEscala);
        textViewResultado = (TextView) findViewById(R.id.textViewResultadoEscala);
        textViewNotas = (TextView) findViewById(R.id.textViewEscalaNotas);
        textViewItemEscala1 = (TextView) findViewById(R.id.textViewItemEscala1);
        textViewItemEscala2 = (TextView) findViewById(R.id.textViewItemEscala2);
        textViewItemEscala3 = (TextView) findViewById(R.id.textViewItemEscala3);
        textView1 = (TextView) findViewById(R.id.textViewVariable1);
        textView2 = (TextView) findViewById(R.id.textViewVariable2);
        textView3 = (TextView) findViewById(R.id.textViewVariable3);
        dropdownItem1 = (Spinner)findViewById(R.id.spinnerVariable1);
        dropdownItem2 = (Spinner)findViewById(R.id.spinnerVariable2);
        dropdownItem3 = (Spinner)findViewById(R.id.spinnerVariable3);

        prepararData(numeroEscala);

        textViewItemEscala1.setText(String.valueOf(Param1));
        textViewItemEscala2.setText(String.valueOf(Param2));
        textViewItemEscala3.setText(String.valueOf(Param3));
        UpdateRespuesta();

        AdapterSpinner adapter1 = new AdapterSpinner(this,R.layout.spinner_item, items1);
        dropdownItem1.setAdapter(adapter1);

        AdapterSpinner adapter2 = new AdapterSpinner(this,R.layout.spinner_item, items2);
        dropdownItem2.setAdapter(adapter2);

        AdapterSpinner adapter3 = new AdapterSpinner(this,R.layout.spinner_item, items3);
        dropdownItem3.setAdapter(adapter3);

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

        //Agregar el adView
        AdView adView = (AdView)this.findViewById(R.id.adViewEscala3Param);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        //Analytics
        Tracker tracker = ((AnalyticsApplication)  getApplication()).getTracker(AnalyticsApplication.TrackerName.APP_TRACKER);
        String nameActivity = getApplicationContext().getPackageName() + "." + this.getClass().getSimpleName();
        tracker.setScreenName(nameActivity);
        tracker.enableAdvertisingIdCollection(true);
        tracker.send(new HitBuilders.AppViewBuilder().build());
    }

    private void prepararData(int tipo) {
        switch(tipo){
            case GLASGOW:
                textViewTitle.setText(getResources().getString(R.string.scale_glasgow_title));
                textView1.setText(getResources().getString(R.string.label_glasgow_ocular));
                textView2.setText(getResources().getString(R.string.label_glasgow_verbal));
                textView3.setText(getResources().getString(R.string.label_glasgow_motora));
                textViewNotas.setText(getResources().getString(R.string.descripcion_scale_glasgow));
                textViewNotas.setVisibility(View.VISIBLE);
                Param1=4; Param2=5; Param3=6;
                items1 = new String[]{getResources().getString(R.string.glasgow_espontanea),getResources().getString(R.string.glasgow_voz), getResources().getString(R.string.glasgow_dolor), getResources().getString(R.string.glasgow_ninguna)};
                items1Values = new int[]{4,3,2,1};
                items2 = new String[]{getResources().getString(R.string.glasgow_orientada),getResources().getString(R.string.glasgow_confusa), getResources().getString(R.string.glasgow_incoherente), getResources().getString(R.string.glasgow_incomprensible), getResources().getString(R.string.glasgow_ninguna)};
                items2Values = new int[]{5,4,3,2,1};
                items3 = new String[]{getResources().getString(R.string.glasgow_obedece_ordenes),getResources().getString(R.string.glasgow_localiza_tacto), getResources().getString(R.string.glasgow_localiza_dolor), getResources().getString(R.string.glasgow_decorticacion), getResources().getString(R.string.glasgow_descerebracion), getResources().getString(R.string.glasgow_ninguna)};
                items3Values = new int[]{6,5,4,3,2,1};
                tipoEscala="Glasgow";
                break;
            case 2:
                break;
        }

    }

    private void UpdateRespuesta(){
        textViewResultado.setText(tipoEscala + ": " + String.valueOf(Param1+Param2+Param3));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


}
