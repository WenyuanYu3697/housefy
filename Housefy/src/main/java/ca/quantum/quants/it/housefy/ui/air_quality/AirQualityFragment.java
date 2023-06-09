package ca.quantum.quants.it.housefy.ui.air_quality;

/*
 * @author Artem Tsurkan, n01414146
 * @author Wenyuan Yu, n01403697
 * @author Kyrylo Lvov, n01414058
 * @course Software Project - CENG-322-0NA
 */

import android.graphics.Color;
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

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;
import java.util.List;

import ca.quantum.quants.it.housefy.CustomValueFormatter;
import ca.quantum.quants.it.housefy.databinding.FragmentAirQualityBinding;

public class AirQualityFragment extends Fragment {

    private FragmentAirQualityBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AirQualityViewModel airQuialityViewModel =
                new ViewModelProvider(this).get(AirQualityViewModel.class);

        binding = FragmentAirQualityBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textView;
        BarChart chart = binding.airQualityBarChartHistory;

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, 10));
        entries.add(new BarEntry(1f, 20));
        entries.add(new BarEntry(2f, 30));
        entries.add(new BarEntry(3f, 40));
        entries.add(new BarEntry(4f, 50));

        String[] dates = new String[]{"Date 1", "Date 2", "Date 3", "Date 4", "Date 5"};

        BarDataSet dataSet = new BarDataSet(entries, "Air Quality History");
        BarData barData = new BarData(dataSet);

// Set custom value formatter for the x-axis labels (dates)
        XAxis xAxis = chart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(dates));

// Set custom value formatter for the values on top of each column
        dataSet.setValueFormatter(new CustomValueFormatter(dates));

        chart.setData(barData);
        chart.invalidate(); // Refresh the chart

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

