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
import android.widget.ExpandableListView;


public class MainActivity extends ActionBarActivity implements ActionBar.TabListener {

    SectionsPagerAdapter mSectionsPagerAdapter;
    private static int tipo;
    ViewPager mViewPager;

    private static final int GLASGOW=31;
    private static final int RAMSAY=32;
    private static final int RASS=1;
    private static final int APGAR=51;
    private static final int APGAR_FAM=52;
    private static final int NORTON=53;
    private static final int DOWNTON=54;
    private static final int SOFA=61;
    private static final int BARTHEL=101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                tipo = position+1;
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }

        //forceTabs();
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

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            //tipo = position+1;
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
            }
            return null;
        }
    }

    public static class PlaceholderFragment extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";
        List<String> listDataHeader;
        HashMap<String, List<String>> listDataChild;
        AdapterExpandableList myAdapter;
        private final static int ESCALAS = 1;
        private final static int FORMULAS = 2;

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            tipo = getArguments().getInt(ARG_SECTION_NUMBER);
            prepararData(tipo);
            tipo = 1;
            final ExpandableListView myExpand;
            myAdapter = new AdapterExpandableList(getActivity(), listDataHeader, listDataChild);
            myExpand = (ExpandableListView) rootView.findViewById(R.id.myExpandList);
            myExpand.setAdapter(myAdapter);
            myExpand.setGroupIndicator(null);
            myExpand.expandGroup(0);
            myExpand.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
                int lastExpandedPosition = -1;
                @Override
                public void onGroupExpand(int pos) {
                    if(lastExpandedPosition != -1 && pos != lastExpandedPosition)
                        myExpand.collapseGroup(lastExpandedPosition);
                    lastExpandedPosition = pos;
                }
            });

            myExpand.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                @Override
                public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                    return false;
                }
            });

            myExpand.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                @Override
                public boolean onChildClick(ExpandableListView eListView, View view, int groupPosition, int childPosition, long id) {
                    Intent intent = new Intent(getActivity(), Input2ParametrosActivity.class);
                    switch (tipo){
                        case ESCALAS:
                            switch (groupPosition){
                                case 0://Escalas en UCI
                                    switch (childPosition+1){
                                        case 1://Glasgow
                                            intent = new Intent(getActivity(),Escala3ParametrosActivity.class);
                                            intent.putExtra("numeroEscala",GLASGOW);
                                            break;
                                        case 2://Ramsay
                                            //intent = new Intent(getActivity(),Escala5ParametrosActivity.class);
                                            //intent.putExtra("numeroEscala",RAMSAY);
                                            break;
                                        case 3://RASS
                                            intent = new Intent(getActivity(),EscalaNoParametrosActivity.class);
                                            intent.putExtra("numeroEscala",RASS);
                                            break;
                                        case 4://SOFA
                                            intent = new Intent(getActivity(),Escala6ParametrosActivity.class);
                                            intent.putExtra("numeroEscala",SOFA);
                                            break;
                                    }
                                    startActivity(intent);
                                    break;
                                case 1://Escalas en Geriatria
                                    switch (childPosition+1){
                                        case 1://Norton
                                            intent = new Intent(getActivity(),Escala5ParametrosActivity.class);
                                            intent.putExtra("numeroEscala",NORTON);
                                            break;
                                        case 2://Downton
                                            intent = new Intent(getActivity(),Escala5ParametrosActivity.class);
                                            intent.putExtra("numeroEscala",DOWNTON);
                                            break;
                                        case 3://Barthel
                                            intent = new Intent(getActivity(),Escala10ParametrosActivity.class);
                                            intent.putExtra("numeroEscala",BARTHEL);
                                            break;
                                    }
                                    startActivity(intent);
                                    break;
                                case 2://Escalas en Periatria
                                    switch (childPosition+1){
                                        case 1://Test de Apgar
                                            intent = new Intent(getActivity(),Escala5ParametrosActivity.class);
                                            intent.putExtra("numeroEscala",APGAR);
                                            break;
                                    }
                                    startActivity(intent);
                                    break;
                                case 3://Otras Escalas
                                    switch (childPosition+1){
                                        case 1://Apgar Familiar
                                            intent = new Intent(getActivity(),Escala5ParametrosActivity.class);
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
                                            intent = new Intent(getActivity(), Input3ParametrosActivity.class);
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
                                            intent = new Intent(getActivity(), InputConvertSolutionActivity.class);
                                            break;
                                        case 10: case 11:case 12:// Velocidad de Goteo y similares
                                            intent = new Intent(getActivity(), Input2ParametrosActivity.class);
                                            break;
                                    }
                                    intent.putExtra("formula",childPosition + 8); // Empieza en el 8
                                    startActivity(intent);
                                    break;
                                case 2://Fórmulas para Fármacos en UCI
                                    switch (childPosition+15){
                                        case 15:// Calculo de Dopamina y Dobutamina
                                            intent = new Intent(getActivity(), Input4ParametrosActivity.class);
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
            });

            return rootView;
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
    }

}
