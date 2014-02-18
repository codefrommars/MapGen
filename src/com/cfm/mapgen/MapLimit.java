package com.cfm.mapgen;

public abstract class MapLimit {
	
	public boolean contains(int index){
		return contains( index % getWidth() , index / getWidth() );
	}
	
	public int getX(int index){
		return index % getWidth();
	}
	
	public int getY(int index){
		return index / getHeight();
	}
	
	public abstract boolean contains(int x, int y);

	public abstract int getWidth();

	public abstract int getHeight();

	public int getIndex(int x, int y) {
		return x + y * getWidth();
	}
	
	public int getArea(){
		return getWidth() * getHeight();
	}

}