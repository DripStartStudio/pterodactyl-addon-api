package fr.blackbalrog.pterodactyl.ui;

import java.awt.Color;
import java.awt.Font;

public final class Theme
{
	// Backgrounds
	public static final Color BG_DEEP     = new Color(9,  11,  19);
	public static final Color BG_DARK     = new Color(15, 20,  33);
	public static final Color BG_SURFACE  = new Color(22, 29,  46);
	public static final Color BG_ITEM     = new Color(28, 38,  56);
	public static final Color BG_HOVER    = new Color(40, 52,  74);
	public static final Color BG_SELECTED = new Color(50, 42,  80);

	// FOREGROUND
	public static final Color FG_TEXT_CONSOLE = new Color(79,  70,  229);

	// Borders
	public static final Color BORDER       = new Color(42, 52, 72);
	public static final Color BORDER_FOCUS = new Color(129, 140, 248);

	// Accent — indigo
	public static final Color ACCENT      = new Color(129, 140, 248);
	public static final Color ACCENT_DARK = new Color(79,  70,  229);

	// Text
	public static final Color TEXT           = new Color(226, 232, 240);
	public static final Color TEXT_MUTED     = new Color(100, 116, 139);
	public static final Color TEXT_SECONDARY = new Color(148, 163, 184);

	// Status colors
	public static final Color STATUS_RUNNING  = new Color(52,  211, 153);
	public static final Color STATUS_OFFLINE  = new Color(100, 116, 139);
	public static final Color STATUS_STARTING = new Color(251, 191,  36);
	public static final Color STATUS_STOPPING = new Color(251, 146,  60);

	// Buttons
	public static final Color BTN_START   = new Color(22,  163,  74);
	public static final Color BTN_STOP    = new Color(220,  38,  38);
	public static final Color BTN_RESTART = new Color(217, 119,   6);
	public static final Color BTN_KILL    = new Color(153,  27,  27);
	public static final Color BTN_SEND    = new Color(79,   70, 229);
	public static final Color BTN_QUIT    = new Color(51,   65,  85);
	public static final Color BTN_CONNECT = new Color(79,   70, 229);

	// Fonts
	public static final Font FONT_TITLE   = new Font("Segoe UI", Font.BOLD,  22);
	public static final Font FONT_HEADER  = new Font("Segoe UI", Font.BOLD,  13);
	public static final Font FONT_LABEL   = new Font("Segoe UI", Font.PLAIN, 13);
	public static final Font FONT_BOLD    = new Font("Segoe UI", Font.BOLD,  13);
	public static final Font FONT_SMALL   = new Font("Segoe UI", Font.PLAIN, 11);
	public static final Font FONT_CONSOLE = new Font("Consolas", Font.PLAIN, 12);

	private Theme() {}
}
