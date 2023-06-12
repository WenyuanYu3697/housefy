package ca.quantum.quants.it.housefy.ui.settings;

/*
 * @author Artem Tsurkan, n01414146
 * @author Wenyuan Yu, n01403697
 * @author Kyrylo Lvov, n01414058
 * @course Software Project - CENG-322-0NA
 */

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ca.quantum.quants.it.housefy.R;

public class SettingsViewModel extends ViewModel {

    private final MutableLiveData<Integer> mText;

    public SettingsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue(R.string.screen_text_smart_light);
    }

    public LiveData<Integer> getText() {
        return mText;
    }
}