package jason.practice.cleanarchitecture_mvp_livedata.ui.wifi;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import jason.practice.cleanarchitecture_mvp_livedata.App;
import jason.practice.cleanarchitecture_mvp_livedata.R;
import jason.practice.cleanarchitecture_mvp_livedata.data.repository.DataRepository;
import jason.practice.cleanarchitecture_mvp_livedata.domain.GetWifiUseCase;

public class WifiActivity extends AppCompatActivity {

    private TextView txtWifi;
    private WifiViewModel wifiViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);

        App app = (App) getApplication();
        GetWifiUseCase getWifiUseCase = new GetWifiUseCase(new DataRepository(app.getApi()));
        wifiViewModel = new ViewModelProvider(this,
                new WifiViewModelFactory(app.getWifi2GModel(), getWifiUseCase))
                .get(WifiViewModel.class);

        txtWifi = findViewById(R.id.txtWifi);

        findViewById(R.id.btnUpdate).setOnClickListener(this::onUpdateClick);
    }

    private void observeData() {
        wifiViewModel.getWifi2GSsid().observe(this, this::onWifi2GSsidChanged);
        wifiViewModel.getErrorMessage().observe(this, this::onErrorMessageChanged);
    }

    private void removeData() {
        wifiViewModel.getWifi2GSsid().removeObservers(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        wifiViewModel.getCurrentWifi2gSsid();
        observeData();
    }

    @Override
    protected void onPause() {
        super.onPause();
        removeData();
    }

    private void onWifi2GSsidChanged(String ssid) {
        txtWifi.setText(ssid);
        toast(ssid);
    }

    private void onErrorMessageChanged(String error) {
        toast(error);
    }

    private void onUpdateClick(View v) {
        wifiViewModel.fetchGetWifi2GSsid();
    }

    private void toast(String message) {
        Toast.makeText(WifiActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}