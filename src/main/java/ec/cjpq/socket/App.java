package ec.cjpq.socket;

import javax.swing.*;
import java.awt.GridLayout;

import ec.cjpq.socket.servidor.ServidorUI;

/**
 * Clase principal de la aplicación
 * @author Carlos Julio Pérez Quizhpe carlosjulioperez@gmail.com
 * 2017-11-24
 */ 
public class App {

    public static void main( String[] args ) {
        seleccion();
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
            new ServidorUI().crearUI();
        else if (opcion.equals("Cliente"))
            System.out.println("...");
        else 
            System.exit(0);
    }

    private static void servidorUI(){
        String[] items = {"One", "Two", "Three", "Four", "Five"};
        JComboBox combo = new JComboBox(items);
        JTextField field1 = new JTextField("1234.56");
        JTextField field2 = new JTextField("9876.54");
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(combo);
        panel.add(new JLabel("Field 1:"));
        panel.add(field1);
        panel.add(new JLabel("Field 2:"));
        panel.add(field2);
        int result = JOptionPane.showConfirmDialog(null, panel, "Test",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            System.out.println(combo.getSelectedItem()
                    + " " + field1.getText()
                    + " " + field2.getText());
        } else {
            System.out.println("Cancelled");
        }
    }
}
