package TestClass;

import java.util.ArrayList;

public class TestObject {
	

	public static void main(String[] args) {
		Obj p = new Obj(10,10);
		Obj q = new Obj(11,11);
		ArrayList<Obj> objList = new ArrayList<Obj>();
		objList.add(p);
		objList.add(q);
		System.out.println(objList.toString());
		ArrayList<Obj> list2 = new ArrayList<Obj>(objList);
		System.out.println(list2.toString());
		
		changeObject(p);
		System.out.println(list2.toString());
		
		
	}
	
	public static void changeObject(Obj p){
		p.x = 10+11;
		p.y = 11+11;
	}
	
	
}
class Obj{
	int x;
	int y;
	
	public Obj(int x,int y ){
		this.x = x;
		this.y = y;
	}
	public String toString(){
		return "[x :"+x+", y :"+y+"]";
	}
}