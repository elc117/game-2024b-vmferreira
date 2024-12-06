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
import paradigmas.tfinal.PlayerEntity;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter implements InputProcessor{
  private SpriteBatch batch;
  private Texture image;
  private FitViewport view;

  PlayerEntity player;

  private void update(float dt) {
    if (Gdx.input.isTouched()) {
      System.out.println(Gdx.input.getX());
      System.out.println(Gdx.input.getY());
    }

    player.update(dt);
  }
  private void draw() {
    ScreenUtils.clear(0.80f, 0.15f, 0.75f, 1f);
    //view.apply();
    //batch.setProjectionMatrix(view.getCamera().combined);
    batch.begin();
    player.draw(batch);
    batch.end();
  }


  @Override
  public void create() {
    batch = new SpriteBatch();
    image = new Texture("libgdx.png");
    Gdx.graphics.setWindowedMode(640, 480);
    player = new PlayerEntity();
    view = new FitViewport(640, 480);
    Gdx.input.setInputProcessor(this);
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
    player.onClick((float)screenX, 480.0f - (float)screenY);
    return true;
  }


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
