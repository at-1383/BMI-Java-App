import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BodyMassIndexApp {

    private JFrame frame;
    private CardLayout pages;
    private JPanel container;

    public BodyMassIndexApp() {
        initUI();
    }

    private void initUI() {
        frame = new JFrame("Body Mass Index Tool");
        frame.setSize(520, 420);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pages = new CardLayout();
        container = new JPanel(pages);

        container.add(introPage(), "intro");
        container.add(calculatorPage(), "calc");

        frame.add(container);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel introPage() {
        JPanel panel = new JPanel(new BorderLayout(15, 15));

        JLabel header = new JLabel("Welcome to BMI Tool", JLabel.CENTER);
        header.setFont(new Font("Tahoma", Font.BOLD, 26));
        panel.add(header, BorderLayout.NORTH);

        JTextArea info = new JTextArea(
                "This program helps you calculate your BMI.\n\n" +
                "Body Mass Index is used to evaluate body weight\n" +
                "relative to height.\n\n" +
                "Developer: Alireza"
        );
        info.setEditable(false);
        info.setLineWrap(true);
        info.setWrapStyleWord(true);
        info.setFont(new Font("Tahoma", Font.PLAIN, 14));
        info.setBackground(panel.getBackground());

        panel.add(info, BorderLayout.CENTER);

        JButton startBtn = new JButton("Start");
        startBtn.addActionListener(e -> pages.show(container, "calc"));
        panel.add(startBtn, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel calculatorPage() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("BMI Calculator", JLabel.CENTER);
        title.setFont(new Font("Tahoma", Font.BOLD, 22));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField heightInput = new JTextField();
        JTextField weightInput = new JTextField();

        JButton computeBtn = new JButton("Compute");
        JLabel output = new JLabel(" ", JLabel.CENTER);

        heightInput.setMaximumSize(new Dimension(200, 30));
        weightInput.setMaximumSize(new Dimension(200, 30));

        output.setFont(new Font("Tahoma", Font.BOLD, 15));
        output.setAlignmentX(Component.CENTER_ALIGNMENT);

        computeBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        computeBtn.addActionListener(e -> {
            try {
                double h = Double.parseDouble(heightInput.getText()) / 100;
                double w = Double.parseDouble(weightInput.getText());
                double bmi = w / (h * h);
                output.setText(String.format("BMI = %.1f → %s", bmi, bmiStatus(bmi)));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame,
                        "Invalid input values!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton backBtn = new JButton("Back");
        backBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        backBtn.addActionListener(e -> pages.show(container, "intro"));

        panel.add(Box.createVerticalStrut(15));
        panel.add(title);
        panel.add(Box.createVerticalStrut(20));
        panel.add(new JLabel("Height (cm):", JLabel.CENTER));
        panel.add(heightInput);
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("Weight (kg):", JLabel.CENTER));
        panel.add(weightInput);
        panel.add(Box.createVerticalStrut(15));
        panel.add(computeBtn);
        panel.add(Box.createVerticalStrut(15));
        panel.add(output);
        panel.add(Box.createVerticalStrut(20));
        panel.add(backBtn);

        return panel;
    }

    private String bmiStatus(double bmi) {
        if (bmi < 18.5) return "Thin";
        if (bmi < 25) return "Healthy";
        if (bmi < 30) return "Overweight";
        return "Obese";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BodyMassIndexApp::new);
    }
}
