package com.maibang;

import java.awt.Component;

import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

public class ItemRenderer extends BasicComboBoxRenderer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7108652968262902608L;

	@SuppressWarnings("rawtypes")
	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		if (value != null) {
			Item item = (Item) value;
			setText(item.getText().toUpperCase());
		}
		if (index == -1) {
			Item item = (Item) value;
			setText("" + item.getScore());
		}
		return this;
	}
}
