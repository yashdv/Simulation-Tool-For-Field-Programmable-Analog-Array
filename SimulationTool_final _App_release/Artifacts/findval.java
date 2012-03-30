import javax.swing.JFrame;

public class findval {

	private int cube;
	private int last_used;
	private int[] l_used;
	private int flag;
	private String var;

	// To set all the component values of the op-amps being used
	public findval(int[][] coeff,final JFrame frm,String in,String v){
		var = v;
		opvalues[] val = new opvalues[25];
		for(int j=0;j<25;j++){
			val[j] = new opvalues();
			//      initializing(val[j]);
		}

		l_used = new int[10]; // stores the last op-amp used by all the terms of the expression
		cube = 0; //flag to check whether a cube or not
		last_used = -1; // stores the last op-amp used
		flag=0;
		
		// calculates x^3 term
		if(coeff[3][0]!=0){ 
			last_used = multiply(val,last_used,cube);
			l_used[2] = last_used;
			if(coeff[2][0]!=0){
				last_used = gain(val,coeff[2][0],coeff[2][1],last_used,cube);
				l_used[4] = last_used;
			}
			cube = 1;
			last_used = multiply(val,last_used,cube);
			l_used[3] = last_used;
			last_used = gain(val,coeff[3][0],coeff[3][1],last_used,cube);
			l_used[5] = last_used;
			cube = 0;
		}
		
		//calculates x^2 term
		if(coeff[2][0]!=0){
			if(coeff[3][0] == 0){
				last_used = multiply(val,last_used,cube);
				l_used[2] = last_used;

				last_used = gain(val,coeff[2][0],coeff[2][1],last_used,cube);
				l_used[4] = last_used;
			}
		}
		
		//calculates a*x term
		if(coeff[1][0]!=0){
			if(coeff[1][0]==1 && coeff[1][1]==1){
				flag = 1;
			}
			last_used = gain(val,coeff[1][0],coeff[1][1],last_used,2);
			l_used[1] = last_used;
		}

		//adds all the terms
		if((coeff[3][0]==0 && coeff[2][0]==0)||(coeff[3][0]==0 && coeff[1][0]==0)||(coeff[2][0]==0 && coeff[1][0]==0)){}
		else{
			last_used = add(val,last_used);
			l_used[6] = last_used;
		}
		
		//calls default circuit
		if(coeff[2][0]==0 && coeff[3][0]==0 && coeff[1][0]==1)
		{
			new defaultcircuit(frm, in, val, coeff[0][0], 2,last_used);

		}
		else if(coeff[2][0]==0 && coeff[3][0]==0 && coeff[1][0]==0 && coeff[0][0]!=0){
			new defaultcircuit(frm, in, val, coeff[0][0], 1,last_used);
		}
		else{
			new defaultcircuit(frm, in, val, coeff[0][0], 3,last_used);
		}


	}

	//To add the terms
	private int add(opvalues[] values,int l){
		initializing(values[l+1]);
		if(l_used[1]!=0){
			values[l+1].r1 = "10 ohm";
			if(flag==1){
				values[l+1].v1 = var;
			}
			else{
				values[l+1].v1 = values[l_used[1]].out;
			}
		}
		if(l_used[4]!=0){
			values[l+1].r2 = "10 ohm";
			values[l+1].v2 = values[l_used[4]].out;
		}
		if(l_used[5]!=0){
			values[l+1].r3 = "10 ohm";
			values[l+1].v3 = values[l_used[5]].out;
		}
		values[l+1].rdown = "0 ohm";
		values[l+1].rup = "10 ohm";
		values[l+1].vdiode = "0";
		values[l+1].vdown = "0";
		values[l+1].flag = "2";
		values[l+1].out = "out"+Integer.toString(l+1);
		values[l+1].upswitch = "0";
		values[l+1].opnum = l+1;

		values[l+2].r1 = "10 ohm";
		values[l+2].r2 = "0 ohm";
		values[l+2].r3 = "0 ohm";
		values[l+2].rdown = "0 ohm";
		values[l+2].rup = "10 ohm";
		values[l+2].v1 = values[l+1].out;
		values[l+2].v2 = "0";
		values[l+2].v3 = "0";
		values[l+2].vdiode = "0";
		values[l+2].vdown = "0";
		values[l+2].flag = "2";
		values[l+2].out = "out"+Integer.toString(l+2);
		values[l+2].upswitch = "0";
		values[l+2].opnum = l+2;

		return l+2;
	}  


    //To calculate the gain
	private int gain(opvalues[] values,int num,int den,int l,int c){
		double n = (double)num/den;
		if(num != 1 || den != 1){
			if(n > 1){
				initializing(values[l+1]);
				num = num - den;
				values[l+1].r1 = Integer.toString(den)+" ohm";
				values[l+1].r2 = "0 ohm";
				values[l+1].r3 = "0 ohm";
				values[l+1].rdown = "0 ohm";
				values[l+1].rup = Integer.toString(num)+" ohm";
				values[l+1].v1 = "0";
				values[l+1].v2 = "0";
				values[l+1].v3 = "0";
				values[l+1].vdiode = "0";
				if(c==1){ values[l+1].vdown = "out"+Integer.toString(l_used[3]); }
				else if(c==0){ values[l+1].vdown = "out"+Integer.toString(l_used[2]); }
				else if(c==2){ values[l+1].vdown = var; }
				values[l+1].flag = "2";
				values[l+1].out = "out"+Integer.toString(l+1);
				values[l+1].upswitch = "0";
				values[l+1].opnum = l+1;

				return l+1;
			}
			else if(n>0&&n<1){
				initializing(values[l+1]);
				values[l+1].r1 = Integer.toString(den)+" ohm";
				values[l+1].r2 = "0 ohm";
				values[l+1].r3 = "0 ohm";
				values[l+1].rdown = "0 ohm";
				values[l+1].rup = Integer.toString(num)+" ohm";
				values[l+1].vdown = "0";
				values[l+1].v2 = "0";
				values[l+1].v3 = "0";
				values[l+1].vdiode = "0";
				if(c==1){ values[l+1].v1 = "out"+Integer.toString(l_used[3]); }
				else if(c==0){ values[l+1].v1 = "out"+Integer.toString(l_used[2]); }
				else if(c==2){ values[l+1].v1 = var; }
				values[l+1].flag = "2";
				values[l+1].out = "out"+Integer.toString(l+1);
				values[l+1].upswitch = "0";
				values[l+1].opnum = l+1;

				values[l+2].r1 = "10 ohm";
				values[l+2].r2 = "0 ohm";
				values[l+2].r3 = "0 ohm";
				values[l+2].rdown = "0 ohm";
				values[l+2].rup = "10 ohm";
				values[l+2].v1 = values[l+1].out;
				values[l+2].v2 = "0";
				values[l+2].v3 = "0";
				values[l+2].vdiode = "0";
				values[l+2].vdown = "0";
				values[l+2].flag = "2";
				values[l+2].out = "out"+Integer.toString(l+2);
				values[l+2].upswitch = "0";
				values[l+2].opnum = l+2;

				return l+2;

			}
			else if(n<0){
				initializing(values[l+1]);
				values[l+1].r1 = Integer.toString(den)+" ohm";
				values[l+1].r2 = "0 ohm";
				values[l+1].r3 = "0 ohm";
				values[l+1].rdown = "0 ohm";
				values[l+1].rup = Integer.toString(num*-1)+" ohm";
				values[l+1].vdown = "0";
				values[l+1].v2 = "0";
				values[l+1].v3 = "0";
				values[l+1].vdiode = "0";
				if(c==1){ values[l+1].v1 = "out"+Integer.toString(l_used[3]); }
				else if(c==0){ values[l+1].v1 = "out"+Integer.toString(l_used[2]); }
				else if(c==2){ values[l+1].v1 = var; }
				values[l+1].flag = "2";
				values[l+1].out = "out"+Integer.toString(l+1);
				values[l+1].upswitch = "0";
				values[l+1].opnum = l+1;

				return l+1;
			}

		}
		return l;
	}

	//To multiply
	private int multiply(opvalues[] values,int l,int c){
		initializing(values[l+1]);
		initializing(values[l+2]);
		initializing(values[l+3]);
		initializing(values[l+4]);
		initializing(values[l+5]);
		initializing(values[l+6]);

		values[l+1].r1 = "e^34.5 ohm";
		values[l+1].r2 = "0 ohm";
		values[l+1].r3 = "0 ohm";
		values[l+1].rdown = "0 ohm";
		values[l+1].rup = "0 ohm";
		if(c==0){ values[l+1].v1 = var; }
		else { values[l+1].v1 = "out"+Integer.toString(l_used[2]); }
		values[l+1].v2 = "0";
		values[l+1].v3 = "0";
		values[l+1].vdiode = "0";
		values[l+1].vdown = "0";
		values[l+1].flag = "2";
		values[l+1].out = "out"+Integer.toString(l+1);
		values[l+1].upswitch = "1";
		values[l+1].opnum = l+1;

		values[l+2].r1 = "e^34.5 ohm";
		values[l+2].r2 = "0 ohm";
		values[l+2].r3 = "0 ohm";
		values[l+2].rdown = "0 ohm";
		values[l+2].rup = "0 ohm";
		values[l+2].v1 = var;
		values[l+2].v2 = "0";
		values[l+2].v3 = "0";
		values[l+2].vdiode = "0";
		values[l+2].vdown = "0";
		values[l+2].flag = "2";
		values[l+2].out = "out"+Integer.toString(l+2);
		values[l+2].upswitch = "1";
		values[l+2].opnum = l+2;

		values[l+3].r1 = "10 ohm";
		values[l+3].r2 = "10 ohm";
		values[l+3].r3 = "0 ohm";
		values[l+3].rdown = "0 ohm";
		values[l+3].rup = "10 ohm";
		values[l+3].v1 = "out"+Integer.toString(l+1);
		values[l+3].v2 = "out"+Integer.toString(l+2);
		values[l+3].v3 = "0";
		values[l+3].vdiode = "0";
		values[l+3].vdown = "0";
		values[l+3].flag = "2";
		values[l+3].out = "out"+Integer.toString(l+3);
		values[l+3].upswitch = "0";
		values[l+3].opnum = l+3;

		values[l+4].r1 = "10 ohm";
		values[l+4].r2 = "0 ohm";
		values[l+4].r3 = "0 ohm";
		values[l+4].rdown = "0 ohm";
		values[l+4].rup = "10 ohm";
		values[l+4].v1 = "out"+Integer.toString(l+3);
		values[l+4].v2 = "0";
		values[l+4].v3 = "0";
		values[l+4].vdiode = "0";
		values[l+4].vdown = "0";
		values[l+4].flag = "2";
		values[l+4].out = "out"+Integer.toString(l+4);
		values[l+4].upswitch = "0";
		values[l+4].opnum = l+4;

		values[l+5].r1 = "0 ohm";
		values[l+5].r2 = "0 ohm";
		values[l+5].r3 = "0 ohm";
		values[l+5].rdown = "10 ohm";
		values[l+5].rup = "0 ohm";
		values[l+5].v1 = "0";
		values[l+5].v2 = "0";
		values[l+5].v3 = "0";
		values[l+5].vdiode = "0";
		values[l+5].vdown = "out"+Integer.toString(l+4);
		values[l+5].flag = "1";
		values[l+5].out = "out"+Integer.toString(l+5);
		values[l+5].upswitch = "1";
		values[l+5].opnum = l+5;
		values[l+5].currentval = "If";

		values[l+6].r1 = "0 ohm";
		values[l+6].r2 = "0 ohm";
		values[l+6].r3 = "0 ohm";
		values[l+6].rdown = "0 ohm";
		values[l+6].rup = "Rf ohm";
		values[l+6].v1 = "0";
		values[l+6].v2 = "0";
		values[l+6].v3 = "0";
		values[l+6].vdiode = "out"+Integer.toString(l+5);
		values[l+6].vdown = "0";
		values[l+6].flag = "3";
		values[l+6].out = "out"+Integer.toString(l+6);
		values[l+6].upswitch = "0";
		values[l+6].opnum = l+6;

		return l+6;

	}


	//To initialise the op-amp values
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