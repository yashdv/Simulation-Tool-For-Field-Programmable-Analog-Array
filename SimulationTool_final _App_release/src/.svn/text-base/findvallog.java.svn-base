import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class findvallog {

	private int last_used;
	//    public String var;
	public int[] l_used;
	private int[] token;
	public int power;
	public int num, den, lg, constant, flag;
	public int[] check;
	private int[] index;
	private String in;
	private String filtered;
	private int wrong = 0;
	private String var = "testx";

	public findvallog(final JFrame frm, String s) {

		opvalues[] val = new opvalues[100];
		for (int j = 0; j < 100; j++) {
			val[j] = new opvalues();
		}
		last_used = -1;
		l_used = new int[11];
		token = new int[11];
		index = new int[11];
		int i, j, k;
		j = 0;
		for (i = 0; i < 11; i++) {
			token[i] = -1;
		}
		//         power=-1;
		num = 0;
		den = 1;
		lg = 0;
		constant = 0;
		flag = 0;
		var = "testx";
		check = new int[11];


		if (s.length() == 0) {
			wrong = 1;
			System.out.println("wrong1");
		}
		in = s;
		filtered = "+";
		// get rid of brackets, space etc
		for (int v = 0; v < in.length(); v++) {
			char c = in.charAt(v);
			if (c == '(' || c == ')' || c == ' ' || c == '\t') {
				continue;
			}
			filtered += c;
		}
		filtered = filtered.toLowerCase();

		String fil2 = "";
		// add a + sign before all minus sign
		for (int h = 1; h < filtered.length(); h++) {
			fil2 += filtered.charAt(h - 1);
			if (filtered.charAt(h) == '-') {
				fil2 += "+";
			}
		}
		fil2 += filtered.charAt(filtered.length() - 1);
		filtered = fil2.substring(1, fil2.length());
		String[] tok = filtered.split("[\\+]"); // break the input string into term separated by +

		int[][] poly = new int[6][2];
		int tokcnt = 0; // count number of terms
		for (i = 0; i < tok.length; i++) {
			num = 0; // numerator of constant term
			den = 1; // denominator of constant term
			power = 0; // see the power of x
			lg = 0; // flag to see if the term contains 'log'
			if (tok[i].length() > 0) {
				tokcnt++;
				if (tok[i].indexOf("log") == -1) {
					SanitizeStr ss = new SanitizeStr(tok[i]);
					poly = ss.getpolycoeff(); // get coefficients of the term
					for (int p = 1; p <= 3; p++) {
						if (poly[p][0] != 0) {
							power = p;
							break;
						}
					}
					num = poly[power][0];
					den = poly[power][1];
					if (ss.getvalidity() == 0) { // if the term is valid or not
						wrong = 1;
						break;
					} else {
						if (var.equals("testx")) {
							if (!(ss.getvar() == "test")) {
								var = "";
								var += ss.getvar();
							}
						} else if (!(var.equals(ss.getvar()))) {
							if (!(ss.getvar() == "test")) {
								wrong = 1;
								break;
							}
						}
					}

				} else {
					int index = tok[i].indexOf("log");
					int l = tok[i].length();
					if (index + 3 >= l) { // to ensure the log contains 'x'term 
						wrong = 1;
						break;
					}
					if (var.equals("testx")) {
						var = "";
						var += tok[i].charAt(l - 1);
					} else if (var.charAt(0) != tok[i].charAt(l - 1)) { // check if the variable is same throughout all the terms
						wrong = 1;
						break;
					}

					if (!(tok[i].substring(index).equals("log" + var))) {
						wrong = 1;
						break;
					}
					if (index != 0) { // log is there
						index--;
						if (index == 0 && tok[i].charAt(index) != '-') {
							wrong = 1;
							break;
						} else if (index != 0 && tok[i].charAt(index) != '*') {
							wrong = 1;
							break;
						}
						if (wrong == 0 && index != 0) {
							SanitizeStr ss = new SanitizeStr(tok[i].substring(0, index));
							poly = ss.getpolycoeff(); // coeff of the term
							for (int p = 1; p <= 3; p++) {
								if (poly[p][0] != 0) {
									power = p;
									break;
								}
							}
							num = poly[power][0];
							den = poly[power][1];
							lg = 1;
							if (ss.getvalidity() == 0) { // validity of the polynomial part
								wrong = 1;
								break;
							} else {
								if (var.equals("testx")) {
									if (!(ss.getvar() == "test")) {
										var = "";
										var += ss.getvar();
									}

								} else if (!(var.equals(ss.getvar()))) {
									if (!(ss.getvar() == "test")) {
										wrong = 1;
										break;
									}
								}
							}
						} else {       // case of -logx
							num = -1;
							lg = 1;
						}
					} else { // case of logx
						num = 1;
						lg = 1;
					}
				}
				if(tokcnt > 4){ // terms in input must not exceed four
					wrong = 1;
					break;
				}

				//calculates the x^3 term
				if (power == 3) {
					if (l_used[3] == 0) {
						last_used = multiply(val, last_used, flag);
						l_used[2] = last_used;
						flag = 1;
						last_used = multiply(val, last_used, flag);
						l_used[3] = last_used;
					}
					if (num != 0) {
						last_used = gain(val, num, den, last_used, 3);
						flag = 2;
					}
				}
				
				//calculates the x^2 term
				if (power == 2) {
					if (l_used[2] == 0) {
						flag = 0;
						last_used = multiply(val, last_used, flag);
						l_used[2] = last_used;
					}
					if (num != 0) {
						last_used = gain(val, num, den, last_used, 2);
						flag = 2;
					}
				}
				
				//calculates the a*x term
				if (power == 1) {
					if (num != 0 && (num != 1 || den != 1)) {
						last_used = gain(val, num, den, last_used, 1);
						l_used[1] = last_used;
						flag = 2;
					} else {
						flag = 4;
						if (lg == 0) {
							check[i] = 1;
						}
					}
				}
				
				//stores the constant
				if (power == 0 && lg == 0) {
					constant = num;
				}
				
				//calculates the log(x)
				if (lg == 1) {
					if (l_used[4] == 0) {
						last_used = log(val, last_used);
						l_used[4] = last_used;
					}
					//               System.out.println("flag log: " + flag);
					if (power != 0) {
						last_used = multiply(val, last_used, flag);
					} else {
						last_used = gain(val, num, den, last_used, 4);
					}
				}
				//         System.out.println("constant: " + constant);
				if (constant == 0) {
					//            System.out.println("check: " + check[i]);
					if (check[i] != 1) {
						token[i] = last_used;
					}
					else {
						token[i] = 10;
					}
					index[j] = i;
					j++;
				} else {
					token[i] = -1;
				}
			}
		}

		while (j < 3) {
			index[j] = 10;
			j++;
		}
		//          System.out.println("token[index0]: "+token[index[0]]);
		//           System.out.println("token[index1]: "+token[index[1]]);
		//          System.out.println("token[index2]: "+token[index[2]]);

		//Add all the terms
		if ((token[index[2]] == -1 && token[index[1]] == -1) || (token[index[2]] == -1 && token[index[0]] == -1) || (token[index[1]] == -1 && token[index[0]] == -1)) {
		} else {
			//          System.out.println("hello ayush..!");
			last_used = add(val, last_used);
		}
		if (wrong != 1) {
			new defaultcircuit(frm, in, val, constant, 3, last_used);
		} else {
			JOptionPane.showMessageDialog(null, "Invalid Input", "Error..!.", JOptionPane.ERROR_MESSAGE);
		}
	}

	//To add
	private int add(opvalues[] values, int l) {
		initializing(values[l + 1]);
		if (token[index[0]] != -1) {
			//        System.out.println("index0: " + index[0]);
			//        System.out.println("check[index0]: " + check[index[0]]);
			values[l + 1].r1 = "10 ohm";
			if (check[index[0]] == 1) {
				//            System.out.println("hi: " + var);
				values[l + 1].v1 = var;
			} else {
				values[l + 1].v1 = values[token[index[0]]].out;
			}
		}
		if (token[index[1]] != -1) {
			//         System.out.println("index1: " + index[1]);
			//          System.out.println("check[index1]: " + check[index[1]]);
			values[l + 1].r2 = "10 ohm";
			if (check[index[1]] == 1) {
				values[l + 1].v2 = var;
			} else {
				values[l + 1].v2 = values[token[index[1]]].out;
			}
		}
		if (token[index[2]] != -1) {
			//          System.out.println("index2: " + index[2]);
			//          System.out.println("check[index2]: " + check[index[2]]);
			values[l + 1].r3 = "10 ohm";
			if (check[index[2]] == 1) {
				values[l + 1].v3 = var;
			} else {
				values[l + 1].v3 = values[token[index[2]]].out;
			}
		}
		values[l + 1].rdown = "0 ohm";
		values[l + 1].rup = "10 ohm";
		values[l + 1].vdiode = "0";
		values[l + 1].vdown = "0";
		values[l + 1].flag = "2";
		values[l + 1].out = "out" + Integer.toString(l + 1);
		values[l + 1].upswitch = "0";
		values[l + 1].opnum = l + 1;

		values[l + 2].r1 = "10 ohm";
		values[l + 2].r2 = "0 ohm";
		values[l + 2].r3 = "0 ohm";
		values[l + 2].rdown = "0 ohm";
		values[l + 2].rup = "10 ohm";
		values[l + 2].v1 = values[l + 1].out;
		values[l + 2].v2 = "0";
		values[l + 2].v3 = "0";
		values[l + 2].vdiode = "0";
		values[l + 2].vdown = "0";
		values[l + 2].flag = "2";
		values[l + 2].out = "out" + Integer.toString(l + 2);
		values[l + 2].upswitch = "0";
		values[l + 2].opnum = l + 2;

		if (l + 2 > 25) {
			wrong = 1;
		}

		return l + 2;
	}

	//To calculate gain
	private int gain(opvalues[] values, int num, int den, int l, int c) {
		double n = (double) num / den;
		if (num != 1 || den != 1) {
			if (n > 1) {
				initializing(values[l + 1]);
				num = num - den;
				values[l + 1].r1 = Integer.toString(den) + " ohm";
				values[l + 1].r2 = "0 ohm";
				values[l + 1].r3 = "0 ohm";
				values[l + 1].rdown = "0 ohm";
				values[l + 1].rup = Integer.toString(num) + " ohm";
				values[l + 1].v1 = "0";
				values[l + 1].v2 = "0";
				values[l + 1].v3 = "0";
				values[l + 1].vdiode = "0";
				if (c == 3) {
					values[l + 1].vdown = "out" + Integer.toString(l_used[3]);
				} else if (c == 2) {
					values[l + 1].vdown = "out" + Integer.toString(l_used[2]);
				} else if (c == 1) {
					values[l + 1].vdown = var;
				} else if (c == 4) {
					values[l + 1].vdown = "out" + Integer.toString(l);
				}
				values[l + 1].flag = "2";
				values[l + 1].out = "out" + Integer.toString(l + 1);
				values[l + 1].upswitch = "0";
				values[l + 1].opnum = l + 1;

				if (l + 1 > 25) {
					wrong = 1;
				}

				return l + 1;
			} else if (n > 0 && n < 1) {
				initializing(values[l + 1]);
				values[l + 1].r1 = Integer.toString(den) + " ohm";
				values[l + 1].r2 = "0 ohm";
				values[l + 1].r3 = "0 ohm";
				values[l + 1].rdown = "0 ohm";
				values[l + 1].rup = Integer.toString(num) + " ohm";
				values[l + 1].vdown = "0";
				values[l + 1].v2 = "0";
				values[l + 1].v3 = "0";
				values[l + 1].vdiode = "0";
				if (c == 3) {
					values[l + 1].v1 = "out" + Integer.toString(l_used[3]);
				} else if (c == 2) {
					values[l + 1].v1 = "out" + Integer.toString(l_used[2]);
				} else if (c == 1) {
					values[l + 1].v1 = var;
				} else if (c == 4) {
					values[l + 1].v1 = "out" + Integer.toString(l);
				}
				values[l + 1].flag = "2";
				values[l + 1].out = "out" + Integer.toString(l + 1);
				values[l + 1].upswitch = "0";
				values[l + 1].opnum = l + 1;

				values[l + 2].r1 = "10 ohm";
				values[l + 2].r2 = "0 ohm";
				values[l + 2].r3 = "0 ohm";
				values[l + 2].rdown = "0 ohm";
				values[l + 2].rup = "10 ohm";
				values[l + 2].v1 = values[l + 1].out;
				values[l + 2].v2 = "0";
				values[l + 2].v3 = "0";
				values[l + 2].vdiode = "0";
				values[l + 2].vdown = "0";
				values[l + 2].flag = "2";
				values[l + 2].out = "out" + Integer.toString(l + 2);
				values[l + 2].upswitch = "0";
				values[l + 2].opnum = l + 2;

				if (l + 2 > 25) {
					wrong = 1;
				}

				return l + 2;

			} else if (n < 0) {
				initializing(values[l + 1]);
				values[l + 1].r1 = Integer.toString(den) + " ohm";
				values[l + 1].r2 = "0 ohm";
				values[l + 1].r3 = "0 ohm";
				values[l + 1].rdown = "0 ohm";
				values[l + 1].rup = Integer.toString(num * -1) + " ohm";
				values[l + 1].vdown = "0";
				values[l + 1].v2 = "0";
				values[l + 1].v3 = "0";
				values[l + 1].vdiode = "0";
				if (c == 3) {
					values[l + 1].v1 = "out" + Integer.toString(l_used[3]);
				} else if (c == 2) {
					values[l + 1].v1 = "out" + Integer.toString(l_used[2]);
				} else if (c == 1) {
					values[l + 1].v1 = var;
				} else if (c == 4) {
					values[l + 1].v1 = "out" + Integer.toString(l);
				}
				values[l + 1].flag = "2";
				values[l + 1].out = "out" + Integer.toString(l + 1);
				values[l + 1].upswitch = "0";
				values[l + 1].opnum = l + 1;

				if (l + 1 > 25) {
					wrong = 1;
				}

				return l + 1;
			}

		}
		return l;
	}

	//To multiply
	private int multiply(opvalues[] values, int l, int c) {
		initializing(values[l + 1]);
		initializing(values[l + 2]);
		initializing(values[l + 3]);
		initializing(values[l + 4]);
		initializing(values[l + 5]);
		initializing(values[l + 6]);

		values[l + 1].r1 = "e^34.5 ohm";
		values[l + 1].r2 = "0 ohm";
		values[l + 1].r3 = "0 ohm";
		values[l + 1].rdown = "0 ohm";
		values[l + 1].rup = "0 ohm";
		if (c == 0) {
			values[l + 1].v1 = var;
		} else if (c == 1) {
			values[l + 1].v1 = "out" + Integer.toString(l_used[2]);
		} //      else if(c==2){values[l+1].v1 = "out"+Integer.toString(l);}
		else if (c == 4 || c == 2) {
			values[l + 1].v1 = "out" + Integer.toString(l_used[4]);
		}
		values[l + 1].v2 = "0";
		values[l + 1].v3 = "0";
		values[l + 1].vdiode = "0";
		values[l + 1].vdown = "0";
		values[l + 1].flag = "2";
		values[l + 1].out = "out" + Integer.toString(l + 1);
		values[l + 1].upswitch = "1";
		values[l + 1].opnum = l + 1;

		values[l + 2].r1 = "e^34.5 ohm";
		values[l + 2].r2 = "0 ohm";
		values[l + 2].r3 = "0 ohm";
		values[l + 2].rdown = "0 ohm";
		values[l + 2].rup = "0 ohm";
		if (c == 0 || c == 1 || c == 4) {
			values[l + 2].v1 = var;
		} else if (c == 2) {
			values[l + 2].v1 = "out" + Integer.toString(l_used[power]);
			System.out.println(power);
		}
		values[l + 2].v2 = "0";
		values[l + 2].v3 = "0";
		values[l + 2].vdiode = "0";
		values[l + 2].vdown = "0";
		values[l + 2].flag = "2";
		values[l + 2].out = "out" + Integer.toString(l + 2);
		values[l + 2].upswitch = "1";
		values[l + 2].opnum = l + 2;

		values[l + 3].r1 = "10 ohm";
		values[l + 3].r2 = "10 ohm";
		values[l + 3].r3 = "0 ohm";
		values[l + 3].rdown = "0 ohm";
		values[l + 3].rup = "10 ohm";
		values[l + 3].v1 = "out" + Integer.toString(l + 1);
		values[l + 3].v2 = "out" + Integer.toString(l + 2);
		values[l + 3].v3 = "0";
		values[l + 3].vdiode = "0";
		values[l + 3].vdown = "0";
		values[l + 3].flag = "2";
		values[l + 3].out = "out" + Integer.toString(l + 3);
		values[l + 3].upswitch = "0";
		values[l + 3].opnum = l + 3;

		values[l + 4].r1 = "10 ohm";
		values[l + 4].r2 = "0 ohm";
		values[l + 4].r3 = "0 ohm";
		values[l + 4].rdown = "0 ohm";
		values[l + 4].rup = "10 ohm";
		values[l + 4].v1 = "out" + Integer.toString(l + 3);
		values[l + 4].v2 = "0";
		values[l + 4].v3 = "0";
		values[l + 4].vdiode = "0";
		values[l + 4].vdown = "0";
		values[l + 4].flag = "2";
		values[l + 4].out = "out" + Integer.toString(l + 4);
		values[l + 4].upswitch = "0";
		values[l + 4].opnum = l + 4;

		values[l + 5].r1 = "0 ohm";
		values[l + 5].r2 = "0 ohm";
		values[l + 5].r3 = "0 ohm";
		values[l + 5].rdown = "10 ohm";
		values[l + 5].rup = "0 ohm";
		values[l + 5].v1 = "0";
		values[l + 5].v2 = "0";
		values[l + 5].v3 = "0";
		values[l + 5].vdiode = "0";
		values[l + 5].vdown = "out" + Integer.toString(l + 4);
		values[l + 5].flag = "1";
		values[l + 5].out = "out" + Integer.toString(l + 5);
		values[l + 5].upswitch = "1";
		values[l + 5].opnum = l + 5;
		values[l + 5].currentval = "If";

		values[l + 6].r1 = "0 ohm";
		values[l + 6].r2 = "0 ohm";
		values[l + 6].r3 = "0 ohm";
		values[l + 6].rdown = "0 ohm";
		values[l + 6].rup = "Rf ohm";
		values[l + 6].v1 = "0";
		values[l + 6].v2 = "0";
		values[l + 6].v3 = "0";
		values[l + 6].vdiode = "out" + Integer.toString(l + 5);
		values[l + 6].vdown = "0";
		values[l + 6].flag = "3";
		values[l + 6].out = "out" + Integer.toString(l + 6);
		values[l + 6].upswitch = "0";
		values[l + 6].opnum = l + 6;

		if (l + 6 > 25) {
			wrong = 1;
		}

		return l + 6;

	}

	//To calculate Log(x)
	private int log(opvalues[] values, int l) {
		initializing(values[l + 1]);
		initializing(values[l + 2]);

		values[l + 1].r1 = "e^34.5 ohm";
		values[l + 1].r2 = "0 ohm";
		values[l + 1].r3 = "0 ohm";
		values[l + 1].rdown = "0 ohm";
		values[l + 1].rup = "0 ohm";
		values[l + 1].v1 = var;//out"+Integer.toString(l);
		values[l + 1].v2 = "0";
		values[l + 1].v3 = "0";
		values[l + 1].vdiode = "0";
		values[l + 1].vdown = "0";
		values[l + 1].flag = "1";
		values[l + 1].out = "out" + Integer.toString(l + 1);
		values[l + 1].upswitch = "1";
		values[l + 1].opnum = l + 1;

		values[l + 2].r1 = "25.2 Kohm";
		values[l + 2].r2 = "0 ohm";
		values[l + 2].r3 = "0 ohm";
		values[l + 2].rdown = "0 ohm";
		values[l + 2].rup = "1 ohm";
		values[l + 2].v1 = "out" + Integer.toString(l + 1);
		values[l + 2].v2 = "0";
		values[l + 2].v3 = "0";
		values[l + 2].vdiode = "0";
		values[l + 2].vdown = "0";
		values[l + 2].flag = "1";
		values[l + 2].out = "out" + Integer.toString(l + 2);
		values[l + 2].upswitch = "0";
		values[l + 2].opnum = l + 2;

		if (l + 2 > 25) {
			wrong = 1;
		}

		return l + 2;

	}

	
	//To initialise
	private void initializing(opvalues g) {
		g.r1 = "0";
		g.r2 = "0";
		g.r3 = "0";
		g.rdown = "0";
		g.rup = "0";
		g.v1 = "0";
		g.v2 = "0";
		g.v3 = "0";
		g.vdiode = "0";
		g.vdown = "0";
		g.flag = "2";
		g.out = "out";
		g.upswitch = "0";
		g.opnum = -1;
		g.currentval = "";
	}
}
