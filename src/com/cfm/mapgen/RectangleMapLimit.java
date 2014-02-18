package com.cfm.mapgen;

import java.awt.Rectangle;

public class RectangleMapLimit extends MapLimit {
	
	private Rectangle rect ;
	private int[][] imageBits;
	
	
	public RectangleMapLimit(int width, int height){
		rect = new Rectangle(width, height);
	}
	
	@Override
	public boolean contains(int x, int y){
		return rect.contains(x, y);
	}

	@Override
	public int getWidth() {
		return rect.width;
	}

	@Override
	public int getHeight() {
		return rect.height;
	}
	
}
