package org.scify.libgdxgame.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import org.scify.libgdxgame.game.sprites.StaticSprite;
import org.scify.libgdxgame.helpers.GameInfo;

public class CloudsController {

    private World world;
    private Array<StaticSprite> clouds = new Array<StaticSprite>();
    private final float DISTANCE_BETWEEN_CLOUDS = 250f;

    public CloudsController(World world) {
        this.world = world;
    }

    public void createClouds() {
        for(int i = 0; i < 2; i ++) {
            clouds.add(new StaticSprite(world, "dark_cloud_" + i, "Clouds/Dark Cloud.png", GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f));
        }

        int index = 1;
        for(int i = 0; i < 6; i ++) {
            clouds.add(new StaticSprite(world, "cloud_" + i, "Clouds/Cloud " + index + ".png", GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f));
            index++;
            if(index == 4)
                index = 1;
        }

        clouds.shuffle();
        positionClouds();
    }

    public void positionClouds() {
        float tempX = GameInfo.HEIGHT / 2f;
        float positionY = GameInfo.WIDTH / 2f;

        for(StaticSprite cloud : clouds) {
            cloud.setSpritePosition(tempX, positionY);
            positionY -= DISTANCE_BETWEEN_CLOUDS;
        }
    }

    public void drawClouds(SpriteBatch batch) {
        for(StaticSprite cloud : clouds) {
            batch.draw(cloud, cloud.getX() - cloud.getWidth() / 2f, cloud.getY() - cloud.getHeight() / 2f);
        }
    }
}