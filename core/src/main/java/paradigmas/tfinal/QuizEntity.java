package paradigmas.tfinal;

import paradigmas.tfinal.InteractEntity;
import paradigmas.tfinal.Quiz;
import com.badlogic.gdx.graphics.Texture;

public class QuizEntity extends InteractEntity {
  Quiz quiz;
  public QuizEntity(float x, float y, DialogueBox dBox, Quiz quiz) {
    super(x, y, dBox, "quizStar.png");
    this.quiz = quiz;
  }
  public void interact() {
    dialogueBox.setQuiz(quiz);
  }
}
