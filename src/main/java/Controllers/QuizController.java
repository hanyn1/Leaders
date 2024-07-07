package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class QuizController {

    @FXML
    public Label question;

    @FXML
    public Button opt1, opt2, opt3, opt4;

    static int counter = 0;
    static int correct = 0;
    static int wrong = 0;

    @FXML
    private void initialize() {
        loadQuestions();
    }

    private void loadQuestions() {

        if (counter == 0) { //Question 1
            question.setText("1.Dans quel élément mettez-vous JavaScript?");
            opt1.setText("<var>");
            opt2.setText("<script>");
            opt3.setText("<section>");
        }
        if (counter == 1) { //Question 2
            question.setText("2. Comment écrire \"Hello W3docs\" dans une boîte d'alerte?");
            opt1.setText("alertBox(\"Hello W3docs\");");
            opt2.setText("alert(\"Hello W3docs\")");
            opt3.setText("msgBox(\"Hello W3docs\");\n");
        }
        if (counter == 2) { //Question 3
            question.setText("3.Comment déclarez-vous une nouvelle date en JavaScript?");
            opt1.setText("var date = Date();");
            opt2.setText("var date = new Date();");
            opt3.setText("var date = date('now');");
        }
        if (counter == 3) { //Question 4
            question.setText("4. Comment ouvrez-vous une fenêtre de confirmation en JavaScript?");
            opt1.setText("confirm()");
            opt2.setText("location.confirm()");
            opt3.setText("window.open_confirm()");
        }
        if (counter == 4) {//Question 5
            question.setText("5.La Date JavaScript est fondamentalement spécifiée comme ___");
            opt1.setText("\n" + "Le nombre de millisecondes écoulées depuis le 1er janvier 1970");
            opt2.setText("Le nombre de picosecondes écoulées depuis le 1er janvier 1970");
            opt3.setText("\n" + "Le nombre de minutes écoulées depuis le 1er janvier 1980");
        }
        if (counter == 5) { //Question 6
            question.setText("6. La méthode pop() supprime le dernier élément d'un tableau et renvoie un nouveau tableau.");
            opt1.setText("Faux");
            opt2.setText("Vrai");
            opt3.setText("aucun");
        }
        if (counter == 6) { //Question 7
            question.setText("7. Lequel des suivants est vrai pour le ciblage dynamique?");
            opt1.setText("Les variables peuvent être déclarées en dehors de la portée");
            opt2.setText("\n" + "Les variables ne peuvent pas être déclarées en dehors de la portée");
            opt3.setText("Aucune des options mentionnées");
        }
        if (counter == 7) { //Question 8
            question.setText("8. JavaScript n'est pas un langage sensible à la casse.-");
            opt1.setText("vrai");
            opt2.setText("faux");
            opt3.setText("aucun");
        }
        if (counter == 8) { //Question 9
            question.setText("9.Lequel des suivants est utilisé pour assigner une valeur à une variable?");
            opt1.setText("*");
            opt2.setText("+");
            opt3.setText("=");
        }
        if (counter == 9) { //Question 10
            question.setText("10.\n" + "Que se passe-t-il si l'instruction 'return' n'a pas d'expression associée?");
            opt1.setText("Elle va déclencher une exception");
            opt2.setText("Elle va retourner 0");
            opt3.setText("Elle va retourner undefined");
        }

    }


    @FXML
    public void opt1clicked(ActionEvent event) throws IOException{
        checkAnswer(opt1.getText().toString());

        if (checkAnswer(opt1.getText().toString())) {
            correct++;
        } else {
            wrong++;
        }

        if (counter == 9) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/resultat.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Error loading FXML: " + e.getMessage());
            }
        } else {
            counter++;
            loadQuestions();
        }

    }

    boolean checkAnswer(String answer) {

        if (counter == 0) {
            if (answer.equals("<script>")) {
                return true;
            } else {
                return false;
            }
        }
        if (counter == 1) {
            if (answer.equals("alert(\"Hello W3docs\");")) {
                return true;
            } else {
                return false;
            }
        }
        if (counter == 2) {
            if (answer.equals("var date = new Date();")) {
                return true;
            } else {
                return false;
            }
        }
        if (counter == 3) {
            if (answer.equals("confirm()")) {
                return true;
            } else {
                return false;
            }
        }
        if (counter == 4) {
            if (answer.equals("Le nombre de millisecondes écoulées depuis le 1er janvier 1970")) {
                return true;
            } else {
                return false;
            }
        }
        if (counter == 5) {
            if (answer.equals("Vrai")) {
                return true;
            } else {
                return false;
            }
        }
        if (counter == 6) {
            if (answer.equals("Aucune des options mentionnées")) {
                return true;
            } else {
                return false;
            }
        }
        if (counter == 7) {
            if (answer.equals("Faux")) {
                return true;
            } else {
                return false;
            }
        }
        if (counter == 8) {
            if (answer.equals("=")) {
                return true;
            } else {
                return false;
            }
        }
        if (counter == 9) {
            if (answer.equals("Elle va retourner undefined")) {
                return true;
            } else {
                return false;
            }
        }
        return false;


    }
    @FXML
    public void opt2clicked(ActionEvent event) {
        checkAnswer(opt2.getText().toString());
        if (checkAnswer(opt2.getText().toString())) {
            correct = correct + 1;
        } else {
            wrong = wrong + 1;
        }
        if (counter == 9) {
            try {
                System.out.println(correct);
                Stage thisstage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                thisstage.close();
                FXMLLoader quiz = new FXMLLoader(getClass().getResource("/result.fxml"));
                Scene quizscene = new Scene(quiz.load());
                quizscene.setFill(Color.TRANSPARENT);
                Stage quizstage = new Stage();
                quizstage.setScene(quizscene);
                quizstage.initStyle(StageStyle.TRANSPARENT);
                quizstage.show();
            } catch(IOException e) {
                e.printStackTrace();
            }
        } else {
            counter++;
            loadQuestions();
        }
    }

    @FXML
    public void opt3clicked(ActionEvent event) {
        checkAnswer(opt3.getText().toString());
        if (checkAnswer(opt3.getText().toString())) {
            correct = correct + 1;
        } else {
            wrong = wrong + 1;
        }
        if (counter == 9) {
            try {
                System.out.println(correct);
                Stage thisstage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                thisstage.close();
                FXMLLoader quiz = new FXMLLoader(getClass().getResource("/result.fxml"));
                Scene quizscene = new Scene(quiz.load());
                quizscene.setFill(Color.TRANSPARENT);
                Stage quizstage = new Stage();
                quizstage.setScene(quizscene);
                quizstage.initStyle(StageStyle.TRANSPARENT);
                quizstage.show();
            } catch(IOException e) {
                e.printStackTrace();
            }
        } else {
            counter++;
            loadQuestions();
        }
    }

    @FXML
    public void opt4clicked(ActionEvent event) {
        checkAnswer(opt4.getText().toString());
        if (checkAnswer(opt4.getText().toString())) {
            correct = correct + 1;
        } else {
            wrong = wrong + 1;
        }
        if (counter == 9) {
            try {
                System.out.println(correct);
                Stage thisstage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                thisstage.close();
                FXMLLoader quiz = new FXMLLoader(getClass().getResource("/result.fxml"));
                Scene quizscene = new Scene(quiz.load());
                quizscene.setFill(Color.TRANSPARENT);
                Stage quizstage = new Stage();
                quizstage.setScene(quizscene);
                quizstage.initStyle(StageStyle.TRANSPARENT);
                quizstage.show();
            } catch(IOException e) {
                e.printStackTrace();
            }
        } else {
            counter++;
            loadQuestions();
        }
    }

}

