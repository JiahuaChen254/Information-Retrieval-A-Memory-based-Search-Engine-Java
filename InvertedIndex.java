package index;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

import io.DocSource;
import score.TermScoringFun;
import tokenizer.Tokenizer;

public class InvertedIndex extends Index {
	// maps terms (i.e., tokens from your Tokenizer) to a nested HashMap of { doc ID->term frequency in that doc ID }.
	private HashMap<String,HashMap<Integer,Integer>> _index;
	// maps terms to their document frequency: the count of documents that contain the word and is capped by the total #of documents
	private HashMap<String,Integer> _docFreq;
	
	public InvertedIndex(DocSource doc_source, Tokenizer tokenizer, TermScoringFun scoring) {
		super(doc_source, tokenizer, scoring);
		_index = new HashMap<String,HashMap<Integer,Integer>>();
		_docFreq = new HashMap<String,Integer>();
//		_scoring.init(this);
	} 

	@Override
	public void buildIndex() {
		ArrayList<String> tokens = new ArrayList<String>(); 
		for (int i = 0; i < _docSource.getNumDocs(); i++) {
			tokens.clear();
			tokens.addAll(_tokenizer.tokenize(_docSource.getDoc(i)));
			for (String token : tokens) {
				if (_index.containsKey(token)) { // If _index already has this token
					if (_index.get(token).containsKey(i)) {
						_index.get(token).put(i,_index.get(token).get(i)+1);
					}
					else { // If token does not have this docID, add docID and corresponding frequency
						_index.get(token).put(i, 1);
					}		 
				}
				else { // If _index does not have this token, add token and a new HashMap(docID, termfreq)
					_index.put(token, new HashMap<Integer,Integer>());
					_index.get(token).put(i, 1);
				}
			}
		}
		for (String token: _index.keySet()) {
			_docFreq.put(token, _index.get(token).size());                                                
		}
		System.out.println("Done building index."); 
	} // Index all files in DocSource
	
	
	@Override
	public Integer getDocumentFreq(String term) {
	// The document frequency for each term (needed by the TF-IDF calculation) should also be computed at indexing time
	// and be stored separately in private HashMap<String,Integer>docFreq;that maps terms to their document frequency. 
		return _docFreq.get(term); 
	} // Return document frequency of the term
	
	@Override
	public ArrayList<DocScore> getSortedSearchResults(String query) {
		// maintain a HashMap from document IDs to a document’s current score (if non-zero); 
		// as each query term is processed, the score for that term in a document can be added to that document’s 
		// current score in the HashMap (note that term scores are provided by InvertedIndex’s scoring data member). 
		// Once all query terms are processed, you should create SortedDocScore’s for all matching documents and their non-zero scores. 
		// getSortedSearchResults should return a list of SortedDocScore’s sorted by the ordering defined above for SortedDocScore.
		ArrayList<String> tokens = _tokenizer.tokenize(query);
		ArrayList<DocScore> al = new ArrayList<DocScore>();
		TreeSet<SortedDocScore> ts = new TreeSet<SortedDocScore>(); // Sorted set of all matching documents and their non-zero scores
		int freq_tind = 1;
		String token = "";
		double score = 0;
		HashMap<Integer,Double> documents = new HashMap<Integer,Double>(); // Mapping docID to its total score calculated for each token
		for (int i = 0; i < tokens.size(); i++) {
			token = tokens.get(i);
			if (_index.containsKey(token)) { // Check if token exists in _index
				for (int docID : _index.get(token).keySet()) { // Iterate through every document containing the token
					freq_tind = _index.get(token).get(docID);
					score = _scoring.scoreToken(token,freq_tind); // Calculate score awarded to this docID from each token
					if (documents.containsKey(docID)) { // Add to existing score if docID exists
						documents.put(docID,documents.get(docID)+score);
					}
					else { // If docID is new, put the current score in
						documents.put(docID,score);
					}
				} 
			}
		}
		for (int docID: documents.keySet()) { // Create TreeSet of DocScores (containing doc score, id, content) of all documents in HashMap, to sort by scores
			SortedDocScore sds = new SortedDocScore(documents.get(docID), docID, _docSource.getDoc(docID));
            ts.add(sds);
        }
		// Convert sortedDocs back to ArrayList and return
		al.addAll(ts);
	    return al;
	} // Return a ranked list of search results for the provided query   

}
