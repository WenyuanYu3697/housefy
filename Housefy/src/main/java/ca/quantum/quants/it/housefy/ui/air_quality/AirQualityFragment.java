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
        AirQualityViewModel airQualityViewModel =
                new ViewModelProvider(this).get(AirQualityViewModel.class);

        binding = FragmentAirQualityBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        BarChart chart = binding.airQualityBarChartHistory;
        chart.getDescription().setEnabled(false); // Remove description
        chart.getAxisLeft().setDrawGridLines(false); // Remove left axis grid lines
        chart.getAxisRight().setDrawGridLines(false); // Remove right axis grid lines
        chart.getXAxis().setDrawGridLines(false); // Remove X axis grid lines

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, 10));
        entries.add(new BarEntry(1f, 20));
        entries.add(new BarEntry(2f, 30));
        entries.add(new BarEntry(3f, 40));
        entries.add(new BarEntry(4f, 50));

        String[] weekdays = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};

        BarDataSet dataSet = new BarDataSet(entries, "");
        ArrayList<Integer> colors = new ArrayList<>();
        for (int i = 0; i < entries.size(); i++) {
            float value = entries.get(i).getY();
            if (value < 30) {
                colors.add(Color.rgb(102, 187, 106));  // Green A700
            } else if (value < 40) {
                colors.add(Color.rgb(255, 238, 88));   // Yellow A700
            } else if (value <= 50) {
                colors.add(Color.rgb(239, 83, 80));    // Red A700
            }
        }
        dataSet.setColors(colors);
        dataSet.setDrawValues(false); // Do not draw values on the bars

        BarData barData = new BarData(dataSet);
        chart.setData(barData);

        // Set weekdays for the x-axis labels
        XAxis xAxis = chart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(weekdays));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); // Set labels to appear at the bottom
        xAxis.setLabelCount(entries.size()); // Set the count of labels to match entries size
        xAxis.setGranularity(1f); // minimum axis-step (interval) is 1


        chart.invalidate(); // Refresh the chart

        airQualityViewModel.getText().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer stringId) {
                if (stringId != null) {
                    binding.airQualityIndexDescription.setText(getString(stringId));
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
