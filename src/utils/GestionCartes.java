package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class GestionCartes {
	private static Random rand = new Random();
	
	private GestionCartes() {
	    throw new IllegalStateException("Utility class");
	  }
	
	public static <T> T extraire1(List<T> list) {
		int ind = rand.nextInt(list.size());
		T output = list.get(ind);
		list.remove(ind);
		return output;
	}

	public static <T> T extraire2(List<T> list) {
		int ind = rand.nextInt(list.size());
		ListIterator<T> it = list.listIterator(ind);
		T output = it.next();
		it.remove();
		return output;
	}

	public static <T> List<T> melanger(List<T> list) {
		List<T> output = new ArrayList<>();
		while (list.size() != 1) {
			output.add(extraire1(list));
		}
		output.add(list.get(0));
		return output;
	}

	public static <T> boolean verifierMelange(List<T> l1, List<T> l2) {
		for (T i : l1) {
			if (Collections.frequency(l1, i) != Collections.frequency(l2, i)) {
				return false;
			}
		}
		return true;
	}

	public static <T> List<T> rassembler(List<T> list) {
		List<T> output = new ArrayList<>();
		while (!list.isEmpty()) {
			List<T> toRemove = new ArrayList<>();
			T elt = list.get(0);
			for (T e : list) {
				if (e == elt) {
					output.add(e);
					toRemove.add(e);
				}
			}
			list.removeAll(toRemove);
		}
		return output;
	}
	
	private static <T> boolean checkReste(int ind, List<T> list, T elt) {
		for(ListIterator<T> iter = list.listIterator(ind); iter.hasNext();) {
			if(iter.next().equals(elt)) {
				return true;
			}
		}
		return false;
	}

	public static <T> boolean verifierRassemblement(List<T> list) {
		T previous = null;
		for(ListIterator<T> iter = list.listIterator(); iter.hasNext();) {
			T current = iter.next();
			if (current != previous) {
				if(checkReste(iter.nextIndex(), list, previous)) {
					return false;
				}
				previous = current;
			}
		}
		return true;
	}
}
