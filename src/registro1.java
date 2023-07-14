import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class registro1 {
    private JButton guardarButton;
    private JButton cargarButton;
    private JTextField codField;
    private JTextField ceduField;
    private JTextField nombreField;
    private JButton backButton;
    private JTextField apellidoField;
    private JButton nextButton;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JCheckBox rojoCheckBox;
    private JCheckBox verdeCheckBox;
    private JCheckBox ningunoCheckBox;
    private JRadioButton siRadioButton;
    private JRadioButton noRadioButton;
    public JPanel rootPanel;

    public registro1() {
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int codigo = Integer.parseInt(codField.getText());
                int cedula = Integer.parseInt(ceduField.getText());
                String nombre = nombreField.getText();
                String apellido = apellidoField.getText();
                String signo = (String) comboBox1.getSelectedItem();
                String valor2 = (String) comboBox2.getSelectedItem();
                String valor3 = (String) comboBox3.getSelectedItem();
                String valor4 = (String) comboBox4.getSelectedItem();
                boolean rojo = rojoCheckBox.isSelected();
                boolean verde = verdeCheckBox.isSelected();
                boolean ninguno = ningunoCheckBox.isSelected();
                boolean si = siRadioButton.isSelected();
                boolean no = noRadioButton.isSelected();

                guardarDatos(codigo, cedula, nombre, apellido, signo, valor2, valor3, valor4, rojo, verde, ninguno, si, no);
            }
        });

        cargarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarDatos();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame2 = (JFrame) SwingUtilities.getWindowAncestor(rootPanel);
                frame2.setVisible(false);

                JFrame frame1 = new JFrame("Registro 2");
                registro2 segventana = new registro2();
                frame1.setContentPane(segventana.rootPanel);
                frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame1.pack();
                frame1.setVisible(true);
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame1 = (JFrame) SwingUtilities.getWindowAncestor(rootPanel);
                frame1.setVisible(false);

                JFrame frame2 = new JFrame("Registro 2");
                registro2 segventana = new registro2();
                frame2.setContentPane(segventana.rootPanel);
                frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame2.pack();
                frame2.setVisible(true);
            }
        });
    }

    private void createUIComponents() {

    }

    private void guardarDatos(int codigo, int cedula, String nombre, String apellido, String signo,
                              String valor2, String valor3, String valor4, boolean rojo, boolean verde, boolean ninguno, boolean si, boolean no) {
        String filePath = "data.dat";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            String datos = codigo + "," + cedula + "," + nombre + "," + apellido + "," + signo + ","
                    + valor2 + "," + valor3 + "," + valor4 + ","
                    + rojo + "," + verde + "," + ninguno + "," + si + "," + no + "\n";
            writer.write(datos);
            System.out.println("Datos guardados correctamente en el archivo.");
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar los datos en el archivo.", e);
        }
    }

    private void cargarDatos() {
        String filePath = "data.dat";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 13) {
                    int codigo = Integer.parseInt(datos[0]);
                    int cedula = Integer.parseInt(datos[1]);
                    String nombre = datos[2];
                    String apellido = datos[3];
                    String signo = datos[4];
                    String valor2 = datos[5];
                    String valor3 = datos[6];
                    String valor4 = datos[7];
                    boolean rojo = Boolean.parseBoolean(datos[8]);
                    boolean verde = Boolean.parseBoolean(datos[9]);
                    boolean ninguno = Boolean.parseBoolean(datos[10]);
                    boolean si = Boolean.parseBoolean(datos[11]);
                    boolean no = Boolean.parseBoolean(datos[12]);

                    codField.setText(String.valueOf(codigo));
                    ceduField.setText(String.valueOf(cedula));
                    nombreField.setText(nombre);
                    apellidoField.setText(apellido);
                    comboBox1.setSelectedItem(signo);
                    comboBox2.setSelectedItem(valor2);
                    comboBox3.setSelectedItem(valor3);
                    comboBox4.setSelectedItem(valor4);
                    rojoCheckBox.setSelected(rojo);
                    verdeCheckBox.setSelected(verde);
                    ningunoCheckBox.setSelected(ninguno);
                    siRadioButton.setSelected(si);
                    noRadioButton.setSelected(no);

                    System.out.println("Datos cargados correctamente desde el archivo.");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al cargar los datos desde el archivo.", e);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("registro1");
                frame.setContentPane(new registro1().rootPanel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
}