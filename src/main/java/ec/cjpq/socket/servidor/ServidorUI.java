package ec.cjpq.socket.servidor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

/**
 * Clase Servidor con Interfaz gráfica (UI)
 * @author Carlos Julio Pérez Quizhpe carlosjulioperez@gmail.com
 * 2017-11-24
 * https://stackoverflow.com/questions/28308584/connecting-two-computers-for-client-server-communication-in-java
 */ 
public class ServidorUI{

    private ServerSocket ss;
    private Socket s;
    private DataInputStream dis;
    private DataOutputStream dos;

    private JTextField puerto;
    
    private void iniciar(){
        try{
            System.out.println("Server Started");
            ss=new ServerSocket(Integer.parseInt(puerto.getText()));
            s=ss.accept();
            System.out.println(s);
            System.out.println("CLIENT CONNECTED");
            dis= new DataInputStream(s.getInputStream());
            dos= new DataOutputStream(s.getOutputStream());
            ServerChat();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
    public void ServerChat() throws IOException {
        String str, s1;
        do {
            str=dis.readUTF();
            System.out.println("Client Message:"+str);
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            s1=br.readLine();
            dos.writeUTF(s1);
            dos.flush();
        } while(!s1.equals("bye"));
    }
    
    public void crearUI(){

        JFrame frame = new JFrame("Socket Servidor");
        frame.setSize(400,300);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

        JTextField direccionIP  = new JTextField("localhost"); 
        //JTextField puerto       = new JTextField("1234"); 
        puerto       = new JTextField("1234"); 
        
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

        JPanel panelBotones = new JPanel(new FlowLayout());

        JButton btnIniciar = new JButton("Iniciar");
        JButton btnDetener = new JButton("Detener");


        //Definir las acciones de los botones
        btnIniciar.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                iniciar();
            }
        });

        panelBotones.add(btnIniciar);
        panelBotones.add(btnDetener);

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS)); 

        container.add(panel);
        container.add(panelBotones);

        frame.add(container);

        //Display the window. 
        //frame.pack();

        frame.setLocationRelativeTo(null); 

        frame.setVisible(true); 


    }
}

