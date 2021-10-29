import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class wordCountTest {

	@Test
	public void testWordTest() {
		wordTest test = new wordTest();
		int output = test.wordCount("the");
		assertEquals(56, output);
		
		
	}

}
