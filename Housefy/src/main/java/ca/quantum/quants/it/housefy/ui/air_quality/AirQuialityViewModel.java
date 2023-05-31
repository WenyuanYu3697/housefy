package ca.quantum.quants.it.housefy.ui.air_quality;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AirQuialityViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AirQuialityViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is air quality fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}