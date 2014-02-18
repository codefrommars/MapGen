package com.cfm.mapgen;

import java.util.Random;

public class MapGen {
	
	
	protected MapLimit limits;
	protected Random rand;
	
	public MapGen(MapLimit limits, long seed){
		this.limits = limits;
		rand = new Random(seed);
	}
	
	public Map generateMap(){
		Map map = new Map(limits);
		
		for (int i = 0; i < map.getWidth(); i++) {
			for (int j = 0; j < map.getHeight(); j++) {
				map.addFlag(i, j,  Map.WALL_NONE);
				
				if( !limits.contains(i, j) )
					map.set(i, j, Map.MAP_VOID);
			}
		}
		
		return map;
	}
}
