package index;

public class SortedDocScore extends DocScore implements Comparable {
	
	public SortedDocScore(double score, int doc_id, String content) {
		// take this information and pass to the class that I inherit from
		super(score, doc_id, content);
	} 

	public SortedDocScore(DocScore ds) {	
		super(ds);
	}
		
    @Override
    public int compareTo(Object o) {
        if (o instanceof SortedDocScore) {
            SortedDocScore sds = (SortedDocScore) o;
            if (this.getScore() == sds.getScore()) { // score same, compare content
                return this.getContent().compareTo(sds.getContent()); 
    			// s1.compareTo(s2) will sort capital "Z" before lower-case "a"
    			// The result is a negative integer if this String object lexicographically precedes the argument string
    			// The result is 0 if the strings are equal
    			// Or use ASCII Table
            }
            else if (this.getScore() < sds.getScore()) { // when this score is smaller, place later
                return 1;
            }
            else { // this score is greater, should be placed earlier
                return -1;
            }
        }
        else {
            return -1;
        }
    }
	
	// need to override both equals() and hashCode()
	@Override
	public boolean equals(Object o) {
		// compare by _score and _content but not _docID
		if (o == null || !(o instanceof DocScore)) {
			return false; // cannot be equal
		}
		DocScore ds = (DocScore)o; // if o is not DocScore, ClassCastException
		return _score == ds.getScore() && _content.equals(ds.getContent());
	}
	
	@Override
	public int hashCode() {
		// Objects that are equal (according to their equals()) must return the same hash code. 
		// This value needs not remain consistent from one execution of an application to another.
		// It’s not required for different objects to return different hash codes.
		// combine content hashcode with the hashcode for the score (which you will have to generate)
		return new Double(_score).hashCode() + 37*_content.hashCode() + 37*37*_docID;
		// Typically any prime>2 will do above
		// The above is slightly better than _score + _content.hashCode()
		// in terms of distributing hash codes over the full range
		// i.e., if both _score and _content.hashCode() tend to be
		// small then hashCodes would be concentrated for small numbers
	}
	
	// My test
/*	public static void main(String[] args) {	
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
		System.out.println(doc1.compareTo(doc4));
		System.out.println("Does doc1 come before doc5? "+ (doc1.compareTo(doc5) < 0 ? "yes" : "no")); //yes
		System.out.println(doc1.compareTo(doc5));
		// Testing hashCode()
		System.out.println("Doc1 hash code: "+ doc1.hashCode()); // 992203781
		System.out.println("Doc2 hash code: "+ doc2.hashCode()); // -2020291539
	}*/
	
}
