// Note: This class needs the dictionary text file (dict.txt) to be placed
// in the same directory as this Java class file

package WORDLE_app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Dictionary {

	private ArrayList<String> wordList;

	public Dictionary(String filename) {
		try	{
			InputStreamReader isReader = new InputStreamReader(getClass().getResourceAsStream("dict.txt"));
			BufferedReader bReader = new BufferedReader(isReader);
			String line;
			wordList = new ArrayList<String>();
			while ((line = bReader.readLine()) != null)
				wordList.add(line.toUpperCase());
			bReader.close();
			isReader.close();
		}
		catch (Exception e) {
			System.out.println("£rror reading dictionary file " + filename);
			System.out.println(e);
		}
	}

	// Select a random word form the first 300 words in the dictionary
	// Dictionary words are ordered by common usage, most common first
	public String pickRandomWord() {
		return wordList.get((int)(Math.random()*300.0));
	}

	// Check if a given word is in the dictionary
	//
	public boolean contains(String word) {
		for (int i=0; i<wordList.size(); i++)
			if (wordList.get(i).equals(word))
				return true;
		return false;
	}
}
