package score;

import index.Index;

public class TFIDFScoringFun implements TermScoringFun {
	private Index _Index;
	
	@Override
	public void init(Index s) {
		// Note that for TFIDF, the Index gives you access to the document frequency of any term!
		_Index = s;
		
	}

	@Override
	public double scoreToken(String term, int freq) { // TFd,t 
		// (1 + log10(TFd,t)) ·log10(N/DFt)
		// TFd,t is the frequency (count) of term t in document d
		// DFt is the frequency (count) of the number of documents in the corpus (data set) that contain term t
		// and N is the total number of documents.
		double termfreq = (double)freq;
		double docfreq = (double)_Index.getDocumentFreq(term);
		double N = (double)_Index.getDocSource().getNumDocs();
		return (1+Math.log10(termfreq))*Math.log10(N/docfreq);
	}

}
