package ca.quantum.quants.it.housefy.ui.air_conditioner;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ca.quantum.quants.it.housefy.R;

public class AirConditionerViewModel extends ViewModel {

    private final MutableLiveData<Integer> mText;

    public AirConditionerViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue(R.string.screen_text_air_conditioner);
    }

    public LiveData<Integer> getText() {
        return mText;
    }
}