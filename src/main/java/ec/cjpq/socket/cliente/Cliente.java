package ec.cjpq.socket.cliente;

import java.io.DataOutputStream;
import java.io.IOException;

import ec.cjpq.socket.conexion.Conexion;

/**
 * Clase Cliente
 * @author Carlos Julio Pérez Quizhpe carlosjulioperez@gmail.com
 * 2017-11-23
 */ 
public class Cliente extends Conexion{
    
    //Invoca al contructor de Conexion con el parámetro "cliente"
    public Cliente() throws IOException{
        super("cliente");
    }

    public void startClient(){
        try{
            //Flujo de datos hacia el servidor
            salidaServidor = new DataOutputStream(socketCliente.getOutputStream());

            //Se enviarán dos mensajes
            for (int i = 0; i < 2; i++){
                //Se escribe en el servidor usando su flujo de datos
                salidaServidor.writeUTF("Este es el mensaje número: " + (i+1) + "\n");
            }
            socketCliente.close(); //Fin de la conexión
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
