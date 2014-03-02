//import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;

import junit.framework.TestCase;
//if use extended TestCase, the @Test is not needed to to used, the CalTest will be identified as a Junit Class 
//if not using extended TestCase, need import ort.junit.Test and @Test should be used. see CalculatorTest Class
public class CalTest extends TestCase {
	private Calculator cal;
	
	public void setUp() {
		cal = new Calculator();
	}

	public void tearDown() {
	}
	public void testAdd() {
		int result = cal.add(1, 2);
		Assert.assertEquals(3, result);
	}

	public void testMinus() {
		int result = cal.minus(1, 2);
		Assert.assertEquals(-1, result);
	}

	public void testMultiply(){
		int result = cal.multiple(2,3);
		Assert.assertEquals(6, result);
	}

	public void testDivide() throws Exception{
		int result = cal.divide(10,2);
		Assert.assertEquals(5, result);
		
	}

	public void testDivide1() {
		int result = 0;
		try { 
			result = cal.divide(6,4);
		}
		catch (Exception e){
			e.printStackTrace();
			e.toString();
			//Assert.fail();
		}
	}

	public void testDivide2() {
		Throwable tx=null;
		try {
			cal.divide(4, 0);
		}
		catch (Exception ex){
			tx=ex;
		}
		Assert.assertNotNull(tx);
		//Assert.assertEquals(Excepion.class, tx.getClass());
		Assert.assertEquals("除数不能为零！", tx.getMessage());
	}
	
}
