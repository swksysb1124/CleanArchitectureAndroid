package jason.practice.cleanarchitecture_mvp_livedata.ui.wifi;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import jason.practice.cleanarchitecture_mvp_livedata.App;
import jason.practice.cleanarchitecture_mvp_livedata.R;
import jason.practice.cleanarchitecture_mvp_livedata.data.repository.DataRepository;
import jason.practice.cleanarchitecture_mvp_livedata.model.WifiModel;

public class WifiActivity extends AppCompatActivity {

    LiveData<WifiModel> wifi2GModel;

    DataRepository mainRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);

        App app = (App) getApplication();
        mainRepository = new DataRepository(app.getApi());
        wifi2GModel = app.getWifi2GModel();
        wifi2GModel.observe(this, wifiModel ->
                Toast.makeText(WifiActivity.this, wifiModel.getSsid(), Toast.LENGTH_SHORT).show());


        TextView txtWifi = findViewById(R.id.txtWifi);
        txtWifi.setText(wifi2GModel.getValue().getSsid());

        findViewById(R.id.btnUpdate).setOnClickListener(v -> {
            try {
                mainRepository.fetchWifiData(app.getWifi2GModel(), app.getWifi5GModel());
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        wifi2GModel.removeObservers(this);
        wifi2GModel = null;
        mainRepository = null;
    }
}