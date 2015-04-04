package pe.com.codespace.nurse;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import static pe.com.codespace.nurse.MyValues.*;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;



public class RegladetresActivity extends ActionBarActivity {
    double Param1, Param2, Param3;
    double resultado;
    int tipo = -1;
    EditText editText1 = null;
    EditText editText2 = null;
    EditText editText3 = null;
    TextView textViewResultado1 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayShowTitleEnabled(false);
        setContentView(R.layout.activity_regladetres);
        textViewResultado1 = (TextView) findViewById(R.id.tvResultado1);

        TextView textViewDescription = (TextView) findViewById(R.id.tvDescription);
        TextView textViewTitleFormula = (TextView) findViewById(R.id.tvTitleFormula);
        editText1 = (EditText) findViewById(R.id.etInput1);
        editText2 = (EditText) findViewById(R.id.etInput2);
        editText3 = (EditText) findViewById(R.id.etInput3);
        Intent intent = getIntent();
        tipo = intent.getExtras().getInt("formula");
        switch (tipo){
            case REGLATRES://Regla de tres simple
                textViewTitleFormula.setText("Regla de 3 simple directa");
                textViewDescription.setText("Se establece la relación de proporcionalidad directa entre dos valores, y conociendo un tercer valor, se calcula X.");
                break;
        }

        //Agregar el adView
        AdView adView = (AdView)this.findViewById(R.id.adViewReglasParam);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.input, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_calculate:
                String s1 = editText1.getText().toString();
                String s2 = editText2.getText().toString();
                String s3 = editText3.getText().toString();

                if(!Tools.isNumeric(s1) || ! Tools.isNumeric(s2) || ! Tools.isNumeric(s3)){
                    Toast.makeText(getApplicationContext(), "Ingrese los valores solicitados", Toast.LENGTH_SHORT).show();
                    return false;
                }
                Param1 = Double.parseDouble(editText1.getText().toString());
                Param2 = Double.parseDouble(editText2.getText().toString());
                Param3 = Double.parseDouble(editText3.getText().toString());
                switch (tipo){
                    case REGLATRES://Regla de tres simple
                        resultado = Formulas.Regla3Simple(Param1, Param2, Param3);
                        break;
                    case 30:
                        break;

                }
                textViewResultado1.setText("X = " + Double.toString(resultado));

                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);

                break;
            case R.id.action_clean:
                editText1.setText("");
                editText2.setText("");
                editText3.setText("");
                textViewResultado1.setText("X");
                editText1.requestFocus();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
