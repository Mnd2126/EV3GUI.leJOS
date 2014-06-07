package com.rokid.ev3.gui;

import lejos.hardware.lcd.Font;
import lejos.hardware.lcd.GraphicsLCD;

/**
 * Button with label on it.
 * @author Misa.Z
 *
 */
public class LabelButton extends Button {
	String label = "";
	Font font = null;
	
	/**
	 * Get the label string
	 */
	public String getLabel() {
		return label;
	}
	
	/**
	 * Set the label string.
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	
	/**
	 * Set the font.
	 */
	public void setFont(Font font) {
		this.font = font;
	}
	
	public LabelButton(String label) {
		this.label = label;
		font = Font.getDefaultFont();
	}
	
	protected void draw(GraphicsLCD g) {
		super.draw(g);
		if(font == null || label == null)
			return;

		g.setFont(font);
		g.setColor(0, 0, 0);
		drawString(g, label, getWidth()/2, getHeight()/2,
				GraphicsLCD.VCENTER | GraphicsLCD.HCENTER);
	}
}