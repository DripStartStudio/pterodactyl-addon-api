package fr.blackbalrog.pterodactyl.addon;

/**
 * Interface que chaque addon doit implémenter.
 *
 * Un addon est un fichier .jar placé dans %APPDATA%\Pterodactyl\addons\
 * contenant un fichier addon.json avec les métadonnées et le nom de classe
 * du point d'entrée.
 *
 * Exemple addon.json :
 * <pre>
 * {
 *   "id":             "bbmodel-viewer",
 *   "name":           "BBModel Viewer",
 *   "version":        "1.0.0",
 *   "author":         "BlackBalrog",
 *   "description":    "Visualiseur 3D pour fichiers .bbmodel",
 *   "minAppVersion":  "1.0.0",
 *   "entrypoint":     "fr.blackbalrog.addons.bbmodel.BBModelAddon"
 * }
 * </pre>
 */
public interface PterodactylAddon
{
	/**
	 * Appelé quand l'addon est activé (au démarrage ou manuellement).
	 * C'est ici qu'on enregistre les handlers, panneaux, etc.
	 */
	void onEnable(AddonContext context);

	/**
	 * Appelé quand l'addon est désactivé (manuellement ou à la fermeture).
	 * Libérer les ressources ici.
	 */
	void onDisable();

	/** Métadonnées de l'addon (lues depuis addon.json). */
	AddonInfo getInfo();
}
