JAVA_HOME=/usr/lib/jvm/java-8-oracle/
export JAVA_HOME
cd /cnvr
mvn jetty:run -Dzk=2181 &	