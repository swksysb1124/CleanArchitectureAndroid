package jason.practice.cleanarchitecture_mvp_livedata.ui.main;

import androidx.lifecycle.LifecycleOwner;

public interface MainView extends LifecycleOwner {
    void display2GWifi(String ssid, String password, int encryption);
    void display5GWifi(String ssid, String password, int encryption);
    void error(String message);
}
