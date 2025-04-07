package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class GestionCartes {
	public static <T> T extraire1(List<T> liste) {
		Random r = new Random();
		int ind = r.nextInt(liste.size()-1);
		T output = liste.get(ind);
		liste.remove(ind);
		return output;
	}
	
	public static <T> T extraire2(List<T> liste) {
		Random r = new Random();
		int ind = r.nextInt(liste.size()-1);
		ListIterator<T> it= liste.listIterator(ind);
		T output = it.next();
		it.remove();
		return output;
	}
	
	public static <T> List<T> melanger(List<T> l){
		List<T> output= new ArrayList<>();
		while(l.size()!=1) {
			output.add(extraire1(l));
		}
		output.add(l.get(0));
		return output;
	}
	
	public static <T> boolean verifierMelange(List<T> l1, List<T> l2) {
		ListIterator<T> it1= l1.listIterator();
		while(it1.hasNext()) {
			T elt=it1.next();
			if(Collections.frequency(l1, elt)!=Collections.frequency(l2, elt)) {
				return false;
			}
		}
		return true;
	}
	
	public static <T> List<T> rassembler(List<T> l) {
		List<T> output= new ArrayList<>();
		while(!l.isEmpty()) {
			T elt = l.get(0);
			for(T e : l) {
				if(e==elt) {
					output.add(e);
					l.remove(e);
				}
			}
		}
		return output;
	}
	
	public static <T> boolean verifierRassemblement(List<T> l) {
		ListIterator<T> it=l.listIterator();
		T previous=null;
		while(it.hasNext()) {
			if(it.next()!=previous) {
				ListIterator<T> it2=l.listIterator(it.nextIndex());
				while(it2.hasNext()) {
					if(it2.next()==previous) {
						return false;
					}
				}
			}
		}
		return true;
	}
}



