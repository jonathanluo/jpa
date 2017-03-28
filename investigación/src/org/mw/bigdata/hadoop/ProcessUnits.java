package org.mw.bigdata.hadoop;

import java.util.*;
import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * https://www.tutorialspoint.com/map_reduce/implementation_in_hadoop.htm
 * http://a-hadoop.blogspot.com/2015/06/hadoop-mapreduce-mapreduce-is-framework.html

   cd /home/hadoop
   mkdir units
   cp files hadoop-core-1.2.1.jar, WordCount.java, ProcessUnits.java, input.txt, sample.txt to /home/hadoop
   javac -classpath hadoop-core-1.2.1.jar -d units *.java
   jar -cvf units.jar -C units/ .

   start-dfs.sh
   start-yarn.sh

   hadoop fs -mkdir -p input_dir
   hadoop fs -put /home/hadoop/sample.txt input_dir
   hadoop fs -ls input_dir/
   hadoop fs -rmdir output_dir/
   hadoop fs -rm -r output_dir/        # output_dir must not exist before execute the following command 

   hadoop jar units.jar org.mw.bigdata.hadoop.ProcessUnits input_dir output_dir

   hadoop fs -ls output_dir/
   hadoop fs -cat output_dir/part-00000
   
   hadoop fs -get input_dir    ~/Downloads/
   hadoop fs -get input_dir/*  ~/Downloads/
   hadoop fs -get output_dir   ~/Downloads/
   hadoop fs -get output_dir/* ~/Downloads/

 * Input data: sample.txt
 */
public class ProcessUnits
{
   private static final Log LOG = LogFactory.getLog(ProcessUnits.class);

   // electrical consumption - Mapper class
   public static class E_EMapper extends MapReduceBase implements
           Mapper<LongWritable,    /*Input key Type */
           Text,                   /*Input value Type*/
           Text,                   /*Output key Type*/
           IntWritable>            /*Output value Type*/
   {
      //Map function
      public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException
      {
         LOG.info("map: key: '" + key + "', value: '" + value + "'"); // write to hadoop user log, e.g. /j01/srv/hadoop/logs/userlogs/application_1490675168582_0001/container_1490675168582_0001_01_000002/syslog
         String line = value.toString();
         String lasttoken = null;
//         StringTokenizer s = new StringTokenizer(line,"\t"); // not working for sample.txt which does not have \t
         StringTokenizer s = new StringTokenizer(line," ");
         LOG.info("map: line#tokens();: '" + s.countTokens() + "'\n");

         String year = s.nextToken();

         while(s.hasMoreTokens()){
            lasttoken=s.nextToken();
         }

         int avgprice = Integer.parseInt(lasttoken);
         LOG.info("map: output.collect( year: " + year + ", avgprice: " + avgprice +" )");
         output.collect(new Text(year), new IntWritable(avgprice));
      }
   }

   //electrical consumption - Reducer class

   public static class E_EReduce extends MapReduceBase implements
           Reducer< Text, IntWritable, Text, IntWritable >
   {
      //Reduce function
      public void reduce(Text key, Iterator <IntWritable> values, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException
      {
         int maxavg=30;
         int val=Integer.MIN_VALUE;
         while (values.hasNext()) {
            val=values.next().get();
            LOG.info("reduce: val: '" + val +"'");
            if(val > maxavg) {
               LOG.info("reduce: output.collect( key: " + key + ", value: " + val +" )");
               output.collect(key, new IntWritable(val));
            }
         }
      }
   }

   /**
    * hadoop jar units.jar org.mw.bigdata.hadoop.ProcessUnits input_dir output_dir
    *
    * @param args input_dir output_dir
    * @throws Exception
    */
   public static void main(String args[])throws Exception
   {
      JobConf conf = new JobConf(ProcessUnits.class);

      conf.setJobName("max_eletricityunits");

      conf.setMapperClass(E_EMapper.class);
      conf.setCombinerClass(E_EReduce.class);
      conf.setReducerClass(E_EReduce.class);

      conf.setOutputKeyClass(Text.class);
      conf.setOutputValueClass(IntWritable.class);

      conf.setInputFormat(TextInputFormat.class);
      conf.setOutputFormat(TextOutputFormat.class);

      FileInputFormat.setInputPaths(conf, new Path(args[0]));
      FileOutputFormat.setOutputPath(conf, new Path(args[1]));

      JobClient.runJob(conf);
   }
}