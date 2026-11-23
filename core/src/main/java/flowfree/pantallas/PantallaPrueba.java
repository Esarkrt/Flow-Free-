package flowfree.pantallas;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import flowfree.Main;
import flowfree.util.Idiomas;
import static flowfree.util.Idiomas.t;


public class PantallaPrueba extends PantallaBase {

    private final int numero;

    public PantallaPrueba(Main juego, int numero) {
        super(juego);
        this.numero = numero;
    }

    @Override
    protected void construirUI() {
        Table raiz = new Table();
        raiz.setFillParent(true);
        raiz.add(crearTitulo(t("prueba.titulo") + " #" + numero)).padBottom(30).row();
        raiz.add(crearBoton(t("prueba.avanzar"),
                () -> juego.nav.avanzar(new PantallaPrueba(juego, numero + 1)))).width(300).pad(5).row();
        raiz.add(crearBoton(t("prueba.regresar"), () -> juego.nav.regresar())).width(300).pad(5).row();
        raiz.add(crearBoton(t("prueba.idioma"), () -> {
            Idiomas.cargar(Idiomas.getCodigo().equals("es") ? "en" : "es");
            reconstruir();
        })).width(300).pad(5);
        stage.addActor(raiz);
    }
}
