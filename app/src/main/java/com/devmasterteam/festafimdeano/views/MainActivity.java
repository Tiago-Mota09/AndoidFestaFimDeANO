package com.devmasterteam.festafimdeano.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.devmasterteam.festafimdeano.R;
import com.devmasterteam.festafimdeano.util.SecurityPreferences;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private ViewHolder mViewHolder = new ViewHolder();
    private SecurityPreferences mSecurityPreferences;
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mViewHolder.textToday = findViewById(R.id.text_today);
        this.mViewHolder.textDaysLeft = findViewById(R.id.text_days_left);

        this.mSecurityPreferences = new SecurityPreferences(this);

        this.mViewHolder.textToday.setText(SIMPLE_DATE_FORMAT.format(Calendar.getInstance().getTime()));

        String daysLeft = String.format("%s %s", String.valueOf(this.getDaysLeftToEndOfYear()), getString(R.string.dias));
        this.mViewHolder.textDaysLeft.setText(daysLeft);
    }

    @Override
    protected void onResume(){
        super.onResume();
    }
    private int getDaysLeftToEndOfYear(){
        // Incializa instância do calendário e obtém o dia do ano
        Calendar calendarToday = Calendar.getInstance();
        int today = calendarToday.get(Calendar.DAY_OF_YEAR);

        // Pega o dia máximo do ano - De 1 até 365. Podem existir anos bissextos.
        Calendar calendarLastDay = Calendar.getInstance();
        int dayDecember19 = calendarLastDay.getActualMaximum(Calendar.DAY_OF_YEAR);

        // Calcula quantidade de dias restantes pro fim do ano
        return dayDecember19 - today;
    }

    /**
     * Padrão ViewHolder
     * */
    private static class ViewHolder {
        TextView textToday;
        TextView textDaysLeft;
    }
}
