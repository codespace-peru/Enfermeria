package pe.com.codespace.nurse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ExpandableListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    AdapterExpandableList myAdapter;
    ExpandableListView myExpand;
    ExpandableListView.OnGroupClickListener onGroupClick;
    ExpandableListView.OnChildClickListener onChildClick;
    private final static int ESCALAS = 1;
    private final static int FORMULAS = 2;
    int tipo = 1;

    private static final int GLASGOW=31;
    private static final int RASS=1001;
    private static final int RAMSAY=1002;
    private static final int APGAR=51;
    private static final int APGAR_FAM=52;
    private static final int NORTON=53;
    private static final int DOWNTON=54;
    private static final int SOFA=61;
    private static final int BARTHEL=101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getActionBar().setDisplayShowHomeEnabled(false);
        //getActionBar().setDisplayShowTitleEnabled(false);
        //this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
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
                                    case 1:case 2:case 3:case 4:
                                        break;
                                    case 5:
                                        intent = new Intent(getApplicationContext(), Input3ParametrosActivity.class);
                                        break;
                                    case 6:case 7:
                                        break;
                                }
                                intent.putExtra("formula",childPosition + 1); // Para las 7 primeras formulas
                                startActivity(intent);
                                break;
                            case 1://Fórmulas para Sueroterapia
                                switch (childPosition+8){
                                    case 8: case 9: // Conversion Dextrosa y NaCl
                                        intent = new Intent(getApplicationContext(), InputConvertSolutionActivity.class);
                                        break;
                                    case 10: case 11:case 12:// Velocidad de Goteo y similares
                                        intent = new Intent(getApplicationContext(), Input2ParametrosActivity.class);
                                        break;
                                }
                                intent.putExtra("formula",childPosition + 8); // Empieza en el 8
                                startActivity(intent);
                                break;
                            case 2://Fórmulas para Fármacos en UCI
                                switch (childPosition+15){
                                    case 15:// Calculo de Dopamina y Dobutamina
                                        intent = new Intent(getApplicationContext(), Input4ParametrosActivity.class);
                                        intent.putExtra("formula",childPosition + 15); // Empieza en el 15
                                        startActivity(intent);
                                        break;
                                    case 16:
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
                listDataHeader.add("Escalas en UCI");
                listDataHeader.add("Escalas en Geriatría");
                listDataHeader.add("Escalas en Pediatría");
                listDataHeader.add("Otras Escalas");
                //Hijos del Primer Padre
                child = new ArrayList<String>();
                child.add("Escala de Coma de Glasgow");
                child.add("Escala de Sedación de Ramsay"); //
                child.add("Escala RASS"); //
                child.add("Escala SOFA");
                listDataChild.put(listDataHeader.get(0),child);

                //Hijos del Segundo Padre
                child = new ArrayList<String>();
                child.add("Escala de Norton Modificado");
                child.add("Escala de Riesgo de Caídas de J.H.Downton");
                child.add("Escala de Independencia Funcional de Barthel");
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
                listDataHeader.add("Fórmulas Antropométricas");
                listDataHeader.add("Fórmulas para SueroTerapia");
                listDataHeader.add("Fórmulas para Preparación de Medicamentos");
                //Hijos del Primer Padre
                child = new ArrayList<String>();
                child.add("Cálculo del Indice de Masa Corporal"); // tipo=1
                child.add("Pérdidas Insensibles"); // tipo=2
                child.add("Superficie Corporal en Niños"); // tipo=3
                child.add("Superficie Corporal en Adultos"); // tipo=4
                child.add("Test con 3 Parámetros"); // tipo=5
                listDataChild.put(listDataHeader.get(0),child);

                //Hijos del Segundo Padre
                child = new ArrayList<String>();
                child.add("Conversión de Dextrosa"); //tipo=8
                child.add("Conversión de Cloruro de Sodio"); //tipo=9
                child.add("Cálculo de la Velocidad del Goteo"); //tipo=10
                child.add("Cálculo del Volumen Total a Infundir"); //tipo=11
                child.add("Cálculo del Tiempo de la Infusión"); // tipo=12
                listDataChild.put(listDataHeader.get(1), child);

                //Hijos del Tercer Padre
                child = new ArrayList<String>();
                child.add("Preparación de Fármacos en UCI"); //tipo=15
                child.add("Cálculo 2"); //tipo=16
                listDataChild.put(listDataHeader.get(2), child);
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
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
