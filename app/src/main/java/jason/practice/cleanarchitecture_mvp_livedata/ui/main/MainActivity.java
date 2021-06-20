package jason.practice.cleanarchitecture_mvp_livedata.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

import jason.practice.cleanarchitecture_mvp_livedata.App;
import jason.practice.cleanarchitecture_mvp_livedata.R;
import jason.practice.cleanarchitecture_mvp_livedata.data.repository.DataRepository;
import jason.practice.cleanarchitecture_mvp_livedata.domain.GetWifiUseCase;
import jason.practice.cleanarchitecture_mvp_livedata.ui.wifi.WifiActivity;

public class MainActivity extends AppCompatActivity
        implements MainView {

    MainPresenter presenter;
    TextView txtWifi2G, txtWifi5G;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        App app = (App) getApplication();
        presenter = new MainPresenter(
                this,
                app.getWifi2GModel(),
                app.getWifi5GModel(),
                new GetWifiUseCase(new DataRepository(app.getApi()))
        );

        txtWifi2G = findViewById(R.id.txtWifi2G);
        txtWifi5G = findViewById(R.id.txtWifi5G);

        findViewById(R.id.btnFetchWifi).setOnClickListener(this::onFetchWifiClick);
        findViewById(R.id.btnGoTo).setOnClickListener(this::onGoToClick);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.observeModels();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.removeModels();
    }

    @Override
    public void display2GWifi(String ssid, String password, int encryption) {
        txtWifi2G.setText(String.format(Locale.US,
                "ssid=%s, password=%s, encryption=%d", ssid, password, encryption));
    }

    @Override
    public void display5GWifi(String ssid, String password, int encryption) {
        txtWifi5G.setText(String.format(Locale.US,
                "ssid=%s, password=%s, encryption=%d", ssid, password, encryption));
    }

    @Override
    public void error(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void onFetchWifiClick(View v) {
        presenter.fetchWifiData();
    }

    private void onGoToClick(View v) {
        startActivity(new Intent(MainActivity.this, WifiActivity.class));
    }
}