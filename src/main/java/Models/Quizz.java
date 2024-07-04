package Models;

import java.sql.Timestamp;

public class Quizz {
    private int id;
    private String titre;
    private String description;
    private String option1;
    private String option2;
    private String option3;
    private String rightAnswer;
    private Timestamp quizCreatedAt;

    public Quizz(int id, String titre, String description, String option1, String option2, String option3, String rightAnswer, Timestamp quizCreatedAt) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.rightAnswer = rightAnswer;
        this.quizCreatedAt = quizCreatedAt;
    }

    public Quizz(String titre, String description, String option1, String option2, String option3, String rightAnswer, Timestamp quizCreatedAt) {
        this.titre = titre;
        this.description = description;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.rightAnswer = rightAnswer;
        this.quizCreatedAt = quizCreatedAt;
    }

    public Quizz(String titre, String description, String option1, String option2, String option3) {
        this.titre = titre;
        this.description = description;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
    }

    public Quizz(int id, String titre, String description, String option1, String option2, String option3, String rightAnswer) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.rightAnswer = rightAnswer;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public Timestamp getQuizCreatedAt() {
        return quizCreatedAt;
    }

    public void setQuizCreatedAt(Timestamp quizCreatedAt) {
        this.quizCreatedAt = quizCreatedAt;
    }
}