package ca.quantum.quants.it.housefy.ui.smart_light;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ca.quantum.quants.it.housefy.R;

public class SmartLightViewModel extends ViewModel {

    private final MutableLiveData<Integer> mText;

    public SmartLightViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue(R.string.screen_text_smart_light);
    }

    public LiveData<Integer> getText() {
        return mText;
    }
}