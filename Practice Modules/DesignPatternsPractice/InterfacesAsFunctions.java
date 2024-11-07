package DesignPatternsPractice;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class InterfacesAsFunctions {


    public static void main(String[] args) {
        Employee  employee = new Employee(new LinkedHashMap<>());
        employee.addEntry(Map.entry("name", "John Doe"));
        employee.addEntry(Map.entry("age", "30"));
        employee.addEntry(Map.entry("salary", "5000"));
        employee.addEntry(Map.entry("bonus", "500"));

        System.out.println(employee.toString());

    }

    public static class SalaryCalculatorAsFunction implements BiFunction<BigDecimal, BigDecimal, BigDecimal>
    {
        @Override
        public BigDecimal apply(BigDecimal baseSalary, BigDecimal bonus) {
            return baseSalary.add(bonus);
        }
    }

    public static class SalaryCalculationAsClass
    {
        public BigDecimal calculateSalary(BigDecimal baseSalary, BigDecimal bonus)
        {
            return baseSalary.add(bonus);
        }
    }
}

interface SalaryCalculation extends BiFunction<BigDecimal, BigDecimal, BigDecimal>
{
    /*Need only to impement the BiFuction .apply() method*/

    default BigDecimal applyIfTrue(boolean condition, BigDecimal baseSalary, BigDecimal bonus)
    {
        return condition ? apply(baseSalary, bonus) : BigDecimal.ZERO;
    }

    public abstract Boolean ifThenTrueApplyTrueThen(BigDecimal baseSalary, BigDecimal bonus);
}

record Employee(LinkedHashMap<String, String> dataSheet)
{
    public Employee(LinkedHashMap<String, String> dataSheet)
    {
        this.dataSheet = dataSheet;
    }

    public void addEntry(Map.Entry<String,String> input)
    {
        this.dataSheet.put(input.getKey(), input.getValue());
    }

    public String getEmployeeName()
    {
        return dataSheet.get("name");
    }

    //! ToString
    @Override
    public String toString()
    {
        return "Employee{" +
                "dataSheet=" + dataSheet +
                '}';
    }

}

