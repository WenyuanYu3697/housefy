package ca.quantum.quants.it.housefy.ui.home;

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

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<Integer> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue(R.string.screen_text_home);
    }

    public LiveData<Integer> getText() {
        return mText;
    }
}