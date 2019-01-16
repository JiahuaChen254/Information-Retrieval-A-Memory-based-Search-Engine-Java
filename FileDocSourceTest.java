package test_io;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import io.FileDocSource;

public class FileDocSourceTest {
	
	public FileDocSourceTest() {
		
	}

    @Test
    public void testGetNumDocs() {
        System.out.println("getNumDocs");
        
        FileDocSource mine = new FileDocSource("files/Part1/awards_1994/awd_1994_00");
        soln.io.FileDocSource soln = new soln.io.FileDocSource("files/Part1/awards_1994/awd_1994_00");
        
        int expected = soln.getNumDocs();
        System.out.println(expected);
        
        int result = mine.getNumDocs();
        System.out.println(result);
        
        assertEquals(expected, result);            
    }

}
