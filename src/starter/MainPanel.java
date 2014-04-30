package starter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by helifab on 29.04.2014.
 */
public class MainPanel extends JPanel implements KeyListener{
    private JLabel question;
    JLabel answer;
    private JTextArea choice;
    private Font font;
    private JPanel center;
    private BorderLayout bl;
    private JButton nextButton;
    private boolean correct = false;
    public MainPanel(){
        setSize(500, 500);
        bl = new BorderLayout();
        setLayout(bl);
        font = new Font("Serif", Font.BOLD, 30);
        question = new JLabel("", JLabel.CENTER);
        question.setFont(font);
        question.setForeground(Color.BLUE);
        add(question, BorderLayout.NORTH);

        addCenter();
        addKeyListener(this);
    }

    private void addCenter(){
        center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        JPanel top = new JPanel();
        JPanel bottom = new JPanel();
        center.add(top);
        center.add(bottom);
        JButton correctButton = new JButton("Correct");
        correctButton.setFocusable(false);
        correctButton.addActionListener((ActionEvent e)->{
            correct = true;
            correctButton.setEnabled(false);
        });
        //JButton wrongButton = new JButton("Wrong");
        //wrongButton.setFocusable(false);

        nextButton = new JButton("SHOW");
        nextButton.addActionListener((ActionEvent e)->{
            if (nextButton.getText().equalsIgnoreCase("show")){
                nextButton.setText("NEXT");
                answer.setVisible(true);
            }else if(nextButton.getText().equalsIgnoreCase("restart")){
                top.setVisible(true);
                choice.setVisible(true);
                correctButton.setVisible(true);
                //wrongButton.setVisible(true);
                nextButton.setText("SHOW");
            }else{
                correctButton.setEnabled(true);
                answer.setVisible(false);
                nextButton.setText("SHOW");
                if (Main.reader.nextExercise(correct)){
                    top.setVisible(false);
                    choice.setVisible(false);
                    correctButton.setVisible(false);
                    //wrongButton.setVisible(false);
                    nextButton.setText("RESTART");
                };
                correct = false;
            }
        });
        choice = new JTextArea();
        choice.setEditable(false);
        choice.setFocusable(false);
        choice.setFont(font);

        answer = new JLabel("", JLabel.CENTER);
        answer.setFont(font);
        answer.setForeground(Color.GREEN);
        answer.setVisible(false);

        top.add(answer);
        bottom.add(correctButton);
        bottom.add(nextButton);
        //bottom.add(wrongButton);
        add(choice, BorderLayout.CENTER);
        add(center, BorderLayout.SOUTH);
    }

    public String getQuestion() {
        return question.getText();
    }

    public void setQuestion(String question) {
        this.question.setText(question);
    }

    public String getAnswer() {
        return answer.getText();
    }

    public void setAnswer(String answer) {
        this.answer.setText(answer);
    }

    public String getChoice() {
        return choice.getText();
    }

    public void setChoice(String choice) {
        this.choice.setText(choice);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            nextButton.doClick();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
