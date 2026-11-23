package flowfree.persistencia;

import com.badlogic.gdx.Gdx;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public final class GestorArchivos {
    private GestorArchivos() {}

    public static boolean guardar(Serializable objeto, File destino) {
        try {
            File padre = destino.getParentFile();
            if (padre != null) padre.mkdirs();
            File tmp = new File(destino.getPath() + ".tmp");
            try (ObjectOutputStream out = new ObjectOutputStream(
                    new BufferedOutputStream(new FileOutputStream(tmp)))) {
                out.writeObject(objeto);
            }
            Files.move(tmp.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException e) {
            Gdx.app.error("Archivos", "No se pudo guardar " + destino, e);
            return false;
        }
    }

    
    public static <T> T cargar(File origen, Class<T> tipo) {
        if (!origen.exists()) return null;
        try (ObjectInputStream in = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(origen)))) {
            return tipo.cast(in.readObject());
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            Gdx.app.error("Archivos", "Archivo dañado: " + origen, e);
            return null;
        }
    }

    
    public static void borrarRecursivo(File f) {
        if (f == null || !f.exists()) return;          
        if (f.isDirectory()) {
            File[] hijos = f.listFiles();
            if (hijos != null) {
                for (File h : hijos) borrarRecursivo(h); 
            }
        }
        f.delete();                                      
    }
}