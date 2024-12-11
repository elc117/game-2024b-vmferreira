package paradigmas.tfinal;

import paradigmas.tfinal.Entity;
import paradigmas.tfinal.InteractEntity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class PlayerEntity extends Entity {
  private Vector2 movePos;
  private final float SPEED = 2 * 48.0f;
  private InteractEntity targetEntity;
  private boolean flip;
  private boolean moving;
  private float animTime;
  private TextureRegion region;

  public PlayerEntity() {
    position = new Vector2(0, 0);
    movePos = new Vector2(0,0);
    texture = new Texture("player-sheet.png");
    size = new Vector2(32, 37);
    moving = false;
    animTime = 0.0f;
    region = new TextureRegion(texture, 0,0,32,27);
  }
  
  @Override
  public void update(float dt) {
    Vector2 delta = new Vector2(movePos).sub(position);
    if (moving) {
      animTime += dt;
      int walkFrame = 1 + (((int)(animTime * 10.0)) % 3);
      region.setRegion(walkFrame * 32, 0, 32, 37);
      float dist = delta.len();
      // if player is close to the target position, stop moving and interact with the entity
      if (dist < 1.0f) {
        moving = false;
        if (targetEntity != null) {
          targetEntity.interact();
          targetEntity = null;
        }
      }
      // Move the player to the clicked position
      if (dist > (dt * SPEED)) {
        delta.nor().scl(dt * SPEED);
      } else {
        delta.nor().scl(dist);
      }
      position.add(delta);
      if (delta.x < 0) flip = true;
      if (delta.x > 0) flip = false;
    } else {
      region.setRegion(0, 0, 32, 37);
    }
    region.flip(flip, false);
  }

  @Override
  public void draw(SpriteBatch batch) {
    float x = position.x - size.x / 2.0f;
    float y = position.y;
    batch.draw(region, x, y);
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
