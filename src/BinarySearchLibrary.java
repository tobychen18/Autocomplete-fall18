import java.util.*;

public class BinarySearchLibrary {
	
	public static <T>
	    int firstIndexSlow(List<T> list, 
	    		           T target, Comparator<T> comp) {
		int index = Collections.binarySearch(list, target,comp);
		
		if (index < 0) return index;
		
		while (0 <= index && comp.compare(list.get(index),target) == 0) {
			index -= 1;
		}
		return index+1;
	}
	
	/**
	 * Uses binary search to find the index of the first object in parameter
	 * list -- the first object o such that comp.compare(o,target) == 0.
	 * 
	 * This method should not call comparator.compare() more than 1+log n times
	 * @param list is the list of objects being searched
	 * @param target is the object being searched for
	 * @param comp is how comparisons are made
	 * @return index i such that comp.compare(list.get(i),target) == 0
	 * and there is no index < i such that this is true. Return -1
	 * if there is no such object in list.
	 */
	
	public static <T>
    	int firstIndex(List<T> list, 
	               	T target, Comparator<T> comp) {
		
		
		int low = -1; //set low = -1 so that we know the loop invariant is true
		int high = list.size()-1; //set high = list.size -1 so that we know the loop invariant is true
		if(list.size() < 1) {
			return -1; //if the list size is < 1 aka list is empty return -1 aka there is no first index
		}
		// (low,high] contains target
		// TODO: complete method
		while(low+1 != high) { //as long as we haven't deduced our bounds to one single number
			int mid = (low+high)/2; //the middle is the avg between low and high
			if(comp.compare(list.get(mid), target) < 0) { //if the middle is smaller than our target, the we can set low = to mid because we know that it isn't low and will be higher than low
				low = mid;
			}
			else {
				high = mid; //otherwise if it is the same as our target make it equal high 
			}
			//keep going until you find the first one aka low is not target and high is target and it is the one right after low (low +1 )
		}
		if(comp.compare(list.get(high), target) == 0) { //check to make sure that our high value is indeed the same as our target
		return high;
		}
		return -1; //if our high value isn't the same as our target then the target doesn't exist so we return -1
	}	

	/**
	 * Uses binary search to find the index of the last object in parameter
	 * list -- the first object o such that comp.compare(o,target) == 0.
	 * 
	 * This method should not call comparator.compare() more than 1+log n times
	 * @param list is the list of objects being searched
	 * @param target is the object being searched for
	 * @param comp is how comparisons are made
	 * @return index i such that comp.compare(list.get(i),target) == 0
	 * and there is no index > i such that this is true. Return -1
	 * if there is no such object in list.
	 */
	public static <T>
	int lastIndex(List<T> list, 
               	  T target, Comparator<T> comp) {
		
		//similar idea to firstIndex 
		int low = 0; //set low = - so that we know the loop invariant is true
		int high = list.size(); //set high = list.size so that we know the loop invariant is true
		
		if(list.size() < 1) {
			return -1; //if the list size is < 1 aka list is empty return -1 aka there is no last index of target
		}
		// target in [low,high)
		// TODO: complete method
		while(high-1 != low) { //as long as we haven't deduced our bounds to one single number
			int mid = (low+high)/2;
			if(comp.compare(list.get(mid), target) > 0) { //if the mid is greater than target then we can set our high to mid
				high = mid; 
			}
			else {
				low = mid; //else it is mid then we set low to mid and keep going until our low value is equal to target and the next value (high) is not equal to target
			}
		}
		if(comp.compare(list.get(low), target) == 0) { //check to make sure that our low value is indeed the same as our target
		return low; 
		}
		return -1; //if our low value isn't the same as our target then the target doesn't exist so we return -1
	}
	
}
