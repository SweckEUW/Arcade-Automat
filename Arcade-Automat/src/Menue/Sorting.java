package Menue;

import java.util.ArrayList;

public class Sorting {

	/**
	randomly uses heap or merge sort to sort scores in ascending order
	 */
	public static void sortInt(ArrayList<Player> a) {
		int x =(int)(Math.random());
		switch (x) {
		case 0:
			mergeSortInt(a, 0, a.size() - 1);
			break;
		case 1:
			heapSort(a);
			break;
		}		
	}

	/**
	sorts the character at t from the names in ascending order with merge sort
	 */
	public static void sortChar(ArrayList<Player> a, int t) {
	mergeSortChar(a, 0, a.size() - 1 , t);
	}
	
	
	/**
	merge sort for characters at t from the names
	 */
	public static void mergeSortChar(ArrayList<Player> a, int p, int r, int t) {
		if (p < r) {
			int q = (p + r) / 2;
			mergeSortChar(a, p, q,t);
			mergeSortChar(a, q + 1, r,t);
			mergeChar(a, p, q, r,t);
		}
	}
	
	public static void mergeChar(ArrayList<Player> a, int p, int q, int r,int t) {
		int lsize = q - p + 1;
		int rsize = r - q;
		ArrayList<Player> la = new ArrayList<Player>();
		ArrayList<Player> ra = new ArrayList<Player>();
		for (int i = 0; i < lsize; ++i)
			la.add(a.get(p + i));
		for (int j = 0; j < rsize; ++j)
			ra.add(a.get(q + 1 + j));
		int i = 0;
		int j = 0;
		int k = p;
		while (i < lsize && j < rsize) {
			if (la.get(i).getName().toLowerCase().charAt(t) < ra.get(j).getName().toLowerCase().charAt(t)) {
				a.set(k, la.get(i));
				i++;
			} else {
				a.set(k, ra.get(j));
				j++;
			}
			k++;		
		}
		while (i < lsize) {
			a.set(k, la.get(i));
			i++;
			k++;
		}
		while (j < rsize) {
			a.set(k, ra.get(j));
			j++;
			k++;
		}
	}
	
	/**
	merge sort of scores in ascdening order
	 */
	public static void mergeSortInt(ArrayList<Player> a, int p, int r) {
		if (p < r) {
			int q = (p + r) / 2;
			mergeSortInt(a, p, q);
			mergeSortInt(a, q + 1, r);
			mergeInt(a, p, q, r);
		}
	}

	public static void mergeInt(ArrayList<Player> a, int p, int q, int r) {
		int lsize = q - p + 1;
		int rsize = r - q;
		ArrayList<Player> la = new ArrayList<Player>();
		ArrayList<Player> ra = new ArrayList<Player>();
		for (int i = 0; i < lsize; ++i)
			la.add(a.get(p + i));
		for (int j = 0; j < rsize; ++j)
			ra.add(a.get(q + 1 + j));
		int i = 0;
		int j = 0;
		int k = p;
		while (i < lsize && j < rsize) {
			if (la.get(i).getScore() > ra.get(j).getScore()) {
				a.set(k, la.get(i));
				i++;
			} else {
				a.set(k, ra.get(j));
				j++;
			}
			k++;
		}
		while (i < lsize) {
			a.set(k, la.get(i));
			i++;
			k++;
		}
		while (j < rsize) {
			a.set(k, ra.get(j));
			j++;
			k++;
		}
	}

	public static void heapSort(ArrayList<Player> al) {
		int n = al.size();

		// Heap erstellen
		for (int i = n / 2 - 1; i >= 0; i--)
			heap(al, n, i);
		//einer nach dem anderen wird rausgezogen
		for (int i = n - 1; i >= 0; i--) {

			//Bewege aktuelle Wurzel zum Ende
			Player temp = al.get(0);
			al.set(0, al.get(i));
			al.set(i, temp);

			//größter im Heap wird rausgesucht
			heap(al, i, 0);
		}
	}

	public static void heap(ArrayList<Player> al, int n, int i) {
		int largest = i; //Größte als Wurzel
		int l = 2 * i + 1; 
		int r = 2 * i + 2; 

		//linkes Kind größer als die Wurzel
		if (l < n && al.get(l).getScore() < al.get(largest).getScore())
			largest = l;

		// rechtes Kind größer als das Größte
		if (r < n && al.get(r).getScore() < al.get(largest).getScore())
			largest = r;

		// größte ist größer als die Wurzel
		if (largest != i) {
			Player swap = al.get(i);
			al.set(i, al.get(largest));
			al.set(largest, swap);

			// rekursiver Durchgang durch den nächsten Teilbaum
			heap(al, n, largest);
		}
	}

	/**
	searches a string in the list and returns a list with all players that contain the String in the name
	 */
	public static ArrayList<Player> search(ArrayList<Player> searchList,String b) {
		ArrayList<Player> coppyOfScores=new ArrayList<Player>();
		for(Player p:searchList) //copy the list we search in
			coppyOfScores.add(p);
		
		for(int w=0;w<coppyOfScores.size();w++) 
			if(coppyOfScores.get(w).getName().length()<b.length()) 
				coppyOfScores.remove(w);	
			
		for(int w=0;w<searchList.size();w++) 
			if(searchList.get(w).getName().length()<b.length()) 
				searchList.remove(w);	
		
		for(int t=0;t<b.length();t++) { //do this for every character in the string 
			//first sort the characters 
			
			sortChar(coppyOfScores,t);
			sortChar(searchList,t);
			
			int k=binarySearch(coppyOfScores,b.toLowerCase().charAt(t),0,coppyOfScores.size()-1,t); //k= index of found character
			
			if(k==-1) { //nothing found
				coppyOfScores.clear();
				return coppyOfScores;
			}else {
				int o=k; //obere grenze
				int u=k; //untere grenze
				//get douplicate characters
				for(int i=k+1;i<coppyOfScores.size();i++) 
					if(coppyOfScores.get(i).getName().toLowerCase().charAt(t)==coppyOfScores.get(k).getName().toLowerCase().charAt(t)) 
						o++;
					else 
						break;
				
				for(int i=k-1;i>0;i--) 
					if(coppyOfScores.get(i).getName().toLowerCase().charAt(t)==coppyOfScores.get(k).getName().toLowerCase().charAt(t)) 
						u--;
					else 
						break;
				
				ArrayList<Player> tmp=new ArrayList<Player>();
				
				
				//copy old list in a temporary list
				for(Player p:coppyOfScores)
					tmp.add(p);
				
				//clear old list and add the players that contain the string in the name
				coppyOfScores.clear();
				
				for(int i=u;i<=o;i++) 
					coppyOfScores.add(tmp.get(i));			
			}
		}
		//sort the scores in ascending order again
		sortInt(searchList);
		sortInt(coppyOfScores);
		return coppyOfScores;
	}
	
	/**
	searches a string in the list and returns a list with all players that contain the String in the name
	@param s = searched value
	@param e = end index of the interval
	@param a = beginning of the interval
	@param t = character index of the name we search
	 */
	public static int binarySearch(ArrayList<Player> b,char s,int a,int e, int t){ 	   
		int mid;        
	    do {
	        mid = (a + e) / 2; //calculate middle of the interval
	        if (b.get(mid).getName().toLowerCase().charAt(t) > s) 
	        	e=mid-1; //move upper interval border to the middle of the list
	        else
	           	a=mid+1; //move lower interval border to the middle of the list          	
	    }while(b.get(mid).getName().toLowerCase().charAt(t) != s && a<=e); //calculate until interval is negative or the number is found
	              
	    if (b.get(mid).getName().toLowerCase().charAt(t) == s) //return index of found number
          return mid; 
	            
	    //nothing found
	    return -1;
    } 
	
}
	

