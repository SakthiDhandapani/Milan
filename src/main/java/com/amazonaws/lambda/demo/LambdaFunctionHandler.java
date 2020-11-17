package com.amazonaws.lambda.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class LambdaFunctionHandler implements RequestHandler<Object, String> {

    @Override
    public String handleRequest(Object input, Context context) {
        context.getLogger().log("Input: " + input);
        int sum = callAddition();
        int sub = callSub();
        int mul = callMul();
        context.getLogger().log("Addition: " + sum);
        context.getLogger().log("Subtraction: " + sub);
        context.getLogger().log("Multiplication: " + mul);
        return "Hello"+input+"Welcome";
    }
    public int callAddition(){
        int numOne = 5;
        int numTwo = 10;
        return numOne+numTwo;
    }

        public int callSub(){
        int numOne = 5;
        int numTwo = 10;
        return numTwo - numTwo;
    }

        public int callMul(){
        int numOne = 5;
        int numTwo = 10;
        return numOne*numTwo;
    }
}
