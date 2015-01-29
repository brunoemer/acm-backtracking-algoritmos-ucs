import java.io.*;
import java.util.*;

class Main {
	int n; //number of words, max 100
	String[] words; // words, caracteres a-z A-Z 0-9 
	int m; //number of rules, max 1000
	String[] rules; // rules, caracteres 0 or #, 0 - min 1 max 7 times and # - not mandatory 

	void makeDigits(int[] digit, int ndig, String[] word, int rule_id, int ndigit) {
		for (int i = 0; i <= 9; i++) {
			if(ndigit > 0){
				digit[ndig] = i;
				this.makeDigits(digit, ndig+1, word, rule_id, ndigit-1);
			}
		}
		if(ndigit <= 0){
			String rule = this.rules[rule_id];
			int cword = 0, cdig = 0;
			for(int i = 0; i < rule.length(); i++){
				if(rule.charAt(i) == '#'){
					System.out.print(word[cword]);
					cword++;
				}else if(rule.charAt(i) == '0'){
					System.out.print(digit[cdig]);
					cdig++;
				}
			}
			System.out.println();
		}
	}
	
	void makeWords(String[] word, int nword, int rule_id, int nsharp, int ndigit) {
		if(nsharp <= 0){
			int[] digit = new int[ndigit];
			this.makeDigits(digit, 0, word, rule_id, ndigit);
			return;
		}
		for (int i = 0; i < this.n; i++) {
			word[nword] = this.words[i];
			this.makeWords(word, nword+1, rule_id, nsharp-1, ndigit);
		}
	}
	
	void makeRules() {
		for (int i = 0; i < this.m; i++) {
			String line = this.rules[i];
			int nsharp = line.length() - line.replace("#", "").length();
			int ndigit = line.length() - line.replace("0", "").length();
			String[] word = new String[nsharp];
			this.makeWords(word, 0, i, nsharp, ndigit);
		}
	}
	
	int start(String[] args) {
		/*BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			String line = br.readLine();
			if(line.isEmpty()){
				return 1;
			}
			this.n = Integer.parseInt(line);
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.words = new String[this.n];
		int i;
		for (i = 0; i < this.n; i++) {
			try {
				this.words[i] = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			this.m = Integer.parseInt(br.readLine());
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.rules = new String[this.m];
		for (i = 0; i < this.m; i++) {
			try {
				this.rules[i] = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	}*/

		/*Scanner input;
		try {
			input = new Scanner(new FileInputStream(args[0]));
			if(input.hasNextLine()){
			String line = input.nextLine();
			if(line == null || line.isEmpty()){
				return 1;
			}
			this.n = Integer.parseInt(line);
			}
			this.words = new String[this.n];
			int i;
			for (i = 0; i < this.n; i++) {
				if(input.hasNextLine()){
				this.words[i] = input.nextLine();
				}
			}
			
			if(input.hasNextLine()){
			this.m = Integer.parseInt(input.nextLine());
			}
			this.rules = new String[this.m];
			for (i = 0; i < this.m; i++) {
				if(input.hasNextLine()){
				this.rules[i] = input.nextLine();
				}
	       	}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		String line = Main.ReadLn(5);
		if(line == null || line.isEmpty()){
			return 1;
		}
		try{
			this.n = Integer.parseInt(line);
		}catch(Exception exc){
			return 1;
		}

		this.words = new String[this.n];
		int i;
		for (i = 0; i < this.n; i++) {
			this.words[i] = Main.ReadLn(256);
		}
		
		this.m = Integer.parseInt(Main.ReadLn(5));

		this.rules = new String[this.m];
		for (i = 0; i < this.m; i++) {
			this.rules[i] = Main.ReadLn(256);
       	}

		return 0;
	}
	
	static String ReadLn (int maxLg)  // utility function to read from stdin
	{
		byte lin[] = new byte [maxLg];
		int lg = 0, car = -1;
		String line = "";

		try
		{
		    while (lg < maxLg)
		    {
			car = System.in.read();
			if ((car < 0) || (car == '\n')) break;
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

	public static void main(String[] args) {
		int n = 100000, i;
		Main v[] = new Main[n];
		for(i = 0; i < n; i++){
			v[i] = new Main();
			if(v[i].start(args) == 1){
				break;
			}
		}
		
		for(int j = 0; j < i; j++){
			System.out.println("--");
			v[j].makeRules();
		}
	}

}
