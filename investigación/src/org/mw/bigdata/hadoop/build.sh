mkdir -p lib
cp ~/workspace/github/jon/jpa/investigación/lib/commons-lang3-3.4.jar /home/hadoop/lib
cp ~/workspace/github/jon/jpa/investigación/lib/hadoop-core-1.2.1.jar /home/hadoop/lib


cp ~/workspace/github/jon/jpa/investigación/src/org/mw/bigdata/hadoop/ProcessUnits.java /home/hadoop
cp ~/workspace/github/jon/jpa/investigación/src/org/mw/bigdata/hadoop/WordCount.java /home/hadoop

cp ~/workspace/github/jon/jpa/investigación/src/org/mw/bigdata/hadoop/*.txt /home/hadoop

rm -rf units
mkdir units

javac -classpath "lib/*:." -d units *.java
jar -cvf units.jar -C units/ .
