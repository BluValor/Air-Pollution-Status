package classes.performance.strategies;

import classes.api_objects.Value;

import java.util.LinkedList;

public class ValueCounter {

    public static Double countAverageValue(LinkedList<Value> values) {
        return values.stream()
                .mapToDouble(n -> Double.valueOf(n.value))
                .average()
                .orElse(Double.NaN);
    }
}
