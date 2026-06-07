package fr.blackbalrog.pterodactyl.addon;

import java.awt.Window;

import fr.blackbalrog.pterodactyl.transfer.FileTransferSession;
import fr.blackbalrog.pterodactyl.transfer.RemoteFile;

/**
 * Handler appelé quand l'utilisateur double-clique sur un fichier
 * dont l'extension est enregistrée par un addon.
 */
@FunctionalInterface
public interface FileHandler
{
	/**
	 * @param file    fichier cliqué dans le gestionnaire
	 * @param parent  fenêtre parente pour les dialogs
	 * @param session session SFTP/FTP active
	 */
	void handle(RemoteFile file, Window parent, FileTransferSession session);
}
