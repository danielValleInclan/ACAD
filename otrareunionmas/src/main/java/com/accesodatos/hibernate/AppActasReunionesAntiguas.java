package com.accesodatos.hibernate;

import com.accesodatos.hibernate.dao.ActaDao;

public class AppActasReunionesAntiguas {

	public static void main(String[] args) {
		ActaDao actaDao=new ActaDao();
		System.out.println(actaDao.findActasReunionesAntiguas());
	}
}
