package paradigmas.tfinal;

import paradigmas.tfinal.Entity;
import com.badlogic.gdx.math.Vector2;

public class InteractEntity extends Entity {
  private Vector2 interactOffset;

  public Vector2 getInteractPos() {
    return new Vector2(position).add(interactOffset);
  }

  public void interact() {
  }
}
