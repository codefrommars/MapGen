package com.cfm.mapgen;

import java.awt.Color;
import java.awt.Graphics2D;

public class MapRenderer {

	private int squareLen = 10;
	private int doorThick = 1;

	public void renderMap(Graphics2D g, Map map) {
		for (int i = 0; i < map.getWidth(); i++) {
			for (int j = 0; j < map.getHeight(); j++) {
				
				int tile = map.get(i, j);

				if (tile == 0)
					g.setColor(Color.black);
				else
					g.setColor(Color.blue);

				g.fillRect(i * squareLen, j * squareLen, squareLen, squareLen);

				g.setColor(Color.white);
				
				if (map.is(i, j, Map.WALL_UP)) {
					g.fillRect(i * squareLen, j * squareLen, squareLen,
							doorThick);
				}
				if (map.is(i, j, Map.WALL_RIGHT)) {
					g.fillRect((i + 1) * squareLen - doorThick, j * squareLen,
							doorThick, squareLen);
				}

				if (map.is(i, j, Map.WALL_LEFT)) {
					g.fillRect(i * squareLen, j * squareLen, doorThick,
							squareLen);
				}

				if (map.is(i, j, Map.WALL_DOWN)) {
					g.fillRect(i * squareLen, (j + 1) * squareLen - doorThick,
							squareLen, doorThick);
				}
			}
		}
	}

	public int getSquareLen() {
		return squareLen;
	}

}
