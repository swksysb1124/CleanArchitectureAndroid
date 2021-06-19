package jason.practice.cleanarchitecture_mvp_livedata.ui.main;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import jason.practice.cleanarchitecture_mvp_livedata.domain.GetWifiUseCase;
import jason.practice.cleanarchitecture_mvp_livedata.model.WifiModel;

public class MainPresenter {

    private final MainView mainView;
    private final MutableLiveData<WifiModel> wifi2GModel;
    private final MutableLiveData<WifiModel> wifi5GModel;
    private final GetWifiUseCase getWifiUseCase;

    public MainPresenter(MainView mainView,
                         MutableLiveData<WifiModel> wifi2GModel,
                         MutableLiveData<WifiModel> wifi5GModel,
                         GetWifiUseCase getWifiUseCase) {
        this.mainView = mainView;
        this.wifi2GModel = wifi2GModel;
        this.wifi5GModel = wifi5GModel;
        this.getWifiUseCase = getWifiUseCase;
    }

    public void observeModels() {
        wifi2GModel.observe(mainView, wifiModel -> {
            System.out.println("main presenter update: Wi-Fi 2G");
            mainView.display2GWifi(wifiModel.getSsid(), wifiModel.getPassword(), wifiModel.getEncryption());
        });
        wifi5GModel.observe(mainView, wifiModel -> {
            System.out.println("main presenter update: Wi-Fi 5G");
            mainView.display5GWifi(wifiModel.getSsid(), wifiModel.getPassword(), wifiModel.getEncryption());
        });
    }

    public void removeModels() {
        wifi2GModel.removeObservers(mainView);
    }

    public void fetchWifiData() {
        getWifiUseCase.execute(
                new GetWifiUseCase.Callback() {
                    @Override
                    public void onSuccess(List<WifiModel> wifiModels) {
                        wifi5GModel.postValue(wifiModels.get(0));
                        wifi2GModel.postValue(wifiModels.get(1));
                    }

                    @Override
                    public void onFail(int errorType, String message) {
                        showErrorMessage(message);
                    }
                });
    }

    private void showErrorMessage(String message) {
        //TODO
    }


}
