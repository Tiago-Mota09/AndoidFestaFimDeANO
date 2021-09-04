package com.devmasterteam.festafimdeano.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.devmasterteam.festafimdeano.R;
import com.devmasterteam.festafimdeano.constants.FimDeAnoConstants;
import com.devmasterteam.festafimdeano.util.SecurityPreferences;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private SecurityPreferences mSecurityPreferences;
    private Bundle savedInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        this.mSecurityPreferences = new SecurityPreferences(this);

        this.mViewHolder.checkParticipate = findViewById(R.id.check_participate);

        this.mViewHolder.checkParticipate.setOnClickListener(this);

        this.loadDataFromActivity();

        this.savedInstanceState = savedInstanceState;

        this.mViewHolder.buttonConfirm = findViewById(R.id.button_confirm);

        this.mViewHolder.buttonConfirm.setOnClickListener(this);

        this.mSecurityPreferences = new SecurityPreferences(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.check_participate) {
            if (this.mViewHolder.checkParticipate.isChecked()) {
                this.mSecurityPreferences.storeString(FimDeAnoConstants.PRESENCE, FimDeAnoConstants.CONFIRMED_WILL_GO);
            } else {
                this.mSecurityPreferences.storeString(FimDeAnoConstants.PRESENCE, FimDeAnoConstants.CONFIRMED_WONT_GO);
            }
        }

            int idConfirm = view.getId();
            if (idConfirm == R.id.button_confirm) {

                String presence = this.mSecurityPreferences.getStoredString(FimDeAnoConstants.PRESENCE);

                // Lógica de navegação
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra(FimDeAnoConstants.PRESENCE, presence);
                startActivity(intent);
            }
        }

    private void loadDataFromActivity() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String presence = extras.getString(FimDeAnoConstants.PRESENCE);
            if (presence != null && presence.equals(FimDeAnoConstants.CONFIRMED_WILL_GO)) {
                this.mViewHolder.checkParticipate.setChecked(true);
            } else {
                this.mViewHolder.checkParticipate.setChecked(false);
            }
        }
    }

    private static class ViewHolder {
        CheckBox checkParticipate;
        Button buttonConfirm;
    }

    @Override
    protected void onResume(){
        super.onResume();
        this.verifyPresence();
    }

    private void verifyPresence(){
        String presence = this.mSecurityPreferences.getStoredString(FimDeAnoConstants.PRESENCE);
        if (presence.equals(""))
            this.mViewHolder.buttonConfirm.setText(R.string.nao_confirmado);
        else if (presence.equals(FimDeAnoConstants.CONFIRMED_WILL_GO))
            this.mViewHolder.buttonConfirm.setText(R.string.sim);
        else
            this.mViewHolder.buttonConfirm.setText(R.string.nao);
    }
}
