import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Element;


/*
@author:Vishal Singh
*/

class MyJavaEditor implements ActionListener
{
	JFrame jf;
	JLabel jl,jl1,jl2;
	JTextField jtf,jtf1,jtf2a,jtf2b;
	JTextField jtfs;
	JFileChooser jfcs;
	static JTextArea jta;
	JTextArea jta1,lines;
	JButton jbcompile,jbrun,jenter,jclear;	//	,jbrowse;
	JScrollPane jsp,jsp1;
	Runtime r;
	String add = "C:\\Program Files\\Java\\jdk1.8.0_05\\bin\\";
	String fname="";
	String result="";
	String nl="";
	String newPath="";
	JFileChooser jfc;			//,jfc1;
	JMenuBar menuBar;
	JMenu menu,submenu;
	JMenuItem menuItem1,menuItem2,menuItem3,menuItem4,menuItem5,menuItem6,menuItem7,menuItem8,menuItem9,menuItem10,exit;
	
	MyJavaEditor()
	{
		jf=new JFrame("My editor");
//		jf.getContentPane().setBackground(Color.lightGray);	**pasand nahi aa raha koi**
		
		newPath=System.getProperty("user.dir");
		jfc = new JFileChooser(newPath);
//		jfc1 = new JFileChooser(newPath);
//		jfc1.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		menuBar = new JMenuBar();
		menu = new JMenu("Menu");
		menu.setMnemonic(KeyEvent.VK_F);
		menuBar.add(menu);
		
		menuItem2 = new JMenuItem("open",KeyEvent.VK_T);
		menuItem2.addActionListener(this);
		menuItem2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		menu.add(menuItem2);
		
		menu.addSeparator();

		exit=new JMenuItem("exit");
		exit.addActionListener(this);
		menu.add(exit);
		
		menu = new JMenu("Edit");
		menu.setMnemonic(KeyEvent.VK_N);
		menuBar.add(menu);
		menuItem4 = new JMenuItem("copy",KeyEvent.VK_T);
		menuItem4.addActionListener(this);
		menuItem4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		menu.add(menuItem4);
		menuItem5 = new JMenuItem("cut",KeyEvent.VK_T);
		menuItem5.addActionListener(this);
		menuItem5.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		menu.add(menuItem5);
		menuItem6 = new JMenuItem("paste",KeyEvent.VK_T);
		menuItem6.addActionListener(this);
		menuItem6.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
		menu.add(menuItem6);

		menuItem9 = new JMenuItem("find",KeyEvent.VK_T);
		menuItem9.addActionListener(this);
		menuItem9.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
		menu.add(menuItem9);

		menu.addSeparator();
		menuItem7 = new JMenuItem("text color");
		menuItem7.addActionListener(this);
		menu.add(menuItem7);

		menu =new JMenu("Tools");
		menu.setMnemonic(KeyEvent.VK_N);
		menuBar.add(menu);
		menuItem1 = new JMenuItem("Compile",KeyEvent.VK_T);
		menuItem1.addActionListener(this);
		menuItem1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F9, ActionEvent.ALT_MASK));
		menu.add(menuItem1);
		menuItem3 = new JMenuItem("Run",KeyEvent.VK_T);
		menuItem3.addActionListener(this);
		menuItem3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F9, ActionEvent.CTRL_MASK));
		menu.add(menuItem3);
		
		menu.addSeparator();
		menuItem10 = new JMenuItem("Set Bin Location");
		menuItem10.addActionListener(this);
		menu.add(menuItem10);
	
		
		menu = new JMenu("About");
		menu.setMnemonic(KeyEvent.VK_N);
		menuBar.add(menu);
		menuItem8 = new JMenuItem("about",KeyEvent.VK_T);
		menuItem8.addActionListener(this);
		menuItem8.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menu.add(menuItem8);


		menuBar.add(menu);
		jf.setJMenuBar(menuBar);


		jl=new JLabel("Enter java class name : ");
		jl.setBounds(50,20,140,25);
		jl1=new JLabel("Present Directory : ");
		jl1.setBounds(750,20,130,25);	
		jl2=new JLabel("Manual Command : ");
		jl2.setBounds(50,648,130,25);
	
		jtf=new JTextField();
		jtf.setBounds(190,20,230,25);
		jtf.setFont(new Font("varinda",Font.BOLD,15));
		jtf1=new JTextField();
		jtf1.setBounds(860,20,365,25);
		jtf1.setFont(new Font("varinda",Font.BOLD,15));
		jtf1.setText(newPath);
		jtf1.setEditable(false);
		jtf2a=new JTextField();
		jtf2a.setBounds(163,648,80,25);
		jtf2a.setFont(new Font("varinda",Font.BOLD,15));
		jtf2b=new JTextField();
		jtf2b.setBounds(250,648,380,25);
		jtf2b.setFont(new Font("varinda",Font.BOLD,15));
				
		jta=new JTextArea(50,50);
		jta.setFont(new Font("varinda",Font.BOLD,17));
		lines=new JTextArea("1");
		lines.setBackground(Color.LIGHT_GRAY);
		lines.setEditable(false);
		lines.setFont(new Font("varinda",Font.BOLD,17));
		lines.setForeground(Color.red);
		jsp=new JScrollPane();
//**************************************
		jta.getDocument().addDocumentListener(new DocumentListener(){
			public String getText(){
				int caretPosition = jta.getDocument().getLength();
				Element root = jta.getDocument().getDefaultRootElement();
				String text = "1" + System.getProperty("line.separator");
				for(int i = 2; i < root.getElementIndex( caretPosition ) + 2; i++){
					text += i + System.getProperty("line.separator");
				}
				return text;
			}
			@Override
			public void changedUpdate(DocumentEvent de) {
				lines.setText(getText());
			}
 
			@Override
			public void insertUpdate(DocumentEvent de) {
				lines.setText(getText());
			}
 
			@Override
			public void removeUpdate(DocumentEvent de) {
				lines.setText(getText());
			}
 
		});
		jsp.getViewport().add(jta);
		jsp.setRowHeaderView(lines);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
 
		jf.pack();
	
//**************************************

		jta1=new JTextArea(50,50);
		jta1.setFont(new Font("varinda",Font.BOLD,15));
		jta1.setEditable(false);
		jta1.setBackground(Color.black);
		jta1.setForeground(Color.white);
	
		jsp1=new JScrollPane(jta1);
		jsp.setBounds(50,60,670,575);
		jsp1.setBounds(750,60,570,575);
	
		jf.add(jsp);
		jf.add(jsp1);
				
		jbcompile=new JButton("Compile");
		jbcompile.setBounds(1000,648,80,25);
		
		jbrun=new JButton("Run");
		jbrun.setBounds(1100,648,80,25);
		
	//	jbrowse=new JButton("Browse");
	//	jbrowse.setBounds(1235,20,80,25);
	
		jenter=new JButton("Enter");
		jenter.setBounds(639,648,80,25);
		
		jclear=new JButton("Clear");
		jclear.setBounds(1200,648,80,25);

		jf.add(jl);
		jf.add(jl1);
		jf.add(jl2);
		jf.add(jtf);
		jf.add(jtf1);
		jf.add(jtf2a);
		jf.add(jtf2b);

		r=Runtime.getRuntime();
		
		jf.add(jbcompile);
		jf.add(jbrun);
	//	jf.add(jbrowse);
		jf.add(jenter);
		jf.add(jclear);

		jbcompile.addActionListener(this);
		jbrun.addActionListener(this);
	//	jbrowse.addActionListener(this);
		jenter.addActionListener(this);
		jclear.addActionListener(this);
		
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLayout(null);
		jf.setSize(550,550);
		jf.setVisible(true);
		jf.setExtendedState(JFrame.MAXIMIZED_BOTH);	
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("Clear"))
		{
			jta1.setText("");
			result="";
		}
		if(e.getActionCommand().equals("exit"))
			System.exit(0);
		if(e.getActionCommand().equals("Set Bin Location"))
		{
			//**************************************
			JFrame jfs;
			JLabel jls;
		//	JTextField jtfs;
			JButton jset,jbrowse;
		//	JFileChooser jfcs;
			
			jfcs = new JFileChooser("C:\\Program Files\\Java");
			jfcs.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
			jfs=new JFrame("Set Bin Location");
			jls=new JLabel("Location : ");
			jls.setBounds(20,20,70,25);
			jtfs=new JTextField(add);
			jtfs.setBounds(90,20,292,25);
			jfs.add(jls);
			jfs.add(jtfs);
		
			jbrowse=new JButton("Browse");
			jbrowse.setBounds(160,55,80,25);
			jbrowse.addActionListener(new ActionListener()
			{
			public void actionPerformed(ActionEvent e)
			{
				if(e.getActionCommand().equals("Browse"))
				{
					int r=jfcs.showOpenDialog(null);
					if(r==JFileChooser.APPROVE_OPTION)
					{
						nl=""+jfcs.getSelectedFile();
						nl=nl.trim();
						nl=nl+"\\";
						jtfs.setText(nl);
					}
				}
				
			}
			});
			jfs.add(jbrowse);
			jset=new JButton("Set");
			jset.setBounds(260,55,80,25);
			jset.addActionListener(new ActionListener()
			{
			public void actionPerformed(ActionEvent e)
			{
				if(e.getActionCommand().equals("Set"))
				{
					System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");
					add=nl;
				}
			}
			});


			jfs.add(jset);
		
			jfs.setLayout(null);
			jfs.pack();
			jfs.setLocation(200,200);
			jfs.setSize(400,125);
			jfs.setResizable(false);
			jfs.setVisible(true);
			jfs.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			//*************************************
		}

		if(e.getActionCommand().equals("open"))
		{
			int r=jfc.showOpenDialog(null);
			if(r==JFileChooser.APPROVE_OPTION)
			{
				String filePath = jfc.getSelectedFile().getPath();
				File file=jfc.getSelectedFile();
				String fn=file.getName().replaceFirst("[.][^.]+$", "");
				try {
					FileInputStream fr = new FileInputStream(filePath);
					InputStreamReader isr = new InputStreamReader(fr, "UTF-8");
					BufferedReader reader = new BufferedReader(isr);
					StringBuffer buffer = new StringBuffer();

					String line = null;
					while ((line = reader.readLine()) != null) 
					{
						buffer.append(line+"\n");
					}

					reader.close();
					jtf.setText(fn);
					jta.setText(buffer.toString());
				    } 
				catch (IOException ex) 
				{
					ex.printStackTrace();
				}
			}
		}		
		if(e.getActionCommand().equals("copy"))
			jta.copy();
		if(e.getActionCommand().equals("cut"))
			jta.cut();
		if(e.getActionCommand().equals("paste"))
			jta.paste();
		if(e.getActionCommand().equals("find"))
		{
			new Finder();
		}
		if(e.getActionCommand().equals("text color"))
		{
			System.out.println("checked");
			setColor();
		}
		
		if((e.getSource()==jbcompile)||(e.getActionCommand().equals("Compile")))
		{
			if(!jtf.getText().equals(""))
			{
				try
				{
					fname=jtf.getText()+".java";
					FileWriter fw=new FileWriter(fname);
					String s1=jta.getText();
					s1=s1.replaceAll("(?!\\r)\\n", "\r\n");
					PrintWriter pw=new PrintWriter(fw);
					pw.println(s1);
					pw.flush();
					Process error=r.exec(add+"javac.exe "+fname);
					BufferedReader err=new BufferedReader(new InputStreamReader(error.getErrorStream()));
					result="";
					while(true)
					{
						String temp=err.readLine();	
						if(temp!=null)
						{
							result+=temp;
							result+="\n";
						}
						else
							break;
					}
					if(result.equals(""))
					{
						jta1.setText("Compilation successful "+fname);
						err.close();
					}
					else
						jta1.setText(result);
				}
				catch(Exception e2)
				{
					jta1.setText(""+e2);
				}
			}
			else
				jta1.setText("Please enter the java program name");
		}
		if((e.getSource()==jbrun)||(e.getActionCommand().equals("Run")))
		{
			System.out.println(System.getProperty("user.dir"));
			int start=0;
			try
			{
				String fn=jtf.getText().trim();
				Process p=r.exec(add+"java "+fn);
				BufferedReader output=new BufferedReader(new InputStreamReader(p.getInputStream()));
				BufferedReader error=new BufferedReader(new InputStreamReader(p.getErrorStream()));
				while(true)
				{
					String temp=output.readLine();
					if(temp!=null)
					{
						result+=temp;
						result+="\n";
					}
					else
						break;
				}
				if(result.equals(""))
				{
					while(true)
					{
						String temp=error.readLine();
						if(temp!=null)
						{
							result+=temp;
							result+="\n";
						}
						else 	
							break;
					}
				}
				output.close();
				error.close();
				jta1.setText(result);
			}
			catch(Exception e2)
			{
				jta1.setText(""+e2);
			}
		}
		if(e.getActionCommand().equals("about"))
		{
			About a=new About();
		}
		if(e.getActionCommand().equals("Enter"))
		{
			if(jtf2a.getText().trim().equals("javac"))
			{
			   if(!jtf.getText().equals(""))
			   {
				try
				{
					fname=jtf.getText()+".java";
					String fn=jtf2b.getText().trim();
					FileWriter fw=new FileWriter(fname);
					String s1=jta.getText();
					s1=s1.replaceAll("(?!\\r)\\n", "\r\n");
					PrintWriter pw=new PrintWriter(fw);
					pw.println(s1);
					pw.flush();
					Process error=r.exec(add+"javac "+fn);
					BufferedReader err=new BufferedReader(new InputStreamReader(error.getErrorStream()));
					result="";
					while(true)
					{
						String temp=err.readLine();	
						if(temp!=null)
						{
							result+=temp;
							result+="\n";
						}
						else
							break;
					}
					if(result.equals(""))
					{
						jta1.setText("Compilation successful "+fname);
						err.close();
					}
					else
						jta1.setText(result);
				}
				catch(Exception e2)
				{
					jta1.setText(""+e2);
				}
			   }
			   else
			   	jta1.setText("Please enter the java program name");	
			}
			else
			{
			int start=0;
			try
			{
				String fn=jtf2b.getText().trim();
				String tool=jtf2a.getText().trim();
				Process p=r.exec(add+tool+" "+fn);
				BufferedReader output=new BufferedReader(new InputStreamReader(p.getInputStream()));
				BufferedReader error=new BufferedReader(new InputStreamReader(p.getErrorStream()));
				while(true)
				{
					String temp=output.readLine();
					if(temp!=null)
					{
						result+=temp;
						result+="\n";
					}
					else
						break;
				}
				if(result.equals(""))
				{
					while(true)
					{
						String temp=error.readLine();
						if(temp!=null)
						{
							result+=temp;
							result+="\n";
						}
						else 	
							break;
					}
				}
				output.close();
				error.close();
				jta1.setText(result);
			}
			catch(Exception e2)
			{
				jta1.setText(""+e2);
			}
			}
		}			
	/*	if(e.getActionCommand().equals("Browse"))
		{
			int r=jfc1.showOpenDialog(null);
			if(r==JFileChooser.APPROVE_OPTION)
			{
				newPath=""+jfc1.getSelectedFile();
				newPath=newPath.trim();
				jtf1.setText(newPath);
				System.setProperty("user.dir", newPath);
				
				
			}
			
		}
	*/
							
	}
	public void setColor()
	{
		Color bgColor= JColorChooser.showDialog(null,"adavance color selection",Color.RED);
    		if (bgColor != null)
      			jta.setForeground(bgColor);
	}
	public static void main(String s[])
	{
		try {
            		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        	} catch (Exception exc) 
		{
            		System.err.println("Error loading L&F: " + exc);
        	}
		new MyJavaEditor();
	}
}



