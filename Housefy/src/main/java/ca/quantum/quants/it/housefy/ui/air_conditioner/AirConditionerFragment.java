package ca.quantum.quants.it.housefy.ui.air_conditioner;

/*
 * @author Artem Tsurkan, 01414146
 * @author Wenyuan Yu, N01403697
 * @author Kyrylo Lvov, n01414058
 * @course Software Project - CENG-322-0NA
 */

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import ca.quantum.quants.it.housefy.databinding.FragmentAirConditionerBinding;

public class AirConditionerFragment extends Fragment {

    private FragmentAirConditionerBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AirConditionerViewModel airConditionerViewModel =
                new ViewModelProvider(this).get(AirConditionerViewModel.class);

        binding = FragmentAirConditionerBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textAirConditioner;
        airConditionerViewModel.getText().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer stringId) {
                if (stringId != null) {
                    textView.setText(getString(stringId));
                }
            }
        });        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}