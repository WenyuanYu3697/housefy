package ca.quantum.quants.it.housefy.ui.air_quality;

/*
 * @author Artem Tsurkan, 01414146
 * @author Wenyuan Yu, N01403697
 * @author Kyrylo Lvov, n01414058
 * @course Software Project - CENG-322-0NA
 */

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ca.quantum.quants.it.housefy.R;

public class AirQualityViewModel extends ViewModel {

    private final MutableLiveData<Integer> mText;

    public AirQualityViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue(R.string.screen_text_air_quality);
    }

    public LiveData<Integer> getText() {
        return mText;
    }
}