package ca.quantum.quants.it.housefy;

import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;

public class CustomValueFormatter extends ValueFormatter {
    private final String[] dates;

    public CustomValueFormatter(String[] dates) {
        this.dates = dates;
    }

    @Override
    public String getFormattedValue(float value) {
        int index = (int) value;
        if (index >= 0 && index < dates.length) {
            return dates[index];
        } else {
            return "";
        }
    }

    @Override
    public String getBarLabel(BarEntry barEntry) {
        return Integer.toString((int) barEntry.getY());
    }
}
