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

public class Productdel {

	private JFrame frame;
	private JTextField droll;
	private JTextField dclass;
	private JTextField dname;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Productdel window = new Productdel();
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
	public Productdel() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 358);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Roll No");
		label.setBounds(123, 87, 46, 14);
		frame.getContentPane().add(label);
		
		droll = new JTextField();
		droll.setColumns(10);
		droll.setBounds(202, 84, 86, 20);
		frame.getContentPane().add(droll);
		
		dclass = new JTextField();
		dclass.setColumns(10);
		dclass.setBounds(202, 142, 86, 20);
		frame.getContentPane().add(dclass);
		
		JLabel label_1 = new JLabel("Class");
		label_1.setBounds(123, 145, 46, 14);
		frame.getContentPane().add(label_1);
		
		dname = new JTextField();
		dname.setColumns(10);
		dname.setBounds(202, 31, 86, 20);
		frame.getContentPane().add(dname);
		
		JLabel label_2 = new JLabel("Name");
		label_2.setBounds(123, 34, 46, 14);
		frame.getContentPane().add(label_2);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sname = dname.getText();
				String sroll = droll.getText();
				String sclass = dclass.getText();
				String cmd = "hadoop jar base.jar base.delete "+sroll+" "+sname+" "+sclass;
				String[] command = {"cmd.exe", "/C", cmd};
				Runtime r= Runtime.getRuntime();
				Process pr=null;
				
				try {
					pr = r.exec(command);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
				String line = null; 

				     try {
				        while ((line = input.readLine()) != null)
				            System.out.println(line);
				     } catch (IOException e2) {
				            e2.printStackTrace();
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
		btnDelete.setBounds(163, 199, 89, 23);
		frame.getContentPane().add(btnDelete);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Start p =new Start();
				String[] a = new String[0];
				p.main(a);
			}
		});
		btnBack.setBounds(163, 251, 89, 23);
		frame.getContentPane().add(btnBack);
	}
}
