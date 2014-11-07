package pe.com.codespace.nurse;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;


public class Escala10ParametrosActivity extends ActionBarActivity {

    private static final int BARTHEL=101;


    TextView textViewTitle,textViewResultado,textView1, textView2, textView3,textView4, textView5, textView6, textView7, textView8,textView9, textView10, textViewNotas;
    TextView textViewItemEscala1, textViewItemEscala2, textViewItemEscala3,textViewItemEscala4, textViewItemEscala5, textViewItemEscala6, textViewItemEscala7, textViewItemEscala8,textViewItemEscala9, textViewItemEscala10;
    Spinner dropdownItem1,dropdownItem2,dropdownItem3,dropdownItem4,dropdownItem5,dropdownItem6,dropdownItem7,dropdownItem8,dropdownItem9,dropdownItem10;
    int Param1, Param2, Param3, Param4, Param5, Param6, Param7, Param8, Param9, Param10;
    String[] items1, items2, items3, items4, items5, items6, items7, items8, items9, items10;
    int[] items1Values, items2Values, items3Values, items4Values, items5Values, items6Values, items7Values, items8Values, items9Values, items10Values;
    int numeroEscala;
    String tipoEscala;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escala10parametros);
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

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, R.layout.spinner_item, items1);
        dropdownItem1.setAdapter(adapter1);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.spinner_item, items2);
        dropdownItem2.setAdapter(adapter2);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, R.layout.spinner_item, items3);
        dropdownItem3.setAdapter(adapter3);

        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this, R.layout.spinner_item, items4);
        dropdownItem4.setAdapter(adapter4);

        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(this, R.layout.spinner_item, items5);
        dropdownItem5.setAdapter(adapter5);

        ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(this, R.layout.spinner_item, items6);
        dropdownItem6.setAdapter(adapter6);

        ArrayAdapter<String> adapter7 = new ArrayAdapter<String>(this, R.layout.spinner_item, items7);
        dropdownItem7.setAdapter(adapter7);

        ArrayAdapter<String> adapter8 = new ArrayAdapter<String>(this, R.layout.spinner_item, items8);
        dropdownItem8.setAdapter(adapter8);

        ArrayAdapter<String> adapter9 = new ArrayAdapter<String>(this, R.layout.spinner_item, items9);
        dropdownItem9.setAdapter(adapter9);

        ArrayAdapter<String> adapter10 = new ArrayAdapter<String>(this, R.layout.spinner_item, items10);
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
    }

    private void UpdateRespuesta(){
        textViewResultado.setText(tipoEscala + " :  " + String.valueOf(Param1+Param2+Param3+Param4+Param5+Param6+Param7+Param8+Param9+Param10));
    }

    private void prepararData(int tipo){
        switch (tipo){
            case BARTHEL:
                textViewTitle.setText("Escala de Barthel");
                textView1.setText("Alimentación");
                textView2.setText("Bañarse");
                textView3.setText("Arreglarse");
                textView4.setText("Vestirse");
                textView5.setText("Deposición");
                textView6.setText("Micción");
                textView7.setText("Uso del retrete");
                textView8.setText("Traslado sillón/cama");
                textView9.setText("Deambulación");
                textView10.setText("Movilidad en escaleras");
                textViewNotas.setText("Notas");
                textViewNotas.setVisibility(View.VISIBLE);
                Param1=10; Param2=5; Param3=5; Param4=10; Param5=10; Param6=10; Param7=10; Param8=15; Param9=10; Param10=10;
                items1 = new String[]{"Independiente","Necesita ayuda", "Dependiente"};
                items1Values = new int[]{10,5,0};
                items2 = new String[]{"Independiente", "Dependiente"};
                items2Values = new int[]{5,0};
                items3 = new String[]{"Independiente", "Necesita ayuda"};
                items3Values = new int[]{5,0};
                items4 = new String[]{"Independiente","Necesita ayuda","Dependiente"};
                items4Values = new int[]{10,5,0};
                items5 = new String[]{"Continente", "Incontinencia ocasional", "Incontinencia completa"};
                items5Values = new int[]{10,5,0};
                items6 = new String[]{"Continente", "Incontinencia ocasional", "Incontinencia completa"};
                items6Values = new int[]{10,5,0};
                items7 = new String[]{"Independiente", "Necesita algo de ayuda", "Dependiente"};
                items7Values = new int[]{10,5,0};
                items8 = new String[]{"Independiente", "Mínima ayuda", "Gran ayuda", "Dependiente"};
                items8Values = new int[]{15,10,5,0};
                items9 = new String[]{"Independiente, camina al menos 50m", "Necesita ayuda o supervisión para caminar 50m","Independiente en silla de ruedas", "Dependiente"};
                items9Values = new int[]{15,10,5,0};
                items10 = new String[]{"Independiente", "Necesita algo de ayuda", "Dependiente"};
                items10Values = new int[]{10,5,0};
                tipoEscala="Indice de Barthel";
                break;

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.escala_glasgow, menu);
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
