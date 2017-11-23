package ec.cjpq.socket.conexion;

import java.io.DataOutputStream;
import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Clase para establecer la conexión entre el cliente y el servidor
 * @author Carlos Julio Pérez Quizhpe carlosjulioperez@gmail.com
 * 2017-11-23
 */ 
public class Conexion{

    //Definición de constantes
    private final int PUERTO = 1234;            //Puerto para la conexión
    private final String HOST = "localhost";    //Host para la conexión

    protected String mensajeServidor;           //Mensajes entrantes (recibidos) en el servidor
    protected ServerSocket socketServidor;      //Socket del servidor
    protected Socket socketCliente;             //Socket del cliente
    protected DataOutputStream salidaServidor;  //Flujo de datos de salida del servidor
    protected DataOutputStream salidaCliente;   //Flujo de datos de salida del cliente

    public Conexion(String tipo) throws IOException{
        if(tipo.equalsIgnoreCase("servidor")){
            
            //Se crea el socket para el servidor
            socketServidor = new ServerSocket(PUERTO);

            //Socket para el cliente
            socketCliente = new Socket(); 
        }
        else{
            //Socket para el cliente en host y puerto especificado
            socketCliente = new Socket(HOST, PUERTO); 
        }
    }

}
