package base;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Productfil {

	private JFrame frame;
	private JTextField name;
	private JTextField sclass;
	private JTextField roll;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Productfil window = new Productfil();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Productfil() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Name");
		label.setBounds(40, 28, 46, 14);
		frame.getContentPane().add(label);
		
		name = new JTextField();
		name.setColumns(10);
		name.setBounds(88, 25, 86, 20);
		frame.getContentPane().add(name);
		
		JLabel label_1 = new JLabel("Class");
		label_1.setBounds(40, 70, 46, 14);
		frame.getContentPane().add(label_1);
		
		sclass = new JTextField();
		sclass.setColumns(10);
		sclass.setBounds(88, 67, 86, 20);
		frame.getContentPane().add(sclass);
		
		JLabel label_2 = new JLabel("Roll No");
		label_2.setBounds(236, 28, 46, 14);
		frame.getContentPane().add(label_2);
		
		roll = new JTextField();
		roll.setColumns(10);
		roll.setBounds(292, 25, 86, 20);
		frame.getContentPane().add(roll);
		
		JButton btnFind = new JButton("Find");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sname = name.getText();
				String sroll = roll.getText();
				String stclass = sclass.getText();
				String cmd = "hadoop jar base.jar base.Hadoop "+sroll+" "+sname+" "+stclass;
				Runtime r= Runtime.getRuntime();
				Process pr=null;
				String[] command = {"cmd.exe", "/C", cmd};
				
				try {
					pr = r.exec(command);
				} catch (IOException e1){
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
				String line = null; 

				     try {
				        while ((line = input.readLine()) != null)
				            System.out.println(line);
				     } catch (IOException t) {
				           t.printStackTrace();
				     }
				try {
						pr.waitFor();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				String[] commd = {"cmd.exe", "/C", "result.csv"};
				try {
					pr = r.exec(commd);
				} catch (IOException e1){
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					pr.waitFor();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("done");
			}
		});
		btnFind.setBounds(161, 136, 89, 23);
		frame.getContentPane().add(btnFind);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(161, 178, 89, 23);
		frame.getContentPane().add(btnBack);
	}
}
