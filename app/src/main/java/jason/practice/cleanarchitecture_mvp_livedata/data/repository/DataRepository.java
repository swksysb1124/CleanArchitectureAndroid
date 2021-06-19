package jason.practice.cleanarchitecture_mvp_livedata.data.repository;

import androidx.lifecycle.MutableLiveData;

import jason.practice.cleanarchitecture_mvp_livedata.data.remote.MobileApiImp;
import jason.practice.cleanarchitecture_mvp_livedata.data.remote.Response;
import jason.practice.cleanarchitecture_mvp_livedata.model.WifiModel;

public class DataRepository {

    private final MobileApiImp api;

    public DataRepository(MobileApiImp api) {
        this.api = api;
    }

    public void fetchWifiData(MutableLiveData<WifiModel> wifi2GMode, MutableLiveData<WifiModel> wifi5GModel) throws Throwable {
        Response res1 = api.getWifiData(1);
        wifi2GMode.postValue(new WifiModel("Jason-Wifi_2G", "12345678", 3, 1));

        Response res2 = api.getWifiData(0);
        wifi5GModel.postValue(new WifiModel("Jason-Wifi_5G", "12345678", 3, 0));
    }

    public WifiModel fetchWifiData(int _interface) throws Throwable {
        Response res = api.getWifiData(_interface);
        if (_interface == 1) {
            return new WifiModel("Jason-Wifi_2G", "12345678", 3, 1);
        } else {
            return new WifiModel("Jason-Wifi_5G", "12345678", 3, 0);
        }
    }
}
