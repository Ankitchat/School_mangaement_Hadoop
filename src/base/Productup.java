package base;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Productup {

	private JFrame frame;
	private JTextField oname;
	private JTextField oclass;
	private JTextField oroll;
	private JTextField nname;
	private JTextField nclass;
	private JTextField nroll;
	private JTextField n1t;
	private JTextField n2t;
	private JTextField n3t;
	private JCheckBox jan;
	private JCheckBox feb;
	private JCheckBox mar;
	private JCheckBox apr;
	private JCheckBox may;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel lblMarks;
	private JLabel lblChangeTo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Productup window = new Productup();
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
	public Productup() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 622, 485);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		oname = new JTextField();
		oname.setBounds(64, 40, 86, 20);
		frame.getContentPane().add(oname);
		oname.setColumns(10);
		
		JButton btnChangeTo = new JButton("Change");
		btnChangeTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("clicked");
				String njan="",nfeb="",nmar="",napr="",nmay="";
				String sname = oname.getText();
				String sroll = oroll.getText();
				String sclass = oclass.getText();
				String nsname = nname.getText();
				String nsroll = nroll.getText();
				String nsclass = nclass.getText();
				String siterm = n1t.getText();
				String siiterm = n2t.getText();
				String siiiterm = n3t.getText();
				if (jan.isEnabled()){
					njan = "Jan";
				}
				if (feb.isEnabled()){
					nfeb = "Feb";
				}
				if (mar.isEnabled()){
					nmar = "Mar";
				}
				if (apr.isEnabled()){
					napr = "April";
				}
				if (may.isEnabled()){
					nmay = "May";
				}
				String cmd = "hadoop jar base.jar base.Hadoop "+sroll+" "+sname+" "+sclass+" "+nsroll+" "+nsname+" "+nsclass+" "+njan+" "+nfeb+" "+nmar+" "+napr+" "+nmay+" "+siterm+" "+siiterm+" "+siiiterm;
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
				     } catch (IOException e3) {
				            e3.printStackTrace();
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
		btnChangeTo.setBounds(127, 402, 89, 23);
		frame.getContentPane().add(btnChangeTo);
		
		oclass = new JTextField();
		oclass.setBounds(233, 40, 86, 20);
		frame.getContentPane().add(oclass);
		oclass.setColumns(10);
		
		oroll = new JTextField();
		oroll.setBounds(424, 40, 86, 20);
		frame.getContentPane().add(oroll);
		oroll.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(10, 43, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Class");
		lblNewLabel_1.setBounds(170, 43, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Roll No");
		lblNewLabel_2.setBounds(355, 43, 46, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		nname = new JTextField();
		nname.setBounds(64, 181, 86, 20);
		frame.getContentPane().add(nname);
		nname.setColumns(10);
		
		nclass = new JTextField();
		nclass.setBounds(233, 181, 86, 20);
		frame.getContentPane().add(nclass);
		nclass.setColumns(10);
		
		nroll = new JTextField();
		nroll.setBounds(424, 181, 86, 20);
		frame.getContentPane().add(nroll);
		nroll.setColumns(10);
		
		n1t = new JTextField();
		n1t.setBounds(64, 271, 86, 20);
		frame.getContentPane().add(n1t);
		n1t.setColumns(10);
		
		n2t = new JTextField();
		n2t.setBounds(233, 271, 86, 20);
		frame.getContentPane().add(n2t);
		n2t.setColumns(10);
		
		n3t = new JTextField();
		n3t.setBounds(424, 271, 86, 20);
		frame.getContentPane().add(n3t);
		n3t.setColumns(10);
		
		label = new JLabel("Name");
		label.setBounds(10, 184, 35, 14);
		frame.getContentPane().add(label);
		
		label_1 = new JLabel("Class");
		label_1.setBounds(170, 184, 46, 14);
		frame.getContentPane().add(label_1);
		
		label_2 = new JLabel("Roll No");
		label_2.setBounds(355, 184, 46, 14);
		frame.getContentPane().add(label_2);
		
		lblMarks = new JLabel("Marks");
		lblMarks.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMarks.setBounds(10, 244, 46, 14);
		frame.getContentPane().add(lblMarks);
		
		JLabel lblFessPaid = new JLabel("Fess Paid");
		lblFessPaid.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFessPaid.setBounds(10, 317, 73, 14);
		frame.getContentPane().add(lblFessPaid);
		
		jan = new JCheckBox("Jan");
		jan.setBounds(52, 353, 46, 23);
		frame.getContentPane().add(jan);
		
		feb = new JCheckBox("Feb");
		feb.setBounds(140, 353, 46, 23);
		frame.getContentPane().add(feb);
		
		mar = new JCheckBox("Mar");
		mar.setBounds(233, 353, 46, 23);
		frame.getContentPane().add(mar);
		
		apr = new JCheckBox("April");
		apr.setBounds(334, 353, 46, 23);
		frame.getContentPane().add(apr);
		
		may = new JCheckBox("May");
		may.setBounds(437, 353, 46, 23);
		frame.getContentPane().add(may);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Start p =new Start();
				String[] a = new String[0];
				p.main(a);
			
			}   });
		btnBack.setBounds(424, 402, 89, 23);
		frame.getContentPane().add(btnBack);
		
		JButton btnAboveRemainSame = new JButton("Above Remain Same");
		btnAboveRemainSame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nname.setText(oname.getText());
				nroll.setText(oroll.getText());
				nclass.setText(oclass.getText());
			}
		});
		btnAboveRemainSame.setBounds(355, 106, 145, 23);
		frame.getContentPane().add(btnAboveRemainSame);
		
		lblChangeTo = new JLabel("Change To");
		lblChangeTo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblChangeTo.setBounds(147, 109, 79, 20);
		frame.getContentPane().add(lblChangeTo);
	}
}

