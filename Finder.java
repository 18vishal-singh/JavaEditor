import javax.swing.*;
import java.awt.Color;
import java.awt.*;
import java.awt.event.*;
public class Finder implements ActionListener
{
	JFrame jfs;
	JLabel jls;
	JTextField jtfs;
	JButton jfind;
	String str,sstr;
	int cindex=0;
	public Finder()
	{
		jfs=new JFrame("Find");
		jls=new JLabel("Find What : ");
		jls.setBounds(20,20,70,25);
		jtfs=new JTextField();
		jtfs.setBounds(90,20,292,25);
		jfs.add(jls);
		jfs.add(jtfs);
		
		jfind=new JButton("Find");
		jfind.setBounds(200,55,80,25);
		jfind.addActionListener(this);
		jfs.add(jfind);

		jfs.setLayout(null);
		jfs.pack();
		jfs.setLocation(700,200);
		jfs.setSize(400,125);
		jfs.setResizable(false);
		jfs.setVisible(true);
		jfs.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==jfind)
		{
			str=MyJavaEditor.jta.getText();
			sstr=jtfs.getText().trim(); 
			int i=str.indexOf(sstr,cindex);
			if(i!=-1)
			{
				cindex=i+sstr.length();
				MyJavaEditor.jta.select(i,cindex);
			}
			else
					jtfs.setText("Not Found");
		}
	}
			
}




















