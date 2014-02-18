package com.cfm.mapgen;

import java.awt.Color;
import java.io.File;

import javax.swing.JFrame;

public class TestMapGen {
	
	public static void main(String[] args) {
		File file = new File("MapTest.png");
		MapGen gen = new SectorMapGen(new ImageMapLimit(file, Color.black.getRGB()), 10, 2);
		
		Map map = gen.generateMap();
		
		MapCanvas canvas = new MapCanvas();
		canvas.setMap(map);
		
		JFrame frame = new JFrame("TestMapGen");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(canvas);
		frame.pack();
		canvas.export("Output.png");
		frame.setVisible(true);
	}

}
