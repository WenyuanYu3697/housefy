package ca.quantum.quants.it.housefy.ui.air_conditioner;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AirConditionerViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AirConditionerViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is air conditioner fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}