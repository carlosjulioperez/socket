package ec.cjpq.socket.servidor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
 
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Clase Servidor con Interfaz gráfica (UI)
 * @author Carlos Julio Pérez Quizhpe carlosjulioperez@gmail.com
 * 2017-11-25
 * http://www.codingdevil.com/2014/02/simple-chat-application-using-java-socket-programming-2.html
 */
public class ServidorJFrame extends JFrame implements ActionListener {
    
    static ServerSocket server;
    static Socket conn;
    JPanel panel;
    JTextField NewMsg;
    JTextArea ChatHistory;
    JButton Send;
    DataInputStream dis;
    DataOutputStream dos;
 
    public ServidorJFrame() throws UnknownHostException, IOException {
        
        String direccionIP = "";
        try{
            InetAddress ip = InetAddress.getLocalHost(); 
            direccionIP = ip.getHostAddress();
            this.setTitle("Servidor " + direccionIP);
        }
        catch(Exception e){
            e.printStackTrace();
        }
 
        panel = new JPanel();
        NewMsg = new JTextField();
        ChatHistory = new JTextArea();
        
        Send = new JButton("Enviar");
        this.setSize(500, 500);
        this.setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel.setLayout(null);
        this.add(panel);
        ChatHistory.setBounds(20, 20, 450, 360);
        panel.add(ChatHistory);
        NewMsg.setBounds(20, 400, 340, 30);
        panel.add(NewMsg);
        Send.setBounds(375, 400, 95, 30);
        panel.add(Send);
        Send.addActionListener(this);
        
        server = new ServerSocket(2000, 1, InetAddress.getLocalHost());
        //server = new ServerSocket(2000);

        ChatHistory.setText("Esperando por el cliente...");
        conn = server.accept();
        ChatHistory.setText(ChatHistory.getText() + '\n' + "*** Cliente conectado ***");
        while (true) {
            try {
                DataInputStream dis = new DataInputStream(conn.getInputStream());
                String string = dis.readUTF();
                ChatHistory.setText(ChatHistory.getText() + '\n' + "Cliente: " + string);
            } catch (Exception e1) {
                ChatHistory.setText(ChatHistory.getText() + '\n' + "Desconexión");
                try {
                    Thread.sleep(3000);
                    System.exit(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
 
    @Override
    public void actionPerformed(ActionEvent e) {
        if ((e.getSource() == Send) && (NewMsg.getText() != "")) {
            ChatHistory.setText(ChatHistory.getText() + '\n' + "Servidor: " + NewMsg.getText());
            try {
                DataOutputStream dos = new DataOutputStream( conn.getOutputStream());
                dos.writeUTF(NewMsg.getText());
            } catch (Exception e1) {
                try {
                    Thread.sleep(3000);
                    System.exit(0);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
            }
            NewMsg.setText("");
        }
    }
 
    public static void main(String[] args) throws UnknownHostException, IOException {
        new ServidorJFrame();
    }
}
