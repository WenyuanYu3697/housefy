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

import ca.quantum.quants.it.housefy.R;
import ca.quantum.quants.it.housefy.databinding.FragmentAirQualityBinding;

public class AirQualityFragment extends Fragment {

    private FragmentAirQualityBinding binding;
    private static final float BAR_HEIGHT = 7f;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AirQualityViewModel airQualityViewModel =
                new ViewModelProvider(this).get(AirQualityViewModel.class);

        binding = FragmentAirQualityBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        BarChart chart = binding.airQualityBarChartHistory;

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, new float[]{BAR_HEIGHT - 10, 10}));
        entries.add(new BarEntry(1f, new float[]{BAR_HEIGHT - 20, 20}));
        entries.add(new BarEntry(2f, new float[]{BAR_HEIGHT - 30, 30}));
        entries.add(new BarEntry(3f, new float[]{BAR_HEIGHT - 40, 40}));
        entries.add(new BarEntry(4f, new float[]{BAR_HEIGHT - 50, 50}));
        entries.add(new BarEntry(5f, new float[]{BAR_HEIGHT - 35, 35}));
        entries.add(new BarEntry(6f, new float[]{BAR_HEIGHT - 45, 45}));

        String[] weekdays = getResources().getStringArray(R.array.Days_of_Week);

        BarDataSet dataSet = new BarDataSet(entries, getString(R.string.air_quality_bar_chart_label));

        ArrayList<Integer> colors = new ArrayList<>();
        for (int i = 0; i < entries.size(); i++) {
            float value = entries.get(i).getYVals()[1];
            if (value < 30) {
                colors.add(Color.argb(70, 102, 187, 106)); // Lighter color for the "fill"
                colors.add(Color.rgb(102, 187, 106)); // Actual value color
            } else if (value < 40) {
                colors.add(Color.argb(70, 255, 238, 88)); // Lighter color for the "fill"
                colors.add(Color.rgb(255, 238, 88)); // Actual value color
            } else if (value <= 50) {
                colors.add(Color.argb(70, 239, 83, 80)); // Lighter color for the "fill"
                colors.add(Color.rgb(239, 83, 80)); // Actual value color
            }
        }
        dataSet.setColors(colors);
        dataSet.setStackLabels(new String[]{getString(R.string.AQI_Bar_Chart_Data_Statstics), getString(R.string.AQI_Bar_Chart_Data_Statstics1)});
        dataSet.setValueTextColor(Color.TRANSPARENT); // Hide values

        BarData barData = new BarData(dataSet);
        chart.setData(barData);

        XAxis xAxis = chart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(weekdays));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(entries.size());
        xAxis.setGranularity(1f);

        chart.getAxisLeft().setDrawLabels(false);
        chart.getAxisRight().setDrawLabels(false);
        chart.setDrawValueAboveBar(false); // Draw values inside the bars
        chart.getDescription().setEnabled(false);
        chart.getAxisLeft().setDrawGridLines(false);
        chart.getAxisRight().setDrawGridLines(false);
        chart.getXAxis().setDrawGridLines(false);

        chart.invalidate();

        // Get the PieChart
        PieChart pieChart = binding.airQualityPieChart;

// Set the size of the transparent center circle
        pieChart.setHoleRadius(60f);
        pieChart.setTransparentCircleRadius(90f);

// Set the PieChart to only take the top half of the circle
        pieChart.setMaxAngle(180f);

// Rotate the PieChart 180 degrees to the left so the half circle starts from the top
        pieChart.setRotationAngle(180f);

// Disable the legend and description
        pieChart.getLegend().setEnabled(false);
        pieChart.getDescription().setEnabled(false);

// Remove labels
        pieChart.setDrawEntryLabels(false);

// Create a PieEntry list and add a single entry with value 100 to make it a full half-circle
        List<PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(100));

// Create a PieDataSet with your PieEntry list
        PieDataSet pieDataSet = new PieDataSet(pieEntries, "");

        // Define colors
        int green = Color.rgb(102, 187, 106);
        int lightGreen = Color.rgb(200, 245, 210);
        int yellow = Color.rgb(255, 238, 88);
        int lightYellow = Color.rgb(255, 253, 209);
        int red = Color.rgb(239, 83, 80);
        int lightRed = Color.rgb(253, 199, 197);

// Define an array to hold the colors for the pie chart
        ArrayList<Integer> color = new ArrayList<>();

// Depending on the air quality index, set the color of the chart to green, yellow, or red
        int airQualityIndex = 30; // This value can be replaced with the actual air quality index
        if (airQualityIndex < 30) {
            pieEntries.clear();
            pieEntries.add(new PieEntry(33.3f,getString(R.string.AQI_level_good) + "\uD83D\uDE00"));
            pieEntries.add(new PieEntry(33.3f,getString(R.string.AQI_level_fair) + "\uD83D\uDE42"));
            pieEntries.add(new PieEntry(33.3f,getString(R.string.AQI_level_terrible) + "\uD83D\uDE41"));
            color.add(green);
            color.add(lightYellow);
            color.add(lightRed);
        } else if (airQualityIndex < 40) {
            pieEntries.clear();
            pieEntries.add(new PieEntry(33.3f,getString(R.string.AQI_level_good) + "\uD83D\uDE00"));
            pieEntries.add(new PieEntry(33.3f,getString(R.string.AQI_level_fair) + "\uD83D\uDE42"));
            pieEntries.add(new PieEntry(33.3f,getString(R.string.AQI_level_terrible) + "\uD83D\uDE41"));
            color.add(lightGreen);
            color.add(yellow);
            color.add(lightRed);
        } else if (airQualityIndex <= 50) {
            pieEntries.clear();
            pieEntries.add(new PieEntry(33.3f,getString(R.string.AQI_level_good) + "\uD83D\uDE00"));
            pieEntries.add(new PieEntry(33.3f,getString(R.string.AQI_level_fair) + "\uD83D\uDE42"));
            pieEntries.add(new PieEntry(33.3f,getString(R.string.AQI_level_terrible) + "\uD83D\uDE41"));
            color.add(lightGreen);
            color.add(lightYellow);
            color.add(red);
        } else {
            pieEntries.clear();
            pieEntries.add(new PieEntry(33.3f,getString(R.string.AQI_level_good) + "\uD83D\uDE00"));
            pieEntries.add(new PieEntry(33.3f,getString(R.string.AQI_level_fair) + "\uD83D\uDE42"));
            pieEntries.add(new PieEntry(33.3f,getString(R.string.AQI_level_terrible) + "\uD83D\uDE41"));
            color.add(lightGreen);
            color.add(lightYellow);
            color.add(red);
        }

        pieDataSet = new PieDataSet(pieEntries, "");
        pieDataSet.setColors(color);
        pieDataSet.setValueTextColor(Color.BLACK);  // Set color
        pieDataSet.setValueTextSize(14f);  // Set size
        pieDataSet.setDrawValues(true);

// Create a PieData with your PieDataSet and set it to the PieChart
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);

// Invalidate the PieChart to refresh it
        pieChart.invalidate();

        pieDataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getPieLabel(float value, PieEntry pieEntry) {
                // return label instead of value
                return pieEntry.getLabel();
            }
        });

        airQualityViewModel.getText().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer stringId) {
                if (stringId != null) {
                    //binding.airQualityIndexDescription.setText(getString(stringId));
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
