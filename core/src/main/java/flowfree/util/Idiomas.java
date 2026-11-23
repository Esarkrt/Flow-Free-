package flowfree.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.I18NBundle;
import java.util.Locale;

public final class Idiomas {

    private static I18NBundle bundle;
    private static String codigo = "es";

    private Idiomas() {
    }

    public static void cargar(String cod) {
        codigo = cod;
        bundle = I18NBundle.createBundle(Gdx.files.internal("i18n/textos"), new Locale(cod));
    }

    public static String t(String clave) {
        return bundle.get(clave);
    }

    public static String t(String clave, Object... args) {
        return bundle.format(clave, args);
    }

    public static String getCodigo() {
        return codigo;
    }
}
