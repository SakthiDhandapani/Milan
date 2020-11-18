package com.amazonaws.lambda.demo;

import org.junit.Assert;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

public class LambdaFunctionHandlerTest {
	//@Test
	public void testLambdaFunctionHandler() {
		LambdaFunctionHandler handler = new LambdaFunctionHandler();
		Object input = "Milan";
		Context ctx = createContext();
		Object output = handler.handleRequest(input, ctx);
		String name = null;
		System.out.println(name);
		System.out.println(name);
		System.out.println(name);
		System.out.println(name);
		System.out.println(name);
		System.out.println(name);
		System.out.println(name);
		System.out.println(name);
		if (output != null) {
			System.out.println(output.toString());
		}
		Assert.assertEquals("Hello" + input + "Welcome", output);
	}

	private Context createContext() {
		TestContext ctx = new TestContext();
		ctx.setFunctionName("milanfunction");

		return ctx;
	}

	//@Test
	public void testLambdaFunctionHandlerSum() {
		LambdaFunctionHandler handler = new LambdaFunctionHandler();
		Object output = handler.callAddition();
		String name = null;
		System.out.println(name);
		System.out.println(name);
		System.out.println(name);
		System.out.println(name);
		System.out.println(name);
		System.out.println(name);
		System.out.println(name);
		System.out.println(name);
		if (output != null) {
			System.out.println(output.toString());
		}
		Assert.assertEquals(15, output);
	}

	//@Test
	public void testLambdaFunctionHandlerSub() {
		LambdaFunctionHandler handler = new LambdaFunctionHandler();
		Object output = handler.callSub();
		String name = null;
		System.out.println(name);
		System.out.println(name);
		System.out.println(name);
		System.out.println(name);
		System.out.println(name);
		System.out.println(name);
		System.out.println(name);
		System.out.println(name);
		if (output != null) {
			System.out.println(output.toString());
		}
		Assert.assertEquals(5, output);
	}

	//@Test
	public void testLambdaFunctionHandlerMul() {
		LambdaFunctionHandler handler = new LambdaFunctionHandler();
		Object output = handler.callMul();
		String name = null;
		System.out.println(name);
		System.out.println(name);
		System.out.println(name);
		System.out.println(name);
		System.out.println(name);
		System.out.println(name);
		System.out.println(name);
		System.out.println(name);
		if (output != null) {
			System.out.println(output.toString());
		}
		Assert.assertEquals(50, output);
	}

}
