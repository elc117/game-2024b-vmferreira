package paradigmas.tfinal;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;

public class Entity {
  protected float x, y;
  protected Texture texture;

  public Entity() {
    x = 320;
    y = 240;
    texture = new Texture("libgdx.png");
  }

  public void update(float dt) {
    return;
  }
  public void draw(SpriteBatch batch) {
    batch.draw(texture, x, y);
  }
}
