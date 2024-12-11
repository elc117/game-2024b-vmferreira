package paradigmas.tfinal;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import paradigmas.tfinal.InteractEntity;
import paradigmas.tfinal.QuizEntity;
import paradigmas.tfinal.InfoEntity;
import paradigmas.tfinal.Main;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;

public class Room {
  private ArrayList<InteractEntity> entities;
  private Texture background;
  private Texture walk_mask;

  public Room(ArrayList<Quiz> quizes, DialogueBox dBox, String roomDefFile) {
    String bgName = "";
    String bgMaskName = "";
    ArrayList<Integer> entitiesX = new ArrayList<Integer>();
    ArrayList<Integer> entitiesY = new ArrayList<Integer>();
    ArrayList<String> entitiesTextures = new ArrayList<String>();
    ArrayList<String> entitiesData = new ArrayList<String>(); // Message for Info, Quiz filename for Quiz
    ArrayList<Integer> entityTypes = new ArrayList<Integer>(); // 0 for Info, 1 for Quiz
    int numEntities = 0;

    // https://stackoverflow.com/questions/4716503/reading-a-plain-text-file-in-java
    try {
      BufferedReader br = new BufferedReader(new FileReader(roomDefFile));
      String line = br.readLine();
      while (line != null) {
        String[] tokens = line.split(";");
        if (tokens[0].equals("BACKGROUND")) {
          bgName = tokens[1];
          bgMaskName = tokens[2];
        } else if (tokens[0].equals("Info")) {
          entitiesX.add(Integer.parseInt(tokens[1]));
          entitiesY.add(Integer.parseInt(tokens[2]));
          entitiesTextures.add(tokens[4]);
          entitiesData.add(tokens[3].replace('^', '\n'));
          entityTypes.add(0);
          numEntities++;
          System.out.println("info");
        } else if (tokens[0].equals("Quiz")) {
          entitiesX.add(Integer.parseInt(tokens[1]));
          entitiesY.add(Integer.parseInt(tokens[2]));
          entitiesTextures.add("");
          entitiesData.add("");
          entityTypes.add(1);
          numEntities++;
        } else if (tokens[0].equals("Move")) {
          entitiesX.add(Integer.parseInt(tokens[1]));
          entitiesY.add(Integer.parseInt(tokens[2]));
          entitiesTextures.add("");
          entitiesData.add(tokens[3]);
          entityTypes.add(2);
          numEntities++;
        }
        line = br.readLine();
      }
      br.close();
    } catch (Exception e) {
      System.err.println(e);
    }
    background = new Texture(bgName);
    walk_mask = new Texture(bgMaskName);
    entities = new ArrayList<InteractEntity>();

    int quizNum = 0;
    for (int i = 0; i < numEntities; i++) {
      InteractEntity e;
      float x = (float)entitiesX.get(i);
      float y = 480.0f - (float)entitiesY.get(i);
      if (entityTypes.get(i) == 0) {
        e = new InfoEntity(x, y, dBox, entitiesData.get(i), entitiesTextures.get(i));
      } else if (entityTypes.get(i) == 1) {
        e = new QuizEntity(x, y, dBox, quizes.get(quizNum));
        quizNum++;
      } else {
        continue;
      }
      entities.add(e);
    }
  }

  public void draw(SpriteBatch batch) {
    batch.draw(background, 0, 0, 640, 480);
    for (InteractEntity e : entities) {
      e.draw(batch);
    }
  }

  public void onClick(PlayerEntity player, float mx, float my) {
    for (InteractEntity e : entities) {
      if (e.mouseOver(mx, my)) {
        player.onClick(e);
        return;
      }
    }
    if (!walk_mask.getTextureData().isPrepared()) {
      walk_mask.getTextureData().prepare();
    }
    Pixmap pixmap = walk_mask.getTextureData().consumePixmap();
    int tx = (int)mx;
    int ty = 480 - (int)my;
    if (pixmap.getPixel(tx, ty) == 0xffffffff ) {
      player.onClick(mx, my);
    }
  }
}
