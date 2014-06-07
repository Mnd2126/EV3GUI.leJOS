package com.rokid.ev3.gui;

/**
 * Popup class for popup upon the desktop and catch all keyboard events.
 * @author Misa.Z
 *
 */
public class Popup extends Container {
	public static int NONE = 0;
	public static int FULLSCREEN = 1;
	public static int CENTER = 2;
	
	Desktop desktop = null;
	View lastFocused = null;
	
	/**
	 * Popup and show with fullscreen mode or not.
	 */
	public void popup(int mode) {
		popup(Desktop.getDefault(), mode);
	}
	
	/**
	 * Popup and show
	 */
	public void popup() {
		popup(Desktop.getDefault(), NONE);
	}
	
	/**
	 * Unpop and hide
	 */
	public void close() {
		this.hide();
		if(desktop == null || !desktop.popups.contains(this))
			return;
		
		if(desktop.popups.lastElement() != this)
			return;
		desktop.popups.pop();
	}
	
	/**
	 * Popup upon the specific desktop 
	 */
	public void popup(Desktop desktop, int mode) {
		if(desktop == null)
			return;
		
		this.desktop = desktop;
		if(desktop.popups.contains(this))
			return;
		
		desktop.popups.push(this);
		
		if(mode == FULLSCREEN) {
			this.fullscreen();
		}
		else if(mode == CENTER) {
			this.center();
		}
		
		this.show();
	}
	
	/**
	 * resize to the fullscreen.
	 */
	void fullscreen() {
		Desktop desk = desktop;
		if(desk == null) {
			desk = Desktop.getDefault();
		}
		moveTo(0, 0, desk.getWidth(), desk.getHeight());
	}
	
	/**
	 * move to the center of desktop.
	 */
	void center() {
		Desktop desk = desktop;
		if(desk == null) {
			desk = Desktop.getDefault();
		}
		int dw = desk.getWidth();
		int dh = desk.getHeight();
		int w = getWidth();
		int h = getHeight();
		
		moveTo((dw-w)/2, (dh-h)/2);
	}

	protected Event onEvent(Event ev) {
		if(ev.type == Event.KEY && ev.value == lejos.hardware.Button.ID_ESCAPE) {
			close();
			return null;
		}
		return ev;
	}
}