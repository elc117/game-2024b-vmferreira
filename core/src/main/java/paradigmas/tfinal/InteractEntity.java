package paradigmas.tfinal;

import paradigmas.tfinal.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap;

public class InteractEntity extends Entity {
  private Vector2 interactOffset;
  private Vector2 boundsMin;

  public InteractEntity(float x, float y, DialogueBox dBox) {
    super(dBox);
    position.set(x, y);
    interactOffset = new Vector2(16, -4);
    texture = new Texture("tree.png");
    size = new Vector2(texture.getWidth(), texture.getHeight());
  }

  public Vector2 getInteractPos() {
    return new Vector2(position).add(interactOffset);
  }

  public boolean mouseOver(float mouseX, float mouseY) {
    float dx = mouseX - (position.x);
    float dy = mouseY - (position.y);
    // Checks if the mouse is inside the entity's bounds
    if (dx < 0 || dx > size.x) return false;
    if (dy < 0 || dy > size.y) return false;
    // Checks if the exact pixel at the mouse position is not transparent
    if (!texture.getTextureData().isPrepared()) {
      texture.getTextureData().prepare();
    }
    Pixmap pixmap = texture.getTextureData().consumePixmap();
    int tx = (int)dx;
    int ty = (int)dy;
    return (pixmap.getPixel(tx, ty) & 0xff) == 0xff;
  }

  public void interact() {
    dialogueBox.setText("This is a tree");
  }
}
