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


public class Escala5ParametrosActivity extends AppCompatActivity {

    TextView textViewTitle,textViewResultado,textView1, textView2, textView3,textView4, textView5, textViewNotas;
    TextView textViewItemEscala1, textViewItemEscala2, textViewItemEscala3,textViewItemEscala4, textViewItemEscala5;
    Spinner dropdownItem1,dropdownItem2,dropdownItem3,dropdownItem4,dropdownItem5;
    int Param1, Param2, Param3, Param4, Param5;
    String[] items1, items2, items3, items4, items5;
    int[] items1Values, items2Values, items3Values, items4Values, items5Values;
    int numeroEscala;
    String tipoEscala;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_escala5parametros);
        numeroEscala = getIntent().getExtras().getInt("numeroEscala");

        textViewTitle = (TextView) findViewById(R.id.textViewTitleEscala);
        textViewResultado = (TextView) findViewById(R.id.textViewResultadoEscala);
        textViewNotas = (TextView) findViewById(R.id.textViewEscalaNotas);
        textViewItemEscala1 = (TextView) findViewById(R.id.textViewItemEscala1);
        textViewItemEscala2 = (TextView) findViewById(R.id.textViewItemEscala2);
        textViewItemEscala3 = (TextView) findViewById(R.id.textViewItemEscala3);
        textViewItemEscala4 = (TextView) findViewById(R.id.textViewItemEscala4);
        textViewItemEscala5 = (TextView) findViewById(R.id.textViewItemEscala5);
        textView1 = (TextView) findViewById(R.id.textViewVariable1);
        textView2 = (TextView) findViewById(R.id.textViewVariable2);
        textView3 = (TextView) findViewById(R.id.textViewVariable3);
        textView4 = (TextView) findViewById(R.id.textViewVariable4);
        textView5 = (TextView) findViewById(R.id.textViewVariable5);
        dropdownItem1 = (Spinner)findViewById(R.id.spinnerVariable1);
        dropdownItem2 = (Spinner)findViewById(R.id.spinnerVariable2);
        dropdownItem3 = (Spinner)findViewById(R.id.spinnerVariable3);
        dropdownItem4 = (Spinner)findViewById(R.id.spinnerVariable4);
        dropdownItem5 = (Spinner)findViewById(R.id.spinnerVariable5);
        prepararData(numeroEscala);
        textViewItemEscala1.setText(String.valueOf(Param1));
        textViewItemEscala2.setText(String.valueOf(Param2));
        textViewItemEscala3.setText(String.valueOf(Param3));
        textViewItemEscala4.setText(String.valueOf(Param4));
        textViewItemEscala5.setText(String.valueOf(Param5));
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

        //Agregar el adView
        AdView adView = (AdView)this.findViewById(R.id.adViewEscala5Param);
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
        textViewResultado.setText(tipoEscala + " :  " + String.valueOf(Param1+Param2+Param3+Param4+Param5));
    }

    private void prepararData(int tipo){
        switch (tipo){
            case APGAR:
                textViewTitle.setText(getResources().getString(R.string.test_apgar_title));
                textView1.setText(getResources().getString(R.string.label_apgar_frec_cardiaca));
                textView2.setText(getResources().getString(R.string.label_apgar_mov_respiratorios));
                textView3.setText(getResources().getString(R.string.label_apgar_color_piel));
                textView4.setText(getResources().getString(R.string.label_apgar_tono_muscular));
                textView5.setText(getResources().getString(R.string.label_apgar_respuesta_estimulos));
                textViewNotas.setText(getResources().getString(R.string.descripcion_scale_apgar));
                textViewNotas.setVisibility(View.VISIBLE);
                Param1=2; Param2=2; Param3=2; Param4=2; Param5=2;
                items1 = new String[]{getResources().getString(R.string.opc_apgar_mayor100),getResources().getString(R.string.opc_apgar_menor100), getResources().getString(R.string.opc_apgar_ausente)};
                items1Values = new int[]{2,1,0};
                items2 = new String[]{getResources().getString(R.string.opc_apgar_buena), getResources().getString(R.string.opc_apgar_irregular),getResources().getString(R.string.opc_apgar_ausente)};
                items2Values = new int[]{2,1,0};
                items3 = new String[]{getResources().getString(R.string.opc_apgar_rosada), getResources().getString(R.string.opc_apgar_normal), getResources().getString(R.string.opc_apgar_cianosis)};
                items3Values = new int[]{2,1,0};
                items4 = new String[]{getResources().getString(R.string.opc_apgar_movimientos),getResources().getString(R.string.opc_apgar_extremidades),getResources().getString(R.string.opc_apgar_flacidez)};
                items4Values = new int[]{2,1,0};
                items5 = new String[]{getResources().getString(R.string.opc_apgar_energica), getResources().getString(R.string.opc_apgar_muecas), getResources().getString(R.string.opc_apgar_nula)};
                items5Values = new int[]{2,1,0};
                tipoEscala="Apgar";
                break;
            case SILVERMAN:
                textViewTitle.setText(getResources().getString(R.string.test_silverman_title));
                textView1.setText(getResources().getString(R.string.label_silverman_aleteo));
                textView2.setText(getResources().getString(R.string.label_silverman_tiraje));
                textView3.setText(getResources().getString(R.string.label_silverman_quejido));
                textView4.setText(getResources().getString(R.string.label_silverman_retraccion));
                textView5.setText(getResources().getString(R.string.label_silverman_toracoabdominal));
                textViewNotas.setText(getResources().getString(R.string.descripcion_scale_silverman));
                textViewNotas.setVisibility(View.VISIBLE);
                Param1=0; Param2=0; Param3=0; Param4=0; Param5=0;
                items1 = new String[]{getResources().getString(R.string.opc_silverman_ausente),getResources().getString(R.string.opc_silverman_minima), getResources().getString(R.string.opc_silverman_marcada)};
                items1Values = new int[]{0,1,2};
                items2 = new String[]{getResources().getString(R.string.opc_silverman_ausente), getResources().getString(R.string.opc_silverman_apenas_visible),getResources().getString(R.string.opc_silverman_marcada)};
                items2Values = new int[]{0,1,2};
                items3 = new String[]{getResources().getString(R.string.opc_silverman_ausente), getResources().getString(R.string.opc_silverman_estetoscopio), getResources().getString(R.string.opc_silverman_audible)};
                items3Values = new int[]{0,1,2};
                items4 = new String[]{getResources().getString(R.string.opc_silverman_sin_retraccion),getResources().getString(R.string.opc_silverman_apenas_visible),getResources().getString(R.string.opc_silverman_marcada)};
                items4Values = new int[]{0,1,2};
                items5 = new String[]{getResources().getString(R.string.opc_silverman_sincronizado), getResources().getString(R.string.opc_silverman_torax_escaso), getResources().getString(R.string.opc_silverman_depresion_torax)};
                items5Values = new int[]{0,1,2};
                tipoEscala="Silverman";
                break;
            case APGAR_FAM:
                textViewTitle.setText(getResources().getString(R.string.test_apgarfam_title));
                textView1.setText(getResources().getString(R.string.apgarfam_pregunta1));
                textView2.setText(getResources().getString(R.string.apgarfam_pregunta2));
                textView3.setText(getResources().getString(R.string.apgarfam_pregunta3));
                textView4.setText(getResources().getString(R.string.apgarfam_pregunta4));
                textView5.setText(getResources().getString(R.string.apgarfam_pregunta5));
                textViewNotas.setText(getResources().getString(R.string.descripcion_scale_apgarfam));
                textViewNotas.setVisibility(View.VISIBLE);
                Param1=0; Param2=0; Param3=0; Param4=0; Param5=0;
                items1 = new String[]{getResources().getString(R.string.opc_apgarfam_casinunca),getResources().getString(R.string.opc_apgarfam_aveces), getResources().getString(R.string.opc_apgarfam_casisiempre)};
                items1Values = new int[]{0,1,2};
                items2 = new String[]{getResources().getString(R.string.opc_apgarfam_casinunca),getResources().getString(R.string.opc_apgarfam_aveces), getResources().getString(R.string.opc_apgarfam_casisiempre)};
                items2Values = new int[]{0,1,2};
                items3 = new String[]{getResources().getString(R.string.opc_apgarfam_casinunca),getResources().getString(R.string.opc_apgarfam_aveces), getResources().getString(R.string.opc_apgarfam_casisiempre)};
                items3Values = new int[]{0,1,2};
                items4 = new String[]{getResources().getString(R.string.opc_apgarfam_casinunca),getResources().getString(R.string.opc_apgarfam_aveces), getResources().getString(R.string.opc_apgarfam_casisiempre)};
                items4Values = new int[]{0,1,2};
                items5 = new String[]{getResources().getString(R.string.opc_apgarfam_casinunca),getResources().getString(R.string.opc_apgarfam_aveces), getResources().getString(R.string.opc_apgarfam_casisiempre)};
                items5Values = new int[]{0,1,2};
                tipoEscala = getResources().getString(R.string.label_apgarfam_tipoescala);
                break;
            case NORTON:
                textViewTitle.setText(getResources().getString(R.string.scale_norton_title));
                textView1.setText(getResources().getString(R.string.label_norton_estado_fisico));
                textView2.setText(getResources().getString(R.string.label_norton_estado_mental));
                textView3.setText(getResources().getString(R.string.label_norton_actividad));
                textView4.setText(getResources().getString(R.string.label_norton_movilidad));
                textView5.setText(getResources().getString(R.string.label_norton_incontinencia));
                textViewNotas.setText(getResources().getString(R.string.descripcion_scale_norton));
                textViewNotas.setVisibility(View.VISIBLE);
                Param1=4; Param2=4; Param3=4; Param4=4; Param5=4;
                items1 = new String[]{getResources().getString(R.string.opc_norton_bueno),getResources().getString(R.string.opc_norton_mediano), getResources().getString(R.string.opc_norton_regular),getResources().getString(R.string.opc_norton_muymalo)};
                items1Values = new int[]{4,3,2,1};
                items2 = new String[]{getResources().getString(R.string.opc_norton_alerta), getResources().getString(R.string.opc_norton_apatico),getResources().getString(R.string.opc_norton_confuso),getResources().getString(R.string.opc_norton_estuporoso)};
                items2Values = new int[]{4,3,2,1};
                items3 = new String[]{getResources().getString(R.string.opc_norton_ambulante), getResources().getString(R.string.opc_norton_camina), getResources().getString(R.string.opc_norton_sentado),getResources().getString(R.string.opc_norton_encama)};
                items3Values = new int[]{4,3,2,1};
                items4 = new String[]{getResources().getString(R.string.opc_norton_total), getResources().getString(R.string.opc_norton_disminuida), getResources().getString(R.string.opc_norton_muylimitada),getResources().getString(R.string.opc_norton_inmovil)};
                items4Values = new int[]{4,3,2,1};
                items5 = new String[]{getResources().getString(R.string.opc_norton_ninguna),getResources().getString(R.string.opc_norton_ocasional),getResources().getString(R.string.opc_norton_urinaria1),getResources().getString(R.string.opc_norton_urinaria2)};
                items5Values = new int[]{4,3,2,1};
                tipoEscala = getResources().getString(R.string.label_norton_tipoescala);
                break;
            case DOWNTON:
                textViewTitle.setText(getResources().getString(R.string.scale_downton_title));
                textView1.setText(getResources().getString(R.string.label_downton_caidas));
                textView2.setText(getResources().getString(R.string.label_downton_medicacion));
                textView3.setText(getResources().getString(R.string.label_downton_deficit_sensorial));
                textView4.setText(getResources().getString(R.string.label_downton_estado_mental));
                textView5.setText(getResources().getString(R.string.label_downton_deambulacion));
                textViewNotas.setText(getResources().getString(R.string.descripcion_scale_downton));
                textViewNotas.setVisibility(View.VISIBLE);
                Param1=0; Param2=0; Param3=0; Param4=0; Param5=0;
                items1 = new String[]{getResources().getString(R.string.opc_downton_no),getResources().getString(R.string.opc_downton_si)};
                items1Values = new int[]{0,1};
                items2 = new String[]{getResources().getString(R.string.opc_downton_ninguno), getResources().getString(R.string.opc_downton_sedantes),getResources().getString(R.string.opc_downton_diureticos),getResources().getString(R.string.opc_downton_hipotensores),getResources().getString(R.string.opc_downton_antiparkinsonianos),getResources().getString(R.string.opc_downton_antidepresivos),getResources().getString(R.string.opc_downton_otros_medicamentos)};
                items2Values = new int[]{0,1,1,1,1,1,1};
                items3 = new String[]{getResources().getString(R.string.opc_downton_ninguno), getResources().getString(R.string.opc_downton_alteraciones_visuales), getResources().getString(R.string.opc_downton_alteraciones_auditivas),getResources().getString(R.string.opc_downton_extremidades)};
                items3Values = new int[]{0,1,1,1};
                items4 = new String[]{getResources().getString(R.string.opc_downton_orientado), getResources().getString(R.string.opc_downton_confuso)};
                items4Values = new int[]{0,1};
                items5 = new String[]{getResources().getString(R.string.opc_downton_normal),getResources().getString(R.string.opc_downton_segura),getResources().getString(R.string.opc_downton_insegura),getResources().getString(R.string.opc_downton_imposible)};
                items5Values = new int[]{0,1,1,1};
                tipoEscala=getResources().getString(R.string.label_downton_puntos);
                break;
        }
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
