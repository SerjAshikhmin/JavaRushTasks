package com.javarush.task.task34.task3404;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Рекурсия для мат. выражения
*/

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.recurse("-2+(-2+(-2)-2*(2+2))", 0); //expected output 0.5 6
    }

    public void recurse(final String expression, int countOperation) {
        //implement
        if (countOperation == 0) {
            countOperation = countOperations(expression, countOperation);
            String preparedExpression = expression;
            if (expression.contains(" ")) {
                preparedExpression = preparedExpression.replaceAll(" ", "");
            }
            if (countOperation == 0) {
                DecimalFormat dF = new DecimalFormat("#.##");
                dF.setRoundingMode(RoundingMode.HALF_EVEN);
                preparedExpression = preparedExpression.replaceAll("\\(", "");
                preparedExpression = preparedExpression.replaceAll("\\)", "");
                double result = Double.parseDouble(preparedExpression);
                System.out.println(dF.format(result) + " " + countOperation);
            } else {
                preparedExpression = "(" + preparedExpression + ")";
                recurse(preparedExpression, countOperation);
            }
            return;
        }
        int openParenthesis = -1;
        int closingBracket = -1;
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '(') {
                openParenthesis = i;
            }
            if (expression.charAt(i) == ')') {
                closingBracket = i;
                break;
            }
        }
        if (openParenthesis == -1 && closingBracket == -1) {
            if (!checkForOperationAvailable(expression)) {
                DecimalFormat dF = new DecimalFormat("#.##");
                dF.setRoundingMode(RoundingMode.HALF_EVEN);
                double result;
                if (expression.isEmpty()) {
                    result = 0;
                } else {
                    result = Double.parseDouble(expression);
                }
                System.out.println(dF.format(result) + " " + countOperation);
            }
        } else {
            String bracketedExpression = expression.substring(openParenthesis + 1, closingBracket);
            if (bracketedExpression.startsWith("--")) {
                bracketedExpression = bracketedExpression.replaceAll("--", "");
            }
            bracketedExpression = bracketedExpression.replaceAll("--", "+");
            if (!checkForOperationAvailable(bracketedExpression)) {
                if (openParenthesis > 2) {
                    String expressionBeforeParentheses = expression.substring(openParenthesis - 3, openParenthesis);
                    if (checkForTrigonometricOperationAvailableAndCalculate(expressionBeforeParentheses, bracketedExpression, expression, openParenthesis, closingBracket, countOperation)) {
                        return;
                    }
                }
                recurse(expression.substring(0, openParenthesis) + bracketedExpression + expression.substring(closingBracket + 1), countOperation);
                return;
            }
            if (checkForExponentiationAvailableAndCalculate(expression, countOperation, openParenthesis, closingBracket, bracketedExpression)) {
                return;
            }
            if (checkForDivisionAndMultiplicationAvailableAndCalculate(expression, countOperation, openParenthesis, closingBracket, bracketedExpression)) {
                return;
            }
            if (checkForAdditionOrDivisionOrMultiplicationAvailableAndCalculate(expression, countOperation, openParenthesis, closingBracket, bracketedExpression, '*')) {
                return;
            }
            if (checkForAdditionOrDivisionOrMultiplicationAvailableAndCalculate(expression, countOperation, openParenthesis, closingBracket, bracketedExpression, '/')) {
                return;
            }
            if (checkForAdditionOrDivisionOrMultiplicationAvailableAndCalculate(expression, countOperation, openParenthesis, closingBracket, bracketedExpression, '+')) {
                return;
            }
            if (checkForSubtractionAvailableAndCalculate(expression, countOperation, openParenthesis, closingBracket, bracketedExpression)) {
                return;
            }
        }
    }

    public Solution() {
        //don't delete
    }

    private boolean checkForTrigonometricOperationAvailableAndCalculate(String expressionBeforeParentheses, String bracketedExpression, String expression, int openParenthesis, int closingBracket, int countOperation) {
        if (expressionBeforeParentheses.equals("sin") || expressionBeforeParentheses.equals("cos") || expressionBeforeParentheses.equals("tan")) {
            double argument = Double.parseDouble(bracketedExpression) * Math.PI / 180;
            String result = "";
            switch (expressionBeforeParentheses) {
                case "sin" :
                    result = String.valueOf(BigDecimal.valueOf(Math.sin(argument)).setScale(6, RoundingMode.HALF_EVEN));
                    break;
                case "cos" :
                    result = String.valueOf(BigDecimal.valueOf(Math.cos(argument)).setScale(6, RoundingMode.HALF_EVEN));
                    break;
                case "tan" :
                    result = String.valueOf(BigDecimal.valueOf(Math.tan(argument)).setScale(6, RoundingMode.HALF_EVEN));
                    break;
                default:
                    break;
            }
            result = validateResult("", result, "");
            recurse(expression.substring(0, openParenthesis - 3) + result + expression.substring(closingBracket + 1), countOperation);
            return true;
        }
        return false;
    }

    private int countOperations(String expression, int countOperation) {
        countOperation += expression.split("\\*").length - 1;
        countOperation += expression.split("/").length - 1;
        countOperation += expression.split("\\^").length - 1;
        countOperation += expression.split("\\+").length - 1;
        countOperation += expression.split("-").length - 1;
        countOperation += expression.split("sin").length - 1;
        countOperation += expression.split("cos").length - 1;
        countOperation += expression.split("tan").length - 1;
        return countOperation;
    }

    private boolean checkForSubtractionAvailableAndCalculate(String expression, int countOperation, int openParenthesis, int closingBracket, String bracketedExpression) {
        if (bracketedExpression.contains("-") && (bracketedExpression.indexOf("-") != 0 || bracketedExpression.indexOf("-") == 0 && bracketedExpression.lastIndexOf("-") != 0)) {
            String regexForSubtraction = "[+-]?((\\d+\\.?\\d*)|(\\.\\d+))-((\\d+\\.?\\d*)|(\\.\\d+))";
            Pattern pattern = Pattern.compile(regexForSubtraction);
            Matcher matcher = pattern.matcher(bracketedExpression);
            if (matcher.groupCount() == 0) {
                recurse(expression.substring(0, openParenthesis) + bracketedExpression + expression.substring(closingBracket + 1), countOperation);
            }
            if (matcher.find()) {
                String operation = matcher.group();
                double firstNumber = Double.parseDouble(operation.substring(0, operation.lastIndexOf('-')));
                double secondNumber = Double.parseDouble(operation.substring(operation.lastIndexOf('-') + 1));
                String result = String.valueOf(BigDecimal.valueOf(firstNumber - secondNumber).setScale(6, RoundingMode.HALF_EVEN));
                result = validateResult(bracketedExpression, result, operation);
                bracketedExpression = bracketedExpression.replaceFirst(regexForSubtraction, result);
                recurse(expression.substring(0, openParenthesis + 1) + bracketedExpression + expression.substring(closingBracket), countOperation);
                return true;
            }
        }
        return false;
    }

    private boolean checkForAdditionOrDivisionOrMultiplicationAvailableAndCalculate(String expression, int countOperation,
                                                                                    int openParenthesis, int closingBracket, String bracketedExpression, char operationSign) {
        if (bracketedExpression.contains(String.valueOf(operationSign))) {
            String regexForOperation = "-?\\d+\\.?\\d*\\%s-?\\d+\\.?\\d*";
            Pattern pattern = Pattern.compile(String.format(regexForOperation, operationSign));
            Matcher matcher = pattern.matcher(bracketedExpression);
            if (matcher.find()) {
                String operation = matcher.group();
                double firstNumber = Double.parseDouble(operation.substring(0, operation.indexOf(operationSign)));
                double secondNumber = Double.parseDouble(operation.substring(operation.indexOf(operationSign) + 1));
                String result = "";
                switch (operationSign) {
                    case '+':
                        result = String.valueOf(BigDecimal.valueOf(firstNumber + secondNumber).setScale(6, RoundingMode.HALF_EVEN));
                        break;
                    case '/':
                        result = String.valueOf(BigDecimal.valueOf(firstNumber / secondNumber).setScale(6, RoundingMode.HALF_EVEN));
                        break;
                    case '*':
                        result = String.valueOf(BigDecimal.valueOf(firstNumber * secondNumber).setScale(6, RoundingMode.HALF_EVEN));
                        break;
                    default:
                        break;
                }
                result = validateResult(bracketedExpression, result, operation);
                bracketedExpression = bracketedExpression.replaceFirst(String.format(regexForOperation, operationSign), result);
                recurse(expression.substring(0, openParenthesis + 1) + bracketedExpression + expression.substring(closingBracket), countOperation);
                return true;
            }
        }
        return false;
    }

    private boolean checkForDivisionAndMultiplicationAvailableAndCalculate(String expression, int countOperation, int openParenthesis, int closingBracket, String bracketedExpression) {
        if (bracketedExpression.contains("*") && bracketedExpression.contains("/")) {
            String regexForOperation = "-?\\d+\\.?\\d*\\%s-?\\d+\\.?\\d*";
            int multiplicationPosition = bracketedExpression.indexOf("*");
            int divisionPosition = bracketedExpression.indexOf("/");
            char operationSign;
            if (multiplicationPosition < divisionPosition) {
                operationSign = '*';
            } else {
                operationSign = '/';
            }
            Pattern pattern = Pattern.compile(String.format(regexForOperation, operationSign));
            Matcher matcher = pattern.matcher(bracketedExpression);
            if (matcher.find()) {
                String operation = matcher.group();
                double firstNumber = Double.parseDouble(operation.substring(0, operation.indexOf(operationSign)));
                double secondNumber = Double.parseDouble(operation.substring(operation.indexOf(operationSign) + 1));
                String result;
                if (operationSign == '*') {
                    result = String.valueOf(BigDecimal.valueOf(firstNumber * secondNumber).setScale(6, RoundingMode.HALF_EVEN));;
                } else {
                    result = String.valueOf(BigDecimal.valueOf(firstNumber / secondNumber).setScale(6, RoundingMode.HALF_EVEN));;
                }
                result = validateResult(bracketedExpression, result, operation);
                bracketedExpression = bracketedExpression.replaceFirst(String.format(regexForOperation, operationSign), result);
                recurse(expression.substring(0, openParenthesis + 1) + bracketedExpression + expression.substring(closingBracket), countOperation);
                return true;
            }
        }
        return false;
    }

    private boolean checkForExponentiationAvailableAndCalculate(String expression, int countOperation, int openParenthesis, int closingBracket, String bracketedExpression) {
        if (bracketedExpression.contains("^")) {
            String regexForExponentiation = "\\d+\\.?\\d*\\^-?\\d+\\.?\\d*";
            Pattern pattern = Pattern.compile(regexForExponentiation);
            Matcher matcher = pattern.matcher(bracketedExpression);
            if (matcher.find()) {
                String operation = matcher.group();
                double firstNumber = Double.parseDouble(operation.substring(0, operation.indexOf('^')));
                double secondNumber = Double.parseDouble(operation.substring(operation.indexOf('^') + 1));
                String result = String.valueOf(String.valueOf(BigDecimal.valueOf(Math.pow(firstNumber, secondNumber)).setScale(6, RoundingMode.HALF_EVEN)));
                result = validateResult(bracketedExpression, result, operation);
                bracketedExpression = bracketedExpression.replaceFirst(regexForExponentiation, result);
                recurse(expression.substring(0, openParenthesis + 1) + bracketedExpression + expression.substring(closingBracket), countOperation);
                return true;
            }
        }
        return false;
    }

    private String validateResult(String bracketedExpression, String result, String operation) {
        if (result.equals("0.0") || result.equals("0.00")) {
            result = "";
        }
        if (result.endsWith(".0")) {
            result = result.replace(".0", "");
        }
        if (result.endsWith(".00")) {
            result = result.replace(".00", "");
        }
        if (!result.isEmpty() && result.charAt(0) != '-' && bracketedExpression.indexOf(operation) != 0
                && Character.isDigit(bracketedExpression.charAt(bracketedExpression.indexOf(operation) - 1))) {
            result = "+" + result;
        }
        return result;
    }

    private boolean checkForOperationAvailable(String expression) {
        if (expression.contains("^") || expression.contains("*") || expression.contains("/") || expression.contains("+") ||
                (expression.contains("-") && (expression.indexOf("-") != 0 || expression.indexOf("-") == 0 && expression.lastIndexOf("-") != 0)) ||
                expression.contains("sin") || expression.contains("cos") || expression.contains("tan")) {
            return true;
        } else {
            return false;
        }
    }
}
