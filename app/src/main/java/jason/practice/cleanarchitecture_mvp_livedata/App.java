package jason.practice.cleanarchitecture_mvp_livedata;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import jason.practice.cleanarchitecture_mvp_livedata.data.remote.MobileApiImp;
import jason.practice.cleanarchitecture_mvp_livedata.model.WifiModel;

public class App extends Application {

    private MobileApiImp api;

    private MutableLiveData<WifiModel> wifi2GModel;
    private MutableLiveData<WifiModel> wifi5GModel;

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("app created");

        wifi2GModel = new MutableLiveData<>();
        wifi5GModel = new MutableLiveData<>();

        api = new MobileApiImp();
    }

    public MutableLiveData<WifiModel> getWifi2GModel() {
        return wifi2GModel;
    }

    public MutableLiveData<WifiModel> getWifi5GModel() {
        return wifi5GModel;
    }

    public MobileApiImp getApi() {
        return api;
    }
}
