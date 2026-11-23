package flowfree;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import flowfree.pantallas.Navegador;
import flowfree.pantallas.PantallaPrueba;
import flowfree.util.Idiomas;

public class Main extends Game {

    public Skin skin;
    public Navegador nav;

    @Override
    public void create() {
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        Idiomas.cargar("es");
        nav = new Navegador(this);
        nav.avanzar(new PantallaPrueba(this, 1));
    }

    @Override
    public void dispose() {
        super.dispose();
        nav.limpiar();
        skin.dispose();
    }
}
