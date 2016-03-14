package base;

import java.awt.EventQueue;
import java.io.*;

import javax.swing.JFrame;
import javax.swing.JTextField;

import java.awt.BorderLayout;

import javax.swing.JButton;

import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JCheckBox;

public class Product {

	private JFrame frame;
	private JTextField name;
	private JTextField roll;
	private JTextField stclass;
	private JTextField iterm;
	private JTextField iiterm;
	private JTextField iiiterm;
	private JCheckBox Jan;
	private JCheckBox Feb;
	private JCheckBox Mar;
	private JCheckBox April;
	private JCheckBox May;
	private JButton btnBack;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Product window = new Product();
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
	public Product() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 436);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		name = new JTextField();
		name.setBounds(153, 42, 145, 18);
		frame.getContentPane().add(name);
		name.setColumns(10);
		
		roll = new JTextField();
		roll.setBounds(153, 79, 145, 20);
		frame.getContentPane().add(roll);
		roll.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(81, 44, 46, 14);
		frame.getContentPane().add(lblName);
		
		JLabel lblRollNo = new JLabel("Roll No.");
		lblRollNo.setBounds(81, 82, 46, 14);
		frame.getContentPane().add(lblRollNo);
		
		JLabel lblClass = new JLabel("Class");
		lblClass.setBounds(81, 122, 46, 14);
		frame.getContentPane().add(lblClass);
		
		JLabel lblNewLabel = new JLabel("Marks");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(41, 147, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblITerm = new JLabel("I Term");
		lblITerm.setBounds(81, 172, 46, 14);
		frame.getContentPane().add(lblITerm);
		
		JLabel lblIiTerm = new JLabel("II Term");
		lblIiTerm.setBounds(81, 197, 46, 14);
		frame.getContentPane().add(lblIiTerm);
		
		JLabel lblIiiTerm = new JLabel("III Term");
		lblIiiTerm.setBounds(81, 222, 46, 14);
		frame.getContentPane().add(lblIiiTerm);
		
		JLabel lblFees = new JLabel("Fees Paid");
		lblFees.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFees.setBounds(41, 251, 70, 14);
		frame.getContentPane().add(lblFees);
		
		Jan = new JCheckBox("Jan");
		Jan.setBounds(68, 290, 46, 23);
		frame.getContentPane().add(Jan);
		
		Feb = new JCheckBox("Feb");
		Feb.setBounds(136, 290, 46, 23);
		frame.getContentPane().add(Feb);
		
		Mar = new JCheckBox("Mar");
		Mar.setBounds(191, 290, 46, 23);
		frame.getContentPane().add(Mar);
		
		April = new JCheckBox("April");
		April.setBounds(264, 290, 46, 23);
		frame.getContentPane().add(April);
		
		May = new JCheckBox("May");
		May.setBounds(328, 290, 46, 23);
		frame.getContentPane().add(May);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
						
				System.out.println("clicked");
				String jan="",feb="",mar="",apr="",may="";
				String sname = name.getText();
				String sroll = roll.getText();
				String sclass = stclass.getText();
				String siterm = iterm.getText();
				String siiterm = iiterm.getText();
				String siiiterm = iiiterm.getText();
				if (Jan.isEnabled()){
					jan = "Jan";
				}
				if (Feb.isEnabled()){
					feb = "Feb";
				}
				if (Mar.isEnabled()){
					mar = "Mar";
				}
				if (April.isEnabled()){
					apr = "April";
				}
				if (May.isEnabled()){
					may = "May";
				}
				String cmd = "hadoop jar base.jar base.Insert "+sroll+" "+sname+" "+sclass+" "+jan+" "+feb+" "+mar+" "+apr+" "+may+" "+siterm+" "+siiterm+" "+siiiterm;
				Runtime r= Runtime.getRuntime();
				Process pr=null;
				System.out.println("dirr");
				String[] chdir = {"cmd.exe", "/C", "dir"};
				try {
					pr = r.exec(chdir);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
				String line = null; 

				     try {
				        while ((line = input.readLine()) != null)
				            System.out.println(line);
				     } catch (IOException e) {
				            e.printStackTrace();
				     }
				try {
					pr.waitFor();
				} catch (InterruptedException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				System.out.println("exe");
				String[] command = {"cmd.exe", "/C", cmd};
				
				try {
					pr = r.exec(command);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
				line = null; 

				     try {
				        while ((line = input.readLine()) != null)
				            System.out.println(line);
				     } catch (IOException e) {
				            e.printStackTrace();
				     }
				try {
						pr.waitFor();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				System.out.println("done");
			}
		});
		btnSave.setBounds(62, 334, 89, 23);
		frame.getContentPane().add(btnSave);
		
		stclass = new JTextField();
		stclass.setBounds(153, 119, 145, 20);
		frame.getContentPane().add(stclass);
		stclass.setColumns(10);
		
		iterm = new JTextField();
		iterm.setBounds(151, 169, 86, 20);
		frame.getContentPane().add(iterm);
		iterm.setColumns(10);
		
		iiterm = new JTextField();
		iiterm.setBounds(151, 194, 86, 20);
		frame.getContentPane().add(iiterm);
		iiterm.setColumns(10);
		
		iiiterm = new JTextField();
		iiiterm.setBounds(153, 219, 86, 20);
		frame.getContentPane().add(iiiterm);
		iiiterm.setColumns(10);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Start p =new Start();
				String[] a = new String[0];
				p.main(a);
			}
		});
		btnBack.setBounds(285, 334, 89, 23);
		frame.getContentPane().add(btnBack);
	}
}
