package com.xter.support;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
	@Test
	public void addition_isCorrect() throws Exception {
		assertEquals(4, 2 + 2);
		System.out.println((-1) % 2 == 1 ? "奇数" : "偶数");
		System.out.println((-1) % 2 == 0 ? "偶数" : "奇数");
	}
}