package jason.practice.cleanarchitecture_mvp_livedata.data.repository;

import jason.practice.cleanarchitecture_mvp_livedata.data.remote.MobileApi;
import jason.practice.cleanarchitecture_mvp_livedata.data.remote.Response;
import jason.practice.cleanarchitecture_mvp_livedata.model.WifiModel;

public class DataRepository {

    private final MobileApi api;

    public DataRepository(MobileApi api) {
        this.api = api;
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
