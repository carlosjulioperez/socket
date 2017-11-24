# socket

Configuración del entorno:
	export PATH=/mnt-system/local/apache-maven-3.3.9/bin:$PATH
	export JAVA_HOME=/home/knoppix/local/jdk1.8.0_131

Creación de proyecto maven:

    mvn archetype:generate -DgroupId=ec.cjpq.socket -DartifactId=socket -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false

Iniciar clases:

    mvn exec:java -Dexec.mainClass="ec.cjpq.socket.inicio.IniciarServidor"
    mvn exec:java -Dexec.mainClass="ec.cjpq.socket.inicio.IniciarCliente"

    mvn exec:java -Dexec.mainClass="ec.cjpq.socket.App"

