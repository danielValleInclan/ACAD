package com.accesodatos.hibernate;


import com.accesodatos.hibernate.dao.SalaDao;

public class AppConsultas {

	public static void main(String[] args) {
		SalaDao saladao= new SalaDao();
		
		System.out.println("Salas para 6: "+ saladao.findSalasParaNpersonas(6));
		System.out.println("Salas para 3: "+ saladao.findSalasParaNpersonas(3));
		
		System.out.println("Salas de más o igual de 3 y menos o igual de 6:"+saladao.findSalasAdecuadasParaNpersonas(3));
	}

}
