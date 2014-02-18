package com.cfm.mapgen;

public class Map {
	
	public static final int MAP_VOID = 0,
			WALL_NONE = 1,
			WALL_UP = 2,
			WALL_RIGHT = 4,
			WALL_DOWN = 8,
			WALL_LEFT = 16,
			WALL_ALL = WALL_NONE | WALL_UP | WALL_RIGHT | WALL_DOWN | WALL_LEFT;
	
	private int [][] map;
	private MapLimit limits;
	
	public Map(MapLimit limits){
		this.limits = limits;
		map = new int[limits.getHeight()][limits.getWidth()];
	}
	
	public void set(int x, int y, int value){
		map[y][x] = value;
	}
	
	public void set(int index, int value){
		set(index % getWidth(), index / getWidth(), value);
	}
	
	public void addFlag(int x, int y, int flag){
		map[y][x] |= flag; 
	}
	
	public void addFlag(int index, int flag){
		addFlag(index % getWidth(), index / getWidth(), flag);
	}
	
	public int get(int x, int y){
		return map[y][x];
	}
	
	public int getWidth(){
		return limits.getWidth();
	}
	
	public int getHeight(){
		return limits.getHeight();
	}
	
	public boolean is(int x, int y, int flag){
		return (flag & get(x, y)) == flag;
	}
	
	public int getX(int index){
		return limits.getX(index);
	}
	
	public int getY(int index){
		return limits.getY(index);
	}
}
