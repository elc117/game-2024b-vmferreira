package paradigmas.tfinal;

import paradigmas.tfinal.Entity;
import com.badlogic.gdx.graphics.Texture;

public class PlayerEntity extends Entity {
  private float moveX = 0, moveY = 0;
  private final float SPEED = 24.0f;

  public PlayerEntity() {
    x = 0;
    y = 0;
    texture = new Texture("player.png");
  }
  
  @Override
  public void update(float dt) {
    float dx = moveX - x;
    float dy = moveY - y;
    // Normalize the direction vector
    float len = dx * dx + dy * dy;
    if (len > 0.0f) {
      len = (float)Math.sqrt(len);
      dx /= len;
      dy /= len;
    }
    x += dx * dt * SPEED;
    y += dy * dt * SPEED;
  }

  public void onClick(float mouseX, float mouseY) {
    moveX = mouseX;
    moveY = mouseY;
    return;
  }
}
