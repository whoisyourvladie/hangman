package hangman;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;


public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private Label label3;

    @FXML
    private Label label4;

    @FXML
    private Label label5;

    @FXML
    private Label label6;

    @FXML
    private Label label7;

    @FXML
    private Label label8;

    @FXML
    private Label label9;

    @FXML
    private Label label10;

    @FXML
    private TextArea definitionTA;

   @FXML
    private ImageView wonlabel;

    @FXML
    private ImageView line1;

    @FXML
    private ImageView line2;

    @FXML
    private ImageView line3;

    @FXML
    private ImageView line4;

    @FXML
    private ImageView line5;

    @FXML
    private ImageView line6;

    @FXML
    private ImageView line7;

    @FXML
    private ImageView line8;

    @FXML
    private ImageView line9;

    @FXML
    private ImageView line10;
    
    @FXML
    private ImageView line11;

   
    @FXML
    private TextField guessTf;

    @FXML
    private Button EnterBt;

    @FXML
    private Button FirstBt;

    @FXML
    private Button LastBt;

    @FXML
    private Button ExitBt;

    @FXML
    private Button NewBt;

    @FXML
    private Button HintBt;

    @FXML
    private Button Abt;

    @FXML
    private Button Bbt;

    @FXML
    private Button Cbt;

    @FXML
    private Button Dbt;

    @FXML
    private Button Ebt;

    @FXML
    private Button Fbt;

    @FXML
    private Button Gbt;

    @FXML
    private Button Hbt;

    @FXML
    private Button Ibt;

    @FXML
    private Button Jbt;

    @FXML
    private Button Kbt;

    @FXML
    private Button Lbt;

    @FXML
    private Button Mbt;

    @FXML
    private Button Nbt;

    @FXML
    private Button Obt;

    @FXML
    private Button Pbt;

    @FXML
    private Button Qbt;

    @FXML
    private Button Rbt;

    @FXML
    private Button Sbt;

    @FXML
    private Button Tbt;

    @FXML
    private Button Ubt;

    @FXML
    private Button Vbt;

    @FXML
    private Button Wbt;

    @FXML
    private Button Xbt;

    @FXML
    private Button Ybt;

    @FXML
    private Button Zbt;

    private Scanner infile;
    private ArrayList<String> wordList = new ArrayList<>();
    private ArrayList<String> defList = new ArrayList<>();
    private String currentWord;
    private Random randomGenerator = new Random();
    private int enterPressed = 0;
    private int errorCount = 0;

    @FXML
    private void exitEvent(ActionEvent event) {
        System.exit(1);
    }

    @FXML
    private void hintBtEvent(ActionEvent event) {

        boolean hintRevealed = false;

        ArrayList<String> whosOpen = new ArrayList<>();
        whosOpen.add(label1.getText());
        whosOpen.add(label2.getText());
        whosOpen.add(label3.getText());
        whosOpen.add(label4.getText());
        whosOpen.add(label5.getText());
        whosOpen.add(label6.getText());
        whosOpen.add(label7.getText());
        whosOpen.add(label8.getText());
        whosOpen.add(label9.getText());
        whosOpen.add(label10.getText());
        //System.out.println("whos open: " + whosOpen);
        while (!hintRevealed) {
            int hintPosition = randomGenerator.nextInt(currentWord.length());

            if (whosOpen.get(hintPosition).equals("")) {
                openTextField("" + currentWord.charAt(hintPosition));
                disableLetterbutton("" + currentWord.charAt(hintPosition));
                hintRevealed = true;

                //whosOpen.removeAll(Collections.singleton(""));//remove empty elements from array
            } else if (isSolved()) {

                hintRevealed = true;
                clearHangman();
                wonlabel.setVisible(true);
                HintBt.setDisable(true);
                FirstBt.setDisable(true);
                LastBt.setDisable(true);
            }

        }
        guessTf.requestFocus();

    }

    @FXML
    private void firstLetterevent(ActionEvent event) {
        //label1.setText("" + currentWord.charAt(0));
        openTextField("" + currentWord.charAt(0));
        disableLetterbutton("" + currentWord.charAt(0));

        FirstBt.setDisable(true);
        guessTf.requestFocus();
    }

    @FXML
    private void lastLetterevent(ActionEvent event) {

        openTextField("" + currentWord.charAt(currentWord.length() - 1));
        disableLetterbutton("" + currentWord.charAt(currentWord.length() - 1));

        // label1.setText("" + currentWord.charAt(0));
        LastBt.setDisable(true);
        guessTf.requestFocus();
    }

    //value changed listener for the text field
    @FXML
    private void inputGuessChanged(ActionEvent event) {
        EnterBt.setDisable(false);
        EnterBt.requestFocus();
    }

    @FXML
    private void newGameEvent(ActionEvent event) {
        currentWord = wordList.get(randomGenerator.nextInt(wordList.size()));
        //System.out.println("word: " + currentWord);

        definitionTA.setText(defList.get(wordList.indexOf(currentWord)));

        newGameLabelsNumber(currentWord);
        enableAllLetterButtons();
        clearAllLabels();
        clearHangman();
        EnterBt.setDisable(true);
        FirstBt.setDisable(false);
        LastBt.setDisable(false);
        HintBt.setDisable(false);
        //enterPressed = 0;
        errorCount = 0;
        guessTf.requestFocus();
        

    }

    @FXML
    private void enterHit(ActionEvent event) {
        disableLetterbutton(guessTf.getText());

        //System.out.println("solved before: " + isSolved());
        if (currentWord.contains(guessTf.getText().toUpperCase())) {

            openTextField(guessTf.getText());
            
            guessTf.clear();
            guessTf.requestFocus();

        } else {
            errorCount++;

            onMisGuess();
        }
        //System.out.println("error count: " + errorCount);
        //System.out.println("solved after? "+isSolved());
        if (isSolved() && errorCount < 11) {
            clearHangman();
            wonlabel.setVisible(true);

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Font.loadFont(Hangman.class.getResource("Schoolbell.ttf").toExternalForm(), 10);

        EnterBt.defaultButtonProperty().bind(EnterBt.focusedProperty());

        try {
            createWordDefArrays();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        currentWord = wordList.get(randomGenerator.nextInt(wordList.size()));
        definitionTA.setText(defList.get(wordList.indexOf(currentWord)));

        newGameLabelsNumber(currentWord);
        EnterBt.setDisable(true);
        //System.out.println("word: " + currentWord);

        guessTf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                if ((!newValue.equals(null) && !newValue.equals("")) && (Character.isLetter(newValue.charAt(0))) && newValue.length() == 1) {
                    EnterBt.setDisable(false);
                    EnterBt.requestFocus();
                } else {
                    EnterBt.setDisable(true);
                }
            }
        });

    }

    private void newGameLabelsNumber(String currentword) {
        switch (currentword.length()) {
            case 5:
                label1.setVisible(true);
                label2.setVisible(true);
                label3.setVisible(true);
                label4.setVisible(true);
                label5.setVisible(true);
                label6.setVisible(false);
                label7.setVisible(false);
                label8.setVisible(false);
                label9.setVisible(false);
                label10.setVisible(false);
                break;
            case 6:
                label1.setVisible(true);
                label2.setVisible(true);
                label3.setVisible(true);
                label4.setVisible(true);
                label5.setVisible(true);
                label6.setVisible(true);
                label7.setVisible(false);
                label8.setVisible(false);
                label9.setVisible(false);
                label10.setVisible(false);
                break;
            case 7:
                label1.setVisible(true);
                label2.setVisible(true);
                label3.setVisible(true);
                label4.setVisible(true);
                label5.setVisible(true);
                label6.setVisible(true);
                label7.setVisible(true);
                label8.setVisible(false);
                label9.setVisible(false);
                label10.setVisible(false);
                break;
            case 8:
                label1.setVisible(true);
                label2.setVisible(true);
                label3.setVisible(true);
                label4.setVisible(true);
                label5.setVisible(true);
                label6.setVisible(true);
                label7.setVisible(true);
                label8.setVisible(true);
                label9.setVisible(false);
                label10.setVisible(false);
                break;
            case 9:
                label1.setVisible(true);
                label2.setVisible(true);
                label3.setVisible(true);
                label4.setVisible(true);
                label5.setVisible(true);
                label6.setVisible(true);
                label7.setVisible(true);
                label8.setVisible(true);
                label9.setVisible(true);
                label10.setVisible(false);
                break;
            case 10:
                label1.setVisible(true);
                label2.setVisible(true);
                label3.setVisible(true);
                label4.setVisible(true);
                label5.setVisible(true);
                label6.setVisible(true);
                label7.setVisible(true);
                label8.setVisible(true);
                label9.setVisible(true);
                label10.setVisible(true);
                break;
        }

        EnterBt.setDisable(true);
    }

// method to reveal the guessed letter in the Label
    private void openTextField(String value) {
        int index = currentWord.indexOf(value.toUpperCase());
        ArrayList<Integer> guessArr = new ArrayList<>();
        while (index >= 0) {
            guessArr.add(index);
            index = currentWord.indexOf(value.toUpperCase(), index + 1);
        }

        for (Integer guessArr1 : guessArr) {
            switch (guessArr1) {
                case 0:
                    label1.setText(value.toUpperCase());
                    label1.setStyle("-fx-background-color:  #f4df42;-fx-border-color: #020e8e");

                    break;
                case 1:
                    label2.setText(value.toUpperCase());
                    label2.setStyle("-fx-background-color:  #f4df42;-fx-border-color: #020e8e");
                    break;
                case 2:
                    label3.setText(value.toUpperCase());
                    label3.setStyle("-fx-background-color:  #f4df42;-fx-border-color: #020e8e");
                    break;
                case 3:
                    label4.setText(value.toUpperCase());
                    label4.setStyle("-fx-background-color:  #f4df42;-fx-border-color: #020e8e");
                    break;
                case 4:
                    label5.setText(value.toUpperCase());
                    label5.setStyle("-fx-background-color:  #f4df42;-fx-border-color: #020e8e");
                    break;
                case 5:
                    label6.setText(value.toUpperCase());
                    label6.setStyle("-fx-background-color:  #f4df42;-fx-border-color: #020e8e");
                    break;
                case 6:
                    label7.setText(value.toUpperCase());
                    label7.setStyle("-fx-background-color:  #f4df42;-fx-border-color: #020e8e");
                    break;
                case 7:
                    label8.setText(value.toUpperCase());
                    label8.setStyle("-fx-background-color:  #f4df42;-fx-border-color: #020e8e");
                    break;
                case 8:
                    label9.setText(value.toUpperCase());
                    label9.setStyle("-fx-background-color:  #f4df42;-fx-border-color: #020e8e");
                    break;
                case 9:
                    label10.setText(value.toUpperCase());
                    label10.setStyle("-fx-background-color:  #f4df42;-fx-border-color: #020e8e");
                    break;
            }
        }
    }

    private void clearAllLabels() {
        label1.setText("");
        label2.setText("");
        label3.setText("");
        label4.setText("");
        label5.setText("");
        label6.setText("");
        label7.setText("");
        label8.setText("");
        label9.setText("");
        label10.setText("");
        label1.setStyle("-fx-background-color:  #cfe5f9;-fx-border-color: #020e8e");
        label2.setStyle("-fx-background-color:  #cfe5f9;-fx-border-color: #020e8e");
        label3.setStyle("-fx-background-color:  #cfe5f9;-fx-border-color: #020e8e");
        label4.setStyle("-fx-background-color:  #cfe5f9;-fx-border-color: #020e8e");
        label5.setStyle("-fx-background-color:  #cfe5f9;-fx-border-color: #020e8e");
        label6.setStyle("-fx-background-color:  #cfe5f9;-fx-border-color: #020e8e");
        label7.setStyle("-fx-background-color:  #cfe5f9;-fx-border-color: #020e8e");
        label8.setStyle("-fx-background-color:  #cfe5f9;-fx-border-color: #020e8e");
        label9.setStyle("-fx-background-color:  #cfe5f9;-fx-border-color: #020e8e");
        label10.setStyle("-fx-background-color:  #cfe5f9;-fx-border-color: #020e8e");
        
        
        
    }

    private boolean isSolved() {
        switch (currentWord.length()) {
            case 5:
                if (!label1.getText().equals("")
                        && !label2.getText().equals("")
                        && !label3.getText().equals("")
                        && !label4.getText().equals("")
                        && !label5.getText().equals("")) {
                    return true;
                }
                return false;

            case 6:
                if (!label1.getText().equals("")
                        && !label2.getText().equals("")
                        && !label3.getText().equals("")
                        && !label4.getText().equals("")
                        && !label5.getText().equals("")
                        && !label6.getText().equals("")) {
                    return true;
                }
                return false;

            case 7:
                if (!label1.getText().equals("")
                        && !label2.getText().equals("")
                        && !label3.getText().equals("")
                        && !label4.getText().equals("")
                        && !label5.getText().equals("")
                        && !label6.getText().equals("")
                        && !label7.getText().equals("")) {
                    return true;
                }
                return false;

            case 8:
                if (!label1.getText().equals("")
                        && !label2.getText().equals("")
                        && !label3.getText().equals("")
                        && !label4.getText().equals("")
                        && !label5.getText().equals("")
                        && !label6.getText().equals("")
                        && !label7.getText().equals("")
                        && !label8.getText().equals("")) {
                    return true;
                }
                return false;

            case 9:
                if (!label1.getText().equals("")
                        && !label2.getText().equals("")
                        && !label3.getText().equals("")
                        && !label4.getText().equals("")
                        && !label5.getText().equals("")
                        && !label6.getText().equals("")
                        && !label7.getText().equals("")
                        && !label8.getText().equals("")
                        && !label9.getText().equals("")) {
                    return true;
                }
                return false;

            case 10:
                if (!label1.getText().equals("")
                        && !label2.getText().equals("")
                        && !label3.getText().equals("")
                        && !label4.getText().equals("")
                        && !label5.getText().equals("")
                        && !label6.getText().equals("")
                        && !label7.getText().equals("")
                        && !label8.getText().equals("")
                        && !label9.getText().equals("")
                        && !label10.getText().equals("")) {
                    return true;
                }
                return false;
            default:
                return false;
        }
    }

    private void clearHangman() {
        line1.setVisible(false);
        line2.setVisible(false);
        line3.setVisible(false);
        line4.setVisible(false);
        line5.setVisible(false);
        line6.setVisible(false);
        line7.setVisible(false);
        line8.setVisible(false);
        line9.setVisible(false);
        line10.setVisible(false);
        line11.setVisible(false);
        //lostlabel.setVisible(false);
        wonlabel.setVisible(false);
    }

    private void createWordDefArrays() throws FileNotFoundException {

        Scanner inFile = new Scanner(new FileReader("wordsBig2.txt"));
        inFile.useDelimiter("\t|\r\n");

        while (inFile.hasNextLine()) {
            wordList.add(inFile.next().toUpperCase());
            defList.add(inFile.next());

        }

        inFile.close();

    }

//method to draw a hanged man in case of misguess
    private void onMisGuess() {
        //enterPressed++;

        guessTf.clear();
        guessTf.requestFocus();

        switch (errorCount) { //was enterPressed
            case 1:
                line1.setVisible(true);
                break;
            case 2:
                line1.setVisible(true);
                line2.setVisible(true);
                break;
            case 3:
                line1.setVisible(true);
                line2.setVisible(true);
                line3.setVisible(true);
                break;
            case 4:
                line4.setVisible(true);
                line1.setVisible(true);
                line2.setVisible(true);
                line3.setVisible(true);
                break;
            case 5:
                line5.setVisible(true);
                line4.setVisible(true);
                line1.setVisible(true);
                line2.setVisible(true);
                line3.setVisible(true);
                break;
            case 6:
                line6.setVisible(true);
                line5.setVisible(true);
                line4.setVisible(true);
                line1.setVisible(true);
                line2.setVisible(true);
                line3.setVisible(true);
                break;
            case 7:
                line6.setVisible(true);
                line7.setVisible(true);
                line5.setVisible(true);
                line4.setVisible(true);
                line1.setVisible(true);
                line2.setVisible(true);
                line3.setVisible(true);
                break;
            case 8:
                line7.setVisible(true);
                line6.setVisible(true);
                line8.setVisible(true);
                line5.setVisible(true);
                line4.setVisible(true);
                line1.setVisible(true);
                line2.setVisible(true);
                line3.setVisible(true);
                break;
            case 9:
                line8.setVisible(true);
                line7.setVisible(true);
                line6.setVisible(true);
                line9.setVisible(true);
                line5.setVisible(true);
                line4.setVisible(true);
                line1.setVisible(true);
                line2.setVisible(true);
                line3.setVisible(true);
                break;
            case 10:
                line1.setVisible(true);
                line2.setVisible(true);
                line3.setVisible(true);
                line4.setVisible(true);
                line10.setVisible(true);
                line5.setVisible(true);
                line6.setVisible(true);
                line7.setVisible(true);
                line8.setVisible(true);
                line9.setVisible(true);
                break;
            
            case 11:
                line1.setVisible(false);
                line2.setVisible(false);
                line3.setVisible(false);
                line4.setVisible(false);
                line5.setVisible(false);
                line6.setVisible(false);
                line7.setVisible(false);
                line8.setVisible(false);
                line9.setVisible(false);
                line10.setVisible(false);
               line11.setVisible(true);
                //wonlabel.setVisible(false);
                EnterBt.setDisable(true);
                FirstBt.setDisable(true);
                LastBt.setDisable(true);
                HintBt.setDisable(true);

                try {
                    label1.setText("" + currentWord.charAt(0));
                    label2.setText("" + currentWord.charAt(1));
                    label3.setText("" + currentWord.charAt(2));
                    label4.setText("" + currentWord.charAt(3));
                    label5.setText("" + currentWord.charAt(4));
                    label6.setText("" + currentWord.charAt(5));
                    label7.setText("" + currentWord.charAt(6));
                    label8.setText("" + currentWord.charAt(7));
                    label9.setText("" + currentWord.charAt(8));
                    label10.setText("" + currentWord.charAt(9));

                } catch (Exception e) {

                }
                break;

        }
    }

    @FXML
    private void letterButtonClicked(ActionEvent event) {

        switch (((Button) event.getSource()).getText()) {
            case "A":
                guessTf.setText("A");
                Abt.setDisable(true);
                enterHit(event);
                break;
            case "B":
                guessTf.setText("B");
                Bbt.setDisable(true);
                enterHit(event);
                break;
            case "C":
                guessTf.setText("C");
                Cbt.setDisable(true);
                enterHit(event);
                break;
            case "D":
                guessTf.setText("D");
                Dbt.setDisable(true);
                enterHit(event);
                break;
            case "E":
                guessTf.setText("E");
                Ebt.setDisable(true);
                enterHit(event);
                break;
            case "F":
                guessTf.setText("F");
                Fbt.setDisable(true);
                enterHit(event);
                break;
            case "G":
                guessTf.setText("G");
                Gbt.setDisable(true);
                enterHit(event);
                break;
            case "H":
                guessTf.setText("H");
                Hbt.setDisable(true);
                enterHit(event);
                break;
            case "I":
                guessTf.setText("I");
                Ibt.setDisable(true);
                enterHit(event);
                break;
            case "J":
                guessTf.setText("J");
                Jbt.setDisable(true);
                enterHit(event);
                break;
            case "K":
                guessTf.setText("K");
                Kbt.setDisable(true);
                enterHit(event);
                break;
            case "L":
                guessTf.setText("L");
                Lbt.setDisable(true);
                enterHit(event);
                break;
            case "M":
                guessTf.setText("M");
                Abt.setDisable(true);
                enterHit(event);
                break;
            case "N":
                guessTf.setText("N");
                Nbt.setDisable(true);
                enterHit(event);
                break;
            case "O":
                guessTf.setText("O");
                Obt.setDisable(true);
                enterHit(event);
                break;
            case "P":
                guessTf.setText("P");
                Pbt.setDisable(true);
                enterHit(event);
                break;
            case "Q":
                guessTf.setText("Q");
                Qbt.setDisable(true);
                enterHit(event);
                break;
            case "R":
                guessTf.setText("R");
                Rbt.setDisable(true);
                enterHit(event);
                break;
            case "S":
                guessTf.setText("S");
                Sbt.setDisable(true);
                enterHit(event);
                break;
            case "T":
                guessTf.setText("T");
                Tbt.setDisable(true);
                enterHit(event);
                break;
            case "U":
                guessTf.setText("U");
                Ubt.setDisable(true);
                enterHit(event);
                break;
            case "V":
                guessTf.setText("V");
                Vbt.setDisable(true);
                enterHit(event);
                break;
            case "W":
                guessTf.setText("W");
                Wbt.setDisable(true);
                enterHit(event);
                break;
            case "X":
                guessTf.setText("X");
                Xbt.setDisable(true);
                enterHit(event);
                break;
            case "Y":
                guessTf.setText("Y");
                Ybt.setDisable(true);
                enterHit(event);
                break;
            case "Z":
                guessTf.setText("Z");
                Zbt.setDisable(true);
                enterHit(event);
                break;

        }

    }

    private void enableAllLetterButtons() {
        Abt.setDisable(false);
        Bbt.setDisable(false);
        Cbt.setDisable(false);
        Dbt.setDisable(false);
        Ebt.setDisable(false);
        Fbt.setDisable(false);
        Gbt.setDisable(false);
        Hbt.setDisable(false);
        Ibt.setDisable(false);
        Jbt.setDisable(false);
        Kbt.setDisable(false);
        Lbt.setDisable(false);
        Mbt.setDisable(false);
        Nbt.setDisable(false);
        Obt.setDisable(false);
        Pbt.setDisable(false);
        Qbt.setDisable(false);
        Rbt.setDisable(false);
        Sbt.setDisable(false);
        Tbt.setDisable(false);
        Ubt.setDisable(false);
        Vbt.setDisable(false);
        Wbt.setDisable(false);
        Xbt.setDisable(false);
        Ybt.setDisable(false);
        Zbt.setDisable(false);

    }

    private void disableLetterbutton(String getText) {
        getText=getText.toUpperCase();
        //System.out.println("I'm here, letter: "+getText);
        switch (getText) {
            case "A":
                Abt.setDisable(true);
                break;
            case "B":
                Bbt.setDisable(true);
                break;
            case "C":
                Cbt.setDisable(true);
                break;
            case "D":
                Dbt.setDisable(true);
                break;
            case "E":
                Ebt.setDisable(true);
                break;
            case "F":
                Fbt.setDisable(true);
                break;
            case "G":
                Gbt.setDisable(true);
                break;
            case "H":
                Hbt.setDisable(true);
                break;
            case "I":
                Ibt.setDisable(true);
                break;
            case "J":
                Jbt.setDisable(true);
                break;
            case "K":
                Kbt.setDisable(true);
                break;
            case "L":
                Lbt.setDisable(true);
                break;
            case "M":
                Mbt.setDisable(true);
                break;
            case "N":
                Nbt.setDisable(true);
                break;
            case "O":
                Obt.setDisable(true);
                break;
            case "P":
                Pbt.setDisable(true);
                break;
            case "Q":
                Qbt.setDisable(true);
                break;
            case "R":
                Rbt.setDisable(true);
                break;
            case "S":
                Sbt.setDisable(true);
                break;
            case "T":
                Tbt.setDisable(true);
                break;
            case "U":
                Ubt.setDisable(true);
                break;
            case "V":
                Vbt.setDisable(true);
                break;
            case "W":
                Wbt.setDisable(true);
                break;
            case "X":
                Xbt.setDisable(true);
                break;
            case "Y":
                Ybt.setDisable(true);
                break;
            case "Z":
                Zbt.setDisable(true);
                break;

        }
    }

}
