package ca.quantum.quants.it.housefy.ui.air_quality;

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

import ca.quantum.quants.it.housefy.databinding.FragmentAirQualityBinding;

public class AirQualityFragment extends Fragment {

    private FragmentAirQualityBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AirQualityViewModel airQuialityViewModel =
                new ViewModelProvider(this).get(AirQualityViewModel.class);

        binding = FragmentAirQualityBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textAirQuality;
        airQuialityViewModel.getText().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer stringId) {
                if (stringId != null) {
                    textView.setText(getString(stringId));
                }
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}