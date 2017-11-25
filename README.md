# socket
# Por Carlos Julio Pérez Quizhpe
# Proyecto 1 de materia Aplicaciones Distribuidas, ESPOL. 2017
Configuración del entorno:
	export PATH=/mnt-system/local/apache-maven-3.3.9/bin:$PATH
	export JAVA_HOME=/home/knoppix/local/jdk1.8.0_131

Creación de proyecto maven:

    mvn archetype:generate -DgroupId=ec.cjpq.socket -DartifactId=socket -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false

Iniciar clases con maven:

    mvn exec:java -Dexec.mainClass="ec.cjpq.socket.App"

Iniciar la aplicación jar:
    Desde la línea de comandos:

        java -jar socket-1.0-SNAPSHOT.jar

    O simplemente hacer doble clic en el archivo jar (autoejecutable y multiplataforma) desde el sistema operativo 
