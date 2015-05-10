package pe.com.codespace.nurse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import static pe.com.codespace.nurse.MyValues.*;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;


public class ApacheActivity extends AppCompatActivity {

    TextView textViewTitle,textViewResultado,textView1, textView2, textView3,textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11, textView12,textView13, textView14, textViewNotas;
    TextView textViewItemEscala1, textViewItemEscala2, textViewItemEscala3,textViewItemEscala4, textViewItemEscala5, textViewItemEscala6, textViewItemEscala7, textViewItemEscala8,textViewItemEscala9, textViewItemEscala10, textViewItemEscala11, textViewItemEscala12, textViewItemEscala13, textViewItemEscala14;
    Spinner dropdownItem1,dropdownItem2,dropdownItem3,dropdownItem4,dropdownItem5,dropdownItem6,dropdownItem7,dropdownItem8,dropdownItem9,dropdownItem10, dropdownItem11,dropdownItem12,dropdownItem13,dropdownItem14;
    int Param1, Param2, Param3, Param4, Param5, Param6, Param7, Param8, Param9, Param10, Param11, Param12, Param13, Param14;
    String[] items1, items2, items3, items4, items5, items6, items7, items8, items9, items10, items11, items12, items13, items14;
    int[] items1Values, items2Values, items3Values, items4Values, items5Values, items6Values, items7Values, items8Values, items9Values, items10Values, items11Values, items12Values, items13Values, items14Values;
    Switch switch1, switch2, switch3;
    int numeroEscala;
    int FRA = 1; // indice para fallo renal agudo
    //int A,B,C;
    String tipoEscala;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_apache);
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
        textViewItemEscala11 = (TextView) findViewById(R.id.textViewItemEscala11);
        textViewItemEscala12 = (TextView) findViewById(R.id.textViewItemEscala12);
        textViewItemEscala13 = (TextView) findViewById(R.id.textViewItemEscala13);
        textViewItemEscala14 = (TextView) findViewById(R.id.textViewItemEscala14);
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
        textView11 = (TextView) findViewById(R.id.textViewVariable11);
        textView12 = (TextView) findViewById(R.id.textViewVariable12);
        textView13 = (TextView) findViewById(R.id.textViewVariable13);
        textView14 = (TextView) findViewById(R.id.textViewVariable14);
        dropdownItem1 = (Spinner) findViewById(R.id.spinnerVariable1);
        dropdownItem2 = (Spinner) findViewById(R.id.spinnerVariable2);
        dropdownItem3 = (Spinner) findViewById(R.id.spinnerVariable3);
        dropdownItem4 = (Spinner) findViewById(R.id.spinnerVariable4);
        dropdownItem5 = (Spinner) findViewById(R.id.spinnerVariable5);
        dropdownItem6 = (Spinner) findViewById(R.id.spinnerVariable6);
        dropdownItem7 = (Spinner) findViewById(R.id.spinnerVariable7);
        dropdownItem8 = (Spinner) findViewById(R.id.spinnerVariable8);
        dropdownItem9 = (Spinner) findViewById(R.id.spinnerVariable9);
        dropdownItem10 = (Spinner) findViewById(R.id.spinnerVariable10);
        dropdownItem11 = (Spinner) findViewById(R.id.spinnerVariable11);
        dropdownItem12 = (Spinner) findViewById(R.id.spinnerVariable12);
        dropdownItem13 = (Spinner) findViewById(R.id.spinnerVariable13);
        dropdownItem14 = (Spinner) findViewById(R.id.spinnerVariable14);
        switch1 = (Switch) findViewById(R.id.switch1);
        switch2 = (Switch) findViewById(R.id.switch2);
        switch3 = (Switch) findViewById(R.id.switch3);
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
        textViewItemEscala11.setText(String.valueOf(Param11));
        textViewItemEscala12.setText(String.valueOf(Param12));
        textViewItemEscala13.setText(String.valueOf(Param13));
        textViewItemEscala14.setText(String.valueOf(Param14));
        UpdateRespuesta();

        AdapterSpinner adapter1 = new AdapterSpinner(this,R.layout.spinner_item1, items1);
        dropdownItem1.setAdapter(adapter1);
        dropdownItem1.setSelection(3);

        AdapterSpinner adapter2 = new AdapterSpinner(this,R.layout.spinner_item1, items2);
        dropdownItem2.setAdapter(adapter2);
        dropdownItem2.setSelection(3);

        AdapterSpinner adapter3 = new AdapterSpinner(this,R.layout.spinner_item1, items3);
        dropdownItem3.setAdapter(adapter3);
        dropdownItem3.setSelection(3);

        AdapterSpinner adapter4 = new AdapterSpinner(this,R.layout.spinner_item1, items4);
        dropdownItem4.setAdapter(adapter4);
        dropdownItem4.setSelection(3);

        AdapterSpinner adapter5 = new AdapterSpinner(this,R.layout.spinner_item1, items5);
        dropdownItem5.setAdapter(adapter5);
        dropdownItem5.setSelection(0);

        AdapterSpinner adapter6 = new AdapterSpinner(this,R.layout.spinner_item1, items6);
        dropdownItem6.setAdapter(adapter6);
        dropdownItem6.setSelection(3);

        AdapterSpinner adapter7 = new AdapterSpinner(this,R.layout.spinner_item1, items7);
        dropdownItem7.setAdapter(adapter7);
        dropdownItem7.setSelection(4);

        AdapterSpinner adapter8 = new AdapterSpinner(this,R.layout.spinner_item1, items8);
        dropdownItem8.setAdapter(adapter8);
        dropdownItem8.setSelection(3);

        AdapterSpinner adapter9 = new AdapterSpinner(this,R.layout.spinner_item1, items9);
        dropdownItem9.setAdapter(adapter9);
        dropdownItem9.setSelection(3);

        AdapterSpinner adapter10 = new AdapterSpinner(this,R.layout.spinner_item1, items10);
        dropdownItem10.setAdapter(adapter10);
        dropdownItem10.setSelection(3);

        AdapterSpinner adapter11 = new AdapterSpinner(this,R.layout.spinner_item1, items11);
        dropdownItem11.setAdapter(adapter11);
        dropdownItem11.setSelection(3);

        AdapterSpinner adapter12 = new AdapterSpinner(this,R.layout.spinner_item1, items12);
        dropdownItem12.setAdapter(adapter12);
        dropdownItem12.setSelection(0);

        AdapterSpinner adapter13 = new AdapterSpinner(this,R.layout.spinner_item1, items13);
        dropdownItem13.setAdapter(adapter13);
        dropdownItem13.setSelection(0);

        AdapterSpinner adapter14 = new AdapterSpinner(this,R.layout.spinner_item1, items14);
        dropdownItem14.setAdapter(adapter14);
        dropdownItem14.setSelection(0);

        dropdownItem1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Param1 = items1Values[i];
                textViewItemEscala1.setText(String.valueOf(Param1));
                UpdateRespuesta();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        dropdownItem2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Param2 = items2Values[i];
                textViewItemEscala2.setText(String.valueOf(Param2));
                UpdateRespuesta();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        dropdownItem3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Param3 = items3Values[i];
                textViewItemEscala3.setText(String.valueOf(Param3));
                UpdateRespuesta();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        dropdownItem4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Param4 = items4Values[i];
                textViewItemEscala4.setText(String.valueOf(Param4));
                UpdateRespuesta();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        dropdownItem5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Param5 = items5Values[i];
                textViewItemEscala5.setText(String.valueOf(Param5));
                UpdateRespuesta();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        dropdownItem6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Param6 = items6Values[i];
                textViewItemEscala6.setText(String.valueOf(Param6));
                UpdateRespuesta();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        dropdownItem7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Param7 = items7Values[i];
                textViewItemEscala7.setText(String.valueOf(Param7));
                UpdateRespuesta();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        dropdownItem8.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Param8 = items8Values[i];
                textViewItemEscala8.setText(String.valueOf(Param8));
                UpdateRespuesta();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        dropdownItem9.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Param9 = items9Values[i];
                textViewItemEscala9.setText(String.valueOf(Param9));
                UpdateRespuesta();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        dropdownItem10.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Param10 = items10Values[i];
                textViewItemEscala10.setText(String.valueOf(Param10));
                UpdateRespuesta();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        dropdownItem11.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Param11 = items11Values[i];
                textViewItemEscala11.setText(String.valueOf(Param11));
                UpdateRespuesta();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        dropdownItem12.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Param12 = items12Values[i];
                textViewItemEscala12.setText(String.valueOf(Param12));
                UpdateRespuesta();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        dropdownItem13.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Param13 = items13Values[i];
                textViewItemEscala13.setText(String.valueOf(Param13));
                UpdateRespuesta();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        dropdownItem14.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Param14 = items14Values[i];
                textViewItemEscala14.setText(String.valueOf(Param14));
                UpdateRespuesta();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    FRA=2;
                    textViewItemEscala9.setText(String.valueOf(Param9*FRA));
                    UpdateRespuesta();
                }
                else{
                    FRA=1;
                    textViewItemEscala9.setText(String.valueOf(Param9*FRA));
                    UpdateRespuesta();
                }
            }
        });


        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    LinearLayout ll = (LinearLayout) findViewById(R.id.layout_item14);
                    textView14.setVisibility(View.VISIBLE);
                    ll.setVisibility(View.VISIBLE);
                    int index = dropdownItem14.getSelectedItemPosition();
                    Param14 = items14Values[index];
                    UpdateRespuesta();
                } else {
                    LinearLayout ll = (LinearLayout) findViewById(R.id.layout_item14);
                    textView14.setVisibility(View.INVISIBLE);
                    ll.setVisibility(View.GONE);
                    Param14 = 0;
                    UpdateRespuesta();
                }
            }
        });

        switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                ArrayAdapter<String> adapter;
                if (isChecked) {
                    textView5.setText("5. AaDO2");
                    items5 = new String[]{"> 499", "350 - 499", "200 - 349", "< 200"};
                    items5Values = new int[]{4,3,2,0};
                    adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_item1, items5);
                    dropdownItem5.setAdapter(adapter);
                    dropdownItem5.setSelection(3);
                } else {
                    textView5.setText("5. PaO2");
                    items5 = new String[]{">70", "61 - 70", "56 - 60", "<56"};
                    items5Values = new int[]{0,1,3,4};
                    adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_item1, items5);
                    dropdownItem5.setAdapter(adapter);
                    dropdownItem5.setSelection(0);
                }

            }
        });


        //Agregar el adView
        AdView adView = (AdView) this.findViewById(R.id.adViewEscalaApache);
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
        textViewResultado.setText(tipoEscala + " :  " + String.valueOf(Param1+Param2+Param3+Param4+Param5+Param6+Param7+Param8+(Param9*FRA)+Param10+Param11+Param12+Param13+Param14));
    }

    private void prepararData(int tipo){
        switch (tipo){
            case APACHE:
                textViewTitle.setText(getResources().getString(R.string.score_apache_title));
                textView1.setText(getResources().getString(R.string.apache_temp_rectal));
                textView2.setText(getResources().getString(R.string.apache_pam));
                textView3.setText(getResources().getString(R.string.apache_frec_cardiaca));
                textView4.setText(getResources().getString(R.string.apache_frec_respiratoria));
                textView5.setText(getResources().getString(R.string.apache_fio2));
                textView6.setText(getResources().getString(R.string.apache_ph));
                textView7.setText(getResources().getString(R.string.apache_sodio));
                textView8.setText(getResources().getString(R.string.apache_potasio));
                textView9.setText(getResources().getString(R.string.apache_creatinina));
                textView10.setText(getResources().getString(R.string.apache_hematocrito));
                textView11.setText(getResources().getString(R.string.apache_leucocitos));
                textView12.setText(getResources().getString(R.string.apache_glasgow));
                textView13.setText(getResources().getString(R.string.apache_edad));
                textView14.setText(getResources().getString(R.string.apache_motivo_admision));
                textViewNotas.setText(getResources().getString(R.string.descripcion_apache));
                textViewNotas.setVisibility(View.VISIBLE);
                Param1=0; Param2=0; Param3=0; Param4=0; Param5=0; Param6=0; Param7=0; Param8=0; Param9=0; Param10=0; Param11=0; Param12=0; Param13=0; Param14=0;
                items1 = new String[]{">= 41°C","39.0 - 40.9°C", "38.5 - 38.9°C", "36.0 - 38.4°C", "34.0 - 35.9°C", "32.0 - 33.9°C", "30.0 - 31.9°C", "<= 29.9°C"};
                items1Values = new int[]{4,3,1,0,1,2,3,4};
                items2 = new String[]{">= 160", "130 - 159","110 - 129","70 - 109","50 - 69","<= 49"};
                items2Values = new int[]{4,3,2,0,2,4};
                items3 = new String[]{">= 180", "140 - 179","110 - 139","70 - 109","55 - 69","40 - 54","<= 39"};
                items3Values = new int[]{4,3,2,0,2,3,4};
                items4 = new String[]{">= 50","35 - 49","25 - 34","12 - 24","10 - 11","6 - 9","<= 5"};
                items4Values = new int[]{4,3,1,0,1,2,4};
                items5 = new String[]{">70", "61 - 70", "56 - 60", "<56"};
                items5Values = new int[]{0,1,3,4};
                items6 = new String[]{">= 7.70","7.60 - 7.59","7.50 - 7.59","7.33 - 7.49","7.25 - 7.32","7.15 - 7.24","< 7.10"};
                items6Values = new int[]{4,3,1,0,2,3,4};
                items7 = new String[]{">= 180","160 - 179","155 - 159","150 - 154","130 - 149","120 - 129","111 - 119","<= 110"};
                items7Values = new int[]{4,3,2,1,0,2,3,4};
                items8 = new String[]{">= 7.0","6.0 - 6.9","5.5 - 5.9","3.5 - 5.4","3.0 - 3.4","2.5 - 2.9","< 2.5"};
                items8Values = new int[]{4,3,1,0,1,2,4};
                items9 = new String[]{">= 3.5","2.0 - 3.4","1.5 - 1.9","0.6 - 1.4","< 0.6"};
                items9Values = new int[]{4,3,2,0,2};
                items10 = new String[]{"<= 60","50 - 59.9","46 - 49.9","30 - 45.9","20 - 29.9","< 20"};
                items10Values = new int[]{4,2,1,0,2,4};
                items11 = new String[]{">= 40.0","20.0 - 39.9","15.0 - 19.9","3.0 - 14.9","1.0 - 2.9","< 1.0"};
                items11Values = new int[]{4,2,1,0,2,4};
                items12 = new String[]{"15","14","13","12","11","10","9","8","7","6","5","4","3"};
                items12Values = new int[]{0,1,2,3,4,5,6,7,8,9,10,11,12};
                items13 = new String[]{"<= 44","45 - 54","55 - 54","65 - 74","> 75"};
                items13Values = new int[]{0,2,3,5,6};
                items14 = new String[]{getResources().getString(R.string.apache_motivo1),getResources().getString(R.string.apache_motivo2),getResources().getString(R.string.apache_motivo3)};
                items14Values = new int[]{5,5,2};
                tipoEscala="Apache";
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
