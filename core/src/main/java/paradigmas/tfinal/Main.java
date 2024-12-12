package paradigmas.tfinal;

import java.util.ArrayList;
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
import com.badlogic.gdx.files.FileHandle;
import paradigmas.tfinal.PlayerEntity;
import paradigmas.tfinal.InteractEntity;
import paradigmas.tfinal.QuizEntity;
import paradigmas.tfinal.InfoEntity;
import paradigmas.tfinal.Quiz;
import paradigmas.tfinal.DialogueBox;
import java.util.Collections;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter implements InputProcessor{
  private SpriteBatch batch;
  private FitViewport view;
  private DialogueBox dialogueBox;
  private Vector2 touchPos;
  private Room room;
  private Room[] rooms;
  private Texture telhadoVerdeBG;
  private boolean done;
  private PlayerEntity player;
  private int nQuizesDone = 0;

  private void update(float dt) {
    if (done) return;
    player.update(dt);
  }
  private void draw() {
    if (done) {
      ScreenUtils.clear(0.40f, 0.15f, 0.35f, 1f);
      view.apply();
      batch.setProjectionMatrix(view.getCamera().combined);
      batch.begin();
      batch.draw(telhadoVerdeBG, 0, 0, 640, 480);
      batch.end();
      dialogueBox.draw(view.getCamera().combined);
      return;
    }
    ScreenUtils.clear(0.40f, 0.15f, 0.35f, 1f);
    view.apply();
    batch.setProjectionMatrix(view.getCamera().combined);
    batch.begin();
    room.draw(batch);
    player.draw(batch);
    batch.end();

    dialogueBox.draw(view.getCamera().combined);
  }


  @Override
  public void create() {
    done = false;
    batch = new SpriteBatch();
    player = new PlayerEntity();
    view = new FitViewport(640, 480);
    touchPos = new Vector2(0, 0);
    telhadoVerdeBG = new Texture("backgrounds/telhadoVerde.png");
    dialogueBox = new DialogueBox();
    dialogueBox.setText("Bem Vindo a\nJardim Botânico Quest\nResponda todos os quizes\npara poder subir\nao Telhado Verde");
    Gdx.input.setInputProcessor(this);

    FileHandle handle = Gdx.files.internal("quizes.txt");
    String[] lines = handle.readString().split("\n");
    ArrayList<Quiz> quizes = new ArrayList<Quiz>();
    for (String line : lines) {
      String[] tokens = line.split(";");
      String[] answers = new String[4];
      String question = tokens[0].replace('^', '\n');
      int answer = Integer.parseInt(tokens[5]);
      answers[0] = tokens[1];
      answers[1] = tokens[2];
      answers[2] = tokens[3];
      answers[3] = tokens[4];
      quizes.add(new Quiz(question, answers, answer));
    }
    Collections.shuffle(quizes);
    rooms = new Room[5];
    rooms[0] = new Room(quizes, this, dialogueBox, "rooms/room1.txt");
    rooms[1] = new Room(quizes, this, dialogueBox, "rooms/room2.txt");
    rooms[2] = new Room(quizes, this, dialogueBox, "rooms/room3.txt");
    rooms[3] = new Room(quizes, this, dialogueBox, "rooms/room4.txt");
    rooms[4] = new Room(quizes, this, dialogueBox, "rooms/room5.txt");
    room = rooms[0];
  }

  public void travel(int r, float nx, float ny) {
    room = rooms[r];
    player.setPos(nx, 480-ny);
    if (nQuizesDone == 5) {
      dialogueBox.setText("Você terminou Jardim Botânico Quest!");
      done = true;
    }
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
  }

  @Override
  public boolean touchDown(int screenX, int screenY, int ptr, int btn) {
    touchPos.set((float)screenX, (float)screenY);
    view.unproject(touchPos);
    if (dialogueBox.isEnabled()) {
      if (dialogueBox.interact(touchPos.x, touchPos.y)) {
        nQuizesDone++;
        if (nQuizesDone == 5) {
          dialogueBox.setText("Você respondeu todos os quizes!");
        }
      }
      return true;
    }
    room.onClick(player, touchPos.x, touchPos.y);
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
