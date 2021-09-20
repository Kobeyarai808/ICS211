package edu.ics211.h04;

import java.util.ArrayList;

public class SymbolTable implements SymbolTableInterface{

	public static void main(String[] args) {
		SymbolTable testing = new SymbolTable();
		testing.add("alpha");
		testing.add("charlie");
		testing.add("delta");
		testing.add("bravo");
		testing.add("foxtrot");
		testing.add("echo");
		testing.add("d");
		testing.add("a");
		testing.add("c");
		testing.add("b");
		System.out.println(testing.size());
		System.out.println(testing);
	}
	private ArrayList<String> str;
	
	//Constructor creates a new instance of str that has a length of 1. 
	public SymbolTable() {
		str = new ArrayList<String>();
		str.add("");
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
			if(!str.get(i).equals("") && str.get(i).compareToIgnoreCase(value)==0){
				return false;
			}
			else if(!str.get(i).equals("") && str.get(i).compareToIgnoreCase(value)>0) {
				str.add(i,value);
				if(str.get(str.size()-1).equals("")) {
					str.remove(str.size()-1);
				}
				else {
					for(int j=0;j<2;j++) {
						str.add("");
					}
				}
				return true;
			}
		}
		if(!str.get(str.size()-1).equals("")) {
			str.add(value);
			for(int j=0;j<2;j++) {
				str.add("");
			}
		}
		else if(str.size()>1 && str.get(str.size()-2).equals("")){
			str.set(str.size()-2,value);
		}
		else {
			str.set(str.size()-1,value);
		}
		return true;
	}

	@Override
	public boolean remove(String value) {
		for(int i=0;i<str.size();i++) {
			if(str.get(i).equals(value)){
				str.remove(i);
				str.add("");
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
		if(str.size()==0) {
			return "";
		}
		String result = str.get(0);
		for(int i=1;i<str.size();i++) {
			result+= " " + str.get(i);
		}
		return result;
	}
}
