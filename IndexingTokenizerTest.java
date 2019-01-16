package test_tokenizer;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;
import tokenizer.IndexingTokenizer;

public class IndexingTokenizerTest {
	
    public IndexingTokenizerTest() {
    	
    }

    // the 3 functions run in reverse order!
    @Test
    public void testTokenize() {
        System.out.println("tokenize term");
        String s = "project";
        
        IndexingTokenizer mine = new IndexingTokenizer();
        soln.tokenizer.IndexingTokenizer soln = new soln.tokenizer.IndexingTokenizer();
        
        ArrayList<String> expected = soln.tokenize(s);
        System.out.println(expected);
        ArrayList<String> result = mine.tokenize(s);
        System.out.println(result);
        
        assertEquals(expected, result);

    }
    
    @Test
    public void testTokenizeMultiple() {
        System.out.println("tokenize multiple terms, including uppercase and lowercase");
        String s = "tRUe or fAlSe";
        
        IndexingTokenizer mine = new IndexingTokenizer();
        soln.tokenizer.IndexingTokenizer soln = new soln.tokenizer.IndexingTokenizer();
        
        ArrayList<String> expected = soln.tokenize(s);
        System.out.println(expected);
        ArrayList<String> result = mine.tokenize(s);
        System.out.println(result);
        
        assertEquals(expected, result);

    }
    
    @Test
    public void testTokenizeHyphens() {
        System.out.println("tokenize hyphenated word");
        String s = "state-of-the-art and art-of-the-state";
        
        IndexingTokenizer mine = new IndexingTokenizer();
        soln.tokenizer.IndexingTokenizer soln = new soln.tokenizer.IndexingTokenizer();
        
        ArrayList<String> expected = soln.tokenize(s);
        System.out.println(expected);
        ArrayList<String> result = mine.tokenize(s);
        System.out.println(result);
        
        assertEquals(expected, result);

    }

}
