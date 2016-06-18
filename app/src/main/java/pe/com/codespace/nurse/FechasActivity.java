package pe.com.codespace.nurse;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static pe.com.codespace.nurse.MyValues.*;


public class FechasActivity extends AppCompatActivity {
    Date fecha;
    String label1="", descripcion="";
    int tipo = -1;
    Button btnDate = null;
    TextView textViewResultado1 = null;
    TextView textViewLabel = null;
    TextView textViewDescription = null;
    int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fechas);

        Toolbar toolbar = (Toolbar) findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }

        textViewResultado1 = (TextView) findViewById(R.id.tvResultado1);
        textViewDescription = (TextView) findViewById(R.id.tvDescription);
        textViewLabel = (TextView) findViewById(R.id.tvLabel);
        btnDate = (Button) findViewById(R.id.btnDate);
        final Calendar calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        btnDate.setText(mDay + " / " + mMonth + " / " + mYear);

        final DatePickerDialog dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                mYear = year;
                mMonth = month;
                mDay = day;
                btnDate.setText(day + " / " + month + " / " + year);
            }
        },mYear,mMonth,mDay);

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dpd.show();
            }
        });

        Intent intent = getIntent();
        tipo = intent.getExtras().getInt(TIPO_FORMULAS);

        switch (tipo){
            case FPP:
                getSupportActionBar().setTitle(getResources().getString(R.string.formula_fecha_parto_title));
                textViewLabel.setText(getResources().getString(R.string.label_fecha_parto));
                descripcion = getResources().getString(R.string.descripcion_fecha_parto);
                textViewDescription.setText(descripcion);
                break;
        }

        //Agregar el adView
         AdView adView = (AdView)this.findViewById(R.id.adViewFechas);
         AdRequest adRequest = new AdRequest.Builder().build();
         adView.loadAd(adRequest);

        //Analytics
        Tracker tracker = ((AnalyticsApplication)  getApplication()).getTracker(AnalyticsApplication.TrackerName.APP_TRACKER);
        String nameActivity = getApplicationContext().getPackageName() + "." + this.getClass().getSimpleName();
        tracker.setScreenName(nameActivity);
        tracker.enableAdvertisingIdCollection(true);
        tracker.send(new HitBuilders.AppViewBuilder().build());

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
                int dia = mDay;
                int mes = mMonth;
                int anyo = mYear;

                switch (tipo){
                    case FPP://Fecha Probable de Parto
                        fecha = Formulas.FechaProbabledeParto(dia, mes, anyo);
                        label1 = getResources().getString(R.string.label_fpp);
                        break;
                }
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
                String resultado = simpleDateFormat.format(fecha);
                textViewResultado1.setText(label1 + " : " +resultado);
                textViewResultado1.setVisibility(View.VISIBLE);
                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if(getCurrentFocus()!=null){
                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                }
                break;
            case R.id.action_clean:
                textViewResultado1.setVisibility(View.INVISIBLE);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
