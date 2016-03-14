package base;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;;

public class CSVReader {
	
	public List<Student>  run(String info) {


		//BufferedReader br = null;
		//String line = "";
		String cvsSplitBy = ",";
		List<Student> object = new ArrayList<Student>();
		//int Skip_Line=0;
		//try {

			

			//br = new BufferedReader(new FileReader(csvFile));
			//while ((line = br.readLine()) != null) {

				//if(Skip_Line == 0)
				
					String[] Record= info.split(cvsSplitBy);
					Student ObjStudent = new Student();
					ObjStudent.setRoll_No(Integer.parseInt(Record[0].trim()));
					ObjStudent.setClass(Record[2]);
					ObjStudent.setName(Record[1]);
					if (info.matches("(.*)Jan(.*)"))
						ObjStudent.setJan("January");
					if (info.matches("(.*)Feb(.*)"))
						ObjStudent.setJan("February");
					if (info.matches("(.*)Mar(.*)"))
						ObjStudent.setJan("March");
					if (info.matches("(.*)April(.*)"))
						ObjStudent.setJan("April");
					if (info.matches("(.*)May(.*)"))
						ObjStudent.setJan("May");
					ObjStudent.setiMarks(Integer.parseInt(Record[Record.length-3]));
					ObjStudent.setiiMarks(Integer.parseInt(Record[Record.length-2]));
					ObjStudent.setiiiMarks(Integer.parseInt(Record[Record.length-1]));
					//Shit
					System.out.println("shit");
					object.add(ObjStudent);
				
				//Skip_Line++;}
			
			

		/*} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}*/

		System.out.println("Done");
		return object;
	  }
	}
	


