package WORDLE_app;

public class checkGuess {
		
	public static int[] verify(String secretWord, String guessWord) {
		secretWord = secretWord.toUpperCase();
		guessWord = guessWord.toUpperCase();
		int [] vals = new int[secretWord.length()];
		if(secretWord.length() != guessWord.length()) {
			System.out.println("Guess word is the wrong length!");
			System.out.println("The secret word is " + secretWord.length() + " letters long.");
			return vals;
		}
		for(int i = 0; i<secretWord.length(); i++) {
			if (vals[i] == 2) continue;
			if(secretWord.charAt(i) == guessWord.charAt(i)) {
				vals[i] = 2;
			}else{
				for(int j = 0; j<secretWord.length(); j++){
					if(j == i || vals[j] != 0) continue;
					if(secretWord.charAt(j) == guessWord.charAt(j)) {
						vals[j] = 2;
						continue;
					}
					if(secretWord.charAt(i) == guessWord.charAt(j)) {
						vals[j] = 1;
						break;
					}
				}
			}
			
		}
		return vals;
	}
}
