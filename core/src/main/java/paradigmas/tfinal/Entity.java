package paradigmas.tfinal;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;

public class Entity {
  protected float x, y;
  private Texture texture; 

  public void update(float dt) {
    return;
  }
  public void draw(SpriteBatch batch) {
    batch.draw(texture, x, y);
  }
}
