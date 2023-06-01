package ca.quantum.quants.it.housefy.ui.energy_consumption;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ca.quantum.quants.it.housefy.R;

public class EnergyConsumptionViewModel extends ViewModel {

    private final MutableLiveData<Integer> mText;

    public EnergyConsumptionViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue(R.string.screen_text_energy_consumption);
    }

    public LiveData<Integer> getText() {
        return mText;
    }
}