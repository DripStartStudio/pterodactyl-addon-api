# Pterodactyl Panel Manager — Addon API

API publique pour créer des addons pour **Pterodactyl Panel Manager**.

[![](https://jitpack.io/v/DripStartStudio/pterodactyl-addon-api.svg)](https://jitpack.io/#DripStartStudio/pterodactyl-addon-api)

---

## Ajouter la dépendance

**`pom.xml`**
```xml
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>

<dependency>
  <groupId>com.github.DripStartStudio</groupId>
  <artifactId>pterodactyl-addon-api</artifactId>
  <version>3.0.0</version>
  <scope>provided</scope>
</dependency>
```

> `provided` — le JAR n'est **pas** embarqué dans l'addon. Le programme principal le fournit au runtime.

---

## Structure d'un addon

Un addon est un fichier `.jar` contenant :

```
mon-addon.jar
├── addon.json          ← métadonnées obligatoires
└── com/exemple/...     ← ton code
```

### `addon.json`
```json
{
  "id":            "mon-addon",
  "name":          "Mon Addon",
  "version":       "1.0.0",
  "author":        "TonPseudo",
  "description":   "Description courte de l'addon",
  "minAppVersion": "1.0.0",
  "entrypoint":    "com.exemple.MonAddon"
}
```

### Classe principale
```java
import fr.blackbalrog.pterodactyl.addon.AddonContext;
import fr.blackbalrog.pterodactyl.addon.AddonInfo;
import fr.blackbalrog.pterodactyl.addon.PterodactylAddon;

public class MonAddon implements PterodactylAddon {

    private static final AddonInfo INFO = new AddonInfo(
        "mon-addon", "Mon Addon", "1.0.0",
        "TonPseudo", "Description courte", "1.0.0"
    );

    @Override
    public AddonInfo getInfo() { return INFO; }

    @Override
    public void onEnable(AddonContext context) {
        // Enregistrer un handler pour l'extension .xyz
        context.registerFileHandler("xyz", (file, parent, session) -> {
            // appelé quand l'utilisateur double-clique sur un .xyz
        });
    }

    @Override
    public void onDisable() {
        // Libérer les ressources si nécessaire
    }
}
```

---

## API disponible

### `AddonContext`

| Méthode | Description |
|---|---|
| `registerFileHandler(extension, handler)` | Enregistre un handler pour une extension de fichier (ex : `"bbmodel"`) |
| `addSettingsPanel(label, panel)` | Ajoute un panneau dans les paramètres du programme |
| `getDataDir()` | Dossier de données persistantes de l'addon (`%APPDATA%\Pterodactyl\addons\{id}\`) |
| `getAddonsDir()` | Dossier global des addons |

### `FileHandler`

```java
@FunctionalInterface
public interface FileHandler {
    void handle(RemoteFile file, Window parent, FileTransferSession session);
}
```

Appelé quand l'utilisateur double-clique sur un fichier dont l'extension est enregistrée.

### `RemoteFile`

```java
file.getName()            // nom du fichier
file.getPath()            // chemin complet sur le serveur
file.isDirectory()
file.isFile()
file.getSize()            // taille en octets
file.getModifiedEpochMs() // date de modification (ms depuis epoch)
```

### `FileTransferSession`

```java
session.downloadFile(path)                        // → InputStream
session.uploadFile(remotePath, inputStream, size)
session.uploadFile(remotePath, localFile)
session.listDirectory(path)                       // → List<RemoteFile>
session.deleteFile(path)
session.renameFile(oldPath, newPath)
session.createDirectory(path)
session.getProtocol()  // "SFTP" ou "FTP"
session.getHost()
```

### `Theme` — constantes visuelles

```java
Theme.BG_DARK        // couleur de fond principale
Theme.ACCENT         // indigo #818cf8
Theme.TEXT           // texte principal
Theme.TEXT_MUTED     // texte secondaire
Theme.FONT_BOLD      // police Segoe UI Bold 13
Theme.BTN_START / BTN_STOP / BTN_QUIT / ...
```

### `ModernButton`

```java
ModernButton btn = new ModernButton("Texte", Theme.BTN_START);
// JButton avec coins arrondis et animation hover/press
```

---

## Exemple complet — visualiseur de fichier

```java
@Override
public void onEnable(AddonContext context) {
    context.registerFileHandler("xyz", (file, parent, session) -> {
        SwingUtilities.invokeLater(() -> {
            JDialog dialog = new JDialog(parent, file.getName(), false);
            dialog.setSize(600, 400);

            JTextArea area = new JTextArea();
            area.setBackground(Theme.BG_DARK);
            area.setForeground(Theme.TEXT);
            area.setFont(Theme.FONT_CONSOLE);

            new SwingWorker<String, Void>() {
                @Override
                protected String doInBackground() throws Exception {
                    try (InputStream is = session.downloadFile(file.getPath())) {
                        return new String(is.readAllBytes());
                    }
                }
                @Override
                protected void done() {
                    try { area.setText(get()); }
                    catch (Exception e) { area.setText("Erreur : " + e.getMessage()); }
                }
            }.execute();

            dialog.add(new JScrollPane(area));
            dialog.setLocationRelativeTo(parent);
            dialog.setVisible(true);
        });
    });
}
```

---

## Build

```bash
mvn package          # compile + shade (fat JAR)
mvn verify           # compile + shade + ProGuard (JAR obfusqué)
```

Le JAR final se trouve dans `target/`.  
Place-le dans `%APPDATA%\Pterodactyl\addons\` pour le tester.

---

## Licence

Ce module est distribué sous licence MIT.
