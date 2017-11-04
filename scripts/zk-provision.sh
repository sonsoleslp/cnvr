apt-get update
apt-get install -y openjdk-8-jdk
sudo add-apt-repository -y ppa:webupd8team/java
sudo apt-get update -y 
echo "oracle-java8-installer shared/accepted-oracle-license-v1-1 select true" | sudo debconf-set-selections
echo "oracle-java8-installer shared/accepted-oracle-license-v1-1 seen true" | sudo debconf-set-selections
sudo apt-get install -y oracle-java8-installer
cd /cnvr
curl -O http://ftp.cixug.es/apache/zookeeper/zookeeper-3.4.10/zookeeper-3.4.10.tar.gz
tar --extract --file zookeeper-3.4.10.tar.gz
cd /cnvr/zookeeper-3.4.10
mkdir /tmp
mkdir /tmp/1
mkdir /tmp/2
mkdir /tmp/3
echo 1 > /tmp/1/myid
echo 2 > /tmp/2/myid
echo 3 > /tmp/3/myid
