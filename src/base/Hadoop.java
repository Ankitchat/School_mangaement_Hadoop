package base;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Iterator;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.fs.Path;

public class Hadoop {

	
	public static class Map extends Mapper<LongWritable,Text,Text,Text>{
		private int marks;
		public void setup(Context context) throws IOException,
		InterruptedException {
		marks = Integer.parseInt(context.getConfiguration().get("marks"));
		} 

		public void map(LongWritable key, Text value,Context context)
				throws IOException,InterruptedException {
						
			List<Student> objstudent = new ArrayList<Student>();
			CSVReader csv = new CSVReader();
			String s = value.toString();
			if (!s.matches("(.*)Roll(.*)")){
			objstudent	= csv.run(s);}
			String oname = context.getConfiguration().get("oname");
			int oroll= Integer.parseInt(context.getConfiguration().get("oroll"));
			String oclass = context.getConfiguration().get("oclass");
			Iterator<Student> stulist = objstudent.iterator();
			
			
			System.out.println("here beforre");
			while(stulist.hasNext()){
				
				Student sob=stulist.next();
				if ((sob.getName().equals(oname)) && sob.getRoll_No()==oroll && sob.getsClass().equals(oclass)){
					Text abcd= new Text("yes");
				System.out.println("here abcd"+abcd);
				context.write(new Text(),value);}
			
			}
			
			
		}
		
		
	}
	/*public static class Reduce extends Reducer<Text,IntWritable,Text,IntWritable>{

		public void reduce(Text key, Iterable<IntWritable> values,
				Context context)
				throws IOException,InterruptedException {
			int sum=0;
			// TODO Auto-generated method stub
			for(IntWritable x: values)
			{
				sum+=x.get();
			}
			context.write(key, new IntWritable(sum));
			
		}
		
	}*/
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		//JobConf job = new JobConf(Hadoop.class);

		Configuration conf= new Configuration();
		conf.set("marks", "60");
		conf.set("oroll", args[0]);
		conf.set("oname", args[1]);
		conf.set("oclass", args[2]);
		
		//conf.setJobName("mywc");
		Job job = new Job(conf,"Hadoop");
		
		job.setJarByClass(Hadoop.class);
		job.setMapperClass(Map.class);
		//job.setReducerClass(Reduce.class);
		
		//conf.setMapperClass(Map.class);
		//conf.setReducerClass(Reduce.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		

		Path outputPath = new Path("/Result");
		Path inputPath = new Path("/usr/Student.csv");
			
	        //Configuring the input/output path from the filesystem into the job
	        
	    FileInputFormat.addInputPath(job, inputPath);
	    FileOutputFormat.setOutputPath(job, outputPath);
			
			//deleting the output path automatically from hdfs so that we don't have delete it explicitly
			
		outputPath.getFileSystem(conf).delete(outputPath);
			
			//exiting the job only if the flag value becomes false
			
		job.waitForCompletion(true);
		
		Runtime r= Runtime.getRuntime();
		String[] command = {"cmd.exe", "/C", "cd"};
		Process pr = r.exec(command);
		BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
		String line = input.readLine();
		System.out.println(line);
		pr.waitFor();
		line=line+"\\result.csv";
		String[] comand = {"cmd.exe", "/C", "hadoop fs -copyToLocal /Result/part-r-00000 "+line};
		pr = r.exec(comand);
		pr.waitFor();
	}

}
