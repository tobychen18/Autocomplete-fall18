import java.util.*;

/**
 * 
 * Using a sorted array of Term objects, this implementation uses binary search
 * to find the top term(s).
 * 
 * @author Austin Lu, adapted from Kevin Wayne
 * @author Jeff Forbes
 * @author Owen Astrachan in Fall 2018, revised API
 */
public class BinarySearchAutocomplete implements Autocompletor {

	Term[] myTerms;

	/**
	 * Given arrays of words and weights, initialize myTerms to a corresponding
	 * array of Terms sorted lexicographically.
	 * 
	 * This constructor is written for you, but you may make modifications to
	 * it.
	 * 
	 * @param terms
	 *            - A list of words to form terms from
	 * @param weights
	 *            - A corresponding list of weights, such that terms[i] has
	 *            weight[i].
	 * @return a BinarySearchAutocomplete whose myTerms object has myTerms[i] =
	 *         a Term with word terms[i] and weight weights[i].
	 * @throws a
	 *             NullPointerException if either argument passed in is null
	 */
	public BinarySearchAutocomplete(String[] terms, double[] weights) {
		if (terms == null || weights == null) {
			throw new NullPointerException("One or more arguments null");
		}
		
		myTerms = new Term[terms.length];
		
		for (int i = 0; i < terms.length; i++) {
			myTerms[i] = new Term(terms[i], weights[i]);
		}
		
		Arrays.sort(myTerms);
	}

	/**
	 * Uses binary search to find the index of the first Term in the passed in
	 * array which is considered equivalent by a comparator to the given key.
	 * This method should not call comparator.compare() more than 1+log n times,
	 * where n is the size of a.
	 * 
	 * @param a
	 *            - The array of Terms being searched
	 * @param key
	 *            - The key being searched for.
	 * @param comparator
	 *            - A comparator, used to determine equivalency between the
	 *            values in a and the key.
	 * @return The first index i for which comparator considers a[i] and key as
	 *         being equal. If no such index exists, return -1 instead.
	 */
	public static int firstIndexOf(Term[] a, Term key, Comparator<Term> comparator) {	
		int index = BinarySearchLibrary.firstIndex(Arrays.asList(a), key, comparator);
		return index;
	}

	/**
	 * The same as firstIndexOf, but instead finding the index of the last Term.
	 * 
	 * @param a
	 *            - The array of Terms being searched
	 * @param key
	 *            - The key being searched for.
	 * @param comparator
	 *            - A comparator, used to determine equivalency between the
	 *            values in a and the key.
	 * @return The last index i for which comparator considers a[i] and key as
	 *         being equal. If no such index exists, return -1 instead.
	 */
	public static int lastIndexOf(Term[] a, Term key, Comparator<Term> comparator) {
		int index = BinarySearchLibrary.lastIndex(Arrays.asList(a), key, comparator);
		return index;
	}

	/**
	 * Required by the Autocompletor interface. Returns an array containing the
	 * k words in myTerms with the largest weight which match the given prefix,
	 * in descending weight order. If less than k words exist matching the given
	 * prefix (including if no words exist), then the array instead contains all
	 * those words. e.g. If terms is {air:3, bat:2, bell:4, boy:1}, then
	 * topKMatches("b", 2) should return {"bell", "bat"}, but topKMatches("a",
	 * 2) should return {"air"}
	 * 
	 * @param prefix
	 *            - A prefix which all returned words must start with
	 * @param k
	 *            - The (maximum) number of words to be returned
	 * @return An array of the k words with the largest weights among all words
	 *         starting with prefix, in descending weight order. If less than k
	 *         such words exist, return an array containing all those words If
	 *         no such words exist, reutrn an empty array
	 * @throws a
	 *             NullPointerException if prefix is null
	 */
	@Override
	public List<Term> topMatches(String prefix, int k) {	
		if(prefix == null) {
			throw new NullPointerException("prefix is null: not valid"); //if the prefix is null then throw a null pointer exception 
		}
		ArrayList<Term> list = new ArrayList<>(); //create an array list that will store our prefixes that match our prefixes. 
		Term termPrefix = new Term(prefix, 0); //create a term that represents the prefix String, weight is irrelevant
		int lowest = firstIndexOf(myTerms, termPrefix, new Term.PrefixOrder(prefix.length())); 
		int highest = lastIndexOf(myTerms, termPrefix, new Term.PrefixOrder(prefix.length())); 
		if(lowest == -1 || highest == -1) { //if the first term or the last term doesnt exist
			return list; //then return nothing because our prefix isn't in our array
		}
		for(int i = lowest; i<= highest; i++) { //loop from i, the first index to the highestindex so that you get all the terms with the same prefix
			list.add(myTerms[i]); //add to the list all the terms with the same prefix
		}
		Collections.sort(list, new Term.ReverseWeightOrder()); //then sort the terms in descending weight order so that the highest weight is first
		return list.subList(0, Math.min(k, list.size())); //take the part of the list up to k or up to everything in the list, which ever comes first
	}
}
