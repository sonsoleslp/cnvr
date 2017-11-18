apt-get update
apt-get install -y apache2
apt-get install -y git-core
apt-get install -y maven
apt-get install -y openjdk-8-jdk
sudo add-apt-repository -y ppa:webupd8team/java
sudo apt-get update -y 
echo "oracle-java8-installer shared/accepted-oracle-license-v1-1 select true" | sudo debconf-set-selections
echo "oracle-java8-installer shared/accepted-oracle-license-v1-1 seen true" | sudo debconf-set-selections
sudo apt-get install -y oracle-java8-installer
