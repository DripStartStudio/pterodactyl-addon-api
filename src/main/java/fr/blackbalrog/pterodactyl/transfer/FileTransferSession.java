package fr.blackbalrog.pterodactyl.transfer;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface FileTransferSession extends Closeable
{
	/** Liste le contenu d'un répertoire. */
	List<RemoteFile> listDirectory(String path) throws IOException;

	/** Retourne un InputStream sur le fichier distant (à fermer par l'appelant). */
	InputStream downloadFile(String path) throws IOException;

	/** Upload depuis un InputStream. */
	void uploadFile(String remotePath, InputStream in, long size) throws IOException;

	/** Upload depuis un fichier local. */
	void uploadFile(String remotePath, File localFile) throws IOException;

	/** Supprime un fichier. */
	void deleteFile(String path) throws IOException;

	/** Supprime récursivement un dossier. */
	void deleteDirectory(String path) throws IOException;

	/** Renomme / déplace un fichier ou dossier. */
	void renameFile(String oldPath, String newPath) throws IOException;

	/** Crée un répertoire. */
	void createDirectory(String path) throws IOException;

	/** Crée un fichier vide. */
	void createFile(String path) throws IOException;

	boolean isConnected();

	/**
	 * Ouvre une session indépendante dédiée à un thread de recherche parallèle.
	 * La session retournée doit être fermée par l'appelant.
	 */
	FileTransferSession openParallelSession() throws IOException;

	/** "SFTP" ou "FTP" */
	String getProtocol();

	/** Hôte distant. */
	String getHost();

	/**
	 * Préfixe unique par connexion serveur — utilisé comme clé de cache.
	 * Inclut host + port + username.
	 */
	String getCachePrefix();
}
