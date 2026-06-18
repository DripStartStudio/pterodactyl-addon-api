package fr.blackbalrog.pterodactyl.ui.components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicScrollBarUI;

import fr.blackbalrog.pterodactyl.ui.Theme;

public class MinimalScrollBarUI extends BasicScrollBarUI
{
	private final Dimension ZERO = new Dimension();

	@SuppressWarnings("serial")
	@Override
	protected JButton createDecreaseButton(int o)
	{
		return new JButton() { public Dimension getPreferredSize() { return ZERO; } };
	}

	@SuppressWarnings("serial")
	@Override
	protected JButton createIncreaseButton(int o)
	{
		return new JButton() { public Dimension getPreferredSize() { return ZERO; } };
	}

	@Override
	protected void installDefaults()
	{
		super.installDefaults();
		scrollbar.setBackground(Theme.BG_SURFACE);
		scrollbar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
	}

	@Override
	protected void paintTrack(Graphics g, JComponent c, Rectangle r)
	{
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setColor(Theme.BG_SURFACE);
		g2.fillRect(r.x, r.y, r.width, r.height);
		g2.setColor(new java.awt.Color(30, 38, 58));
		g2.fillRoundRect(r.x + 3, r.y + 2, r.width - 6, r.height - 4, 6, 6);
		g2.dispose();
	}

	@Override
	protected void paintThumb(Graphics g, JComponent c, Rectangle r)
	{
		if (r.width > r.height) return;
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Theme.ACCENT);
		g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 0.80f));
		g2.fill(new RoundRectangle2D.Float(r.x + 3, r.y + 2, r.width - 6, r.height - 4, 6, 6));
		g2.dispose();
	}

	@Override
	protected void setThumbBounds(int x, int y, int w, int h)
	{
		super.setThumbBounds(x, y, w, h);
		scrollbar.repaint();
	}
}
