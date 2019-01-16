package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileDocSource extends DocSource {
	private ArrayList<File> _fds;
	
	
	public FileDocSource(String location) {
		// uses FileFinder to find all of the documents in "location"
		_fds = new ArrayList<File>();
		_fds.addAll(FileFinder.GetAllFiles(location));
	}
	
	@Override
	public int getNumDocs() {
		// returns the number of documents found
		return _fds.size();
	}
	
	@Override
	public String getDoc(int id) {
		// loads the file corresponding to document location "id"
		StringBuilder sb = new StringBuilder();
		String s = "";
		try {			
			BufferedReader cin = new BufferedReader(new FileReader(_fds.get(id)));			
			do {	
				s = cin.readLine();
				if (s != null) {
					sb.append(s + " ");
				}
			} while (s != null);
			cin.close();			
		}
		catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException");
		}
		catch (IOException e) {
			System.out.println("IOException");
		}
		return sb.toString();
	}
}
