package ec.cjpq.socket.servidor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

/**
 * Clase Servidor con Interfaz gráfica (UI)
 * @author Carlos Julio Pérez Quizhpe carlosjulioperez@gmail.com
 * 2017-11-24, 25
 * https://stackoverflow.com/questions/28308584/connecting-two-computers-for-client-server-communication-in-java
 */ 
public class ServidorUI{

    private ServerSocket ss;
    private Socket s;
    private DataInputStream dis;
    private DataOutputStream dos;

    String numeroPuerto;

    private boolean continuar;
    
    private void iniciar(){
        try{
            continuar = true;
            System.out.println("Server Started");
            ss=new ServerSocket(Integer.parseInt(numeroPuerto));
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
            
        do{
            str=dis.readUTF();
            System.out.println("Client Message:"+str);
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            s1=br.readLine();
            dos.writeUTF(s1);
            dos.flush();
        }while(continuar);
        //}while(!s1.equals("bye"));

    }
    
    public void crearUI(String numeroPuerto){
        this.numeroPuerto = numeroPuerto;

        String direccionIP = "";
        try{
            InetAddress ip = InetAddress.getLocalHost(); 
            direccionIP = ip.getHostAddress();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        final JFrame frame = new JFrame("Socket Servidor " + direccionIP);
        frame.setSize(400,300);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

        JTextField puerto = new JTextField(numeroPuerto); 
        puerto.setEditable(false);
        
        JTextField mensaje = new JTextField(""); 
        mensaje.setEditable(false);

        JPanel panel = new JPanel(new GridLayout(0, 1));
        
        panel.add(new JLabel("Puerto"));
        panel.add(puerto);
        
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

        btnDetener.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false); //you can't see me!
                frame.dispose(); //Destroy the JFrame object
                System.exit(0);
            }
        });

        //panelBotones.add(btnIniciar);
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

        iniciar();
        
    }
}

