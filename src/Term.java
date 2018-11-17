
/*************************************************************************
 * @author Kevin Wayne
 *
 * Description: A term and its weight.
 * 
 *************************************************************************/

import java.util.Comparator;

public class Term implements Comparable<Term> {

	private final String myWord;
	private final double myWeight;

	/**
	 * The constructor for the Term class. Should set the values of word and
	 * weight to the inputs, and throw the exceptions listed below
	 * 
	 * @param word
	 *            The word this term consists of
	 * @param weight
	 *            The weight of this word in the Autocomplete algorithm
	 * @throws NullPointerException
	 *             if word is null
	 * @throws IllegalArgumentException
	 *             if weight is negative
	 */
	public Term(String word, double weight) {

		// TODO: Complete Term constructor
		if(word == null) {
			throw new NullPointerException("Null term"); //if word is null then throw null pointer exception
		}
		if(weight < 0) {
			throw new IllegalArgumentException("negative weight " + weight); //if weight < 0 then throw illegal argument
		}
		myWord = word;
		myWeight = weight;
	}

	/**
	 * The default sorting of Terms is lexicographical ordering.
	 */
	public int compareTo(Term that) {
		return myWord.compareTo(that.myWord); //compare the word lexicographically
	}

	/**
	 * Getter methods, use these in other classes which use Term
	 */
	public String getWord() {
		return myWord;
	}

	public double getWeight() {
		return myWeight;
	}

	public String toString() {
		return String.format("(%2.1f,%s)", myWeight, myWord);
	}

	@Override
	public boolean equals(Object o) {
		Term other = (Term) o;
		return this.compareTo(other) == 0;
	}

	/**
	 * A Comparator for comparing Terms using a set number of the letters they
	 * start with. This Comparator may be useful in writing your implementations
	 * of Autocompletors.
	 *
	 */
	public static class PrefixOrder implements Comparator<Term> {
		private final int myPrefixSize;

/**
 * The constructor for prefix order, where we set the size of the prefix
 * @param r is the size of the prefix we want to compare
 */
		public PrefixOrder(int r) {
			this.myPrefixSize = r; 
		}

		/**
		 * Compares v and w lexicographically using only their first r letters.
		 * If the first r letters are the same, then v and w should be
		 * considered equal. This method should take O(r) to run, and be
		 * independent of the length of v and w's length. You can access the
		 * Strings to compare using v.word and w.word.
		 * If one string does not have enough letters, only compare those letters, and if those are the same return the bigger word
		 * @param v/w
		 *            - Two Terms whose words are being compared
		 */
		public int compare(Term v, Term w) {

			int minOfV = Math.min(v.myWord.length(), myPrefixSize); //finds if the word in term v is smaller than myPrefixSize
			int minOfW = Math.min(w.myWord.length(), myPrefixSize); //finds if the word in term w is smaller than myPrefixSize
			int minOfBothWords = Math.min(minOfV, minOfW); //finds the smallest out of those two in order to compare them
			for(int i = 0; i < minOfBothWords ; i++) { //compare the avaliable letters or up to myPrefixSize
				if(v.myWord.charAt(i) != w.myWord.charAt(i)) { //if the characters are different
					return v.myWord.charAt(i)-w.myWord.charAt(i); //return the difference between the characters
				}
			}
			return minOfV - minOfW; //otherwise if the characters were all the same it means that the characters we were comparing were all the same. 
			//If we compared the same lengths of W and V it means that the words w and v were the same
			//if the lengths differ then return which one was bigger, aka the difference in sizes
		}

	}

	/**
	 * A Comparator for comparing Terms using only their weights, in descending
	 * order. This Comparator may be useful in writing your implementations of
	 * Autocompletor
	 *
	 */
	public static class ReverseWeightOrder implements Comparator<Term> {
		public int compare(Term v, Term w) {
			// TODO: implement compare
			//compares the weights of the terms if v.getWeight > w.getWeight you get a positive difference and you reverse it (with negative sign) so that it is in descending order
			return (int) -(v.getWeight() - w.getWeight());
		}
	}

	/**
	 * A Comparator for comparing Terms using only their weights, in ascending
	 * order. This Comparator may be useful in writing your implementations of
	 * Autocompletor
	 *
	 */
	public static class WeightOrder implements Comparator<Term> {
		public int compare(Term v, Term w) {
			//make it by weight in ascending order by just comparing which weight is bigger
			//if v is heavier weight it will be positive so v is bigger than w aka after w
			return (int) (v.getWeight() - w.getWeight());
		}
	}
}
