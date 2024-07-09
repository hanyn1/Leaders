package Controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class QuizController {

    @FXML
    private Label question, timerLabel;

    @FXML
    private Button opt1, opt2, opt3;

    private Timeline timeline;
    private int timeSeconds = 12;
    private static int counter = 0;
    static int correct = 0;
    static int wrong = 0;
    private static int failedAttempts = 0;
    private static LocalDate lastFailedAttemptDate;

    @FXML
    private void initialize() {
        loadQuestions();
        startTimer();
    }

    private void startTimer() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            timeSeconds--;
            timerLabel.setText("Temps restant : " + timeSeconds + "s");
            if (timeSeconds <= 0) {
                timeline.stop();
                handleTimeout();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void handleTimeout() {
        failedAttempts++;
        if (failedAttempts >= 3) {
            lastFailedAttemptDate = LocalDate.now();
            disableQuiz("Vous avez échoué . Veuillez réessayer après .");
        } else {
            counter++;
            loadQuestions();
            startTimer();
        }
    }

    private void disableQuiz(String message) {
        question.setText(message);
        opt1.setDisable(true);
        opt2.setDisable(true);
        opt3.setDisable(true);
        timerLabel.setText("");
    }

    private void loadQuestions() {
        switch (counter) {
            case 0:
                question.setText("1. Dans quel élément mettez-vous JavaScript?");
                opt1.setText("<var>");
                opt2.setText("<script>");
                opt3.setText("<section>");
                break;
            case 1:
                question.setText("2. Comment écrire \"Hello W3docs\" dans une boîte d'alerte?");
                opt1.setText("alertBox(\"Hello W3docs\");");
                opt2.setText("alert(\"Hello W3docs\")");
                opt3.setText("msgBox(\"Hello W3docs\");");
                break;
            case 2:
                question.setText("3. Comment déclarez-vous une nouvelle date en JavaScript?");
                opt1.setText("var date = Date();");
                opt2.setText("var date = new Date();");
                opt3.setText("var date = date('now');");
                break;
            case 3:
                question.setText("4. Comment ouvrez-vous une fenêtre de confirmation en JavaScript?");
                opt1.setText("confirm()");
                opt2.setText("location.confirm()");
                opt3.setText("window.open_confirm()");
                break;
            case 4:
                question.setText("5. La Date JavaScript est fondamentalement spécifiée comme ___");
                opt1.setText("Le nombre de millisecondes écoulées depuis le 1er janvier 1970");
                opt2.setText("Le nombre de picosecondes écoulées depuis le 1er janvier 1970");
                opt3.setText("Le nombre de minutes écoulées depuis le 1er janvier 1980");
                break;
            case 5:
                question.setText("6. La méthode pop() supprime le dernier élément d'un tableau et renvoie un nouveau tableau.");
                opt1.setText("Faux");
                opt2.setText("Vrai");
                opt3.setText("Aucun");
                break;
            case 6:
                question.setText("7. Lequel des suivants est vrai pour le ciblage dynamique?");
                opt1.setText("Les variables peuvent être déclarées en dehors de la portée");
                opt2.setText("Les variables ne peuvent pas être déclarées en dehors de la portée");
                opt3.setText("Aucune des options mentionnées");
                break;
            case 7:
                question.setText("8. JavaScript n'est pas un langage sensible à la casse.");
                opt1.setText("Vrai");
                opt2.setText("Faux");
                opt3.setText("Aucun");
                break;
            case 8:
                question.setText("9. Lequel des suivants est utilisé pour assigner une valeur à une variable?");
                opt1.setText("*");
                opt2.setText("+");
                opt3.setText("=");
                break;
            case 9:
                question.setText("10. Que se passe-t-il si l'instruction 'return' n'a pas d'expression associée?");
                opt1.setText("Elle va déclencher une exception");
                opt2.setText("Elle va retourner 0");
                opt3.setText("Elle va retourner undefined");
                break;
            default:
                break;
        }
    }

    boolean checkAnswer(String answer) {
        switch (counter) {
            case 0:
                return answer.equals("<script>");
            case 1:
                return answer.equals("alert(\"Hello W3docs\");");
            case 2:
                return answer.equals("var date = new Date();");
            case 3:
                return answer.equals("confirm()");
            case 4:
                return answer.equals("Le nombre de millisecondes écoulées depuis le 1er janvier 1970");
            case 5:
                return answer.equals("Vrai");
            case 6:
                return answer.equals("Aucun");
            case 7:
                return answer.equals("Faux");
            case 8:
                return answer.equals("=");
            case 9:
                return answer.equals("Elle va retourner undefined");
            default:
                return false;
        }
    }

    @FXML
    public void opt1clicked(ActionEvent event) {
        handleOptionClick(opt1.getText(), event);
    }

    @FXML
    public void opt2clicked(ActionEvent event) {
        handleOptionClick(opt2.getText(), event);
    }

    @FXML
    public void opt3clicked(ActionEvent event) {
        handleOptionClick(opt3.getText(), event);
    }

    private void handleOptionClick(String answer, ActionEvent event) {
        if (checkAnswer(answer)) {
            correct++;
        } else {
            wrong++;
        }
        if (counter == 9) {
            showResult(event);
        } else {
            counter++;
            loadQuestions();
        }
    }

    private void showResult(ActionEvent event) {
        try {
            System.out.println("Correct: " + correct);
            Stage thisStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            thisStage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resultat.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void submitClicked(ActionEvent event) {
        // Logique à exécuter lorsque le bouton Soumettre est cliqué
    }
}