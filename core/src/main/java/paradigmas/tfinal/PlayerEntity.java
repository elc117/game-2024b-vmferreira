package paradigmas.tfinal;

import paradigmas.tfinal.Entity;
import paradigmas.tfinal.InteractEntity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class PlayerEntity extends Entity {
  private Vector2 movePos;
  private final float SPEED = 8 * 48.0f;
  private InteractEntity targetEntity;
  private boolean moving;

  public PlayerEntity() {
    position = new Vector2(0, 0);
    movePos = new Vector2(0,0);
    texture = new Texture("player.png");
    size = new Vector2(texture.getWidth(), texture.getHeight());
    moving = false;
  }
  
  @Override
  public void update(float dt) {
    Vector2 delta = new Vector2(movePos).sub(position);
    if (moving) {
      // if player is close to the target position, stop moving and interact with the entity
      if (delta.len2() < 8.0f) {
        moving = false;
        if (targetEntity != null) {
          targetEntity.interact();
          targetEntity = null;
        }
      }
      // Move the player to the clicked position
      delta.nor().scl(dt * SPEED);
      position.add(delta);
    }

  }

  public void onClick(float mouseX, float mouseY) {
    movePos.set(mouseX, mouseY);
    targetEntity = null;
    moving = true;
    return;
  }

  public void onClick(InteractEntity tEntity) {
    movePos.set(tEntity.getInteractPos());
    targetEntity = tEntity;
    moving = true;
    return;
  }
}
