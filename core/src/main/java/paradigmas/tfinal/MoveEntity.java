package paradigmas.tfinal;

import paradigmas.tfinal.InteractEntity;
import paradigmas.tfinal.Main;

public class MoveEntity extends InteractEntity {
  private int target;
  private float tx, ty;
  private Main mainGame;
  public MoveEntity(float x, float y, DialogueBox dBox, int target, Main mainGame, float tx, float ty) {
    super(x, y, dBox, "move.png");
    this.target = target;
    this.mainGame = mainGame;
    this.tx = tx;
    this.ty = ty;
  }
  public void interact() {
    mainGame.travel(target, tx, ty);
  }
}
