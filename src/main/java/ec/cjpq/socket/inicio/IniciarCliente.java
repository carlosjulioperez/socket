package ec.cjpq.socket.inicio;

/**
 * Clase IniciarCliente
 * @author Carlos Julio Pérez Quizhpe carlosjulioperez@gmail.com
 * 2017-11-23
 */ 

import java.io.IOException;
import ec.cjpq.socket.cliente.Cliente;

public class IniciarCliente{
    
    public static void main(String[] args) throws IOException{

        //Nueva instancia de Cliente
        Cliente cliente = new Cliente();

        System.out.println("Iniciando cliente...\n");
        cliente.startClient();
    }
}

