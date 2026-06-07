package fr.blackbalrog.pterodactyl.addon;

/**
 * Interface de log dediee a un addon.
 *
 * Obtenu via AddonContext.getLogger().
 * Ecrit dans logs/addons/{addonId}/{timestamp}.log
 * sans apparaitre dans le log principal.
 */
public interface AddonLogger
{
	void info (String message);
	void warn (String message);
	void error(String message);
	void error(String message, Throwable throwable);
}