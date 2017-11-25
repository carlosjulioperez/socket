package ec.cjpq.socket.servidor;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Server extends JFrame {
   private static final int PORT = 12345;
   private static final int BACKLOG = 100;

   private final JTextField msgField =
      new JTextField();
   private final JTextArea msgDisplayArea =
      new JTextArea();
   private ObjectOutputStream out;
   private ObjectInputStream in;
   private ServerSocket server;
   private Socket socket;

   public Server() {
      super("Server");
      msgField.setEditable(false);
      msgField.addActionListener((ActionEvent e) -> {
         send(e.getActionCommand());
         msgField.setText("");
         throw new UnsupportedOperationException
            ("Not supported yet.");
      });
      super.add(msgField, BorderLayout.NORTH);
      super.add(new JScrollPane(msgDisplayArea));
      super.setSize(400, 250);
      super.setVisible(true);
      super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }

   public void startServer() {
      try {
         server = new ServerSocket(PORT, BACKLOG);
         while (true) {
            try {
               waitForCall();
               setIOStreams();
               processCall();
            } catch (EOFException ex) {
               showMsg("\nServer connection closed");
            } finally {
               endCall();
            }
         }
      } catch (IOException e) {
      }
   }

   private void setIOStreams() throws IOException {
      out = new ObjectOutputStream(socket.getOutputStream());
      out.flush();
      in = new ObjectInputStream(socket.getInputStream());
      showMsg("\nI/O Stream is ready.");
   }

   private void waitForCall() throws IOException {
      showMsg("\nWaiting for call...");
      socket = server.accept();
      showMsg("Connection accepted from " +
         socket.getInetAddress().getHostName());
   }

   private void processCall() {
      String msg = "Connection established successfully.";
      send(msg);
      setMsgFieldEditable(true);
      do {
         try {
            msg = (String) in.readObject();
            showMsg("\n" + msg);
         } catch (ClassNotFoundException | IOException ex) {
            showMsg("\nInvalid object received");
         }
      } while (!msg.equals("Client# bye"));
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
         out.writeObject("\nServer# " + msg);
         out.flush();
         showMsg("\nServer# " + msg);
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
