# CNVR

(Se asume que Zookeeper está lanzado)

## Despliegue completo
* Instalar Vagrant
* Instalar VirtualBox
* Situarse en el directorio del proyecto y hacer `vagrant up`
* Abrir el navegador en `http://localhost:8081/cnvr`

## Pruebas con 1 server
* Instalar Eclipse
* Instalar plugin de Maven ( Help -> Install New Software. Buscar "Maven" en All Sites Available) y reiniciar Eclipse
* Clonar el proyecto desde la perspectiva de git de Eclipse 
* Cambiar a la perspectiva de Java EE.
* Botón derecho del ratón sobre el proyecto y Maven -> Update
* Botón derecho del ratón sobre el proyecto y Run -> Maven build.... Configurar como Goal: jetty:run
* Botón derecho del ratón sobre el proyecto y Run -> Maven build.
* En la consola, si no hay errores significa que se ha lanzado el servidor web.
* Abrir navegador en `http://localhost:3000/cnvr`


