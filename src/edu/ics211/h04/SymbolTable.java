package edu.ics211.h04;

import java.util.ArrayList;

public class SymbolTable implements SymbolTableInterface{

	private ArrayList<String> str;
	
	//Constructor creates a new instance of str that has a length of 1. 
	public SymbolTable() {
		str = new ArrayList<String>(1);
	}

	@Override
	public int size() {
		return str.size();
	}

	@Override
	public String get(int index) {
		return str.get(index);
	}

	@Override
	public boolean add(String value) {
		for(int i=0;i<str.size();i++) {
			if(str.get(i).equals(value)){
				return false;
			}
		}
		str.add(value);
		return true;
	}

	@Override
	public boolean remove(String value) {
		for(int i=0;i<str.size();i++) {
			if(str.get(i).equals(value)){
				str.remove(i);
				return true;
			}
		}
		return false;
	}

	@Override
	public int indexOf(String value) {
		for(int i=0;i<str.size();i++) {
			if(str.get(i).equals(value)){
				return i;
			}
		}
		return -1;
	}
	
	@Override
	public String toString() {
		String result = str.get(0);
		
		for(int i=1;i<str.size();i++) {
			result+= " " + str.get(i);
		}
		return result;
	}

}
