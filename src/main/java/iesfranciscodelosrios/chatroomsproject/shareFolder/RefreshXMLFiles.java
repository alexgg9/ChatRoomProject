package iesfranciscodelosrios.chatroomsproject.shareFolder;
import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static iesfranciscodelosrios.chatroomsproject.shareFolder.ConfigManager.readSharedFolderPath;

public class RefreshXMLFiles {

    // Ruta de la carpeta para refrescar los archivos XML
    private String folderPath = readSharedFolderPath();
    // Intervalo de refresco en segundos
    private long refreshIntervalSec = 10;

    // Constructor para inicializar la ruta de la carpeta y el intervalo de refresco
    public RefreshXMLFiles(String folderPath, long refreshIntervalSec) {
        this.folderPath = folderPath;
        this.refreshIntervalSec = refreshIntervalSec;
    }

    /** Método para iniciar el refresco periódico
     * de los archivos XML
     * *******************************************/
    public void startRefreshing() {
        // Crear un executor para programar la tarea de refresco a intervalos regulares
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(() -> {
            refreshXMLFiles();
        }, 0, refreshIntervalSec, TimeUnit.SECONDS);
    }

    /** Método para refrescar los archivos XML
     * en la carpeta especificada
     * ***************************************/
    private void refreshXMLFiles() {
        File folder = new File(folderPath);
        // Obtener la carpeta a partir de la ruta especificada
        if (folder.exists() && folder.isDirectory()) {
            // Obtener todos los archivos XML en la carpeta
            File[] xmlFiles = folder.listFiles((dir, name) -> name.endsWith(".xml"));

            if (xmlFiles != null) {
                // Iterar sobre los archivos XML y mostrar un mensaje de refresco para cada uno
                for (File xmlFile : xmlFiles) {
                    System.out.println("Refrescando: " + xmlFile.getName());
                }
            }
        }
    }


}


