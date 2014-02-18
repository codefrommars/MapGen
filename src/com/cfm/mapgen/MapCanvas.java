package com.cfm.mapgen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

@SuppressWarnings("serial")
public class MapCanvas extends JComponent{
	public Map map;
	private MapRenderer renderer;
	public MapCanvas(){
		renderer = new MapRenderer();
	}
	
	public void setMap(Map map) {
		this.map = map;
		setPreferredSize(new Dimension(map.getWidth() * renderer.getSquareLen(), map.getHeight() * renderer.getSquareLen()));
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		renderer.renderMap((Graphics2D) g, map);
	}
	
	public void export(String fileName){
		BufferedImage img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_BGR);
		Graphics g = img.getGraphics();
		paint(g);
		try {
			ImageIO.write(img, "PNG", new File(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
