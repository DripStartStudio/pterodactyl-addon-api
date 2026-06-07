package fr.blackbalrog.pterodactyl.addon;

import java.nio.file.Path;

import javax.swing.JPanel;

/**
 * Contexte fourni à chaque addon lors de l'activation.
 * Donne accès aux APIs du programme principal.
 */
public interface AddonContext
{
	// ── Gestion des fichiers ──────────────────────────────────────

	/**
	 * Enregistre un handler pour une extension de fichier.
	 * Quand l'utilisateur double-clique sur un fichier avec cette extension
	 * dans le gestionnaire, le handler de l'addon est appelé à la place
	 * de l'éditeur par défaut.
	 *
	 * @param extension ex : "bbmodel", "nbt", "schematic" (sans le point)
	 * @param handler   callback à appeler
	 */
	void registerFileHandler(String extension, FileHandler handler);

	// ── Paramètres ────────────────────────────────────────────────

	/**
	 * Ajoute un panneau personnalisé dans la section Addons des Paramètres.
	 *
	 * @param label label affiché sous le nom de l'addon
	 * @param panel panneau de configuration de l'addon
	 */
	void addSettingsPanel(String label, JPanel panel);

	// ── Utilitaires ───────────────────────────────────────────────

	/** Dossier de données persistantes de cet addon (%APPDATA%\Pterodactyl\addons\{id}\). */
	Path getDataDir();

	/** Dossier addons global (%APPDATA%\Pterodactyl\addons\). */
	Path getAddonsDir();

	// ── Logging ───────────────────────────────────────────────────

	/**
	 * Logger dédié à cet addon.
	 * Écrit dans {@code logs/addons/{addonId}/{timestamp}.log} — isolé du log principal.
	 */
	AddonLogger getLogger();
}
