package ca.quantum.quants.it.housefy.ui.energy_consumption;

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

import ca.quantum.quants.it.housefy.databinding.FragmentEnergyConsumptionBinding;

public class EnergyConsumptionFragment extends Fragment {

    private FragmentEnergyConsumptionBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        EnergyConsumptionViewModel energyConsumptionViewModel =
                new ViewModelProvider(this).get(EnergyConsumptionViewModel.class);

        binding = FragmentEnergyConsumptionBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textEnergyConsumption;
        energyConsumptionViewModel.getText().observe(getViewLifecycleOwner(), new Observer<Integer>() {
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