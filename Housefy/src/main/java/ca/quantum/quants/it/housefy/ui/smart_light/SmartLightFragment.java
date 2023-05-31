package ca.quantum.quants.it.housefy.ui.smart_light;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
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

        final TextView textView = binding.textSmartLight;
        smartLightViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}