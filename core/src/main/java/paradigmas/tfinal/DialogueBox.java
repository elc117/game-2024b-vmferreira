package paradigmas.tfinal;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class DialogueBox {
  private ShapeRenderer shapeRenderer;
  private Vector2 min;
  private Vector2 size;
  private BitmapFont font;
  private GlyphLayout layout;
  private SpriteBatch batch;
  private final float SCREEN_CENTER_X = 320.0f;
  private final float SCREEN_CENTER_Y = 240.0f;
  private boolean enabled;
  
  public DialogueBox() {
    shapeRenderer = new ShapeRenderer();
    min = new Vector2(320-40, 240-40);
    size = new Vector2(80, 80);
    font = new BitmapFont();
    font.setColor(Color.BLACK);
    layout = new GlyphLayout();
    batch = new SpriteBatch();
    enabled = false;
  }

  public void setText(String text) {
    layout.setText(font, text);
    size.set(layout.width + 8, layout.height + 8);
    min.set(SCREEN_CENTER_X - layout.width/2.0f - 4, SCREEN_CENTER_Y - layout.height/2.0f + 4);
    enabled = true;
  }

  public void draw() {
    if (!enabled) {
      return;
    }
    float x = min.x;
    float y = min.y - size.y;
    shapeRenderer.begin(ShapeType.Filled);
    shapeRenderer.setColor(Color.WHITE);
    shapeRenderer.rect(x-2, y-2, size.x+4, size.y+4);
    shapeRenderer.end();
    shapeRenderer.begin(ShapeType.Line);
    shapeRenderer.setColor(Color.BLACK);
    shapeRenderer.rect(x, y, size.x, size.y);
    shapeRenderer.end();

    batch.begin();
    font.draw(batch, layout, min.x+4, min.y-2);
    batch.end();
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void disable() {
    enabled = false;
  }
}
