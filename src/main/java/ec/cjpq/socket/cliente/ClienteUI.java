package ec.cjpq.socket.cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

import java.nio.charset.StandardCharsets;

/**
 * Clase Cliente con Interfaz gráfica (UI)
 * @author Carlos Julio Pérez Quizhpe carlosjulioperez@gmail.com
 * 2017-11-24
 * https://stackoverflow.com/questions/28308584/connecting-two-computers-for-client-server-communication-in-java
 */ 
public class ClienteUI{

    private Socket s;
    private DataInputStream dis;
    private DataOutputStream dos;

    private JTextField direccionIP;
    private JTextField puerto;
    private JTextField mensaje;
    
    private void iniciar(){
        try{
            s=new Socket(direccionIP.getText(), Integer.parseInt(puerto.getText()));
            System.out.println(s);
            dis= new DataInputStream(s.getInputStream());
            dos= new DataOutputStream(s.getOutputStream());
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
    private void enviarMensaje(){
        try{

            InputStream stream = new ByteArrayInputStream(mensaje.getText().getBytes(StandardCharsets.UTF_8.name()));

            BufferedReader br= new BufferedReader(new InputStreamReader(stream));
            String s1;
            s1=br.readLine();
            dos.writeUTF(s1);
            dos.flush();
            System.out.println("Server Message:"+dis.readUTF());
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public void crearUI(){

        JFrame frame = new JFrame("Socket Cliente");
        frame.setSize(400,300);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

        direccionIP     = new JTextField("localhost"); 
        puerto          = new JTextField("1234"); 
        mensaje         = new JTextField(""); 

        JPanel panel = new JPanel(new GridLayout(0, 1));
        
        panel.add(new JLabel("Dirección IP del servidor"));
        panel.add(direccionIP);
        
        panel.add(new JLabel("Puerto"));
        panel.add(puerto);
        
        panel.add(new JLabel("Mensaje"));
        panel.add(mensaje);

        JButton btnMensaje = new JButton("Enviar mensaje");

        //Definir las acciones de los botones
        btnMensaje.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                iniciar();
                enviarMensaje();
            }
        });

        panel.add(btnMensaje);
        frame.add(panel);

        //Display the window. 
        //frame.pack();

        frame.setLocationRelativeTo(null); 

        frame.setVisible(true); 


    }
}


