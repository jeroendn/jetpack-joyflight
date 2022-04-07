package com.github.jetpackjoyflight;

import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.YaegerGame;
import com.github.jetpackjoyflight.scenes.GameLevel;
import com.github.jetpackjoyflight.scenes.GameOverScene;
import com.github.jetpackjoyflight.scenes.TitleScene;

public class Main extends YaegerGame {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void setupGame() {
        setGameTitle("Jetpack Joyflight");
        setSize(new Size(1200, 600));
    }

    @Override
    public void setupScenes() {
        addScene(0, new TitleScene(this));
        addScene(1, new GameLevel(this));
        addScene(2, new GameOverScene(this));
    }
}
