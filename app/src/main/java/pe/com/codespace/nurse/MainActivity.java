package pe.com.codespace.nurse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TabHost;
import android.widget.TextView;

import static pe.com.codespace.nurse.MyValues.*;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class MainActivity extends ActionBarActivity {

    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    AdapterExpandableList myAdapter;
    ExpandableListView myExpand;
    ExpandableListView.OnGroupClickListener onGroupClick;
    ExpandableListView.OnChildClickListener onChildClick;
    int tipo = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("escalas");
        tabSpec.setContent(R.id.tabEscalas);
        tabSpec.setIndicator("Escalas");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("formulas");
        tabSpec.setContent(R.id.tabFormulas);
        tabSpec.setIndicator("Formulas");
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
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        List<String> child;
        String[] temp;
        switch(tipo){
            case ESCALAS:
                listDataHeader.add("UCI");
                listDataHeader.add("Geriatría");
                listDataHeader.add("Pediatría");
                listDataHeader.add("Otras Escalas");
                //Hijos del Primer Padre
                child = new ArrayList<String>();
                child.add("Escala de Coma de Glasgow");
                child.add("Escala de Sedación de Ramsay"); //
                child.add("Escala de Sedación RASS"); //
                child.add("Score SOFA");
                listDataChild.put(listDataHeader.get(0),child);

                //Hijos del Segundo Padre
                child = new ArrayList<String>();
                child.add("Escala de Norton Modificado");
                child.add("Escala de J.H.Downton");
                child.add("Escala de Barthel");
                listDataChild.put(listDataHeader.get(1), child);

                //Hijos del Tercer Padre
                child = new ArrayList<String>();
                child.add("Test de Apgar"); // tipo=21
                //child.add("Cálculo 2"); //tipo=22
                listDataChild.put(listDataHeader.get(2), child);

                //Hijos del Cuarto Padre
                child = new ArrayList<String>();
                child.add("Test de Apgar Familiar"); // tipo=31
                listDataChild.put(listDataHeader.get(3), child);
                break;
            case FORMULAS:
                listDataHeader.add("Antropométricas");
                listDataHeader.add("SueroTerapia");
                listDataHeader.add("Preparación de Medicamentos");
                listDataHeader.add("Gineco-Obstetricia");
                listDataHeader.add("Otras");
                //Hijos del Primer Padre
                child = new ArrayList<String>();
                child.add("Indice de Masa Corporal"); // tipo=1
                child.add("Pérdidas Insensibles"); // tipo=2
                child.add("Superficie Corporal en Niños"); // tipo=3
                child.add("Superficie Corporal en Adultos"); // tipo=4
                //child.add("Test con 3 Parámetros"); // tipo=5
                listDataChild.put(listDataHeader.get(0),child);

                //Hijos del Segundo Padre
                child = new ArrayList<String>();
                child.add("Conversión de Dextrosa"); //tipo=8
                child.add("Conversión de Cloruro de Sodio"); //tipo=9
                child.add("Velocidad del Goteo"); //tipo=10
                child.add("Volumen Total a Infundir"); //tipo=11
                child.add("Tiempo de Infusión"); // tipo=12
                listDataChild.put(listDataHeader.get(1), child);

                //Hijos del Tercer Padre
                child = new ArrayList<String>();
                child.add("Velocidad de Infusión Dopamina/Dobutamina"); //tipo=15
                child.add("Volumen total Dopamina/Dobutamina"); //tipo=16
                listDataChild.put(listDataHeader.get(2), child);

                //Hijos del Cuarto Padre
                child = new ArrayList<String>();
                child.add("Fecha Probable del Parto");
                listDataChild.put(listDataHeader.get(3), child);

                //Hijos del Quinto Padre
                child = new ArrayList<String>();
                child.add("Regla de Tres Simple");
                listDataChild.put(listDataHeader.get(4), child);
                break;
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        EasyTracker.getInstance(this).activityStart(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EasyTracker.getInstance(this).activityStop(this);
    }

}
