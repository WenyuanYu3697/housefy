package ca.quantum.quants.it.housefy.ui.smart_light;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SmartLightViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public SmartLightViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is smart light fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}