package test_tokenizer;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;
import tokenizer.Tokenizer;
import tokenizer.SimpleTokenizer;

public class SimpleTokenizerTest {

	@Test
	public void testTokenize() {
		Tokenizer tok = new SimpleTokenizer();
		ArrayList<String> tokens = tok.tokenize("A state-of-the-art product.");
		assertEquals("Failed lowercase", "a", tokens.get(0)); // "A" vs "a"
		assertEquals("Failed hyphen test", "state-of-the-art", tokens.get(1));
		assertEquals("Failed simple token", "product", tokens.get(2));
	}
	
	@Test
	public void myNewTest() {
		// Create all objects to be used for this test
		
		// run the code you want to test. Try to test a variety of different cases...
		// If you can, generate random input (although this is not mandatory)
		
		assertEquals("Who knows","Hello","hello");
	}

	@Test
	public void failAlways() {
		//Thread.sleep(3000);
		fail("This always fails");
	}
}
