package pe.com.codespace.nurse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TabHost;
import android.widget.TextView;

import static pe.com.codespace.nurse.MyValues.*;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import pe.com.codespace.nurse.AnalyticsApplication.TrackerName;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;


public class MainActivity extends AppCompatActivity {

    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    AdapterExpandableList myAdapter;
    ExpandableListView myExpand;
    ExpandableListView.OnGroupClickListener onGroupClick;
    ExpandableListView.OnChildClickListener onChildClick;
    int tipo = 1;
    String tab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setIcon(R.drawable.ic_launcher);
        }

        PrefSingleton.getInstance().Initialize(getApplicationContext());
        boolean flag = PrefSingleton.getInstance().getPreference(MyValues.SISTEMA_METRICO,false);
        if(!flag){
            PrefSingleton.getInstance().putPreference(MyValues.SISTEMA_METRICO,true);
            PrefSingleton.getInstance().putPreference(MyValues.SISTEMA_INGLES,false);
        }

        final TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("escalas");
        tabSpec.setContent(R.id.tabEscalas);
        tab = getResources().getString(R.string.tab1); // Escalas
        tabSpec.setIndicator(tab);
        tabHost.addTab(tabSpec);
        tabSpec = tabHost.newTabSpec("formulas");
        tabSpec.setContent(R.id.tabFormulas);
        tab = getResources().getString(R.string.tab2); // Formulas
        tabSpec.setIndicator(tab);
        tabHost.addTab(tabSpec);

        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
            TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextSize(20);
        }

        myExpand = (ExpandableListView) findViewById(R.id.myExpandListEscalas);
        prepararData(tipo);
        myAdapter = new AdapterExpandableList(this, listDataHeader, listDataChild);
        myExpand.setAdapter(myAdapter);

        //Agregar el adView
        AdView adView = (AdView)this.findViewById(R.id.adViewMain);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        //Analytics
        Tracker tracker = ((AnalyticsApplication)  getApplication()).getTracker(TrackerName.APP_TRACKER);
        String nameActivity = getApplicationContext().getPackageName() + "." + this.getClass().getSimpleName();
        tracker.setScreenName(nameActivity);
        tracker.enableAdvertisingIdCollection(true);
        tracker.send(new HitBuilders.AppViewBuilder().build());

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                tipo = tabHost.getCurrentTab() + 1;
                prepararData(tipo);
                switch (tipo) {
                    case ESCALAS:
                        myExpand = (ExpandableListView) findViewById(R.id.myExpandListEscalas);
                        break;
                    case FORMULAS:
                        myExpand = (ExpandableListView) findViewById(R.id.myExpandListFormulas);
                        break;
                }
                myAdapter = new AdapterExpandableList(getApplicationContext(), listDataHeader, listDataChild);
                myExpand.setAdapter(myAdapter);
                myExpand.setGroupIndicator(null);
                myExpand.setOnGroupClickListener(onGroupClick);
                myExpand.setOnChildClickListener(onChildClick);

            }
        });

        onGroupClick = new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                return false;
            }
        };

        myExpand.setOnGroupClickListener(onGroupClick);

        onChildClick = new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView eListView, View view, int groupPosition, int childPosition, long id) {
                Intent intent = new Intent(getApplicationContext(), Input2ParametrosActivity.class);
                switch (tipo){
                    case ESCALAS:
                        switch (groupPosition){
                            case 0://Escalas en UCI
                                switch (childPosition+1){
                                    case 1://Glasgow
                                        intent = new Intent(getApplicationContext(),Escala3ParametrosActivity.class);
                                        intent.putExtra("numeroEscala",GLASGOW);
                                        break;
                                    case 2://Ramsay
                                        intent = new Intent(getApplicationContext(),EscalaNoParametrosActivity.class);
                                        intent.putExtra("numeroEscala",RAMSAY);
                                        break;
                                    case 3://RASS
                                        intent = new Intent(getApplicationContext(),EscalaNoParametrosActivity.class);
                                        intent.putExtra("numeroEscala",RASS);
                                        break;
                                    case 4://SOFA
                                        intent = new Intent(getApplicationContext(),Escala6ParametrosActivity.class);
                                        intent.putExtra("numeroEscala",SOFA);
                                        break;
                                    case 5://APACHE
                                        intent = new Intent(getApplicationContext(),ApacheActivity.class);
                                        intent.putExtra("numeroEscala",APACHE);
                                        break;
                                }
                                startActivity(intent);
                                break;
                            case 1://Escalas en Geriatria
                                switch (childPosition+1){
                                    case 1://Norton
                                        intent = new Intent(getApplicationContext(),Escala5ParametrosActivity.class);
                                        intent.putExtra("numeroEscala",NORTON);
                                        break;
                                    case 2://Downton
                                        intent = new Intent(getApplicationContext(),Escala5ParametrosActivity.class);
                                        intent.putExtra("numeroEscala",DOWNTON);
                                        break;
                                    case 3://Barthel
                                        intent = new Intent(getApplicationContext(),Escala10ParametrosActivity.class);
                                        intent.putExtra("numeroEscala",BARTHEL);
                                        break;
                                }
                                startActivity(intent);
                                break;
                            case 2://Escalas en Periatria
                                switch (childPosition+1){
                                    case 1://Test de Apgar
                                        intent = new Intent(getApplicationContext(),Escala5ParametrosActivity.class);
                                        intent.putExtra("numeroEscala",APGAR);
                                        break;
                                    case 2://Test de Silverman
                                        intent = new Intent(getApplicationContext(),Escala5ParametrosActivity.class);
                                        intent.putExtra("numeroEscala",SILVERMAN);
                                        break;
                                }
                                startActivity(intent);
                                break;
                            case 3://Otras Escalas
                                switch (childPosition+1){
                                    case 1://Apgar Familiar
                                        intent = new Intent(getApplicationContext(),Escala5ParametrosActivity.class);
                                        intent.putExtra("numeroEscala",APGAR_FAM);
                                        break;
                                }
                                startActivity(intent);
                                break;
                        }
                        break;
                    case FORMULAS:
                        switch (groupPosition){
                            case 0://Fórmulas Antropométricas
                                switch (childPosition+1){
                                    case 1:
                                        intent = new Intent(getApplicationContext(), Input2ParametrosActivity.class);
                                        intent.putExtra("formula",IMC);
                                        startActivity(intent);
                                        break;
                                    case 2:
                                        intent = new Intent(getApplicationContext(), Input2ParametrosActivity.class);
                                        intent.putExtra("formula",PERDIDAS);
                                        startActivity(intent);
                                        break;
                                    case 3:
                                        intent = new Intent(getApplicationContext(), Input2ParametrosActivity.class);
                                        intent.putExtra("formula",SC_NINOS);
                                        startActivity(intent);
                                        break;
                                    case 4:
                                        intent = new Intent(getApplicationContext(), Input2ParametrosActivity.class);
                                        intent.putExtra("formula",SC_ADULTOS);
                                        startActivity(intent);
                                        break;
                                }
                                break;
                            case 1://Fórmulas para Sueroterapia
                                switch (childPosition+8){
                                    case 8:// Conversion Dextrosa
                                        intent = new Intent(getApplicationContext(), InputConvertSolutionActivity.class);
                                        intent.putExtra("formula",CONVERSIONDEXTROSA);
                                        startActivity(intent);
                                        break;
                                    case 9:// Conversion Cloruro
                                        intent = new Intent(getApplicationContext(), InputConvertSolutionActivity.class);
                                        intent.putExtra("formula",CONVERSIONCLORURO);
                                        startActivity(intent);
                                        break;
                                    case 10:// Velocidad de Goteo
                                        intent = new Intent(getApplicationContext(), Input2ParametrosActivity.class);
                                        intent.putExtra("formula",VELOCIDADGOTEO);
                                        startActivity(intent);
                                        break;
                                    case 11:// Velocidad de Goteo
                                        intent = new Intent(getApplicationContext(), Input2ParametrosActivity.class);
                                        intent.putExtra("formula",VOLUMENINFUSION);
                                        startActivity(intent);
                                        break;
                                    case 12:// Velocidad de Goteo
                                        intent = new Intent(getApplicationContext(), Input2ParametrosActivity.class);
                                        intent.putExtra("formula",TIEMPOINFUSION);
                                        startActivity(intent);
                                        break;
                                }

                                break;
                            case 2://Fórmulas para Fármacos en UCI
                                switch (childPosition+15){
                                    case 15:// Calculo de Dopamina y Dobutamina
                                        intent = new Intent(getApplicationContext(), Input4ParametrosActivity.class);
                                        intent.putExtra("formula",GOTEO_FARMACOS_UCI); // Empieza en el 15
                                        startActivity(intent);
                                        break;
                                    case 16:// Cálculo del Volumen total Dopamina y Dobutamina
                                        intent = new Intent(getApplicationContext(), Input2ParametrosActivity.class);
                                        intent.putExtra("formula",VOLUMEN_FARMACOS_UCI);
                                        startActivity(intent);
                                        break;
                                }
                                break;
                            case 3://GinecoObstetricas
                                switch (childPosition+22){
                                    case 22:// Fecha Probable de Parto
                                        intent = new Intent(getApplicationContext(), FechasActivity.class);
                                        intent.putExtra("formula",FPP); // Empieza en el 22
                                        startActivity(intent);
                                        break;
                                    case 23:
                                        break;
                                }
                                break;
                            case 4://Otras
                                switch (childPosition+29){
                                    case 29:// Regla de tres
                                        intent = new Intent(getApplicationContext(), RegladetresActivity.class);
                                        intent.putExtra("formula",REGLATRES); // Empieza en el 29
                                        startActivity(intent);
                                        break;
                                    case 30:
                                        break;
                                }
                                break;
                        }
                        break;
                }
                return false;
            }
        };

        myExpand.setOnChildClickListener(onChildClick);
    }



    private void prepararData(int tipo){
        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();
        List<String> child;
        switch(tipo){
            case ESCALAS:
                listDataHeader.add(getResources().getString(R.string.scale_uci_title));
                listDataHeader.add(getResources().getString(R.string.scale_geriatria_title));
                listDataHeader.add(getResources().getString(R.string.scale_pediatria_title));
                listDataHeader.add(getResources().getString(R.string.scale_otras_title));
                //Hijos del Primer Padre
                child = new ArrayList<>();
                child.add(getResources().getString(R.string.scale_glasgow_title));
                child.add(getResources().getString(R.string.scale_ramsay_title));
                child.add(getResources().getString(R.string.scale_rass_title));
                child.add(getResources().getString(R.string.score_sofa_title));
                child.add(getResources().getString(R.string.score_apache_title));
                listDataChild.put(listDataHeader.get(0),child);

                //Hijos del Segundo Padre
                child = new ArrayList<>();
                child.add(getResources().getString(R.string.scale_norton_title));
                child.add(getResources().getString(R.string.scale_downton_title));
                child.add(getResources().getString(R.string.scale_barthel_title));
                listDataChild.put(listDataHeader.get(1), child);

                //Hijos del Tercer Padre
                child = new ArrayList<>();
                child.add(getResources().getString(R.string.test_apgar_title)); // tipo=21
                child.add(getResources().getString(R.string.test_silverman_title)); //tipo=22
                listDataChild.put(listDataHeader.get(2), child);

                //Hijos del Cuarto Padre
                child = new ArrayList<>();
                child.add(getResources().getString(R.string.test_apgarfam_title)); // tipo=31
                listDataChild.put(listDataHeader.get(3), child);
                break;
            case FORMULAS:
                listDataHeader.add(getResources().getString(R.string.formula_antropom_title));
                listDataHeader.add(getResources().getString(R.string.formula_suero_title));
                listDataHeader.add(getResources().getString(R.string.formula_medicamentos_title));
                listDataHeader.add(getResources().getString(R.string.formula_obstetricia_title));
                listDataHeader.add(getResources().getString(R.string.formula_otras_title));
                //Hijos del Primer Padre
                child = new ArrayList<>();
                child.add(getResources().getString(R.string.formula_imc_title)); // tipo=1
                child.add(getResources().getString(R.string.formula_perdidas_insensibles_title)); // tipo=2
                child.add(getResources().getString(R.string.formula_superficie_corporal_ninos_title)); // tipo=3
                child.add(getResources().getString(R.string.formula_superficie_corporal_adultos_title)); // tipo=4
                //child.add("Test con 3 Parámetros"); // tipo=5
                listDataChild.put(listDataHeader.get(0),child);

                //Hijos del Segundo Padre
                child = new ArrayList<>();
                child.add(getResources().getString(R.string.formula_conversion_dextrosa_title)); //tipo=8
                child.add(getResources().getString(R.string.formula_conversion_cloruro_title)); //tipo=9
                child.add(getResources().getString(R.string.formula_velocidad_goteo_title)); //tipo=10
                child.add(getResources().getString(R.string.formula_volumen_infusion_title)); //tipo=11
                child.add(getResources().getString(R.string.formula_tiempo_infusion_title)); // tipo=12
                listDataChild.put(listDataHeader.get(1), child);

                //Hijos del Tercer Padre
                child = new ArrayList<>();
                child.add(getResources().getString(R.string.formula_velocidad_inotropicos_title)); //tipo=15
                child.add(getResources().getString(R.string.formula_volumen_inotropicos_title)); //tipo=16
                listDataChild.put(listDataHeader.get(2), child);

                //Hijos del Cuarto Padre
                child = new ArrayList<>();
                child.add(getResources().getString(R.string.formula_fecha_parto_title));
                listDataChild.put(listDataHeader.get(3), child);

                //Hijos del Quinto Padre
                child = new ArrayList<>();
                child.add(getResources().getString(R.string.formula_regla_tres_title));
                listDataChild.put(listDataHeader.get(4), child);
                break;
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_share:
                Social.share(this,getResources().getString(R.string.share_title), getResources().getString(R.string.share_description) + " " +  Uri.parse("https://play.google.com/store/apps/details?id=pe.com.codespace.nurse"));
                break;
            case R.id.action_settings:
                Intent intent = new Intent(getApplicationContext(), PreferencesActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
