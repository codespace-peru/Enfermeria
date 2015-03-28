package pe.com.codespace.nurse;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class Input4ParametrosActivity extends ActionBarActivity {
    double Param1, Param2, Param3, Param4;
    double resultado;
    String label1="", label2="", unidades="", descripcion="";
    int tipo = -1;
    EditText editText1 = null;
    EditText editText2 = null;
    EditText editText3 = null;
    EditText editText4 = null;
    TextView textViewResultado1 = null;
    TextView textViewResultado2 = null;
    TextView textViewDescription = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayShowTitleEnabled(false);
        setContentView(R.layout.activity_input4param);
        textViewResultado1 = (TextView) findViewById(R.id.tvResultado1);
        textViewResultado2 = (TextView) findViewById(R.id.tvResultado2);
        textViewDescription = (TextView) findViewById(R.id.tvDescription);
        TextView tvTitleFormula = (TextView) findViewById(R.id.tvTitleFormula);
        TextView tvParam1 = (TextView) findViewById(R.id.tvInput1);
        TextView tvParam2 = (TextView) findViewById(R.id.tvInput2);
        TextView tvParam3 = (TextView) findViewById(R.id.tvInput3);
        TextView tvParam4 = (TextView) findViewById(R.id.tvInput4);
        editText1 = (EditText) findViewById(R.id.etInput1);
        editText2 = (EditText) findViewById(R.id.etInput2);
        editText3 = (EditText) findViewById(R.id.etInput3);
        editText4 = (EditText) findViewById(R.id.etInput4);
        Intent intent = getIntent();
        tipo = intent.getExtras().getInt("formula");

        switch (tipo){
            case 15:
                tvTitleFormula.setText("Velocidad de Goteo de Dopamina - Dobutamina");
                tvParam1.setText("Dosis (mcg/kg/min): ");
                tvParam2.setText("Peso del Paciente (kg):");
                tvParam3.setText("Cantidad del Fármaco (mg): ");
                tvParam4.setText("Volumen Total de la Infusión (ml): ");
                descripcion ="La cantidad del fármaco indica los mg. que están en la preparación.\nEl volumen total de la infusión incluye los volúmenes de la solución diluyente y del fármaco a preparar.";
                textViewDescription.setText(descripcion);
                break;
            case 16:
                break;
        }

        //Agregar el adView
        AdView adView = (AdView)this.findViewById(R.id.adViewInput4Param);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

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
                String s1 = editText1.getText().toString();
                String s2 = editText2.getText().toString();
                String s3 = editText3.getText().toString();
                String s4 = editText4.getText().toString();

                if(!Tools.isNumeric(s1) || !Tools.isNumeric(s2) || !Tools.isNumeric(s3) || !Tools.isNumeric(s4)) {
                    Toast.makeText(getApplicationContext(), "Ingrese los valores solicitados", Toast.LENGTH_SHORT).show();
                    return false;
                }
                Param1 = Double.parseDouble(editText1.getText().toString());
                Param2 = Double.parseDouble(editText2.getText().toString());
                Param3 = Double.parseDouble(editText3.getText().toString());
                Param4 = Double.parseDouble(editText4.getText().toString());
                switch (tipo){
                    case 15://Calculo de Dopamina
                        resultado = Formulas.ConversionFarmacoDopamina(Param1, Param2, Param3, Param4);
                        label1 = "Velocidad de goteo: ";
                        unidades=" ml/hora";
                        break;
                }
                textViewResultado1.setText(label1 + resultado + unidades);
                textViewResultado1.setVisibility(View.VISIBLE);
                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                break;
            case R.id.action_clean:
                textViewResultado1.setVisibility(View.INVISIBLE);
                textViewResultado2.setVisibility(View.GONE);
                editText1.setText("");
                editText2.setText("");
                editText3.setText("");
                editText4.setText("");
                editText1.requestFocus();
                break;
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
