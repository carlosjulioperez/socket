package ec.cjpq.socket.inicio;

/**
 * Clase IniciarServidor
 * @author Carlos Julio Pérez Quizhpe carlosjulioperez@gmail.com
 * 2017-11-23
 */ 

import java.io.IOException;
import ec.cjpq.socket.servidor.Servidor;

public class IniciarServidor{
    
    public static void main(String[] args) throws IOException{

        //Nueva instancia de Servidor
        Servidor servidor = new Servidor();

        System.out.println("Iniciando servidor...\n");
        servidor.startServer();
    }
}
