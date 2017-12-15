# Examen CNVR

## Clonar repo
```
cd ~
git clone http://github.com/sonsoleslp/cnvr.git
cd cnvr
```

## Zookeeper
### Modo en un único ordenador los 3 servidores
1. Corro el servidor de zookeeper
	```
	sh scripts/lab/zk-setup.sh
	```
2. Compruebo que funciona
	```
	sh scripts/lab/zk-status.sh
	```

### Modo en 3 ordenadores distintos
1. Modifico `zk/zoo_sample2.cfg`:
	* Cambio `dataDir=/tmp/1` para que tenga el nº de servidor que me corresponde
	* Igual en `server.1=138.4.31.104:2888:3888` (pongo mi IP en el servidor que me corresponde y las de los demás servidores en los suyos)
2. Modifico `distributed-zk-setup2.sh` para mi nº de servidor (líneas 6 y 7)
3. Lanzo zookeper:
	```
	sh scripts/lab/distributed-zk-setup2.sh
	```
4. Compruebo que funciona:
	```
	sh scripts/lab/zk-distributed-status2.sh
	```

## Servidores web
1. Instalar maven
	```
	sh scripts/lab/install_maven.sh
	```
2. Ejecuto el comando siguiente :
	```
	sh scripts/lab/web-up.sh "138.4.31.104:2181" "http://138.4.31.102"
	```
	donde la primera dirección es la del servidor zookeeper al que me quiero conectar y la segunda es la propia del servidor web

