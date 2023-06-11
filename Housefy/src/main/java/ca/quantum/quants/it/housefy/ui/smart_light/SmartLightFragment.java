package ca.quantum.quants.it.housefy.ui.smart_light;

/*
 * @author Artem Tsurkan, n01414146
 * @author Wenyuan Yu, n01403697
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

import ca.quantum.quants.it.housefy.databinding.FragmentSmartLightBinding;

public class SmartLightFragment extends Fragment {

    private FragmentSmartLightBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SmartLightViewModel smartLightViewModel =
                new ViewModelProvider(this).get(SmartLightViewModel.class);

        binding = FragmentSmartLightBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        smartLightViewModel.getText().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer stringId) {
            }
        });        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}