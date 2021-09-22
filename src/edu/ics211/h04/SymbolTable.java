package edu.ics211.h04;

import java.util.ArrayList;
import java.util.Arrays;

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
		System.out.println(testing);
	}
	
	private String[] arr = new String[1];
	private int size;
	
	//Constructor creates a new instance of str that has a length of 1. 
	public SymbolTable() {
		arr[0] = "";
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public String get(int index) {
		return arr[index];
	}

	@Override
	public boolean add(String value) {
		System.out.println("size: "+size);
		if(size == arr.length-1) {
			arr = Arrays.copyOf(arr, arr.length+3);
			for(int i=size;i<arr.length;i++) {
				arr[i]="";
			}
		}
		System.out.println("value: "+value);
		if(size==0) {
			arr[0]=value;
			size++;
			return true;
		}
		for(int i=size;i>=0;i--) {
			if(i==0) {
				arr[i]=value;
				size++;
				return true;
			}
			if(value.compareToIgnoreCase(arr[i-1])>0) {
				arr[i] = value;
				size++;
				for(int k=0; k<size();k++) {
					System.out.print(" "+arr[k]);
				}
				System.out.print("\n");
				return true;
			}
			else {
				arr[i]=arr[i-1];
			}
		}
		return false;
	}
	
	@Override
	public boolean remove(String value) {
		for(int i=0;i<size();i++) {
			if(arr[i].compareToIgnoreCase(value)==0){
				for(int j=i;j<size-1;j++) {
					arr[i]=arr[i+1];
				}
				arr[size-1]="";
				size--;
				return true;
			}
		}
		return false;
	}

	@Override
	public int indexOf(String value) {
		for(int i=0;i<size;i++) {
			if(arr[i].compareToIgnoreCase(value)==0){
				return i;
			}
		}
		return -1;
	}
	
	@Override
	public String toString() {
		if(size==0) {
			return "";
		}
		String result = arr[0];
		for(int i=1;i<size;i++) {
			result+= " " + arr[i];
		}
		return result;
	}
}
