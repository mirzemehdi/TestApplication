package com.mmk.testapplication.repository;

import javax.inject.Inject;

public class CalculatorRepository {

    @Inject
    public CalculatorRepository() {
    }

    public String calculateExpression(String expression) {
        if (expression == null || expression.isEmpty()) return "";
        String[] numbers = expression.split("\\+");
        double result = 0;
        for (String s : numbers) {
            try {
                double number = Double.parseDouble(s);
                result += number;
            } catch (NumberFormatException e) {
                result += 0;
            }

        }
        String resultInString = String.valueOf(result);

        //Means it is integer without point
        if (result % 1 == 0) return String.valueOf(Math.round(result));
        else return resultInString;

    }

    public String getExpression(String currentExpr, String value) {
        String finalExpression = currentExpr;
        if (value.equals("x")) {
            if (!currentExpr.isEmpty())
                finalExpression = currentExpr.substring(0, currentExpr.length() - 1);
        } else {
            if (canAddNewValue(currentExpr, value))
                finalExpression = finalExpression.concat(value);
        }
        return finalExpression;
    }

    private boolean canAddNewValue(String currentExpr, String value) {


        if (!currentExpr.isEmpty()) {
            String[] numbers = currentExpr.split("\\+");
            int lastIndex = numbers.length - 1;
            try {
                String lastNumber = numbers[lastIndex];
                //To prevent writing more than one .(point) in one number
                if (lastNumber.contains(".") && value.equals(".")) return false;

                //To prevent writing more than one zero at the beginning
                if (lastNumber.contains("0") && lastNumber.length() == 1 && value.equals("0"))
                    return false;

            } catch (ArrayIndexOutOfBoundsException e) {
                return true;
            }
        }
        return true;

    }
}
