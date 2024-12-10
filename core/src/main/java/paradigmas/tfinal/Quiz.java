package paradigmas.tfinal;

public class Quiz {
  private String question;
  private String[] answers;
  private int correctAnswer;

  public Quiz(String q, String[] a, int c) {
    question = q;
    answers = a;
    correctAnswer = c;
  }

  public String getQuestion() {
    return question;
  }
  public String getAnswer(int ind) {
    return answers[ind];
  }

  public boolean checkAnswer(int answer) {
    return answer == correctAnswer;
  }

}
