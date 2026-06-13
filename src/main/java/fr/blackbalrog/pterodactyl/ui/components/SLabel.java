package fr.blackbalrog.pterodactyl.ui.components;

import java.awt.Font;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * JLabel dont la famille de police est toujours "Segoe UI Symbol",
 * ce qui garantit l'affichage correct des émojis et glyphes Unicode
 * (★ ↺ ▸ ●…) sans carré de remplacement.
 * <p>
 * Le style (gras, italique) et la taille passés via {@link #setFont}
 * sont conservés — seule la famille est remplacée.
 */
@SuppressWarnings("serial")
public class SLabel extends JLabel
{
	private static final String SYMBOL_FONT = "Segoe UI Symbol";

	// ── Constructeurs miroir de JLabel ────────────────────────────

	public SLabel()
	{
		this("");
	}

	public SLabel(String text)
	{
		this(text, SwingConstants.LEADING);
	}

	public SLabel(String text, int horizontalAlignment)
	{
		super(text, horizontalAlignment);
		initFont();
	}

	public SLabel(Icon icon, int horizontalAlignment)
	{
		super(icon, horizontalAlignment);
		initFont();
	}

	public SLabel(String text, Icon icon, int horizontalAlignment)
	{
		super(text, icon, horizontalAlignment);
		initFont();
	}

	// ── Police par défaut ─────────────────────────────────────────

	private void initFont()
	{
		super.setFont(new Font(SYMBOL_FONT, Font.PLAIN, 11));
	}

	/**
	 * Remplace la famille par {@code "Segoe UI Symbol"} tout en
	 * conservant le style et la taille transmis (ex. {@code Theme.FONT_BOLD}).
	 */
	@Override
	public void setFont(Font font)
	{
		if (font != null)
			font = new Font(SYMBOL_FONT, font.getStyle(), font.getSize());
		super.setFont(font);
	}
}
