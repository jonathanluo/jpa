hadoop fs -rm -r input_dir
hadoop fs -mkdir -p input_dir
hadoop fs -put /home/hadoop/input.txt input_dir

hadoop fs -rm -r output_dir/

hadoop jar units.jar org.mw.bigdata.hadoop.WordCount input_dir output_dir

echo "hadoop fs -ls output_dir/"
hadoop fs -ls output_dir/

echo "hadoop fs -cat output_dir/part-r-00000"
hadoop fs -cat output_dir/part-r-00000
   