package com.excilys.cbd.dao;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
public class Connexion 
{
	String url = "jdbc:mysql://localhost:3306/computer-database-db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	String user = "root";
	String password = "Chabert1996";
	Connection connexion;
	public Connection connexionOpen()
	{
		try
		{
			connexion = DriverManager.getConnection(url,user,password);
			System.out.println("La connexion est �tabli");
			//Statement statement=connexion.createStatement();
			return connexion;
		}
		catch (SQLException e)
		{
			System.out.print("La connexion � �chouer");
			e.printStackTrace();
		}
		return null;
	}
	public void connexionClose()
	{
		if ( connexion != null )
		{
			try 
			{
				connexion.close();
				System.out.print("La connexion est ferm�e");
			}
			catch ( SQLException ignore ) 
			{ 
			}
		}
		else
		{
			System.out.print("La connexion n'est pas ouverte");
		}
	}
}


