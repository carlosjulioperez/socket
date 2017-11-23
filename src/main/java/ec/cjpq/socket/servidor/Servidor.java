package ec.cjpq.socket.servidor;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import ec.cjpq.socket.conexion.Conexion;

/**
 * Clase Servidor que hereda Conexion para usar los sockets
 * @author Carlos Julio Pérez Quizhpe carlosjulioperez@gmail.com
 * 2017-11-23
 */ 
public class Servidor extends Conexion {

    //Invoca al contructor de Conexion con el parámetro "servidor"
    public Servidor() throws IOException{
        super("servidor");
    } 

    public void startServer(){
        try {
            System.out.println("Esperando conexión...");
            
            //Accept comienza el socket y espera una conexión desde un cliente 
            socketCliente = socketServidor.accept();

            System.out.println("Cliente en línea.");

            //Se obtiene el flujo de salida del cliente para enviarle mensajes
            salidaCliente = new DataOutputStream(socketCliente.getOutputStream());

            //Se le envía un mensaje al cliente usando su flujo de salida
            salidaCliente.writeUTF("Petición recibida y aceptada.");

            //Se obtiene el flujo entrante desde el cliente
            BufferedReader entrada = new BufferedReader( new InputStreamReader(socketCliente.getInputStream()) );
            
            //Mientras haya mensajes desde el cliente
            while((mensajeServidor = entrada.readLine()) != null){
                //Se muestra por pantalla el mensaje recibido
                System.out.println(mensajeServidor);
            }

            System.out.println("Fin de la conexión.");

            socketServidor.close();//Se finaliza la conexión con el cliente
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
