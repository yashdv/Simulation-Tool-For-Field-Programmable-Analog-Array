import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;

public class drawcircuitv2 extends JPanel implements MouseMotionListener, MouseListener {

	private int x1, y1, x2, y2, x3, y3, x4, y4, ln, wd, cnst, flg, last;
	static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	private int xpos, ypos, prev_xpos = -1, prev_ypos = -1, trans_graph_x = 0, trans_graph_y = 0;
	Color colour;
	opvalues temp1[] = new opvalues[25];
	Stroke drawingStroke = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);

	drawcircuitv2(int x1, int y1, int length, int width, opvalues a[], int cons, int flag, int last_used) {
		cnst = cons;
		flg = flag;
		last = last_used;
		temp1 = a;
		this.x1 = x1;
		this.y1 = y1;
		ln = length;
		wd = width;
		x2 = x1 + length;
		x3 = x2;
		x4 = x1;
		y2 = y1;
		y3 = y1 + width;
		y4 = y3;
		addMouseMotionListener(this);
		addMouseListener(this);
	}

	;

	// get co-ordinates of the mouse position on screen
	public void mouseMoved(MouseEvent me) {
		xpos = me.getX();
		ypos = me.getY();
	}

	public void mouseReleased(MouseEvent me) {

		prev_xpos = -1;
		prev_ypos = -1;
		repaint();
	}
	
	// empty
	public void mouseEntered(MouseEvent me) {
	}

	// empty
	public void mouseExited(MouseEvent me) {
	}

	// get the direction and magnitude of the drag, so that the background can be movd accordingly
	public void mouseDragged(MouseEvent me) {

		xpos = me.getX();
		ypos = me.getY();
		if (prev_xpos != -1 && prev_ypos != -1) {
			trans_graph_x = xpos - prev_xpos + trans_graph_x;
			trans_graph_y = ypos - prev_ypos + trans_graph_y;
		}
		prev_xpos = xpos;
		prev_ypos = ypos;
		repaint();
	}

	// repainting the panel
	public void mousePressed(MouseEvent me) {
		repaint();
	}

	// repainting the panel
	public void mouseClicked(MouseEvent me) {
		repaint();
	}

	// draw op-amp
	private void Bigopam(Graphics j, int x1, int y1, int length, int width, int flag) {
		//j.setColor(Color.BLUE);
		j.drawLine(x1, y1, x1 + flag * length, y1 + (width / 2));
		j.drawLine(x1 + flag * length, y1 + (width / 2), x1, y1 + width);
		j.drawLine(x1, y1, x1, y1 + width);
		j.drawLine(x1 + flag * length, y1, x1 + flag * length, y1 + width / 2);
		int f = 20;
		Font font = new Font("Serif", Font.PLAIN,f);
		j.setFont(font);
		j.drawString("_",x1,y1+(width/10));//minus	
		j.drawString("+",x1,y1+width-(width/5));//plus
	}

	// draw diode
	private void formopam(Graphics j, int x1, int y1, int length, int width, int flag) {
		//j.setColor(Color.BLUE);
		j.drawLine(x1, y1, x1 + flag * length, y1 + (width / 2));
		j.drawLine(x1 + flag * length, y1 + (width / 2), x1, y1 + width);
		j.drawLine(x1, y1, x1, y1 + width);
		j.drawLine(x1 + flag * length, y1, x1 + flag * length, y1 + width);
	}

	// draw lines around op-amp and diode
	private void smallopam(Graphics j, int x1, int y1, int length, int width, int flag) {
		j.drawLine(x1, y1, x1 + (length / 3), y1);
		j.drawLine(x1 + 2 * (length / 3), y1, x1 + length, y1);
		if (flag == 1) {
			x1 = x1 + (length / 3);
		} else {
			x1 = x1 + 2 * (length / 3);
		}
		y1 = y1 - width / 2;
		formopam(j, x1, y1, length / 3, width, flag);
	}

	// three resistor on left and right
	public void resistor_sideward(Graphics j, int xcord, int ycord, int length, int var, opvalues temp) {
		j.drawLine(xcord, ycord, xcord + (length / 4), ycord);
		int i, k, x1 = xcord + length / 4, y1 = ycord, a;
		for (i = 0; i < 10; i++) {
			if (i % 2 != 0) {
				k = 1;
			} else {
				k = -1;
			}
			a = y1 + k * wd / 20;
			j.drawLine(x1, y1, x1 + (length / 20), a);
			y1 = a;
			x1 = x1 + (length / 20);
		}
		//Font font = new Font("Serif", Font.PLAIN,length/10);
		int f = 12;
		Font font = new Font("Serif", Font.PLAIN, f);
		j.setFont(font);
		j.setColor(Color.black);
		if (var == 1) {
			j.drawString(temp.r1, xcord + length / 4 + 25, y1 - (wd / 20) - 2);
			j.drawString(temp.v1, xcord, y1 - 2);
		} else if (var == 2) {
			j.drawString(temp.r2, xcord + length / 4 + 25, y1 - (wd / 20) - 2);
			j.drawString(temp.v2, xcord, y1 - 2);
		} else if (var == 3) {
			j.drawString(temp.r3, xcord + length / 4 + 25, y1 - (wd / 20) - 2);
			j.drawString(temp.v3, xcord, y1 - 2);
			j.drawString(temp.vdiode, xcord, y1 - 2 + wd / 6);
			j.drawString(temp.vdown, xcord, y1 - 2 + wd / 3);
		} else {
			j.drawString(temp.rup, xcord + length / 4, y1 - (wd / 20) - 2);
		}

		//j.drawString(s,xcord+length/4,y1-(wd/20)-2);
		j.setColor(colour);
		j.drawLine(x1, ycord, x1 + (length / 4), ycord);
	}

	// draw the grounding
	public void drawground(Graphics j, int x1, int y1) {
		int d = 10, c = 0;
		for (int i = 0; i < 4; i++) {
			j.drawLine(x1 - d, y1 + c, x1 + d, y1 + c);
			d = d - 5 / 2;
			c += 2;

		}
	}

	// resistor boxes on left and right
	public void resistor_box(Graphics j, int xcord, int ycord, int length, int var, opvalues temp) {
		final double dcr = 10 * dim.height / 1080.00;
		int i, k, x1 = xcord + length / 4, y1 = ycord, a;
		for (i = 0; i < 10; i++) {
			if (i % 2 != 0) {
				k = 1;
			} else {
				k = -1;
			}
			a = y1 + k * wd / 20;
			//j.drawLine(x1,y1,x1+(length/20),a);
			y1 = a;
			x1 = x1 + (length / 20);
		}

		j.drawLine(xcord, ycord, xcord + (length / 4), ycord);
		j.drawRect(xcord + length / 4, ycord - (int) dcr / 2, length / 2, (int) dcr);
		int f = 12;
		Font font = new Font("Serif", Font.PLAIN, f);
		j.setFont(font);
		j.setColor(Color.black);
		if (var == 1) {
			j.drawString(temp.r1, xcord + length / 4 + 25, y1 - (wd / 20) - 2);
			j.drawString(temp.v1, xcord, y1 - 2);
		} else if (var == 2) {
			j.drawString(temp.r2, xcord + length / 4 + 25, y1 - (wd / 20) - 2);
			j.drawString(temp.v2, xcord, y1 - 2);
		} else if (var == 3) {
			j.drawString(temp.r3, xcord + length / 4 + 25, y1 - (wd / 20) - 2);
			j.drawString(temp.v3, xcord, y1 - 2);
			j.drawString(temp.vdiode, xcord, y1 - 2 + wd / 6);
			j.drawString(temp.vdown, xcord, y1 - 2 + wd / 3);
		} else {
			j.drawString(temp.rup, xcord + length / 4, y1 - (wd / 20) - 2);
		}

		//j.drawString(s,xcord+length/4,y1-(wd/20)-2);
		j.setColor(colour);
		j.drawLine(xcord + 3 * (length / 4), ycord, xcord + length, ycord);
	}

	// draw resistor box at the bottom
	public void resistor_box_upward(Graphics j, int xcord, int ycord, int length, opvalues temp) {
		final double dcr = 10 * dim.height / 1080.00;
		int i, k, x1 = xcord, y1 = ycord + length / 4, a;
		for (i = 0; i < 10; i++) {
			if (i % 2 != 0) {
				k = 1;
			} else {
				k = -1;
			}
			a = x1 + k * wd / 20;
			//j.drawLine(x1,y1,a,y1+(length/20));
			x1 = a;
			y1 = y1 + (length / 20);
		}
		j.drawLine(xcord, ycord, xcord, ycord + length / 4);
		j.drawRect(xcord - length / 8, ycord + length / 4, (int) dcr, length / 2);
		//Font font = new Font("Serif", Font.PLAIN,length/10);
		int f = 12;
		Font font = new Font("Serif", Font.PLAIN, f);
		j.setFont(font);
		j.setColor(colour);
		//j.drawString(s,xcord-ln/3,ycord+length/2);
		j.setColor(colour);
		j.drawLine(x1, ycord + 3 * (length / 4), x1, ycord + length);
		j.drawString(temp.rdown, x1 + (length / 8), ycord + (length / 2));//printing string at doen resistor
	}

	// resistor at the bottom
	public void resistor_upward(Graphics j, int xcord, int ycord, int length, opvalues temp) {
		j.drawLine(xcord, ycord, xcord, ycord + length / 4);
		int i, k, x1 = xcord, y1 = ycord + length / 4, a;
		for (i = 0; i < 10; i++) {
			if (i % 2 != 0) {
				k = 1;
			} else {
				k = -1;
			}
			a = x1 + k * wd / 20;
			j.drawLine(x1, y1, a, y1 + (length / 20));
			x1 = a;
			y1 = y1 + (length / 20);
		}
		//Font font = new Font("Serif", Font.PLAIN,length/10);
		int f = 12;
		Font font = new Font("Serif", Font.PLAIN, 12);
		j.setFont(font);
		j.drawLine(x1, ycord + 3 * (length / 4), x1, ycord + length);
		j.drawString(temp.rdown, x1, ycord + 3 * (length / 4));
	}

	// the function which calls the above modules to draw stuff in itself
	public void justpaint(Graphics j, int x1, int y1, opvalues temp) {
		int l = wd / 6;
		//adding big opam
		Bigopam(j, x1 + 2 * (ln / 3), y1 + l + l / 4, ln / 3, 4 * l, 1);

		//adding common registor;
		//		resistor_sideward(j,x1,y1,ln/3,1,temp);
		//		resistor_sideward(j,x1,y1+l,ln/3,2,temp);
		//		resistor_sideward(j,x1,y1+2*l,ln/3,3,temp);
		resistor_box(j, x1, y1, ln / 3, 1, temp);
		resistor_box(j, x1, y1 + l, ln / 3, 2, temp);
		resistor_box(j, x1, y1 + 2 * l, ln / 3, 3, temp);
		///adding sideward small opam
		smallopam(j, x1, y1 + 3 * l, ln / 3, wd / 5, -1);
		j.drawLine(x1 + ln / 3, y1, x1 + ln / 3, y1 + 2 * l);
		//drawing this line
		j.drawLine(x1, y1 + 4 * l, x1 + 2 * (ln / 3), y1 + 4 * l);
		resistor_box_upward(j, x1 + ln / 3, y1 + 4 * l, 2 * l, temp);

		drawground(j, x1 + ln / 3, y1 + 6 * l);

		//upward opam and resisitor
		smallopam(j, x1 + 2 * (ln / 3) - ln / 12, y1, ln / 3 + ln / 12, wd / 5, 1);
		//line from upward opam to three resistor

		j.drawLine(x1+2*(ln/3)-ln/12,y1+1,x1+2*(ln/3)-ln/12,y1+l/2);
		j.drawLine(x1+2*(ln/3)-ln/12,y1+l,x1+2*(ln/3)-ln/24,y1+l/2);
		j.drawLine(x1+2*(ln/3)-ln/12,y1+l,x1+2*(ln/3)-ln/12,y1+3*l/2);

		resistor_box(j, x1 + 2 * (ln / 3), y1 + l, ln / 3, 4, temp);
		//line after opam
		j.drawLine(x1 + ln, y1, x1 + ln, y1 + l + l / 4);
		//line from big Opam to output
		j.drawLine(x1 + ln, y1 + 3 * l + l / 4, x1 + ln + ln / 6, y1 + 3 * l + l / 4);
		j.drawString(temp.out, x1 + ln + ln / 6, y1 + 3 * l + l / 4 - 3);//adding string to output line
		//drawing horizantal line from small upward opam to  big opam
		j.drawLine(x1 + ln / 3, y1 + 3 * (l / 2), x1 + ln / 3 + ln / 8, y1 + 3 * (l / 2));
		//line from registor to commom point
		j.drawLine(x1 + 2 * (ln / 3) - ln / 8, y1 + 3 * (l / 2), x1 + 2 * (ln / 3), y1 + 3 * (l / 2));
		//line from  Big opam to common point
		j.drawLine(x1 + ln / 3, y1 + 3 * l, x1 + ln / 3 + ln / 8, y1 + 3 * l);
		//line from left opam to common point
		j.drawLine(x1 + ln / 3 + ln / 8, y1 + 3 * l, x1 + ln / 3 + ln / 8, y1 + 9 * (l / 4));
		//adding current
		j.drawLine(x1 + ln / 3 + ln / 8, y1 - ln / 12 - 20, x1 + ln / 3 + ln / 8, y1 + l);
		//adding left line for ground
		j.drawLine(x1 + ln / 3 + ln / 8, y1 - ln / 12 - 20, x1 + ln / 3 + ln / 8 - 25, y1 - ln / 12 - 20);
		//adding vertical line for grounding
		j.drawLine(x1 + ln / 3 + ln / 8 - 25, y1 - ln / 12 - 20, x1 + ln / 3 + ln / 8 - 25, y1 - ln / 12 - 10);
		//adding ground
		drawground(j, x1 + ln / 3 + ln / 8 - 25, y1 - ln / 12 - 10);

		//drawing current circle
		int radius = ln / 30;
		j.drawOval(x1 + ln / 3 + ln / 8 - radius, y1 + (l / 4) - radius, radius * 2, radius * 2);
		if (temp.currentval != "") {
			j.drawString("If", x1 + ln / 3 + ln / 8 + ln / 24, y1 + ln / 10);//adding string to the current
		}


	}
	
	// start points for DC shift line
	static double yddc = 6 * dim.height / 1080.00;
	static double yddc1 = 9 * dim.height / 1080.00;
	static int increment;
	static int increment1;
	int finalx = 0, finaly = 0;
	int x_cord = (int) x1;
	int y_cord = (int) y1;
	int outx, outy;

	public void paintComponent(Graphics j) {
		super.paintComponent(j);

		// repainting and storing current panel state
		Graphics2D g2d = (Graphics2D) j;
		AffineTransform tx = new AffineTransform();
		AffineTransform oldtx = new AffineTransform();
		oldtx = g2d.getTransform();
		tx.translate(trans_graph_x, trans_graph_y);
		g2d.setTransform(tx);

		int i = 0, k = 0, local_y = y1;
		for (i = 0; i < 5; i++) {
			int local_x = x1;

			for (k = 0; k < 5; k++) {

				if (temp1[k * 5 + i].opnum != -1) {
					colour = Color.blue;
					j.setColor(Color.blue);
					justpaint(j, local_x, local_y, temp1[k * 5 + i]);

				} else {
					colour = Color.gray;
					j.setColor(Color.gray);
				}

				//j.drawLine(local_x,local_y, local_x, local_y+27);
				local_x = local_x + (int) (3 * ln);
			}
			local_y = local_y + (int) (3 * wd);
		}
		for (i = 0; i < 5; i++) {
			y_cord = (int) y1;
			x_cord = (int) x1 + (i * (int) ((3) * (int) ln));
			for (k = 0; k < 5; k++) {
				y_cord = (int) y1 + (k * (int) (3 * (int) wd));
				if (temp1[i * 5 + k].opnum != -1) {
					finalx = x_cord + ((int) ln) + ((int) ln) / 6;
					finaly = y_cord + 3 * ((int) wd) / 6 + ((int) wd) / 24;
				}

			}
		}
		new wires(j, this.x1, this.y1, ln, wd, temp1);
		int xdc = finalx + 500;
		double yddc2 = 10 * dim.height / 1080.00;

		int ydc = (int) yddc2;

		increment = (int) yddc;
		increment1 = (int) yddc1;
		int j1, a;
		j.setColor(Color.BLACK);
		j.drawString("DC shift", xdc, ydc/4);
		//super.paintComponent(j);
		for (i = 12; i > 0; i--) { // upper half of line
			j.drawLine(xdc, ydc, xdc, ydc + increment1);
			int c = (2 * ydc + 10) / 2;
			j.drawLine(xdc, c, xdc + 5, c);
			if (i == cnst) {

				j.setColor(Color.RED);
				j.drawString("final_output", xdc + 6, c);
				j.setColor(Color.black);
			}
			String s = Integer.toString(i);
			if (i == 12) {
				j.drawString(s + 'v', xdc + 6, c);
			}
			//j.drawLine(xdc,ydc,a,ydc+5);
			ydc = ydc + increment1;
			for (j1 = 0; j1 < 6; j1++) { // zig-zag resistor
				if (j1 % 2 != 0) {
					k = 1;
				} else {
					k = -1;
				}
				if (j1 == 3) {
					j.setColor(Color.RED);
					j.drawString("1 ohm", xdc - 45, ydc);
					j.setColor(Color.BLACK);

				}
				a = xdc + k * 5;
				j.drawLine(xdc, ydc, a, ydc + increment);
				xdc = a;
				ydc = ydc + increment;
			}

		}
		for (i = 0; i <= 12; i++) { // lower half of line
			int c = (2 * ydc + 10) / 2;
			j.setColor(Color.BLUE);
			if (i == 0 && flg == 2) {
				outx = xdc;
				outy = c;
				j.drawString("input", xdc - 40, c);
			} else if (i == 0 && flg == 3) {
				outx = xdc;
				outy = c;
				j.drawString("out" + Integer.toString(last), xdc - 40, c);
			}
			j.setColor(Color.black);
			j.drawLine(xdc, ydc, xdc, ydc + increment1);
			j.drawLine(xdc, c, xdc + 5, c);
			if (i == (-1) * cnst) {

				j.setColor(Color.BLUE);
				if (i == 0) {
					j.drawString("(final_output)", xdc + 40, c);
				} else {
					j.drawString("final_output", xdc + 6, c);
				}
				j.setColor(Color.black);
			}
			String s1 = Integer.toString(i);
			if (i == 12) {
				j.drawString('-' + s1 + 'v', xdc + 6, c);
			} else if (i == 0) {
				j.drawString("V_in", xdc + 11, ydc + 6);
			}

			//j.drawLine(xdc,ydc,a,ydc+5);
			ydc = ydc + increment1;
			if (i != 12) {
				for (j1 = 0; j1 < 6; j1++) { // zig-zag resistor
					if (j1 % 2 != 0) {
						k = 1;
					} else {
						k = -1;
					}
					if (j1 == 3) {
						j.setColor(Color.decode("#008000"));
						j.drawString("1 ohm", xdc - 45, ydc);
						j.setColor(Color.black);
					}
					a = xdc + k * 5;
					j.drawLine(xdc, ydc, a, ydc + increment);
					xdc = a;
					ydc = ydc + increment;
				}
			}
		}
		if (finalx != 0 && finaly != 0) { // final wire connecting
			j.setColor(Color.red);
			//j.drawLine(finalx,finaly,finalx,outy);
			//j.drawLine(finalx,outy,outx,outy);
			j.drawLine(finalx, finaly, outx - 100, finaly);
			j.drawLine(outx - 100, finaly, outx - 100, outy);
			j.drawLine(outx - 100, outy, outx, outy);

		}

		i = 0;
		k = 0;
		local_y = y1;
		for (i = 0; i < 5; i++) {  // three way switch in op-amp
			int local_x = x1;
			for (k = 0; k < 5; k++) {
				if (temp1[k * 5 + i].opnum != -1) {
					//justpaint(j,local_x,local_y,temp1[k*5+i]);
					g2d.setStroke(drawingStroke);
					j.drawLine(local_x + ln / 3 + ln / 12, local_y + 3 * (wd / 12), local_x + 2 * (ln / 3) - ln / 8, local_y + 3 * (wd / 12));
					j.drawLine(local_x + ln / 3 + ln / 8, local_y + wd / 6, local_x + 2 * (ln / 3) - ln / 8, local_y + 3 * (wd / 12));
					j.drawLine(local_x + ln / 3 + ln / 8, local_y + 9 * (wd / 24), local_x + 2 * (ln / 3) - ln / 8, local_y + 3 * (wd / 12));
					//g2d.resetStroke(drawingStroke);
					//j.drawLine(x1+2*(ln/3)-ln/12,y1+3*(l/2),x1+2*(ln/3),y1+3*(l/2));
					local_x = local_x + (3 * ln);
				}
			}
			local_y = local_y + (3 * wd);
		}
		repaint();
		g2d.setTransform(oldtx);
	}
}
