package ec.cjpq.socket.servidor;

import javax.swing.*;
import java.awt.*;

/**
 * Clase principal de la aplicación
 * @author Carlos Julio Pérez Quizhpe carlosjulioperez@gmail.com
 * 2017-11-24
 */ 
public class ServidorUI{

    public void crearUI(){

        JFrame frame = new JFrame("Socket Servidor");
        frame.setSize(400,300);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

        JTextField direccionIP  = new JTextField("localhost"); 
        JTextField puerto       = new JTextField("1234"); 
        
        JTextField estado       = new JTextField("Detenido"); 
        estado.setEditable(false);
        
        JTextField mensaje = new JTextField(""); 
        mensaje.setEditable(false);

        JPanel panel = new JPanel(new GridLayout(0, 1));
        
        panel.add(new JLabel("Dirección IP del servidor"));
        panel.add(direccionIP);
        
        panel.add(new JLabel("Puerto"));
        panel.add(puerto);
        
        panel.add(new JLabel("Estado"));
        panel.add(estado);
        
        panel.add(new JLabel("Mensaje"));
        panel.add(mensaje);

        frame.add(panel);

        //Display the window. 
        //frame.pack();
        
        frame.setLocationRelativeTo(null); 

        frame.setVisible(true); 


    }
}

