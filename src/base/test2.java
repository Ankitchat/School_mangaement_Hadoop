package base;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class test2 {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test2 window = new test2();
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
	public test2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnButton = new JButton("button");
		btnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Runtime r= Runtime.getRuntime();
				String[] chdir = {"cmd.exe", "/C", "cd"};
				Process pr=null;
				try {
					pr = r.exec(chdir);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
				String line=null;
				try {
					line = input.readLine();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				System.out.println(line);
				line=line+"\\result.csv";
				System.out.println(line);
				String[] commd = {"cmd.exe", "/C", "result.csv"};
				try {
					pr = r.exec(commd);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					pr.waitFor();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				     
			}
		});
		frame.getContentPane().add(btnButton, BorderLayout.CENTER);
	}

}
