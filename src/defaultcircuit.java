import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class defaultcircuit {

	static int x = 110;
	static int y = 205;
	opvalues temp[] = new opvalues[25];
	private JButton view;

	public defaultcircuit(final JFrame frm, final String in, opvalues a[], final int cnst, final int flg, final int last_used) {

		temp = a;
		double ssw = 50;
		double ssh = 150;
		double ssw1 = 220;
		double ssh1 = 140;
		double w = 20;
		double h1 = 160;
		double h2 = 50;
		double h3 = 370;
		double w2 = 200;
		double h4 = 40;
		double w3 = 100;
		double h5 = 40;

		// new page 2 panel
		drawcircuit c1 = new drawcircuit((int) ssw, (int) ssh, (int) ssw1, (int) ssh1, temp, cnst, flg, last_used);
		c1.setLayout(null);
		c1.setBackground(Color.WHITE);

		// putting the new panel on the frame/window
		frm.setContentPane(c1);
		frm.validate();
		frm.repaint();

		// text field to hold the value input by the user 
		JTextField input = new JTextField(in);
		input.setBounds((int) h1, (int) w, (int) w2, (int) h4);
		input.setEditable(false);
		c1.add(input);
		
		// reset button to go to page 2
		JButton but = new JButton("Reset");
		but.setBounds((int) h2, (int) w, (int) w3, (int) h5);
		but.addActionListener(
				new ActionListener() {

					public void actionPerformed(ActionEvent event) {
						inputframe nextframe = new inputframe(frm);
						frm.setContentPane(nextframe);
						frm.validate();
						frm.repaint();
					}
				});
		c1.add(but);

		// button to go to view 2 i.e the view with wires and connections
		view = new JButton("Circuit");
		view.setBounds((int) h3, (int) w, (int) w3, (int) h5);
		view.addActionListener(
				new ActionListener() {

					public void actionPerformed(ActionEvent event) {
						new defaultcircuitv2(frm, in, temp, cnst, flg, last_used);
					}
				});
		c1.add(view);

	}
}
