package iesfranciscodelosrios.chatroomsproject.shareFolder;
import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static iesfranciscodelosrios.chatroomsproject.shareFolder.ConfigManager.readSharedFolderPath;

public class RefreshXMLFiles {


    private String folderPath = readSharedFolderPath();
    private long refreshIntervalSec = 10;

    public RefreshXMLFiles(String folderPath, long refreshIntervalSec) {
        this.folderPath = folderPath;
        this.refreshIntervalSec = refreshIntervalSec;
    }

    public void startRefreshing() {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(() -> {
            refreshXMLFiles();
        }, 0, refreshIntervalSec, TimeUnit.SECONDS);
    }

    private void refreshXMLFiles() {
        File folder = new File(folderPath);

        if (folder.exists() && folder.isDirectory()) {
            File[] xmlFiles = folder.listFiles((dir, name) -> name.endsWith(".xml"));

            if (xmlFiles != null) {
                for (File xmlFile : xmlFiles) {
                    System.out.println("Refrescando: " + xmlFile.getName());
                }
            }
        }
    }


}


