package paradigmas.tfinal;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.math.Vector2;
import paradigmas.tfinal.PlayerEntity;
import paradigmas.tfinal.InteractEntity;
import paradigmas.tfinal.QuizEntity;
import paradigmas.tfinal.Quiz;
import paradigmas.tfinal.DialogueBox;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter implements InputProcessor{
  private SpriteBatch batch;
  private Texture image;
  private FitViewport view;
  private DialogueBox dialogueBox;

  PlayerEntity player;
  InteractEntity tree;

  private void update(float dt) {
    player.update(dt);
  }
  private void draw() {
    ScreenUtils.clear(0.40f, 0.15f, 0.35f, 1f);
    view.apply();
    batch.setProjectionMatrix(view.getCamera().combined);
    batch.begin();
    tree.draw(batch);
    player.draw(batch);
    batch.end();

    dialogueBox.draw(view.getCamera().combined);
  }


  @Override
  public void create() {
    batch = new SpriteBatch();
    image = new Texture("libgdx.png");
    Gdx.graphics.setWindowedMode(640, 480);
    player = new PlayerEntity();
    view = new FitViewport(640, 480);
    dialogueBox = new DialogueBox();
    dialogueBox.setText("Hello, World!\nDialogue Box Test");
    Gdx.input.setInputProcessor(this);
    String[] answers = {"1", "2", "3", "4"};

    Quiz quiz = new Quiz("What is 1 + 1\nextra text to make this\n quiz way bigger", answers, 2);
    tree = new QuizEntity(400, 200, dialogueBox, quiz);
  }
  
  @Override
  public void resize(int width, int height) {
      view.update(width, height, true);
  }

  @Override
  public void render() {
    float dt = Gdx.graphics.getDeltaTime();
    update(dt);
    draw();
  }

  @Override
  public void dispose() {
    batch.dispose();
    image.dispose();
  }

  @Override
  public boolean touchDown(int screenX, int screenY, int ptr, int btn) {
    Vector2 pos = new Vector2((float)screenX, (float)screenY);
    view.unproject(pos);
    if (dialogueBox.isEnabled()) {
      dialogueBox.interact(pos.x, pos.y);
      return true;
    }
    if (tree.mouseOver(pos.x, pos.y)) {
      player.onClick(tree);
    } else {
      player.onClick(pos.x, pos.y);
    }
    return true;
  }



// Unused input handling methods. Need to be implemented due to the InputProcessor interface
  @Override public boolean mouseMoved (int screenX, int screenY) {
    return false;
  }

  @Override public boolean touchDragged (int screenX, int screenY, int pointer) {
    return true;
  }

  @Override public boolean touchUp (int screenX, int screenY, int pointer, int button) {
    return true;
  }
  @Override public boolean touchCancelled (int screenX, int screenY, int pointer, int button) {
    return true;
  }

  
  @Override public boolean keyDown (int keycode) {
    return false;
  }

  @Override public boolean keyUp (int keycode) {
    return false;
  }

  @Override public boolean keyTyped (char character) {
    return false;
  }

  @Override public boolean scrolled (float a, float b) {
    return false;
  }
}
