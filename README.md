# CNVR

## Despliegue completo
* Instalar Vagrant
* Instalar VirtualBox
* Clonar el proyecto `git clone https://github.com/sonsoleslp/cnvr.git`
* Situarse en el directorio del proyecto `cd cnvr`
* Ejecutar el comando `vagrant up`
* Abrir el navegador en `http://localhost:8081/cnvr`
* Para deshacer el escenario: `vagrant halt`

## Alternativa: Pruebas con 1 server en local
* Instalar Zookeeper
* Lanzar Zookeeper
* Instalar Eclipse
* Instalar plugin de Maven ( Help -> Install New Software. Buscar "Maven" en All Sites Available) y reiniciar Eclipse
* Clonar el proyecto desde la perspectiva de git de Eclipse 
* Cambiar a la perspectiva de Java EE.
* Cambiar la IP de Zookeeper en StartupServlet
* Botón derecho del ratón sobre el proyecto y Maven -> Update
* Botón derecho del ratón sobre el proyecto y Run -> Maven build.... Configurar como Goal: jetty:run
* Botón derecho del ratón sobre el proyecto y Run -> Maven build.
* En la consola, si no hay errores significa que se ha lanzado el servidor web.
* Abrir navegador en `http://localhost:3000/cnvr`


