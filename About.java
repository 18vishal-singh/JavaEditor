import javax.swing.*;
import java.awt.Color;
import java.awt.*;
public class About
{
	static final String NEWLINE = System.getProperty("line.separator");
	JTextArea ta;
	About()
	{
		JFrame f = new JFrame("About programmer");
		JPanel jp=new JPanel();
		ta=new JTextArea();
		ta.setText("*****JAVA DEVELOPMENT ENVIRONMENT*****\nThis Editor is developed purely in java.\nMake sure you have latest version of JDK\ni.e. jkd_1.8 installed on your computer.\nAnd set bin folder path from tool menu.\n\n\n\n\n\nMade By:-\nVishal Singh\n1209710121\nCS-B(3rd year)");
		ta.setEditable(false);
		jp.setLayout(new BorderLayout());
		jp.add(ta,BorderLayout.CENTER);
		jp.setBackground(Color.GREEN);
		f.add(jp);
		f.setLocation(200,200);
		f.setSize(500,500);
		f.setResizable(false);
		jp.setBorder(BorderFactory.createEmptyBorder(80,80,80,80));
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	
	}
	
}	

