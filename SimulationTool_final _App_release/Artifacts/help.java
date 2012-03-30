import javax.swing.*;
import java.awt.*;
import java.io.FileReader;
import java.io.BufferedReader;
import java.lang.StringBuffer;

public class help extends JFrame{
	
	private JTabbedPane tabbedPane;
	private String instructions;
	private JTextArea textArea;
	private BufferedReader myInput;
	private FileReader fr;
	private String details;
	private JTextArea textArea2;
	private BufferedReader myInput2;
	private FileReader fr2;
	private String howto;
	private JTextArea textArea3;
	private BufferedReader myInput3;
	private FileReader fr3;
	private Font font;
	private Dimension dim;
	
	public help(){
		
		super("Help..!.");
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		tabbedPane = new JTabbedPane();
		JPanel panel1 = new JPanel();
		//Open Instructions.txt
		try{
			fr = new FileReader("Instructions.txt");
			myInput = new BufferedReader(fr);
		}
		catch(Exception e){
			System.out.println("Error\n");
		}

		String s;
		StringBuffer b = new StringBuffer();
		try{
			while ((s = myInput.readLine()) != null) {
				b.append(s);
				b.append("\n");
			}
		}
		catch(Exception e){
			System.out.println("Error\n");
		}

		instructions = b.toString();
		textArea = new JTextArea(instructions);

		int f = 20*dim.height/1080;
		font = new Font("Serif",Font.PLAIN,f);
		textArea.setLineWrap(true);
		textArea.setColumns(55);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		textArea.setFont(font);
		panel1.add(textArea);
		tabbedPane.add("Instructions",panel1); //Tab 1
		JPanel panel2 = new JPanel();
		String s2;
		StringBuffer b2 = new StringBuffer();

		//Open operatorguide.txt
		try{
			fr2 = new FileReader("operatorguide.txt");
			myInput2 = new BufferedReader(fr2);
		}
		catch(Exception e){
			System.out.println("Error\n");
		}

		try{
			while ((s2 = myInput2.readLine()) != null) {
				b2.append(s2);
				b2.append("\n");
			}
		}
		catch(Exception e){
			System.out.println("Error\n");
		}

		details = b2.toString();

		textArea2 = new JTextArea(details);

		font = new Font("Serif",Font.PLAIN,f);
		textArea2.setLineWrap(true);
		textArea2.setColumns(40);
		textArea2.setWrapStyleWord(true);
		textArea2.setEditable(false);
		textArea2.setFont(font);
		panel2.add(textArea2);

		tabbedPane.add("Operator Guide",panel2);  //Tab 2


		JPanel panel3 = new JPanel();
		String s3;
		StringBuffer b3 = new StringBuffer();

		//Open howtoenter.txt
		try{
			fr3 = new FileReader("howtoenter.txt");
			myInput3 = new BufferedReader(fr3);
		}
		catch(Exception e){
			System.out.println("Error\n");
		}

		try{
			while ((s3 = myInput3.readLine()) != null) {
				b3.append(s3);
				b3.append("\n");
			}
		}
		catch(Exception e){
			System.out.println("Error\n");
		}

		howto = b3.toString();

		textArea3 = new JTextArea(howto);

		font = new Font("Serif",Font.PLAIN,f);
		textArea3.setLineWrap(true);
		textArea3.setColumns(55);
		textArea3.setWrapStyleWord(true);
		textArea3.setEditable(false);
		textArea3.setFont(font);
		panel3.add(textArea3);

		tabbedPane.add("Guidlines to Enter Input",panel3); //Tab 3
		font = new Font("Timesroman",Font.PLAIN,f);
		tabbedPane.setFont(font);
		add(tabbedPane);

		try{
			fr.close();
			fr2.close();
			fr3.close();
		}
		catch(Exception e){
			System.out.println("Error\n");
		}
	}
}
