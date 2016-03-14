package base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Arrays;
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


public class Update {
	public static class Map extends Mapper<LongWritable,Text,Text,Text>{
		private int marks;
		private String oname;
		private int oroll ;
		private String oclass;
		private String uname;
		private String uclass;
		private int uroll;
		private String uadd;
		private int umarks;
		private String full;
		public void setup(Context context) throws IOException,
		InterruptedException {
		marks = Integer.parseInt(context.getConfiguration().get("marks"));// <-----declare context in this one
		full = context.getConfiguration().get("uroll")+","+
		context.getConfiguration().get("uname")+","+context.getConfiguration().get("uclass")+","+
				context.getConfiguration().get("ujan")+","+context.getConfiguration().get("ufeb")+","+
		context.getConfiguration().get("umar")+","+context.getConfiguration().get("uapr")+","+
				context.getConfiguration().get("umay")+","+context.getConfiguration().get("imarks")+","+
		context.getConfiguration().get("iimarks")+","+context.getConfiguration().get("iiimarks");
		}

		public void map(LongWritable key, Text value,Context context)
				throws IOException,InterruptedException {
						
			List<Student> objstudent = new ArrayList<Student>();
			CSVReader csv = new CSVReader();
			String s = value.toString();
			if (!s.matches("(.*)Roll(.*)")){
			objstudent	= csv.run(s);}
			oname = context.getConfiguration().get("oname");
			oroll= Integer.parseInt(context.getConfiguration().get("oroll"));
			oclass = context.getConfiguration().get("oclass");// Don't use direct use setup method
			Iterator<Student> stulist = objstudent.iterator();
			while(stulist.hasNext()){
				
				Student sob=stulist.next();
				if ((sob.getName().equals(oname)) && sob.getRoll_No()==oroll && sob.getsClass().equals(oclass)){
					Text abcd= new Text(full);
				System.out.println("here abcd"+abcd);
				context.write(new Text(),abcd);}
				context.write(new Text(),value);
			
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
		String create="";
		//JobConf job = new JobConf(Hadoop.class);
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
			conf.set("uroll", Integer.toString(st.getRoll_No()));
			conf.set("uname", st.getName());
			conf.set("uclass", st.getsClass());
			conf.set("ujan", st.getJan());
			conf.set("ufeb",st.getFeb());
			conf.set("umar",st.getMar());
			conf.set("uapr",st.getApr());
			conf.set("umay",st.getMay());
			conf.set("uimarks",Integer.toString(st.getiMarks()));
			conf.set("uiimarks",Integer.toString(st.getiiMarks()));
			conf.set("uiiimarks",Integer.toString(st.getiiiMarks()));			
		}
		conf.set("oroll", args[0]);
		conf.set("oname", args[1]);
		conf.set("oclass", args[2]);
		
		
		//conf.setJobName("mywc");
		Job job = new Job(conf,"Update");
		
		job.setJarByClass(Hadoop.class);
		job.setMapperClass(Map.class);
		//job.setReducerClass(Reduce.class);
		
		//conf.setMapperClass(Map.class);
		//conf.setReducerClass(Reduce.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		

		Path outputPath = new Path("/Student.csv");
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
		String[] comand = {"cmd.exe", "/C", "hadoop fs -mv /Student.csv/part-r-00000 /usr/Student.csv"};
		pr = r.exec(comand);
		pr.waitFor();
		
		
		
	}

}
