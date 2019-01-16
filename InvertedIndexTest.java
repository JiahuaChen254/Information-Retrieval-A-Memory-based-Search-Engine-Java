package test_index;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Random;
import org.junit.jupiter.api.Test;
import index.DocScore;
import index.Index;
import index.InvertedIndex;
import tokenizer.IndexingTokenizer;
import tokenizer.Tokenizer;

public class InvertedIndexTest {
	
	public InvertedIndexTest() {
		
	}
	
    @Test
    public void testGetDocFreq() {
        System.out.println("getDocumentFreq(String term)");
		
        Index mine = new InvertedIndex(new io.FileDocSource("files/Part1/awards_1994"), 
				new tokenizer.IndexingTokenizer(), new score.TFIDFScoringFun());              
		Index soln = new InvertedIndex(new soln.io.FileDocSource("files/Part1/awards_1994"), 
				   					new soln.tokenizer.IndexingTokenizer(), new soln.score.TFIDFScoringFun());
		mine.buildIndex(); 
		soln.buildIndex();
		
		Tokenizer tokenize = new IndexingTokenizer();
		Random rand = new Random();
		int i = rand.nextInt(mine.getDocSource().getNumDocs()-1);
		ArrayList<String> tokens = tokenize.tokenize(mine.getDocSource().getDoc(i));
		int j = rand.nextInt(tokens.size()-1);
		String token = tokens.get(j);
		
        int result = mine.getDocumentFreq(token);
        System.out.println(result);
        int expected = soln.getDocumentFreq(token);
        System.out.println(expected);
        
        assertEquals(expected, result);
    }
    
    @Test
    public void testGetSortedSearchResults() {
        System.out.println("getSortedSearchResults(String term)");
        
        Index mine = new InvertedIndex(new io.FileDocSource("files/Part1/awards_1994"), 
				new tokenizer.IndexingTokenizer(), new score.TFIDFScoringFun());              
		Index soln = new InvertedIndex(new soln.io.FileDocSource("files/Part1/awards_1994"), 
				   					new soln.tokenizer.IndexingTokenizer(), new soln.score.TFIDFScoringFun());
		mine.buildIndex(); 
		soln.buildIndex();
		
		Tokenizer tokenize = new IndexingTokenizer();
		Random rand = new Random();
		int i = rand.nextInt(mine.getDocSource().getNumDocs()-1);
		ArrayList<String> tokens = tokenize.tokenize(mine.getDocSource().getDoc(i));
		int j = rand.nextInt(tokens.size()-1);
		String token = tokens.get(j);
        
        ArrayList<DocScore> result = mine.getSortedSearchResults(token);
        System.out.println(result);
        ArrayList<DocScore> expected = soln.getSortedSearchResults(token);
        System.out.println(expected);
        
        assertEquals(result, expected);
    }                   

}
