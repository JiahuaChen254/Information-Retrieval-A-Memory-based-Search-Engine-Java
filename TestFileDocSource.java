package test;

import io.FileFinder;
import index.SortedDocScore;
import io.FileDocSource;
//import soln.io.FileDocSource; // Import YOUR FileDocSource class, not the professor's like here

public class TestFileDocSource {
	
	public static void main(String[] args) {
		FileDocSource fds = new FileDocSource("files");
		System.out.println(FileFinder.GetAllFiles("files"));
		System.out.println("FileDocSource found " + fds.getNumDocs() + " files");
		System.out.println("Example document: ");
		System.out.println(fds.getDoc(0));		
		
		// Test code for SortedDocScore.java
		// sort by _score from biggest to smallest and _content alphabetically
		SortedDocScore doc1 = new SortedDocScore(1.0,0,"The quick brwn fox jups over the lazy dog");
		SortedDocScore doc3 = new SortedDocScore(1.0,2,"The quick brwn fox jups over the lazy dog");
		SortedDocScore doc1Copy = new SortedDocScore(doc1);
		SortedDocScore doc2 = new SortedDocScore(0.9,1,"Amazingly few discotheques provide jukeboxes");
		SortedDocScore doc4 = new SortedDocScore(1.0,0,"Tha");
		SortedDocScore doc5 = new SortedDocScore(1.0,0,"aThe");
		// Testing equals(Object o)
		System.out.println("Does doc1 equal doc1Copy? "+ (doc1.equals(doc1Copy)? "yes" : "no")); //yes
		System.out.println("Does doc1 equal doc3? "+ (doc1.equals(doc3)? "yes" : "no")); //yes
		System.out.println("Does doc1 equal doc2? "+ (doc1.equals(doc2)? "yes" : "no")); //no
		// Testing compareTo(Object o)
		System.out.println("Does doc1 come before doc2? "+ (doc1.compareTo(doc2) < 0 ? "yes" : "no")); //yes doc2-doc1
		System.out.println("Does doc1 come before doc3? "+ (doc1.compareTo(doc3) < 0 ? "yes" : "no")); //no
		System.out.println("Does doc1 come before doc4? "+ (doc1.compareTo(doc4) < 0 ? "yes" : "no")); //no
		System.out.println("Does doc1 come before doc5? "+ (doc1.compareTo(doc5) < 0 ? "yes" : "no")); //yes
		// Testing hashCode()
		System.out.println("Doc1 hash code: "+ doc1.hashCode()); // 992203781
		System.out.println("Doc2 hash code: "+ doc2.hashCode()); // -2020291539
	}

}
