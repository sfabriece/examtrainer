package starter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Created by helifab on 29.04.2014.
 */
public class Reader {
    private ArrayList<Exercise> exercises, done;
    private ArrayList<Integer> rotation;
    private MainPanel mP;
    private enum EX {EXE, CH, ANS};
    private int size;
    public Reader(MainPanel mP){
        if (mP == null){
            throw new RuntimeException();
        }
        this.mP = mP;
        exercises = new ArrayList<Exercise>();
        Scanner scan = new Scanner(getClass().getResourceAsStream("file.txt"));

        Exercise previous = null, exercise = null;
        while(scan.hasNextLine()){
            String a = scan.nextLine();
            EX ex;
            if (a.startsWith("#")){
                ex = EX.EXE;
            }else if (a.startsWith("-")){
               ex = EX.ANS;
            }else {
                ex = EX.CH;
            }
            switch (ex){
                case EXE:
                    exercise = new Exercise();
                    exercise.setQuestion(a.substring(1));
                    exercises.add(exercise);
                    break;
                case CH:
                    exercise.addChoice(a);
                    break;
                case ANS:
                    exercise.addChoice(a.substring(1));
                    exercise.setAnswer(a.substring(1));
                    break;
            }
        }
        done = new ArrayList<>(exercises.size() - 1);
        rotation = new ArrayList<>(exercises.size() - 1);
        size = exercises.size();
        nextExercise(false);
        start = false;
    }
    int prev = 0;
    boolean start = true;

    public boolean nextExercise(boolean correct){
        if (correct) {
            done.add(exercises.get(prev));
            exercises.remove(prev);
        }

        if (done.size() == size){
            exercises = new ArrayList<>(done);
            done.clear();
            return true;
        }

        //check done with current rotation
        if (rotation.size() == exercises.size()){
            rotation.clear();
        }
        int current = 0;
        while(rotation.contains(current)){
            current = (int)(Math.random() * exercises.size());
        }

        if (!correct){
            rotation.add(current);
        }

        showExercise(exercises.get(current));
        prev = current;
        return false;
    }

    private void showExercise(Exercise e) {
        mP.setQuestion(e.getQuestion());
        mP.setAnswer(e.getAnswer());
        String choice = "";
        for (int i = 1; i <= e.getChoice().size(); i++){
            choice += i + ". " + e.getChoice().get(i-1) + "\n";
        }
        mP.setChoice(choice);
    }
}
