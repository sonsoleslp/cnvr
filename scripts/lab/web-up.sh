
# JAVA_HOME=/usr/lib/jvm/java-8-oracle/
# export JAVA_HOME
cd ~/cnvr
mvn jetty:run -DzkIP=$1 -Durl=$2:3000 
