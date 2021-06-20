package jason.practice.cleanarchitecture_mvp_livedata.ui.wifi;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import jason.practice.cleanarchitecture_mvp_livedata.domain.GetWifiUseCase;
import jason.practice.cleanarchitecture_mvp_livedata.model.WifiModel;

public class WifiViewModel extends ViewModel {

    private final MutableLiveData<String> wifi2GSsid;
    private final MutableLiveData<String> errorMessage;

    private final MutableLiveData<WifiModel> wifi2GModelFromCache;
    private final GetWifiUseCase getWifiUseCase;

    public WifiViewModel(MutableLiveData<WifiModel> wifi2GModel, GetWifiUseCase getWifiUseCase) {
        this.wifi2GSsid = new MutableLiveData<>();
        this.errorMessage = new MutableLiveData<>();

        this.wifi2GModelFromCache = wifi2GModel;
        this.getWifiUseCase = getWifiUseCase;
    }

    public MutableLiveData<String> getWifi2GSsid() {
        return wifi2GSsid;
    }

    public MutableLiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public void getCurrentWifi2gSsid() {
        if (wifi2GModelFromCache.getValue() == null) {
            return;
        }
        String ssid = wifi2GModelFromCache.getValue().getSsid();
        wifi2GSsid.postValue(ssid);
    }

    public void fetchGetWifi2GSsid() {
        getWifiUseCase.execute(new GetWifiUseCase.Callback() {
            @Override
            public void onSuccess(List<WifiModel> wifiModels) {
                WifiModel wifi2GModel = wifiModels.get(1);
                String ssid = wifi2GModel.getSsid();
                System.out.println("ssid=" + ssid);
                wifi2GSsid.postValue(ssid);
                wifi2GModelFromCache.postValue(wifi2GModel);
            }

            @Override
            public void onFail(int errorType, String message) {
                errorMessage.postValue(message);
            }
        });
    }
}
