package ec.cjpq.socket;

import javax.swing.*;
import java.awt.GridLayout;

import ec.cjpq.socket.cliente.ClienteUI;
import ec.cjpq.socket.servidor.ServidorUI;

import ec.cjpq.socket.cliente.ClienteJFrame;
import ec.cjpq.socket.servidor.ServidorJFrame;

import ec.cjpq.socket.cliente.Client;
import ec.cjpq.socket.servidor.Server;

/**
 * Clase principal de la aplicación
 * @author Carlos Julio Pérez Quizhpe carlosjulioperez@gmail.com
 * 2017-11-24, 25
 */ 
public class App {

    public static void main( String[] args ) {
        //seleccion();
        dialogo();
    }

    private static void dialogo(){
        try{
            String[] items = {"Servidor", "Cliente"};
            JComboBox combo = new JComboBox(items);
            JTextField puerto = new JTextField("1234"); 

            JPanel panel = new JPanel(new GridLayout(0, 1));
            panel.add(new JLabel("Por Carlos Julio Pérez Quizhpe"));
            panel.add(new JLabel("carlosjulioperez@gmail.com"));
            panel.add(new JLabel(""));
            panel.add(new JLabel("Seleccione la aplicación Socket a ejecutar"));
            panel.add(combo);
            //panel.add(new JLabel("Puerto"));
            //panel.add(puerto);

            int result = JOptionPane.showConfirmDialog(null, panel, "Aplicación Socket",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                String opcion = (String)combo.getSelectedItem();
                if (opcion.equals("Servidor")){
                    new ServidorJFrame();
                    //Server app=new Server();
                    //app.startServer();
                }
                else if (opcion.equals("Cliente")){
                    // prompt the user to enter their name
                    String direccionIP = JOptionPane.showInputDialog(null, "Ingrese la dirección IP del servidor");

                    new ClienteJFrame(direccionIP );

                    //String localhost="127.0.0.1";
                    //Client app=new Client(localhost);
                    //app.startClient();
                }
            } else {
                System.exit(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void seleccion(){

        String[] menu = {"Servidor", "Cliente"};

        JFrame frame = new JFrame();
        String opcion = (String) JOptionPane.showInputDialog(frame, 
                "Seleccione la aplicación Socket a ejecutar",
                "Aplicación Socket - Por Carlos Julio Pérez Quizhpe",
                JOptionPane.QUESTION_MESSAGE, 
                null, 
                menu, 
                menu[0]);

        // opcion will be null if the user clicks Cancel
        System.out.printf("Opción es %s.\n", opcion);
        if (opcion.equals("Servidor"))
            new ServidorUI().crearUI("1234");
        else if (opcion.equals("Cliente"))
            new ClienteUI().crearUI("1234");
        else 
            System.exit(0);
    }
}
