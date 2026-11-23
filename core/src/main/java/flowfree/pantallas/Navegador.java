package flowfree.pantallas;

import com.badlogic.gdx.Game;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Navegador {
    private final Game juego;
    private final Deque<PantallaBase> pila = new ArrayDeque<>();

    public Navegador(Game juego) { this.juego = juego; }

    
    public void avanzar(PantallaBase p) {
        pila.push(p);
        juego.setScreen(p);
    }

    
    public void regresar() {
        if (pila.size() <= 1) return;
        PantallaBase sale = pila.pop();
        juego.setScreen(pila.peek());
        sale.dispose();
    }

    
    public void reiniciarCon(PantallaBase p) {
        List<PantallaBase> viejas = new ArrayList<>(pila);
        pila.clear();
        pila.push(p);
        juego.setScreen(p);
        for (PantallaBase v : viejas) v.dispose();
    }

    public boolean puedeRegresar() { return pila.size() > 1; }

    public void limpiar() {
        for (PantallaBase p : pila) p.dispose();
        pila.clear();
    }
}