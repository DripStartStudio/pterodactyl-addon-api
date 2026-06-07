package fr.blackbalrog.pterodactyl.addon;

/**
 * Métadonnées d'un addon lues depuis addon.json à l'intérieur du JAR.
 */
public record AddonInfo(
	String id,
	String name,
	String version,
	String author,
	String description,
	String minAppVersion
)
{
	/** Format affiché dans le panel Addons. */
	public String displayName()
	{
		return name + " v" + version + " — " + author;
	}
}
