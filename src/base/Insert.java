package base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import base.Update.Map;

public class Insert {
	public static class Map extends Mapper<LongWritable,Text,Text,Text>{
		private int val;
		private String iroll;
		private String iname;
		private String iclass;
		private String ijan;
		private String ifeb;
		private String imar;
		private String iapr;
		private String imay;
		private String i1t;
		private String i2t;
		private String i3t;
		private String full;
		public void setup(Context context) throws IOException,
		InterruptedException {
		val = Integer.parseInt(context.getConfiguration().get("val"));
		full = context.getConfiguration().get("iroll")+","+
		context.getConfiguration().get("iname")+","+context.getConfiguration().get("iclass")+","+
				context.getConfiguration().get("ijan")+","+context.getConfiguration().get("ifeb")+","+
		context.getConfiguration().get("imar")+","+context.getConfiguration().get("iapr")+","+
				context.getConfiguration().get("imay")+","+context.getConfiguration().get("i1t")+","+
		context.getConfiguration().get("i2t")+","+context.getConfiguration().get("i3t");
		}

		public void map(LongWritable key, Text value,Context context)
				throws IOException,InterruptedException {
						
			List<Student> objstudent = new ArrayList<Student>();
			CSVReader csv = new CSVReader();
			String s = value.toString();
			if (s.matches("(.*)Roll(.*)"))
			{
				//objstudent	= csv.run(full);
				context.write(new Text(),new Text(s));
				context.write(new Text(), new Text(full));
			}
			else
				context.write(new Text(),value); 
			/*objstudent =csv.run(s);
			Iterator<Student> stulist = objstudent.iterator();
			
			
			System.out.println("here beforre");
			while(stulist.hasNext()){
				
				Text abcd = new Text(stulist.next().getName());
				if (abcd.toString().contentEquals("Monty")){
					abcd= new Text("Morty");
				System.out.println("here abcd"+abcd);}
				context.write(new Text(),value);
			
			}*/
			
			
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
		String create="";
		for(int i=3;i<args.length;i++)
			create = create + args[i]+",";
		String in = create.substring(0,create.length()-1);
		Configuration conf= new Configuration();
		conf.set("marks", "60");
		CSVReader c = new CSVReader();
		List<Student> ob = c.run(in);
		Iterator<Student> stulist = ob.iterator();
		while(stulist.hasNext()){
			Student st = stulist.next();
			conf.set("val", "0");
			conf.set("iroll",Integer.toString(st.getRoll_No()));
			conf.set("iname",st.getName());
			conf.set("iclass",st.getsClass());
			conf.set("ijan",st.getJan());
			conf.set("ifeb",st.getFeb());
			conf.set("imar",st.getMar());
			conf.set("iapr",st.getApr());
			conf.set("imay",st.getMay());
			conf.set("i1t",Integer.toString(st.getiMarks()));
			conf.set("i2t",Integer.toString(st.getiiMarks()));
			conf.set("i3t",Integer.toString(st.getiiiMarks()));		
		}
		//conf.setJobName("mywc");
		Job job = new Job(conf,"Insert");
		
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
		String[] command = {"cmd.exe", "/C", "hadoop fs -rm /usr/Student.csv"};
		Process pr = r.exec(command);
		pr.waitFor();
		String[] comand = {"cmd.exe", "/C", "hadoop fs -mv /Result/part-r-00000 /usr/Student.csv"};
		pr = r.exec(comand);
		pr.waitFor();
		
		
		
	}

}
