package fr.blackbalrog.pterodactyl.ui.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;

import fr.blackbalrog.pterodactyl.ui.Theme;

@SuppressWarnings("serial")
public class ModernButton extends JButton
{
	private final Color  base;
	private       Color  current;
	private       boolean hovered;

	public ModernButton(String text, Color color)
	{
		super(text);
		this.base    = color;
		this.current = color;

		setContentAreaFilled(false);
		setBorderPainted(false);
		setFocusPainted(false);
		setForeground(Theme.TEXT);
		setFont(Theme.FONT_BOLD);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(MouseEvent e)
			{
				hovered = true;
				current = shift(base, +30);
				repaint();
			}

			public void mouseExited(MouseEvent e)
			{
				hovered = false;
				current = base;
				repaint();
			}

			public void mousePressed(MouseEvent e)
			{
				current = shift(base, -25);
				repaint();
			}

			public void mouseReleased(MouseEvent e)
			{
				current = hovered ? shift(base, +30) : base;
				repaint();
			}
		});
	}

	private static Color shift(Color c, int d)
	{
		return new Color(
			Math.max(0, Math.min(255, c.getRed()   + d)),
			Math.max(0, Math.min(255, c.getGreen() + d)),
			Math.max(0, Math.min(255, c.getBlue()  + d))
		);
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		Color bg = isEnabled() ? current : new Color(
			Math.max(0, current.getRed()   - 60),
			Math.max(0, current.getGreen() - 60),
			Math.max(0, current.getBlue()  - 60));
		g2.setColor(bg);
		g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 8, 8));

		g2.setFont(getFont());
		g2.setColor(isEnabled() ? getForeground() : Theme.TEXT_MUTED);
		java.awt.FontMetrics fm = g2.getFontMetrics();
		String txt = getText();
		int tx = (getWidth()  - fm.stringWidth(txt)) / 2;
		int ty = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
		g2.drawString(txt, tx, ty);

		g2.dispose();
	}

	public void setTextFont(Font font)
	{
		setFont(font);
	}
}
