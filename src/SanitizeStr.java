import java.util.regex.*;

public class SanitizeStr {

	private String in;
	private String filtered;
	private int wrong = 0;
	private int[][] polycoeff = new int[4][2];
	private String variable = "test";


	// Extracts the coefficients from the input and checks for any errors in the input
	public SanitizeStr(String s){

		if(s.length() == 0){ wrong =1; }
		in = s;
		filtered = "+";
		for(int i=0; i<4; i++) {
			polycoeff[i][1] = 1;
		}
		
		// get rid of unnecessary characters and get the variable used
		for(int i=0; i<in.length(); i++){
			char c = in.charAt(i);

			if(Character.isLetter(c)){
				if(variable == "test"){
					variable = "";
					variable += c;
				}
				else{
					if(c != variable.charAt(0)){
						wrong = 1;
					}
				}
			}

			if(Character.isLetter(c)){
				variable = "";
				variable += c;
			}
			if(c=='(' || c==')' || c==' ' || c=='\t'){
				continue;
			}
			filtered += c;
		}

		String fil2 = "";
		// put plus in frontof minuses
		for(int i=1; i<filtered.length(); i++){
			fil2 += filtered.charAt(i-1);
			if(filtered.charAt(i) == '-'){
				fil2 += "+";
			}
		}
		fil2 += filtered.charAt(filtered.length()-1);
		filtered = fil2.substring(1,fil2.length());
		String[] tok = filtered.split("[\\+]");

		for(int i=0; i<tok.length; i++){
			if(tok[i].length() > 0){
				try {
					int x = Integer.parseInt(tok[i]);
					polycoeff[0][0] = x;
				}
				catch(NumberFormatException nFE) {
					if(tok[i].indexOf('/') == -1){
						if(matching(tok[i]) == 0){
							wrong = 1;
							break;
						}
						else{
							int l = tok[i].length();
							if(l>1 && tok[i].charAt(l-2) == '^'){
								if(tok[i].charAt(l-1) == '2'){
									polycoeff[2][0] = getcoeff(tok[i]);
								}
								else if(tok[i].charAt(l-1) == '3'){
									polycoeff[3][0] = getcoeff(tok[i]);
								}
								else if(tok[i].charAt(l-1) == '1'){
									polycoeff[1][0] = getcoeff(tok[i]);
								}
							}
							else{
								polycoeff[1][0] = getcoeff(tok[i]);
							}
						}
					}
					else{
						if(matchingslash(tok[i]) == 0){
							wrong = 1;
							break;
						}
						else{
							int l = tok[i].length();
							if(tok[i].charAt(l-2) == '^'){
								if(tok[i].charAt(l-1) == '2'){
									getcoeffslash(tok[i],2);
								}
								else if(tok[i].charAt(l-1) == '3'){
									getcoeffslash(tok[i],3);
								}
							}
							else{
								getcoeffslash(tok[i],1);
							}
						}
					}
				}
			}
		}
		if(polycoeff[0][0]>12 || polycoeff[0][0]<-12) { wrong = 1; } // const term should be within this range
		if(polycoeff[0][0]==0 && polycoeff[1][0]==0 && polycoeff[2][0]==0 && polycoeff[3][0]==0) { wrong = 1; } // all coeff are zero
		else{
			for(int i=0; i<4; i++){ // simplify the values
				if(polycoeff[i][0]%polycoeff[i][1] == 0){
					polycoeff[i][0] /= polycoeff[i][1];
					polycoeff[i][1] = 1;
				}
				else if(polycoeff[i][1]%polycoeff[i][0] == 0){
					polycoeff[i][1] /= polycoeff[i][0];
					polycoeff[i][0] = 1;
				}
			}
		}
	}

	//Retrieve the coefficients
	private int getcoeff(String tok){
		int index = tok.indexOf('*');
		if(index == -1){
			if(tok.charAt(0) == '-')
				return -1;
			return 1;
		}
		String t = tok.substring(0,index);
		return Integer.parseInt(t);
	}

	// Retrieve fractional coefficients 
	private void getcoeffslash(String tok,int x){
		int index = tok.indexOf('/');
		String numerator = tok.substring(0,index);
		int index2 = tok.indexOf('*', index+1);
		String denominator = tok.substring(index+1, index2);
		polycoeff[x][0] = Integer.parseInt(numerator);
		polycoeff[x][1] = Integer.parseInt(denominator);
	}

	// match if the input is in acceptable format
	private int matching(String tok){
		int m1=0, m2=0, m3=0, m4=0;
		Pattern p1 = Pattern.compile("^-?[0-9]+\\*[a-zA-Z]\\^[1-3]$");
		Pattern p2 = Pattern.compile("^-?[a-zA-Z]\\^[1-3]$");
		Pattern p3 = Pattern.compile("^-?[0-9]+\\*[a-zA-Z]$");
		Pattern p4 = Pattern.compile("^-?[a-zA-Z]$");

		if(p1.matcher(tok).find()) { m1 = 1; }
		if(p2.matcher(tok).find()) { m2 = 1; }
		if(p3.matcher(tok).find()) { m3 = 1; }
		if(p4.matcher(tok).find()) { m4 = 1; }

		return (m1+m2+m3+m4);
	}

	// match if the input is in acceptable format in case of fractions
	private int matchingslash(String tok){
		int m1=0, m2=0;
		Pattern p1 = Pattern.compile("^-?[0-9]+/[1-9]+\\*[a-zA-Z]\\^[1-3]$");
		Pattern p2 = Pattern.compile("^-?[0-9]+/[1-9]+\\*[a-zA-Z]$");

		if(p1.matcher(tok).find()) { m1 = 1; }
		if(p2.matcher(tok).find()) { m2 = 1; }

		return (m1+m2);
	}

	public int[][] getpolycoeff(){ // return all the coefficients
		return polycoeff;
	}

	public int getvalidity(){ // return if valid or not
		return (1-wrong);
	}

	public String getvar(){ // return the variable used in the input
		return variable;
	}
}