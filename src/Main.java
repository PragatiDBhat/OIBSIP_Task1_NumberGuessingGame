import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        new GuessingGameGUI();
    }
}

class GuessingGameGUI extends JFrame {
    private int num;
    private int tries = 10;
    private JTextField guessField;
    private JLabel messageLabel;
    private JLabel chancesLabel;
    private JLabel resultLabel;

    public GuessingGameGUI() {
        setTitle("Number Guessing Game");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        getContentPane().setBackground(new Color(50, 50, 50));

        num = (int) (Math.random() * 10 + 1);

        Font font = new Font("Arial", Font.BOLD, 16);
        Font headingFont = new Font("Arial", Font.BOLD, 20);
        Color textColor = new Color(255, 255, 255);

        JLabel headingLabel = new JLabel("Number Guessing Game");
        headingLabel.setFont(headingFont);
        headingLabel.setForeground(new Color(148, 0, 211));
        headingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(headingLabel);

        add(Box.createRigidArea(new Dimension(0, 10)));

        chancesLabel = new JLabel("Number of chances left: " + tries);
        chancesLabel.setFont(font);
        chancesLabel.setForeground(textColor);
        chancesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(chancesLabel);

        add(Box.createRigidArea(new Dimension(0, 10)));

        messageLabel = new JLabel("Enter a number between 1 and 10:");
        messageLabel.setFont(font);
        messageLabel.setForeground(textColor);
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(messageLabel);

        add(Box.createRigidArea(new Dimension(0, 10)));

        guessField = new JTextField(5);
        guessField.setFont(font);
        guessField.setMaximumSize(guessField.getPreferredSize());
        guessField.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(guessField);

        add(Box.createRigidArea(new Dimension(0, 10)));

        JButton guessButton = new JButton("Check");
        guessButton.setFont(font);
        guessButton.setBackground(new Color(70, 130, 180));
        guessButton.setForeground(textColor);
        guessButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(guessButton);

        add(Box.createRigidArea(new Dimension(0, 10)));

        resultLabel = new JLabel("");
        resultLabel.setFont(font);
        resultLabel.setForeground(new Color(255, 69, 0));
        resultLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(resultLabel);

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int guessedNumber = Integer.parseInt(guessField.getText());
                    if (guessedNumber < 1 || guessedNumber > 10) {
                        resultLabel.setText("Please enter a number between 1 and 10.");
                    } else {
                        tries--;
                        chancesLabel.setText("Number of chances left: " + tries);
                        if (guessedNumber == num) {
                            resultLabel.setText("Congratulations!!! You guessed the number, which is " + num);
                            guessButton.setEnabled(false);
                        } else if (guessedNumber < num) {
                            resultLabel.setText("Too Low");
                        } else {
                            resultLabel.setText("Too High");
                        }
                        if (tries == 0 && guessedNumber != num) {
                            resultLabel.setText("You ran out of guesses, the number was " + num + ". Better Luck Next Time");
                            guessButton.setEnabled(false);
                        }
                    }
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Please enter a valid number.");
                }
                guessField.setText("");
            }
        });

        setVisible(true);
    }
}
