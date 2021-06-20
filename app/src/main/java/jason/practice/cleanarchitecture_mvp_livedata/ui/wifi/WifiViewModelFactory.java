package jason.practice.cleanarchitecture_mvp_livedata.ui.wifi;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import jason.practice.cleanarchitecture_mvp_livedata.domain.GetWifiUseCase;
import jason.practice.cleanarchitecture_mvp_livedata.model.WifiModel;

public class WifiViewModelFactory implements ViewModelProvider.Factory {

    private final MutableLiveData<WifiModel> wifi2GModelFromCache;
    private final GetWifiUseCase getWifiUseCase;

    public WifiViewModelFactory(MutableLiveData<WifiModel> wifi2GModelFromCache, GetWifiUseCase getWifiUseCase) {
        this.wifi2GModelFromCache = wifi2GModelFromCache;
        this.getWifiUseCase = getWifiUseCase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new WifiViewModel(wifi2GModelFromCache, getWifiUseCase);
    }
}
