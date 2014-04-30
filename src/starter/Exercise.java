package starter;

import java.util.ArrayList;

/**
 * Created by helifab on 29.04.2014.
 */
public class Exercise {
    private String question;
    private String answer;
    private ArrayList<String> choice;

    public Exercise(){
        choice = new ArrayList<String>();
    }
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public ArrayList<String> getChoice() {
        return choice;
    }

    public void addChoice(String choice) {
        this.choice.add(choice);
    }
}
