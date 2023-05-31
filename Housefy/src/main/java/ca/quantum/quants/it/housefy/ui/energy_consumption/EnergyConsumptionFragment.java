package ca.quantum.quants.it.housefy.ui.energy_consumption;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
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
        energyConsumptionViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}