package flowfree.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import flowfree.Main;
import flowfree.util.Constantes;

public abstract class PantallaBase implements Screen {

    protected final Main juego;
    protected final Skin skin;
    protected final Stage stage;

    protected PantallaBase(Main juego) {
        this.juego = juego;
        this.skin = juego.skin;
        this.stage = new Stage(new FitViewport(Constantes.ANCHO, Constantes.ALTO));
    }

    protected abstract void construirUI();

    protected void reconstruir() {
        stage.clear();
        construirUI();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        reconstruir();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Constantes.COLOR_FONDO);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int w, int h) {
        stage.getViewport().update(w, h, true);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    protected Label crearTitulo(String texto) {
        Label l = new Label(texto, skin);
        l.setFontScale(1.8f);
        return l;
    }

    protected TextButton crearBoton(String texto, Runnable accion) {
        TextButton b = new TextButton(texto, skin);
        b.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                accion.run();
            }
        });
        return b;
    }
}
