package com.cfm.mapgen;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SectorMapGen extends MapGen {
	
	private int minSector, maxSector; 
	
	private List<Integer> notUsed = new ArrayList<Integer>();
	
	public SectorMapGen(MapLimit limits, int sectors, long seed) {
		super(limits, seed);
		
		for(int i = 0; i < limits.getArea(); i++){
			if( limits.contains(i))
				notUsed.add(i);
		}
		
		maxSector = limits.getArea() / (sectors - 1);
		minSector = limits.getArea() / (sectors + 1);
		
	}

	@Override
	public Map generateMap() {
		List<List<Integer>> sectors = generateSectors(limits);
		return renderSectors(sectors);
	}

	private Map renderSectors(List<List<Integer>> sectors) {
		Map map = new Map(limits);
		
		for(List<Integer> sector : sectors ){
			System.out.println(sector);
			for(Integer index : sector){
				int x = limits.getX(index);
				int y = limits.getY(index);
				System.out.println(x + ", " + y);
			}
			for(Integer index : sector){
				int flag = Map.WALL_ALL;
				
				int x = limits.getX(index);
				int y = limits.getY(index);
				
				if( sector.contains(limits.getIndex(x, y - 1)))
					flag -= Map.WALL_UP;

				if( sector.contains(limits.getIndex(x, y + 1)))
					flag -= Map.WALL_DOWN;
				
				if( sector.contains(limits.getIndex(x - 1, y)))
					flag -= Map.WALL_LEFT;
				
				if( sector.contains(limits.getIndex(x + 1, y)))
					flag -= Map.WALL_RIGHT;
					
				map.set(index, flag);
			}
		}
		
		return map;
	}

	private List<List<Integer>> generateSectors(MapLimit limits) {
		List<List<Integer>> sectors = new ArrayList<List<Integer>>();
		
		while( notUsed.size() > 0 ){
			int sectorLenght = rand.nextInt(maxSector - minSector + 1) + minSector;
			int index = generateValidLocation();
		
			LinkedList<Integer> toVisit = new LinkedList<Integer>();
			toVisit.addLast(index);
		
			List<Integer> sec = new ArrayList<Integer>();
			while( !toVisit.isEmpty() && sec.size() < sectorLenght)
				floodFill(sec, limits, toVisit);
			
			sectors.add(sec);
		}
		
		//TODO: mix small.
		//small is less than the middle of the minSector
		List<Integer> marked = new ArrayList<Integer>();
		
		for( int i = 0; i < sectors.size(); i++){
			List<Integer> sector = sectors.get(i);
			
			if( sector.size() < minSector / 2 ){
				
				
			}
		}
		return sectors;
	}
	
	private boolean floodFill(List<Integer> sector, MapLimit limits, LinkedList<Integer> queue){
		int index = queue.removeFirst();
		
		if( sector.contains(index) || !limits.contains(index) )
			return false;
		
		//add it.
		sector.add(index);
		notUsed.remove((Integer)index);
		int x = limits.getX(index), y = limits.getY(index);

		//Queue the rest if it's possible
		queue.add(limits.getIndex(x - 1, y - 1));
		queue.add(limits.getIndex(x, y - 1));
		queue.add(limits.getIndex(x + 1, y - 1));
		queue.add(limits.getIndex(x - 1, y));
		queue.add(limits.getIndex(x + 1, y));
		queue.add(limits.getIndex(x - 1, y + 1));
		queue.add(limits.getIndex(x, y + 1));
		queue.add(limits.getIndex(x + 1, y + 1));
		
		return true;
	}
	
	private int generateValidLocation(){
		if( notUsed.size() == 0 )
			return -1;
		
		int index = rand.nextInt(notUsed.size()); 		
		return notUsed.remove(index);
	}

}
