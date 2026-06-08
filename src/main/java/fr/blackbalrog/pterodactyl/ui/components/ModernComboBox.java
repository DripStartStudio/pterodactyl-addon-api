package fr.blackbalrog.pterodactyl.ui.components;

import fr.blackbalrog.pterodactyl.ui.Theme;

import javax.swing.*;
import javax.swing.plaf.basic.*;
import java.awt.*;
import java.awt.event.*;

public class ModernComboBox<E> extends JComboBox<E>
{
	public ModernComboBox(E[] items)
	{
		setBackground(Theme.BG_SURFACE);
		setForeground(Theme.TEXT);
		setFont(Theme.FONT_LABEL);
		setMaximumRowCount(12);
		setFocusable(true);

		setUI(new BasicComboBoxUI()
		{
			// ── Flèche personnalisée ────────────────────────────
			@Override
			public JButton createArrowButton()
			{
				JButton btn = new JButton()
				{
					@Override
					protected void paintComponent(Graphics g)
					{
						Graphics2D g2 = (Graphics2D) g.create();
						g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
							RenderingHints.VALUE_ANTIALIAS_ON);

						// Fond
						g2.setColor(getModel().isRollover() ? Theme.BG_HOVER : Theme.BG_ITEM);
						g2.fillRect(0, 0, getWidth(), getHeight());

						// Triangle ▾ centré, couleur accent
						g2.setColor(Theme.ACCENT);
						int cx = getWidth()  / 2;
						int cy = getHeight() / 2;
						int[] xs = { cx - 5, cx + 5, cx };
						int[] ys = { cy - 2, cy - 2, cy + 4 };
						g2.fillPolygon(xs, ys, 3);
						g2.dispose();
					}
				};
				btn.setName("ComboBox.arrowButton");
				btn.setOpaque(false);
				btn.setContentAreaFilled(false);
				btn.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Theme.BORDER));
				btn.setPreferredSize(new Dimension(30, 0));
				btn.setFocusPainted(false);
				btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				return btn;
			}

			// ── Fond du champ principal ─────────────────────────
			@Override
			public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus)
			{
				g.setColor(Theme.BG_SURFACE);
				g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
			}

			// ── Popup déroulante ────────────────────────────────
			@Override
			protected ComboPopup createPopup()
			{
				BasicComboPopup popup = new BasicComboPopup(comboBox)
				{
					@Override
					protected JScrollPane createScroller()
					{
						JScrollPane sp = super.createScroller();
						sp.setBorder(null);
						sp.setBackground(Theme.BG_SURFACE);
						sp.getViewport().setBackground(Theme.BG_SURFACE);
						sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
						JScrollBar vsb = sp.getVerticalScrollBar();
						vsb.setUI(new MinimalScrollBarUI());
						vsb.setBackground(Theme.BG_SURFACE);
						vsb.setPreferredSize(new Dimension(10, vsb.getPreferredSize().height));
						vsb.setOpaque(true);
						return sp;
					}

					@Override
					protected void configureList()
					{
						super.configureList();
						list.setBackground(Theme.BG_SURFACE);
						list.setForeground(Theme.TEXT);
						list.setSelectionBackground(Theme.BG_HOVER);
						list.setSelectionForeground(Theme.ACCENT);
						list.setFont(Theme.FONT_LABEL);
						list.setBorder(BorderFactory.createEmptyBorder(4, 0, 4, 0));
						list.setFixedCellHeight(32);
					}
				};

				// Bordure indigo + fond sombre
				popup.setBorder(BorderFactory.createCompoundBorder(
					BorderFactory.createLineBorder(Theme.BORDER_FOCUS, 1),
					BorderFactory.createMatteBorder(4, 0, 4, 0, Theme.BG_SURFACE)
				));
				popup.setBackground(Theme.BG_SURFACE);
				return popup;
			}
		});

		// Bordure par défaut
		setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(Theme.BORDER, 1),
			BorderFactory.createEmptyBorder(0, 8, 0, 0)
		));

		// Bordure s'allume en indigo au focus
		addFocusListener(new FocusAdapter()
		{
			@Override public void focusGained(FocusEvent e)
			{
				setBorder(BorderFactory.createCompoundBorder(
					BorderFactory.createLineBorder(Theme.BORDER_FOCUS, 1),
					BorderFactory.createEmptyBorder(0, 8, 0, 0)
				));
			}
			@Override public void focusLost(FocusEvent e)
			{
				setBorder(BorderFactory.createCompoundBorder(
					BorderFactory.createLineBorder(Theme.BORDER, 1),
					BorderFactory.createEmptyBorder(0, 8, 0, 0)
				));
			}
		});

		// Modèle
		setModel(new DefaultComboBoxModel<>(items));

		// ── Rendu des éléments ──────────────────────────────────
		setRenderer(new DefaultListCellRenderer()
		{
			@Override
			public Component getListCellRendererComponent(
				JList<?> list, Object value, int index,
				boolean isSelected, boolean cellHasFocus)
			{
				JLabel label = (JLabel) super.getListCellRendererComponent(
					list, value, index, isSelected, cellHasFocus);

				if (index == -1)
				{
					// Valeur affichée dans le champ (hors popup)
					label.setBackground(Theme.BG_SURFACE);
					label.setForeground(Theme.TEXT);
					label.setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 0));
				}
				else if (isSelected)
				{
					// Élément survolé / sélectionné dans la liste
					label.setBackground(Theme.BG_HOVER);
					label.setForeground(Theme.ACCENT);
					label.setBorder(BorderFactory.createCompoundBorder(
						BorderFactory.createMatteBorder(0, 2, 0, 0, Theme.ACCENT),
						BorderFactory.createEmptyBorder(0, 8, 0, 10)
					));
				}
				else
				{
					// Élément normal dans la liste
					label.setBackground(Theme.BG_SURFACE);
					label.setForeground(Theme.TEXT_SECONDARY);
					label.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
				}

				label.setFont(Theme.FONT_LABEL);
				label.setOpaque(true);
				return label;
			}
		});
	}
}
