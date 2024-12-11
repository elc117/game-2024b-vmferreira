package paradigmas.tfinal;

import paradigmas.tfinal.InteractEntity;

public class InfoEntity extends InteractEntity {
  String message;
  public InfoEntity(float x, float y, DialogueBox dBox,String message, String t) {
    super(x, y, dBox, t);
    this.message = message;
  }
  public void interact() {
    dialogueBox.setText(message);
  }
}
