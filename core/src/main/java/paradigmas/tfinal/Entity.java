package paradigmas.tfinal;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Entity {
  protected Vector2 position;
  protected Texture texture;

  public Entity() {
    position = new Vector2(0, 0);
    texture = new Texture("libgdx.png");
  }

  public Vector2 getPos() {
    return position;
  }

  public void update(float dt) {
    return;
  }

  public void draw(SpriteBatch batch) {
    batch.draw(texture, position.x, position.y);
  }
}
