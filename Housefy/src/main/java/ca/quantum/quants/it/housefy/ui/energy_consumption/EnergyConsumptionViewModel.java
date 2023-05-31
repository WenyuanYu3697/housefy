package ca.quantum.quants.it.housefy.ui.energy_consumption;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EnergyConsumptionViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public EnergyConsumptionViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is energy consumption fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}