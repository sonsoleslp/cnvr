cd ~/cnvr/
curl -O http://ftp.cixug.es/apache/zookeeper/zookeeper-3.4.10/zookeeper-3.4.10.tar.gz
tar --extract --file zookeeper-3.4.10.tar.gz
cd ~/cnvr/zookeeper-3.4.10

mkdir /tmp/1
echo 1 > /tmp/1/myid

cd ~/cnvr/zookeeper-3.4.10
cp ~/cnvr/zk/zoo_sample.cfg conf/zoo_sample.cfg


export CLASSPATH=$CLASSPATH:~/cnvr/zookeeper-3.4.10/zookeeper-3.4.10.jar
export CLASSPATH=$CLASSPATH:~/cnvr/zookeeper-3.4.10/lib/*
export PATH=$PATH:~/cnvr/zookeeper-3.4.10/bin
./bin/zkServer.sh start conf/zoo_sample2.cfg

