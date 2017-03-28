hadoop fs -rm -r input_dir
hadoop fs -mkdir -p input_dir
hadoop fs -put /home/hadoop/sample.txt input_dir

hadoop fs -rm -r output_dir/

hadoop jar units.jar org.mw.bigdata.hadoop.ProcessUnits input_dir output_dir

echo "hadoop fs -ls output_dir/"
hadoop fs -ls output_dir/

echo "hadoop fs -cat output_dir/part-00000"
hadoop fs -cat output_dir/part-00000
   