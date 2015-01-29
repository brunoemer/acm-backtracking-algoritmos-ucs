import java.io.*;
import java.util.*;

class Main {
	private int t; // total sum, max 1000
	private int n; // number of integers in the list, from 1 to 12
	private ArrayList<Integer> x = new ArrayList<>(); // list of numbers, each number must be less than 100
	private ArrayList<ArrayList<Integer>> result = new ArrayList<>();
	private ArrayList<Integer> v = new ArrayList<>();

	public static void main(String[] args) {
		boolean finish;
		ArrayList<Main> v = new ArrayList<>();
		while(true) {
			Main m = new Main();
			finish = m.start();
			if(finish == false){
				v.add(m);
			}else{
				break;
			}
		}
		
		for (Main main : v) {
			main.makeSum();
		}
	}

	static String ReadStr (int maxLg, boolean with_space)  // utility function to read from stdin
	{
		byte lin[] = new byte [maxLg];
		int lg = 0, car = -1;
		String line = "";

		try
		{
		    while (lg < maxLg)
		    {
				car = System.in.read();
				if ((car < 0) || (car == '\n') || (with_space && car == ' ')) break;
				lin [lg++] += car;
		    }
		}
		catch (IOException e)
		{
		    return (null);
		}

		if ((car < 0) && (lg == 0)) return (null);  // eof
		return (new String (lin, 0, lg));
	}

	boolean start() {
		try{
			this.t = Integer.parseInt(Main.ReadStr(5, true));
		}catch(NumberFormatException e){
			return true;
		}
		try{
			this.n = Integer.parseInt(Main.ReadStr(3, true));
		}catch(NumberFormatException e){
			return true;
		}
		
		if(this.t == 0 && this.n == 0){
			return true;
		}
		
		try{
			for(int i = 0; i < this.n; i++){
				this.x.add(Integer.parseInt(Main.ReadStr(4, true)));
			}
		}catch(NumberFormatException e){
			System.out.println(e);
			return true;
		}
		
		//4 6 4 3 2 2 1 1
//		this.t = 4;
//		this.n = 6;
//		this.x.add(4);
//		this.x.add(3);
//		this.x.add(2);
//		this.x.add(2);
//		this.x.add(1);
//		this.x.add(1);

		//400 12 50 50 50 50 50 50 25 25 25 25 25 25
//		this.t = 400;
//		this.n = 12;
//		this.x.add(50);
//		this.x.add(50);
//		this.x.add(50);
//		this.x.add(50);
//		this.x.add(50);
//		this.x.add(50);
//		this.x.add(25);
//		this.x.add(25);
//		this.x.add(25);
//		this.x.add(25);
//		this.x.add(25);
//		this.x.add(25);
		
		return false;
	}
	
	void makeSum() {
		System.out.println("Sums of "+this.t+":");
		this.makeRecursion(this.v, 0, 0, 0);
		
		if(this.result.size() > 0){
			for (ArrayList<Integer> integers : this.result) {
				for (int i = 0; i < integers.size(); i++) {
					if(i != 0){
						if(integers.get(i) != null){
							System.out.print("+"+integers.get(i));
						}
					}else{
						System.out.print(integers.get(i));
					}
				}
				System.out.println();
			}
		}else{
			System.out.println("NONE");
		}
	}
	
	void makeRecursion(ArrayList<Integer> xlist, int nxlist, int sum, int ii) {
		if(sum == this.t) {
			this.addOut((ArrayList<Integer>) xlist.clone());
			return;
		}
		if(sum > this.t || ii == this.n){
			return;
		}
		for(int i = ii; i < this.n; i++) {
			int xx = this.x.get(i);
			xlist.add(xx);
			
			this.makeRecursion(xlist, nxlist+1, sum + xx, i+1);
			
			xlist.remove(xlist.size()-1);
		}
	}

	void addOut(ArrayList<Integer> xlist) {
		boolean found = false;
		for (int i = 0; i < this.result.size(); i++) {
			if(this.result.get(i).containsAll(xlist) && xlist.containsAll(this.result.get(i)) && this.result.get(i).size() == xlist.size()) {
				found = true;
				break;
			}
		}
		if(found == false){
			this.result.add(xlist);
		}
	}
}
