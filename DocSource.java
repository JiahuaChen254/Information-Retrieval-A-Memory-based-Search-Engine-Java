package io;

/** This class provides an abstract interface to any source of documents.
 *  See StaticDocSource for a simple instantiation.
 *
 */
public abstract class DocSource {
	public abstract int getNumDocs();
	public abstract String getDoc(int id);
}
