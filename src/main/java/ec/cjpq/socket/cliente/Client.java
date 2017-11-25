package ec.cjpq.socket.cliente;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Client extends JFrame {

   private static final int PORT = 12345;
   private static final int BACKLOG = 100;

   private final JTextField msgField =
      new JTextField();
   private final JTextArea msgDisplayArea =
      new JTextArea();
   private ObjectOutputStream out;
   private ObjectInputStream in;
   private Socket socket;
   private final String host;
   private String msg;

   public Client(String host) {
      super("Client");
      this.host=host;
      msgField.addActionListener((ActionEvent e) -> {
         send(e.getActionCommand());
         msgField.setText("");
      });
      super.add(msgField, BorderLayout.NORTH);
      super.add(new JScrollPane(msgDisplayArea));
      super.setSize(400, 250);
      super.setVisible(true);
      super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }

   public void startClient() {
      try {
         connectCall();
         setIOStreams();
         processCall();

      } catch (IOException e) {
      }
   }

   private void setIOStreams() throws IOException {
      out = new ObjectOutputStream(socket.getOutputStream());
      out.flush();
      in = new ObjectInputStream(socket.getInputStream());
      showMsg("\nI/O Stream is ready.");
   }

   private void connectCall() throws IOException {
      showMsg("\nConnecting...");
      socket = new Socket(InetAddress.getByName(host), PORT);
      showMsg("Connected to " +
         socket.getInetAddress().getHostName());
   }

   private void processCall() {
      setMsgFieldEditable(true);
      do {
         try {
            msg = (String) in.readObject();
            showMsg("\n" + msg);
         } catch (ClassNotFoundException | IOException ex) {
            showMsg("\nInvalid object received");
         }
      } while (!msg.equals("Server# bye"));

   }

   private void showMsg(final String msg) {
      SwingUtilities.invokeLater(() -> {
         msgDisplayArea.append(msg);
      });
   }

   private void setMsgFieldEditable(final boolean flag) {
      SwingUtilities.invokeLater(() -> {
         msgField.setEditable(flag);
      });
   }

   private void send(String msg) {
      try {
         out.writeObject("\nClient# " + msg);
         out.flush();
         showMsg("\nClient# " + msg);
      } catch (IOException ex) {
         msgDisplayArea.append("Error: " + ex);
      }
   }

   private void endCall() {
      showMsg("\nConnection closed");
      setMsgFieldEditable(false);
      try {
         out.close();
         in.close();
         socket.close();
      } catch (IOException ex) {
      }
   }

}
