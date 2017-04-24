//ce fichier sert a tester bd.SessionsTools.checkSession: TERMINE
package test;

import bd.SessionsTools;

public class CheckSessionTest{
	public static void main(String[] args){
		try{
			System.out.println(SessionsTools.checkSession("Vatar","ae9ae5aad14f496692840d3abf76fba6")+"\n");
			System.out.println(SessionsTools.checkSession("1234","ae9ae5aad14f496692840d3abf76fba6"));
		}
		catch(Exception e){}
	}
}