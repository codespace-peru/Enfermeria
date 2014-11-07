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


public class Escala6ParametrosActivity extends ActionBarActivity {

    private static final int SOFA=61;

    TextView textViewTitle,textViewResultado,textView1, textView2, textView3,textView4, textView5, textView6, textViewNotas;
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
    }

    private void UpdateRespuesta(){
        textViewResultado.setText(tipoEscala + " :  " + String.valueOf(Param1+Param2+Param3+Param4+Param5+Param6));
    }

    private void prepararData(int tipo){
        switch (tipo){
            case SOFA:
                textViewTitle.setText("SCORE SOFA");
                textView1.setText("Respiración: PO2/FiO2");
                textView2.setText("Coagulación: Plaquetas");
                textView3.setText("Hígado: Bilirrubina");
                textView4.setText("Cardiovascular: PAM");
                textView5.setText("SNC: Glasgow");
                textView6.setText("Renal: Creatinina");
                textViewNotas.setText("Notas");
                textViewNotas.setVisibility(View.VISIBLE);
                Param1=0; Param2=0; Param3=0; Param4=0; Param5=0; Param6=0;
                items1 = new String[]{"> 400 mmHg","< 400 mmHg", "< 300 mmHg", "< 200 mmHg", "< 100 mmHg"};
                items1Values = new int[]{0,1,2,3,4};
                items2 = new String[]{"> 150,000 mm3","< 150,000 mm3","< 100,000 mm3","< 50,000 mm3","< 20,000 mm3"};
                items2Values = new int[]{0,1,2,3,4};
                items3 = new String[]{"> 1.2 mg/dl","1.2 - 1.9 mg/dl","2.0 - 5.9 mg/dl","6.0 - 11.9 mg/dl","> 12.0 mg/dl"};
                items3Values = new int[]{0,1,2,3,4};
                items4 = new String[]{">= 70 mmHg"," < 70 mmHg","Dopamina <= 5 y/o Dobutamina","Dopamina > 5 y/o Norepinefrina < 0.1","Dopamina > 15 y/o Norepinefrina > 0.1"};
                items4Values = new int[]{0,1,2,3,4};
                items5 = new String[]{"15","13 - 14","10 - 12","6 - 9","< 6"};
                items5Values = new int[]{0,1,2,3,4};
                items6 = new String[]{"< 1.2 mg/dl","1.2 - 1.9 mg/dl","2.0 - 3.4 mg/dl","3.5 - 4.9 mg/dl","> 5.0 mg/dl"};
                items6Values = new int[]{0,1,2,3,4};
                tipoEscala="SOFA";
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
