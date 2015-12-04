package org.noip.andrewvasiliev.makemaximum.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RemoveActorAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.utils.Array;

import org.noip.andrewvasiliev.makemaximum.MakeMaximum;

import java.util.Random;

import javax.sound.midi.Sequence;

/**
 * Created by root on 06.11.15.
 */
public class GameBoardActor extends Actor implements InputProcessor{
    public static GameBoard gb;
    private TileActor[] tiles;
    public Group locGroup;
    private final  TextureRegion tileTexture;
    private Array<TextureRegion> tileTextureArr;
    private int locFieldSize; //размер игрового поля в клетках (поле всегда квадратное)

    private float width;    // width of one tile
    private float height;   // height of one tile
    private int selX;       // X index of selection
    private int selY;       // Y index of selection
    //ava beg
    private int offsetX;    //смещение по Х, для того чтобы поле было посередине
    private int offsetY;    //смещение по Y, для того чтобы поле было посередине
    private int fieldWidth; //ширина игрового поля в пикселях
    private int fieldHeight;//высота игрового поля в пикселях

    private int plr_coor[] = new int [2];   //координаты по Х для отображения имен игроков и заработанных очков
    private int plr_height; //высота текста имен игроков и заработанных очков
    private int plr_width;  //ширина полей для имен и очков

    public GameBoardActor (int FieldSize) {
        locFieldSize = FieldSize;
        CalculateTileParam(MakeMaximum.WIDTH, MakeMaximum.HEIGHT);

        locGroup = new Group();
        this.setTouchable(Touchable.enabled);
        locGroup.setTouchable(Touchable.enabled);

        tileTexture = new TextureRegion(new Texture("tile_100x50.png"));
        tileTextureArr = new Array<TextureRegion>();
        TextureRegion temp;
        int frameWidth = tileTexture.getRegionWidth() / 2;
        int frameCount = 2;
        for(int i = 0; i < frameCount; i++){
            temp = new TextureRegion(tileTexture, i * frameWidth, 0, frameWidth, tileTexture.getRegionHeight());
            tileTextureArr.add(temp);
        }



        gb = new GameBoard();
        gb.GameBoard(locFieldSize, null);

        tiles = new TileActor[locFieldSize * locFieldSize];

        //CalculateTileParam(MakeMaximum.WIDTH, MakeMaximum.HEIGHT);


        for (int y = 0; y< locFieldSize; y++) {
            for (int x = 0; x < locFieldSize; x++) {
                if (gb.getTile(x, y) == 0) {
                    continue;
                }
                tiles[y * locFieldSize + x] = new TileActor(tileTextureArr, gb.getTile(x,y), x, y);

                tiles[y * locFieldSize + x].setSize(width, height);

                tiles[y * locFieldSize + x].setPosition(offsetX + x * width, offsetY + y * height);

                // Set the name of the Jet to it's index within the loop
                tiles[y * locFieldSize + x].setName("tile" + Integer.toString(x) + Integer.toString(y));

                // Add them to the stage
                locGroup.addActor(tiles[y * locFieldSize + x]);
            }
        }

    }

    protected void CalculateTileParam(int w, int h) {
        //ava beg
        fieldWidth  = w < h ? w : h;
        fieldHeight = w < h ? w : h;


        offsetX = ((w - fieldWidth) / 2);
        offsetY = ((h - fieldHeight) / 2);

        width = fieldWidth / (float) locFieldSize;
        height = fieldHeight /  (float) locFieldSize;

        //имена игроков и заработанные очки
        plr_height = (int) height /2;
        if (offsetY < plr_height) {
            //альбом
            plr_width = (int)((w - fieldWidth) /2 );
            plr_coor[0] = 0;
            plr_coor[1] = w - plr_width;

        } else {
            //портрет
            plr_width = (int)(w /5 * 2);
            plr_coor[0] = 0;
            plr_coor[1] = w - plr_width;
        }
    }

    @Override
    public void draw(Batch batch, float alpha) {
        locGroup.act(Gdx.graphics.getDeltaTime());
        locGroup.draw(batch, alpha);
    }


    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 coord = this.screenToLocalCoordinates(new Vector2((float) screenX, (float) screenY));
        TileActor temp = (TileActor) locGroup.hit(coord.x, coord.y, true);
        if (temp != null) {
            temp.touch = true;


            MoveToAction moveAction = new MoveToAction();

            moveAction.setPosition(plr_coor[gb.current_player], 0f);
            moveAction.setDuration(0.3f);

            RemoveActorAction removeActor = new RemoveActorAction();

            SequenceAction mySequence = new SequenceAction(moveAction, removeActor);

            temp.addAction(mySequence);
            //temp.addAction(removeActor);

            //locGroup.removeActor(temp);
            //MakeMaximum.multiplexer.removeProcessor(temp);


            return true;
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Vector2 coord = this.screenToLocalCoordinates(new Vector2((float) screenX, (float) screenY));
        TileActor temp = (TileActor) locGroup.hit(coord.x, coord.y, true);
        if (temp != null) {
            temp.touch = false;

            return true;
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    public int getPlayerTableXCoord (int i){
        return plr_coor[i];
    }

  /*  public int getPlayerTableGlobalXMidCoord (int i){
        Vector2 coord = this.screenToLocalCoordinates(new Vector2((float) plr_coor[i], (float) 0));
        return (int)coord.x + plr_width/2 ;
    }

    public Vector2 getPlayerTableGlobalVectorMidCoord (int i){
        Vector2 coord = this.screenToLocalCoordinates(new Vector2((float) plr_coor[i], (float) 0));
        coord.x += (plr_width - width)/2 ;

        return coord;
    }
*/
    public int getPlayerTableWidth (){
        return plr_width;
    }

    public float getTileWidth (){
        return width;
    }

}
