package paradigmas.tfinal;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Matrix4;
// Seria mais Orientado a Objetos se DialogueBox fosse
// dividida em classes QuizBox e TextBox. Porém a funcionalidade
// de TextBox seria mínima, e seria necessário uma constante
// criação e destruição de objetos na classe Main, ou
// dois objetos que servem a mesma funcionalidade, um que roda apenas
// quando o jogo estiver em um quiz, e um que roda apenas quando o jogo
// estiver em uma caixa de informação. Portanto, acho que nesse caso
// simplesmente implementar as duas funcionalidades como estados diferentes
// de uma única classe torna o código mais sucinto.

public class DialogueBox {
  private final int STATE_DISABLED = 0;
  private final int STATE_TEXT = 1;
  private final int STATE_QUIZ = 2;
  private final float SCREEN_CENTER_X = 320.0f;
  private final float SCREEN_CENTER_Y = 240.0f;

  private ShapeRenderer shapeRenderer;
  private Vector2 min;
  private Vector2 size;
  private BitmapFont font;
  private GlyphLayout layout;
  private SpriteBatch batch;
  private int state;
  private Quiz quiz;
  
  public DialogueBox() {
    shapeRenderer = new ShapeRenderer();
    min = new Vector2(320-40, 240-40);
    size = new Vector2(80, 80);
    font = new BitmapFont();
    font.setColor(Color.BLACK);
    layout = new GlyphLayout();
    batch = new SpriteBatch();
    state = STATE_DISABLED;
  }

  public void setText(String text) {
    layout.setText(font, text);
    size.set(layout.width + 8, layout.height + 8);
    min.set(SCREEN_CENTER_X - layout.width/2.0f - 4, SCREEN_CENTER_Y - layout.height/2.0f + 4);
    state = STATE_TEXT;
  }

  public void setQuiz(Quiz quiz) {
    this.quiz = quiz;
    state = STATE_QUIZ;
  }

  public void drawText(Matrix4 proj) {
    float x = min.x;
    float y = min.y - size.y;
    shapeRenderer.setProjectionMatrix(proj);
    shapeRenderer.begin(ShapeType.Filled);
    shapeRenderer.setColor(Color.WHITE);
    shapeRenderer.rect(x-2, y-2, size.x+4, size.y+4);
    shapeRenderer.end();
    shapeRenderer.begin(ShapeType.Line);
    shapeRenderer.setColor(Color.BLACK);
    shapeRenderer.rect(x, y, size.x, size.y);
    shapeRenderer.end();

    batch.setProjectionMatrix(proj);
    batch.begin();
    font.draw(batch, layout, min.x+3, min.y-2);
    batch.end();
  }

  public void drawQuiz(Matrix4 proj) {
    layout.setText(font, quiz.getQuestion());
    float w = layout.width;
    float h = layout.height;
    float padding = 8;
    float voff = 64;
    float x = SCREEN_CENTER_X - w/2.0f - padding/2.0f;
    float y = SCREEN_CENTER_Y - h/2.0f + padding/2.0f - 32.0f;
    w += padding;
    h += padding;
    shapeRenderer.setProjectionMatrix(proj);
    shapeRenderer.begin(ShapeType.Filled);
    shapeRenderer.setColor(Color.WHITE);
    shapeRenderer.rect(x-2, y-2 + 80, w+4, h+4);
    for (int i = 0; i < 4; i++) {
      shapeRenderer.rect(x-2, y -2 + 20*i, w+4, 20);
    }
    shapeRenderer.end();
    shapeRenderer.begin(ShapeType.Line);
    shapeRenderer.setColor(Color.BLACK);
    shapeRenderer.rect(x, y + 80, w, h);
    for (int i = 0; i < 4; i++) {
      shapeRenderer.rect(x, y + 20*i, w, 16);
    }
    shapeRenderer.end();

    batch.setProjectionMatrix(proj);
    batch.begin();
    font.draw(batch, layout, x + 3, y + h - 2 + 80);
    for (int i = 0; i < 4; i++) {
      layout.setText(font, quiz.getAnswer(i));
      font.draw(batch, layout, x + 3, y + 16 - 4 + (i * 20));
    }
    batch.end();
  }

  public void draw(Matrix4 proj) {
    switch (state) {
      case STATE_TEXT:
        drawText(proj);
        break;
      case STATE_QUIZ:
        drawQuiz(proj);
        break;
      default:
        return;
    }
  }

  public boolean isEnabled() {
    return state != STATE_DISABLED;
  }

  public void disable() {
    state = STATE_DISABLED;
  }

  public void interact(float mx, float my) {
    if (state == STATE_TEXT) {
      disable();
      return;
    }
    if (state == STATE_QUIZ) {
      disable();
      return;
    }
  }
}
