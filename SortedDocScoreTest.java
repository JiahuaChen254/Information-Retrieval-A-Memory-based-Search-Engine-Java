package test_index;

import static org.junit.Assert.*;
import org.junit.Test;
import index.SortedDocScore;

public class SortedDocScoreTest {
	
    public SortedDocScoreTest() {
    	
    }

    @Test
    public void testCompareToScoreDiff() {
        System.out.println("compareTo by score");
        
        SortedDocScore mine = new SortedDocScore(32, 3, "coffee");
        SortedDocScore compare = new SortedDocScore(12, 4, "cappuchino");
        
        // Rank by score
        int expected = -1; // mine should go before compare
        int result = mine.compareTo(compare);
        assertEquals(expected, result); 
    }
    
    /**
     * Test of compareTo method, of class SortedDocScore.
     */
    @Test
    public void testCompareToScoreSame() {
        System.out.println("compareTo by alphabet");
        
        SortedDocScore mine = new SortedDocScore(32, 3, "coffee"); 
        SortedDocScore compare = new SortedDocScore(32, 4, "cappuchino");
        
        boolean goesBefore = (mine.compareTo(compare) < 0) ? true : false; // compare should go before mine
        assertFalse(goesBefore); 
    }

}
