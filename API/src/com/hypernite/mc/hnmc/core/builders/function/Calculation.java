package com.hypernite.mc.hnmc.core.builders.function;

/**
 * @see com.hypernite.mc.hnmc.core.builders.CalculationBuilder#doOther(Calculation)
 */
@FunctionalInterface
public interface Calculation {
    double cal(double result);
}
