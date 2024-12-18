package paradigmas.tfinal;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Entity {
  protected Vector2 position;
  protected Texture texture;
  protected DialogueBox dialogueBox;
  protected Vector2 size;
  protected boolean enabled;

  public Entity(DialogueBox dBox, String t) {
    position = new Vector2(0, 0);
    texture = new Texture(t);
    dialogueBox = dBox;
    size = new Vector2(texture.getWidth(), texture.getHeight());
    enabled = true;
  }
  public Entity() {
    position = new Vector2(0, 0);
    texture = new Texture("libgdx.png");
    size = new Vector2(32, 32);
  }

  public Vector2 getPos() {
    return position;
  }

  public void update(float dt) {
    return;
  }

  public void disable() {
    enabled = false;
  }

  public void setPos(float x, float y) {
    position.set(x, y);
  }

  public void draw(SpriteBatch batch) {
    if (!enabled) return;
    float x = position.x - size.x / 2.0f;
    float y = position.y;
    batch.draw(texture, x, y);
  }
}
