cp ~/workspace/github/moonwave/script/home/hadoop/* /home/hadoop
cd /home/hadoop

./build.sh
./clear-hadoop-logs.sh
./start-hadoop.sh
./runWordCount.sh
cd /j01/srv/hadoop/logs
/j01/bin/egrep.sh -R "map - " *
/j01/bin/egrep.sh -R "reduce - " *


cd /home/hadoop
./clear-hadoop-userlogs.sh
./runProcessUnits.sh
cd /j01/srv/hadoop/logs
/j01/bin/egrep.sh -R "map - " *
/j01/bin/egrep.sh -R "reduce - " *
