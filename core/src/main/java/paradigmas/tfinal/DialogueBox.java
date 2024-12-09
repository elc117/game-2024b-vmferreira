package paradigmas.tfinal;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class DialogueBox {
  private ShapeRenderer shapeRenderer;
  private Vector2 min;
  private Vector2 size;
  public DialogueBox() {
    shapeRenderer = new ShapeRenderer();
    min = new Vector2(320-40, 240-40);
    size = new Vector2(80, 80);
  }

  public void draw() {
    shapeRenderer.begin(ShapeType.Filled);
    shapeRenderer.setColor(Color.WHITE);
    shapeRenderer.rect(min.x-2, min.y-2, size.x+4, size.y+4);
    shapeRenderer.end();
    shapeRenderer.begin(ShapeType.Line);
    shapeRenderer.setColor(Color.BLACK);
    shapeRenderer.rect(min.x, min.y, size.x, size.y);
    shapeRenderer.end();
  }
}
