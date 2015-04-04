package pe.com.codespace.nurse;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.TextView;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static pe.com.codespace.nurse.MyValues.*;


public class FechasActivity extends ActionBarActivity {
    Date fecha;
    String label1="", descripcion="";
    int tipo = -1;
    DatePicker datePicker = null;
    TextView textViewResultado1 = null;
    TextView textViewLabel = null;
    TextView textViewDescription = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayShowTitleEnabled(false);
        setContentView(R.layout.activity_fechas);
        textViewResultado1 = (TextView) findViewById(R.id.tvResultado1);
        textViewDescription = (TextView) findViewById(R.id.tvDescription);
        textViewLabel = (TextView) findViewById(R.id.tvLabel);
        TextView textViewTitleFormula = (TextView) findViewById(R.id.tvTitleFormula);

        datePicker = (DatePicker) findViewById(R.id.datePickerFechas);
        Date hoy = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(hoy);
        calendar.add(Calendar.YEAR,1);
        datePicker.setMaxDate(calendar.getTimeInMillis());
        calendar.add(Calendar.YEAR,-3);
        datePicker.setMinDate(calendar.getTimeInMillis());
        Intent intent = getIntent();
        tipo = intent.getExtras().getInt("formula");

        switch (tipo){
            case FPP:
                textViewTitleFormula.setText("Fecha Probable de Parto");
                textViewLabel.setText("Fecha del primer día de la última regla:");
                descripcion ="Se calcula mediante la fórmula de Naegele, recomendada por la OMS.";
                textViewDescription.setText(descripcion);
                break;
        }

        //Agregar el adView
         AdView adView = (AdView)this.findViewById(R.id.adViewFechas);
         AdRequest adRequest = new AdRequest.Builder().build();
         adView.loadAd(adRequest);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.input, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_calculate:
                int dia = datePicker.getDayOfMonth();
                int mes = datePicker.getMonth();
                int anyo = datePicker.getYear();


                switch (tipo){
                    case FPP://Fecha Probable de Parto
                        fecha = Formulas.FechaProbabledeParto(dia, mes, anyo);
                        label1 = "Resultado: ";
                        break;
                }
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String resultado = simpleDateFormat.format(fecha);
                textViewResultado1.setText(label1 + resultado);
                textViewResultado1.setVisibility(View.VISIBLE);
                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                break;
            case R.id.action_clean:
                textViewResultado1.setVisibility(View.INVISIBLE);
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
