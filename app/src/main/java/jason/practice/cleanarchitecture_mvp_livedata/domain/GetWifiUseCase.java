package jason.practice.cleanarchitecture_mvp_livedata.domain;

import java.util.ArrayList;
import java.util.List;

import jason.practice.cleanarchitecture_mvp_livedata.data.repository.DataRepository;
import jason.practice.cleanarchitecture_mvp_livedata.model.WifiModel;

public class GetWifiUseCase {
    private final DataRepository repository;

    public GetWifiUseCase(DataRepository repository) {
        this.repository = repository;
    }

    public  void execute(Callback callback) {
        new Thread(() -> {
            try {
                WifiModel wifi5GModel = repository.fetchWifiData(0);
                WifiModel wifi2GModel = repository.fetchWifiData(1);
                ArrayList<WifiModel> models = new ArrayList<>();
                models.add(wifi5GModel);
                models.add(wifi2GModel);
                callback.onSuccess(models);
            } catch (Throwable e) {
                callback.onFail(0, e.getMessage());
            }
        }).start();
    }

    public interface Callback {
        void onSuccess(List<WifiModel> wifiModels);
        void onFail(int errorType, String message);
    }

}
