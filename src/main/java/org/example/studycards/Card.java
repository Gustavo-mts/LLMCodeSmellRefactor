package org.example.studycards;

public final class Card {
    private String question;
    private String answer;

    public Card(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public Card withEdit(String newQuestion, String newAnswer) {
        return new Card(newQuestion, newAnswer);
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void edit(String question, String answer) {
        setQuestion(question);
        setAnswer(answer);
    }
}
