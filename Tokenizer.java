package tokenizer;

import java.util.ArrayList;

/** Provides a single method that turns a String s into a list of
 *  individual tokens (i.e., word terms) for indexing.
 *
 */
public interface Tokenizer {
	public abstract ArrayList<String> tokenize(String s);
	// take a string and split into individual words
	// "-" & "upper case to lower case"
}
