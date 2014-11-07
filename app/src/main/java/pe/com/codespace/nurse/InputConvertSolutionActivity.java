package pe.com.codespace.nurse;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;


public class InputConvertSolutionActivity extends ActionBarActivity {
    double Param1, Param2, Param3, Param4;
    int resultado;
    String label1="", label2="", unidades="", descripcion="";
    int tipo = -1;
    EditText editText1 = null;
    TextView textViewResultado1 = null;
    TextView textViewResultado2 = null;
    TextView tvDescription = null;
    Spinner dropdownBase, dropdownMix, dropdownTarget;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convertsolution);

        Intent intent = getIntent();
        tipo = intent.getExtras().getInt("formula");


        dropdownBase = (Spinner)findViewById(R.id.spinnerBase);
        dropdownMix = (Spinner)findViewById(R.id.spinnerMix);
        dropdownTarget = (Spinner)findViewById(R.id.spinnerTarget);
        editText1 = (EditText) findViewById(R.id.etInput1);

        String[] itemsTarget=null, itemsBase=null, itemsMix=null;
        double[] itemsTargetValues=null, itemsBaseValues=null, itemsMixValues=null;

        switch (tipo){
            case 8:
                itemsBase = new String[]{"Agua Destilada","Dextrosa 5%", "Dextrosa 10%"};
                itemsBaseValues = new double[]{0,5,10};
                itemsMix = new String[]{"Dextrosa 5%", "Dextrosa 10%", "Dextrosa 25%", "Dextrosa 33.3%", "Dextrosa 50%", "Dextrosa 70%"};
                itemsMixValues = new double[]{5,10,25,33.3,50,70};
                itemsTarget = new String[]{"Dextrosa 5%", "Dextrosa 10%", "Dextrosa 20%", "Dextrosa 25%", "Dextrosa 30%", "Dextrosa 50%"};
                itemsTargetValues = new double[]{5,10,20,25,30,50};
                break;
            case 9:
                itemsTarget = new String[]{"NaCl 0.45%", "NaCl 1.5%", "NaCl 3%", "NaCl 10%", "NaCl 11.7%"};
                itemsTargetValues = new double[]{0.45,1.5,3,10,11.7};
                itemsBase = new String[]{"Agua Destilada","NaCl 0.9%"};
                itemsBaseValues = new double[]{0,0.9};
                itemsMix = new String[]{"NaCl 0.9%", "NaCl 10%", "NaCl 11.7%", "NaCl 20%"};
                itemsMixValues = new double[]{0.9,10,11.7,20};
                break;
        }

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, R.layout.spinner_item, itemsBase);
        dropdownBase.setAdapter(adapter1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.spinner_item, itemsMix);
        dropdownMix.setAdapter(adapter2);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, R.layout.spinner_item, itemsTarget);
        dropdownTarget.setAdapter(adapter3);

        final double[] finalItemsBaseValues = itemsBaseValues;
        final double[] finalItemsMixValues = itemsMixValues;
        final double[] finalItemsTargetValues = itemsTargetValues;
        dropdownBase.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Param1 = finalItemsBaseValues[i];
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });
        dropdownMix.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Param2 = finalItemsMixValues[i];
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });
        dropdownTarget.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Param3 = finalItemsTargetValues[i];
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });


        TextView tvTitleFormula = (TextView) findViewById(R.id.tvTitleFormula1);
        TextView tvParam1 = (TextView) findViewById(R.id.tvTargetSolucion);
        TextView tvParam2 = (TextView) findViewById(R.id.tvBaseSolucion);
        TextView tvParam3 = (TextView) findViewById(R.id.tvMixSolucion);
        TextView tvParam4 = (TextView) findViewById(R.id.tvTargetVolumen);
        textViewResultado1 = (TextView) findViewById(R.id.tvResultado1);
        textViewResultado2 = (TextView) findViewById(R.id.tvResultado2);
        tvDescription = (TextView) findViewById(R.id.tvDescription);
        switch (tipo){
            case 8:case 9:
                tvTitleFormula.setText("Conversión de Soluciones");
                tvParam1.setText("Solución a preparar: ");
                tvParam2.setText("Solución base: ");
                tvParam3.setText("Solución a mezclar: ");
                tvParam4.setText("Volumen a preparar: ");
                tvDescription.setText("Esta fórmula indica los volúmenes necesarios de las soluciones base que se necesitan para preparar la solución objetivo.");
                break;
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.input, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_calculate:
                String s1 = dropdownBase.getSelectedItem().toString();
                String s2 = dropdownMix.getSelectedItem().toString();
                String s3 = dropdownTarget.getSelectedItem().toString();
                String s4 = editText1.getText().toString();

                if(!Tools.isNumeric(s4)) {
                    Toast.makeText(getApplicationContext(), "Ingrese los valores solicitados", Toast.LENGTH_SHORT).show();
                    return false;
                }
                Param4 = Integer.parseInt(editText1.getText().toString());
                double temp = 0;
                switch (tipo){
                    case 8:case 9://Conversion de Dextrosa y NaCl
                        resultado = Formulas.ConversionSoluciones(Param1, Param2, Param3, Param4);
                        temp = Param4 - resultado;
                        unidades=" ml";
                        label1= s1 + " : ";
                        label2= s2 + " : ";
                        textViewResultado2.setVisibility(View.VISIBLE);
                        textViewResultado2.setText(label2 + resultado + unidades);
                        break;
                }
                textViewResultado1.setText(label1 + Math.round(temp) + unidades);
                textViewResultado1.setVisibility(View.VISIBLE);
                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                break;
            case R.id.action_clean:
                textViewResultado1.setVisibility(View.INVISIBLE);
                textViewResultado2.setVisibility(View.GONE);
                editText1.setText("");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
