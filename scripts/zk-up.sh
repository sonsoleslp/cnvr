cd /cnvr/zookeeper-3.4.10
JAVA_HOME=/usr/lib/jvm/java-8-oracle/
export JAVA_HOME
export CLASSPATH=$CLASSPATH:/cnvr/zookeeper-3.4.10/zookeeper-3.4.10.jar
export CLASSPATH=$CLASSPATH:/cnvr/zookeeper-3.4.10/lib/*
export PATH=$PATH:/cnvr/zookeeper-3.4.10/bin
./bin/zkServer.sh start /cnvr/scripts/zoo1.cfg
./bin/zkServer.sh start /cnvr/scripts/zoo2.cfg
./bin/zkServer.sh start /cnvr/scripts/zoo3.cfg
sleep 20
