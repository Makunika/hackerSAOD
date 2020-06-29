package UI;

import javafx.beans.property.SimpleStringProperty;

public class QuesAns {
    SimpleStringProperty question;
    SimpleStringProperty answer;


    public QuesAns(String question, String answer) {
        this.question = new SimpleStringProperty();
        this.answer = new SimpleStringProperty();
        setAnswer(answer);
        setQuestion(question);
    }

    public String getQuestion() {
        return question.get();
    }

    public SimpleStringProperty questionProperty() {
        return question;
    }

    public void setQuestion(String question) {
        this.question.set(question);
    }

    public String getAnswer() {
        return answer.get();
    }

    public SimpleStringProperty answerProperty() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer.set(answer);
    }
}
