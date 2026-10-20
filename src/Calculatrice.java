import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculatrice extends JFrame implements ActionListener {

    private JTextField ecran;
    private double nombre1;
    private String operateur = "";

    public Calculatrice() {

        // Fenetre
        setTitle("Ma calculatrice");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Ecran de calcul
        ecran = new JTextField();
        ecran.setEditable(false);
        ecran.setHorizontalAlignment(JTextField.RIGHT);
        ecran.setFont(new Font("Arial", Font.PLAIN, 25));

        add(ecran, BorderLayout.NORTH);

        // Boutons
        JPanel panneau = new JPanel();
        panneau.setLayout(new GridLayout(4, 4, 5, 5));

        String[] boutons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "C", "0", "=", "+"
        };

        for (String texte : boutons) {
            JButton bouton = new JButton(texte);
            bouton.setFont(new Font("Arial", Font.BOLD, 20));
            bouton.addActionListener(this);
            panneau.add(bouton);
        }

        add(panneau, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String texte = e.getActionCommand();

        if (texte.matches("[0-9]")) {
            ecran.setText(ecran.getText() + texte);
        }

        else if (texte.equals("C")) {
            ecran.setText("");
            nombre1 = 0;
            operateur = "";
        }

        else if (texte.matches("[+\\-*/]")) {
            if (!ecran.getText().isEmpty()) {
                nombre1 = Double.parseDouble(ecran.getText());
                operateur = texte;
                ecran.setText("");
            }
        }

        else if (texte.equals("=")) {
            if (!ecran.getText().isEmpty() && !operateur.isEmpty()) {

                double nombre2 = Double.parseDouble(ecran.getText());
                double resultat = 0;

                switch (operateur) {
                    case "+":
                        resultat = nombre1 + nombre2;
                        break;

                    case "-":
                        resultat = nombre1 - nombre2;
                        break;

                    case "*":
                        resultat = nombre1 * nombre2;
                        break;

                    case "/":
                        if (nombre2 == 0) {
                            ecran.setText("Erreur");
                            operateur = "";
                            return;
                        }
                        resultat = nombre1 / nombre2;
                        break;
                }

                ecran.setText(String.valueOf(resultat));
                operateur = "";
            }
        }
    }

    
}
