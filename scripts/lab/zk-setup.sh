cd ~/cnvr/
curl -O http://ftp.cixug.es/apache/zookeeper/zookeeper-3.4.10/zookeeper-3.4.10.tar.gz
tar --extract --file zookeeper-3.4.10.tar.gz
cd ~/cnvr/zookeeper-3.4.10

mkdir /tmp/1
mkdir /tmp/2
mkdir /tmp/3
echo 1 > /tmp/1/myid
echo 2 > /tmp/2/myid
echo 3 > /tmp/3/myid

cd ~/cnvr/zookeeper-3.4.10
cp ~/cnvr/zk/zoo1.cfg conf/zoo1.cfg
cp ~/cnvr/zk/zoo2.cfg conf/zoo2.cfg
cp ~/cnvr/zk/zoo3.cfg conf/zoo3.cfg

export CLASSPATH=$CLASSPATH:~/cnvr/zookeeper-3.4.10/zookeeper-3.4.10.jar
export CLASSPATH=$CLASSPATH:~/cnvr/zookeeper-3.4.10/lib/*
export PATH=$PATH:~/cnvr/zookeeper-3.4.10/bin
./bin/zkServer.sh start conf/zoo1.cfg
./bin/zkServer.sh start conf/zoo2.cfg
./bin/zkServer.sh start conf/zoo3.cfg
sleep 5
~/cnvr/zookeeper-3.4.10/bin/zkServer.sh status ~/cnvr/zookeeper-3.4.10/conf/zoo1.cfg 
~/cnvr/zookeeper-3.4.10/bin/zkServer.sh status ~/cnvr/zookeeper-3.4.10/conf/zoo2.cfg 
~/cnvr/zookeeper-3.4.10/bin/zkServer.sh status ~/cnvr/zookeeper-3.4.10/conf/zoo3.cfg 
 
