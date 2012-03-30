import java.util.regex.Pattern;

public class sanitizetrans {

	private String in;
	private String filtered;
	private int wrong = 0;
	private int[][] polycoeff = new int[6][2];
	private String variable = "test";
	private int alpha;
	private int beta;
	private int alphadeno;
	private int betadeno;
	private int whichfunc;


	// Extracts the coefficients from the input and checks for any errors in the input
	public sanitizetrans(String s){


		if(s.length() == 0){ wrong =1; }
		whichfunc = 0; alpha = 1; beta = 1; alphadeno = 1; betadeno = 1;
		in = s;
		filtered = "";
		for(int i=0; i<6; i++) {
			polycoeff[i][1] = 1;
		}
		// get rid of unnecessary characters
		for(int i=0; i<in.length(); i++){
			char c = in.charAt(i);
			if(c=='(' || c==')' || c==' ' || c=='\t' || c=='+'){
				continue;
			}
			filtered += c;
		}
		filtered = filtered.toLowerCase();

		if(filtered.indexOf("sin") != -1){ // if sin
			whichfunc = 2;
		}
		else if(filtered.indexOf("cos") != -1){ // if cos
			whichfunc = 3;
		}
		else if(filtered.indexOf("e") != -1){ // if e^x
			whichfunc = 1;
		}
		else{ // else incorrect
			wrong = 1;
		}

		if(whichfunc != 0){
			variable = "";
			variable += filtered.charAt(filtered.length()-1);
			if(matchingtrans(filtered) == 0){
				wrong = 1;
			}
			else{ // set coefficients of each term
				getalphabeta(filtered);
				if(whichfunc == 1){
					polycoeff[0][0] = 1;
					polycoeff[1][0] = pow(beta,1);
					polycoeff[1][1] = pow(betadeno,1);
					polycoeff[2][0] = pow(beta,2);
					polycoeff[2][1] = 2;
					polycoeff[2][1] *= pow(betadeno,2);
					polycoeff[3][0] = pow(beta,3);
					polycoeff[3][1] = 6;
					polycoeff[3][1] *= pow(betadeno,3);
				}
				else if(whichfunc == 2){
					polycoeff[1][0] = pow(beta,1);
					polycoeff[1][1] = pow(betadeno,1);
					polycoeff[3][0] = pow(beta,3)*(-1);
					polycoeff[3][1] = 6;
					polycoeff[3][1] *= pow(betadeno,3);
					polycoeff[5][0] = pow(beta,5);
					polycoeff[5][1] = 120;
					polycoeff[5][1] *= pow(betadeno,5);
				}
				else if(whichfunc == 3){
					polycoeff[0][0] = 1;
					polycoeff[2][0] = pow(beta,2)*(-1);
					polycoeff[2][1] = 2;
					polycoeff[2][1] *= pow(betadeno,2);
					polycoeff[4][0] = pow(beta,4);
					polycoeff[4][1] = 24;
					polycoeff[4][1] *= pow(betadeno,4);
				}
				for(int i=0; i<6; i++){
					polycoeff[i][0] *= alpha;
					polycoeff[i][1] *= alphadeno;
				}
			}
		}


		if(polycoeff[0][0]>12 || polycoeff[0][0]<-12) { wrong = 1; } // const term should be within this range
		if(polycoeff[0][0]==0 && polycoeff[1][0]==0 && polycoeff[2][0]==0 && polycoeff[3][0]==0 && polycoeff[4][0]==0 && polycoeff[5][0]==0) {
			wrong = 1; //  all coeff are zero
		}
	}

	//Retrieve the coeffecients alpha and beta
	private void getalphabeta(String tok){
		int index_alphabet = 0;
		while(true){
			if(Character.isLetter(tok.charAt(index_alphabet))){
				break;
			}
			index_alphabet++;
		}
		String part1 = tok.substring(0,index_alphabet);
		//System.out.println(tok.charAt(index_alphabet));
		if(whichfunc == 1){
			index_alphabet += 2;
		}
		else{
			index_alphabet += 3;
		}
		String part2 = tok.substring(index_alphabet);
		//        System.out.println(part1+" "+part2);
		if(part1.indexOf('/') == -1){
			if(part1.indexOf('*') == -1){
				alpha = 1;
			}
			else{
				alpha = Integer.parseInt(part1.substring(0, part1.indexOf('*')));
			}
			if(part1.length()>0 && part1.charAt(0) == '-'){
				alpha *= -1;
			}
		}
		else{
			int slash = part1.indexOf('/');
			alpha = Integer.parseInt(part1.substring(0,slash));
			alphadeno = Integer.parseInt(part1.substring(slash+1,part1.indexOf('*')));
			if(part1.length()>0 && part1.charAt(0) == '-'){
				alpha *= -1;
			}
		}

		if(part2.indexOf('/') == -1){
			if(part2.indexOf('*') == -1){
				beta = 1;
			}
			else{
				beta = Integer.parseInt(part2.substring(0, part2.indexOf('*')));
			}
			if(part2.length()>0 && part2.charAt(0) == '-'){
				beta *= -1;
			}
		}
		else{
			int slash = part2.indexOf('/');
			beta = Integer.parseInt(part2.substring(0,slash));
			betadeno = Integer.parseInt(part2.substring(slash+1,part2.indexOf('*')));
			if(part2.length()>0 && part2.charAt(0) == '-'){
				beta *= -1;
			}
		}
	}

	// see if the input format is valid
	private int matchingtrans(String tok){
		int m1=0, m2=0;
		Pattern[] q = new Pattern[6];

		q[0] = Pattern.compile("^-?[0-9]+\\*$");
		q[1] = Pattern.compile("^-?$");
		q[2] = Pattern.compile("^-?[0-9]+/[1-9]+\\*$");

		int index = -1;
		if(tok.indexOf("sin") != -1){
			index = tok.indexOf("sin");
		}
		else if(tok.indexOf("cos") != -1){
			index = tok.indexOf("cos");
		}
		else if(tok.indexOf("e") != -1){
			index = tok.indexOf("e");
		}
		int len = tok.length();
		String part1 = tok.substring(0,index);
		if(whichfunc == 1){
			index += 2;
		}
		else{
			index += 3;
		}
		String part2 = tok.substring(index,len-1);

		System.out.println(part1+" "+part2);
		for(int i=0; i<3; i++){
			if(q[i].matcher(part1).find()){
				m1++;
			}
			if(q[i].matcher(part2).find()){
				m2++;
			}
		}
		if(m1>0 && m2>0){
			return 1;
		}
		return 0;
	}

	// power function
	private int pow(int x,int n){
		int ans = 1;
		for(int i=0; i<n; i++){
			ans *= x;
		}
		return ans;
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

	public int getflag(){ // if it is sin or cos or e^x
		return whichfunc;
	}
}