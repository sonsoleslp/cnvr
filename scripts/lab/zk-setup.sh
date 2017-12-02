cd ~/cnvr/
curl -O http://ftp.cixug.es/apache/zookeeper/zookeeper-3.4.10/zookeeper-3.4.10.tar.gz
tar --extract --file zookeeper-3.4.10.tar.gz
cd ~/cnvr/zookeeper-3.4.10
mkdir ~/cnvr/1
mkdir ~/cnvr/2
mkdir ~/cnvr/3
echo 1 > ~/cnvr/1/myid
echo 2 > ~/cnvr/2/myid
echo 3 > ~/cnvr/3/myid

cd ~/cnvr/zookeeper-3.4.10

export CLASSPATH=$CLASSPATH:~/cnvr/zookeeper-3.4.10/zookeeper-3.4.10.jar
export CLASSPATH=$CLASSPATH:~/cnvr/zookeeper-3.4.10/lib/*
export PATH=$PATH:/cnvr/zookeeper-3.4.10/bin
./bin/zkServer.sh start ~/cnvr/scripts/zoo1.cfg
./bin/zkServer.sh start ~/cnvr/scripts/zoo2.cfg
./bin/zkServer.sh start ~/cnvr/scripts/zoo3.cfg
sleep 20
