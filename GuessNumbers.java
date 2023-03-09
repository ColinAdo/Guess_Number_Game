import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class GuessNumbers extends JFrame implements ActionListener {
    private int min = 1;
    private int max = 100;
    private int round = 1;
    private int score = 0;
    private int attempt = 5;
    private JLabel roundLabel, remainingAttemptsLabel, scoreLabel, hJLabel, demoMessagLabel;
    private JTextField guessField;
    private JButton guessButton, highScoreButton;

    private Random random = new Random();
    private int number = random.nextInt(max - min + 1) + min;

    public GuessNumbers() {
        // Set up the frame
        super("Guess the Number Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        getContentPane().setBackground(new Color(208, 234, 250));

        // creating header in the frame
        hJLabel = new JLabel("GROUP F GUESS NUMBER GAME");
        hJLabel.setFont(new Font("Time new Romans", Font.BOLD, 20));
        hJLabel.setForeground(new Color(40, 17, 80));
        add(hJLabel);

        // Create and add labels
        roundLabel = new JLabel("Round: [ 1 ] ");
        roundLabel.setFont(new Font("Time new Romans", Font.BOLD, 19));
        roundLabel.setForeground(new Color(40, 17, 80));
        add(roundLabel);

        remainingAttemptsLabel = new JLabel("Attempts remaining: " + "[ "+attempt+" ]");
        remainingAttemptsLabel.setFont(new Font("Time new Romans", Font.BOLD, 19));
        remainingAttemptsLabel.setForeground(new Color(40, 17, 80));
        add(remainingAttemptsLabel);

        demoMessagLabel =  new JLabel("Guess a number between " + min + " and " + max);
        demoMessagLabel.setForeground(new Color(40, 17, 80));
        add(demoMessagLabel);

        scoreLabel = new JLabel("Score: " + "[ "+score+" ]");
        scoreLabel.setFont(new Font("Time new Romans", Font.BOLD, 20));
        scoreLabel.setForeground(new Color(40, 17, 80));
        add(scoreLabel);

        // Create and add input field
        guessField = new JTextField();
        guessField.setPreferredSize(new Dimension(250, 40));
        add(guessField);
        
        // Configuring image in frame
        ImageIcon imageIcon = new ImageIcon("Guess-a-number.png");
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        JLabel imageJLabel = new JLabel(scaledImageIcon);
        add(imageJLabel);

        // Create and add buttons
        guessButton = new JButton("Guess");
        guessButton.setFont(new Font("Time new Romans", Font.BOLD, 16));
        guessButton.setPreferredSize(new Dimension(150, 50));
        guessButton.addActionListener(this);
        add(guessButton);

        highScoreButton = new JButton("High Score");
        highScoreButton.setFont(new Font("Time new Romans", Font.BOLD, 16));
        highScoreButton.setPreferredSize(new Dimension(150, 50));
        highScoreButton.addActionListener(this);
        add(highScoreButton);


        // setting fixed size and center frame
        setSize(360, 550);
        setResizable(false);
        setLocationRelativeTo(null);

        // Show the frame
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == guessButton) {
            int guess = 0;
            try {
                guess = Integer.parseInt(guessField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please input numbers only");
                return;
            }
    
            if (guess < min || guess > max) {
                JOptionPane.showMessageDialog(this, "Your guess is out of range");
            } else {
                attempt--;
                if (guess < number) {
                    JOptionPane.showMessageDialog(this, "Your guess in too low!");
                } else if (guess > number) {
                    JOptionPane.showMessageDialog(this, "Your guess is too high ");
                } else {
                    int roundScore = attempt * 10;
                    score += roundScore;
                    JOptionPane.showMessageDialog(this, "Congratulations your guess is right \n" +
                            "Your round score is " + roundScore + " \n" +
                            "Your total score is " + score);
                    scoreLabel.setText("Score: " + "[ "+score+" ]");
                    
                    if (round < 3) {
                        round++;
                        number = random.nextInt(max - min + 1) + min;
                        roundLabel.setText("Round " + "[ "+round+" ]");
                        attempt = 5;
                        remainingAttemptsLabel.setText("Attempts remaining: " + "[ "+attempt+" ]");
                    } else {
                        JOptionPane.showMessageDialog(this, "Game over! Your game score is : " + score);
                        roundLabel.setText("Round: [ 1 ] ");
                        attempt = 5;
                    }
                }
                if (attempt == 0) {
                    JOptionPane.showMessageDialog(this, "You were unable to guess the right number. The number was " + number);
                    if (round < 3) {
                        round++;
                        number = random.nextInt(max - min + 1) + min;
                        roundLabel.setText("Round " + "[ "+round+" ]");
                        attempt = 5;
                        remainingAttemptsLabel.setText("Attempts remaining: " + "[ "+attempt+" ]");
                    } else {
                        JOptionPane.showMessageDialog(this, "Game over! Your game score is : " + score);
                        roundLabel.setText("Round: [ 1 ] ");
                        attempt = 5;
                    }
                }
                guessField.setText("");
                remainingAttemptsLabel.setText("Attempts remaining: " + "[ "+attempt+" ]");
            }
        } else if (e.getSource() == highScoreButton) {
            JOptionPane.showMessageDialog(this, "High score: " + score);
        }
    }
    
    
    
    public static void main(String[] args) {
        GuessNumbers example = new GuessNumbers();
    }
}
