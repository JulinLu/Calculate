import static org.junit.Assert.*;

import java.util.Arrays; 
import java.util.Collection;

import org.junit.Assert; 
import org.junit.Test; 
import org.junit.runner.RunWith; 
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class PremiumTweetsServiceTest {    
	private int numberOfTweets;    
	private double expectedFee;    
	@Parameters (name = "Run #calculateFeesDue : {0}/10={1}")
	public static Collection <Object []> data() {        
		return Arrays.asList(new Object[][] { { 0, 0.00 }, { 50, 5.00 },
				{ 99, 9.90 }, { 100, 10.00 }, { 101, 10.08 }, { 200, 18},
				{ 499, 41.92 }, { 500, 42 }, { 501, 42.05 }, { 1000, 67 },
				{ 10000, 517 }, });    
		}    
	public PremiumTweetsServiceTest(int numberOfTweets, double expectedFee) {        
		super();        
		this.numberOfTweets = numberOfTweets;
		this.expectedFee = expectedFee;    
	} 
	

	@Test    public void shouldCalculateCorrectFee() {        
		PremiumTweetsService premiumTweetsService = new PremiumTweetsService();        
		double calculatedFees = premiumTweetsService.calculateFeesDue(numberOfTweets);        
		assertEquals((Double)calculatedFees, (Double)this.expectedFee);
	}
}