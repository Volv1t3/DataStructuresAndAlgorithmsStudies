package Helpers;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;

import java.security.cert.CertPathBuilderResult;
import java.util.Stack;

public class MathExpressionHelpers {

    public static boolean bracketPairingAlgorithm(String mathExpression) throws IllegalArgumentException {
        //! Main Internal Variables
        int openBracketCount = 0; /*Will be used to count the number of opening brackets, just aesthetical*/
        Stack<String> bracketStrack = new Stack<String>(); /*Will be used to check in the end if we have an even
        expression by seeing if there are any values left in it*/
        boolean result = false;
        //! Base Case.1: The given String is null or is empty
        if (mathExpression == null) {
            throw new IllegalArgumentException("The given String is null");
        }
        //! Base Case.2: The given String is empty
        if (mathExpression.isEmpty() || mathExpression.isBlank()) {
            throw new
                    IllegalArgumentException("The given String is empty or is blank");
        }
        //! Inductive Step.1: Analyze the given String
        for(int i = 0; i < mathExpression.length(); i++) {
            char ch = mathExpression.charAt(i);
            if(isOpenBracket(ch)) {
                bracketStrack.push(String.valueOf(ch));
                openBracketCount++;
            } else if(isCloseBracket(ch)) {
                if(bracketStrack.isEmpty()) {
                    return false;
                }
                char top = bracketStrack.pop().charAt(0);
                if(!matches(top, ch)) {
                    return false;
                }
            }
        }
        return bracketStrack.isEmpty();
    }
    //! Helper methods for bracketPairingAlgorithm
    private static boolean isOpenBracket(char ch) {
        return ch == '(' || ch == '[' || ch == '{';
    }

    private static boolean isCloseBracket(char ch) {
        return ch == ')' || ch == ']' || ch == '}';
    }

    private static boolean matches(char open, char close) {
        return (open == '(' && close == ')') ||
                (open == '[' && close == ']') ||
                (open == '{' && close == '}');
    }


    public static int simpleOperatorInterpeter(String mathExpression)
    {
        //! Defining internal variables
        Stack<Integer> operandStack = new Stack<Integer>();
        Stack<Character> operatorStack = new Stack<Character>();

        //! Base Case 1: String is null
        if (mathExpression == null ) {throw new IllegalArgumentException("The given String is null");}
        //! Base Case 2: String is empty
        if (mathExpression.isEmpty() || mathExpression.isBlank()) {throw new
                IllegalArgumentException("The given String is empty or is blank");}

        //! Inductive Step: Analyzing the given String
        for(int i = 0; i < mathExpression.length(); i++) {
            char characterAtAnalysis = mathExpression.charAt(i);
            /*If the character is numeric we pass it into the operand stack*/
            if (characterAtAnalysis == '+' || characterAtAnalysis == '-')
            {
                while (!operatorStack.isEmpty() &&
                        (operatorStack.peek() == '+'
                                || operatorStack.peek() == '-'
                                || operatorStack.peek() == '*'
                                || operatorStack.peek() == '/'))
                {
                    processAnOperator(operandStack, operatorStack);
                }
                operatorStack.push(characterAtAnalysis);
            }
            else if (characterAtAnalysis == '*' || characterAtAnalysis == '/')
            {
                while (!operatorStack.isEmpty() &&
                        (operatorStack.peek() == '*'
                                || operatorStack.peek() == '/'))
                {
                    processAnOperator(operandStack, operatorStack);
                }
                operatorStack.push(characterAtAnalysis);
            }
            else if (characterAtAnalysis == '(')
            {
                operatorStack.push(characterAtAnalysis);
            }
            else if (characterAtAnalysis == ')')
            {
                while (operatorStack.peek() != '(')
                {
                    processAnOperator(operandStack, operatorStack);
                }
                operatorStack.pop();
            }
            else
            {
                operandStack.push(Character.getNumericValue(characterAtAnalysis));
            }
        }
        while (!operatorStack.isEmpty())
        {
            processAnOperator(operandStack, operatorStack);
        }

        return operandStack.pop();
    }

    //? Helper methods for simpleOperatorInterpeter

    private static void processAnOperator(Stack<Integer> operands, Stack<Character> operators)
    {
        char extractedOperator = operators.pop();
        int rightOperand = operands.pop();
        int leftOperand = operands.pop();

        if (extractedOperator == '+')
        {
            operands.push(leftOperand + rightOperand);
        }
        else if (extractedOperator == '-')
        {
            operands.push(leftOperand - rightOperand);
        }
        else if (extractedOperator == '*')
        {
            operands.push(leftOperand * rightOperand);
        }
        else if (extractedOperator == '/')
        {
            operands.push(leftOperand / rightOperand);
        }
    }


    //! Main Method
    public static void main(String[] args) {
        String mathExpression = "{[(2+ 4)]}";
        System.out.println(bracketPairingAlgorithm(mathExpression));
        System.out.println(simpleOperatorInterpeter("(2+((5+5+5+5+3)*4)-3)+1"));
    }
}
