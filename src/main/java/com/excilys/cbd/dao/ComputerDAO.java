package com.excilys.cbd.dao;
import java.util.ArrayList;

import com.excilys.cbd.model.Company;
import com.excilys.cbd.model.Computer;
import com.excilys.cbd.service.ServiceComputer;

import java.sql.*;
import java.time.LocalDate;

public class ComputerDAO
{
	public static String CREER = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?,?,?,?)";
	public static String TOUTCOMPU = "SELECT * FROM computer";
	public static String PAGINATION =  "SELECT computer.name as computer_name, computer.id as computer_id, computer.introduced, computer.discontinued, computer.company_id, company.name as company_name FROM computer LEFT JOIN company on company.id=computer.company_id LIMIT ?, ?";
	public static String TROUVERID = "SELECT * FROM computer WHERE id = ?";
	public static String TROUVERNOM = "SELECT * FROM computer WHERE name = ?";
	public static String MODIFIER = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
	public static String EFFACER = "DELETE FROM computer WHERE id = ?";
	private ComputerDAO()
	{
	}
	private static ComputerDAO instance;
	public static ComputerDAO getInstance()
	{
		if (instance == null)
		{
			instance= new ComputerDAO();
			return instance;
		}
		else
		{
			return instance;
		}
	}
	private static Connection connexionOpen() throws ClassNotFoundException
	{
		Connection preparation = ConnecH2.getConnec().seConnecter();
		return preparation;
	}
	private static void connexionClose(Connection preparation) throws ClassNotFoundException
	{
		ConnecH2.getConnec().connectionClose(preparation);
	}
	public ArrayList<Computer> toutComputer() throws ClassNotFoundException
	{
		
		ArrayList<Computer> listComputer= new ArrayList<Computer>();
		try
		{
			Connection preparation = ComputerDAO.connexionOpen();
			PreparedStatement prepare = preparation.prepareStatement(TOUTCOMPU) ;
			ResultSet resultat=prepare.executeQuery();
			while (resultat.next())
			{ 
				String name =resultat.getString("computer_name");
				Date intro = resultat.getDate("introduced");
				Date disco = resultat.getDate("discontinued");
				LocalDate introduced = null;
				if (intro!=null)
				{
					introduced =intro.toLocalDate();
				}
				LocalDate discontinued = null;
				if (disco!=null)
				{
					discontinued =disco.toLocalDate();
				}
				String companyName=resultat.getString("company_name");
				
				Company compa = new Company.CompanyBuilder().setName(companyName).build();
				long id =resultat.getLong("computer_id");
				Computer comp = new Computer.ComputerBuilder(name).setIntroduced(introduced).setDiscontinued(discontinued).setCompany(compa).build();			
				comp.setId(id);
				//System.out.println(comp.getName() + " " + comp.getIntroduced() + " " + comp.getDiscontinued() + " " + comp.getIdCompany());
				if (comp.getName()!=null)
				{
					listComputer.add(comp);
				}
			}
			ComputerDAO.connexionClose(preparation);
			return listComputer;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public int count() throws ClassNotFoundException
	{	
		ArrayList<Computer> listComputer= new ArrayList<Computer>();
		int nombreComputer=0;
		try
		{
			Connection preparation = ComputerDAO.connexionOpen();
			PreparedStatement prepare = preparation.prepareStatement(TOUTCOMPU);
			ResultSet resultat=prepare.executeQuery();
			while (resultat.next())
			{ 
				String name =resultat.getString("name");
				Date intro = resultat.getDate("introduced");
				Date disco = resultat.getDate("discontinued");
				LocalDate introduced = null;
				if (intro!=null)
				{
					introduced =intro.toLocalDate();
				}
				LocalDate discontinued = null;
				if (disco!=null)
				{
					discontinued =disco.toLocalDate();
				}
				Long company_id=resultat.getLong("company_id");
				
				Company compa = CompanyDAO.getInstance().trouverCompany(company_id);
				Computer comp = new Computer.ComputerBuilder(name).setIntroduced(introduced).setDiscontinued(discontinued).setCompany(compa).build();			

				//System.out.println(comp.getName() + " " + comp.getIntroduced() + " " + comp.getDiscontinued() + " " + comp.getIdCompany());
				if (comp.getName()!=null)
				{
					listComputer.add(comp);
				}
				nombreComputer=listComputer.size();
			}
			ComputerDAO.connexionClose(preparation);
			return nombreComputer;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return 0;
	}
	public ArrayList<Computer> pageComputer(int limit, int offset) throws ClassNotFoundException
	{
		ArrayList<Computer> listComputer= new ArrayList<Computer>();
		try
		{
			Connection preparation = ComputerDAO.connexionOpen();
			PreparedStatement prepare = preparation.prepareStatement(PAGINATION);
			prepare.setInt(2, limit);
			prepare.setInt(1, offset);
			ResultSet resultat=prepare.executeQuery();
			while (resultat.next())
			{ 
				String name =resultat.getString("computer_name");
				Date intro = resultat.getDate("introduced");
				Date disco = resultat.getDate("discontinued");
				LocalDate introduced = null;
				if (intro!=null)
				{
					introduced =intro.toLocalDate();
				}
				LocalDate discontinued = null;
				if (disco!=null)
				{
					discontinued = disco.toLocalDate();
				}
				String companyName=resultat.getString("company_name");
				Company compa = new Company.CompanyBuilder().setName(companyName).build();
				long id =resultat.getLong("computer_id");
				Computer comp = new Computer.ComputerBuilder(name).setIntroduced(introduced).setDiscontinued(discontinued).setCompany(compa).build();			
				comp.setId(id);

				//System.out.println(comp.getName() + " " + comp.getIntroduced() + " " + comp.getDiscontinued() + " " + comp.getCompany());
				if (comp.getName()!=null)
				{
					listComputer.add(comp);
				}
			}
			ComputerDAO.connexionClose(preparation);
			return listComputer;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public Computer trouverid (long id) throws ClassNotFoundException
	{
		try
		{
			Connection preparation = ComputerDAO.connexionOpen();
			PreparedStatement prepare = preparation.prepareStatement(TROUVERID) ;
			prepare.setLong(1, id);
			ResultSet resultat=prepare.executeQuery();
			while (resultat.next())
			{ 

				String name =resultat.getString("name");
				Date intro = resultat.getDate("introduced");
				Date disco = resultat.getDate("discontinued");
				LocalDate introduced = LocalDate.now();
				if (intro!=null)
				{
					introduced =intro.toLocalDate();
				}
				
				LocalDate discontinued = LocalDate.now();
				if (disco!=null)
				{
					discontinued =disco.toLocalDate();
				}
				long company_id=resultat.getLong("company_id");
				
				Company compa = CompanyDAO.getInstance().trouverCompany(company_id);
				Computer comp = new Computer.ComputerBuilder(name).setIntroduced(introduced).setDiscontinued(discontinued).setCompany(compa).build();			


				//System.out.println(comp.getName() + " " + comp.getIntroduced() + " " + comp.getDiscontinued() + " " + comp.getCompany());
				if (comp.getName()!=null)
				{
					return comp;
				}
				
				else
				{
					//System.out.println("Il n'y a pas d'ordinateur avec cet id");
					return null;
				}
			}
			ComputerDAO.connexionClose(preparation);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public Computer trouvernom (String name) throws ClassNotFoundException
	{
		try
		{
			Connection preparation = ComputerDAO.connexionOpen();
			PreparedStatement prepare = preparation.prepareStatement(TROUVERNOM) ;
			prepare.setString(1,name);
			ResultSet resultat=prepare.executeQuery();
			while (resultat.next())
			{ 

				String nom =resultat.getString("name");
				Date intro = resultat.getDate("introduced");
				Date disco = resultat.getDate("discontinued");
				LocalDate introduced = LocalDate.now();
				if (intro!=null)
				{
					introduced =intro.toLocalDate();
				}
				
				LocalDate discontinued = LocalDate.now();
				if (disco!=null)
				{
					discontinued =disco.toLocalDate();
				}
				long company_id=resultat.getLong("company_id");
				
				Company compa = CompanyDAO.getInstance().trouverCompany(company_id);
				Computer comp = new Computer.ComputerBuilder(name).setIntroduced(introduced).setDiscontinued(discontinued).setCompany(compa).build();			


				//System.out.println(comp.getName() + " " + comp.getIntroduced() + " " + comp.getDiscontinued() + " " + comp.getCompany());
				if (comp.getName()!=null)
				{
					return comp;
				}
				else
				{
					//System.out.println("Il n'y a pas d'ordinateur avec ce nom");
					return null;
				}
			}
			ComputerDAO.connexionClose(preparation);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public int modifier(Computer comp) throws ClassNotFoundException
	{
		int value = 0;
		Connection preparation = ComputerDAO.connexionOpen();
		if (comp.getName()!=null)
		{
			try
			{
				PreparedStatement prepare =preparation.prepareStatement(MODIFIER);
				prepare.setString(1, comp.getName());
				if (comp.getIntroduced()!=null)
				{
					prepare.setTimestamp(2, Timestamp.valueOf(comp.getIntroduced().atStartOfDay()));
				}
				if (comp.getDiscontinued() != null)
				{
					prepare.setTimestamp(3, Timestamp.valueOf(comp.getDiscontinued().atStartOfDay()));
				}
				if (comp != null)
				{
					if (comp.getCompany() != null)
					{
						if (comp.getCompany().getId() != null)
						{
							prepare.setLong(4, comp.getCompany().getId());
						}
						else
						{
							prepare.setNull(4, java.sql.Types.BIGINT);
						}
					}
				}
				System.out.println(comp+"DAO");
				prepare.setLong(5,comp.getId());
				value = prepare.executeUpdate();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		ComputerDAO.connexionClose(preparation);
		return value;
	}
	public void effacer(long computer_id) throws ClassNotFoundException
	{
		Connection preparation = ComputerDAO.connexionOpen();
		try
		{
			PreparedStatement prepare = preparation.prepareStatement(EFFACER) ;
			prepare.setLong(1, computer_id);
			prepare.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		ComputerDAO.connexionClose(preparation);
		
	}
	public int creer(Computer comp) throws ClassNotFoundException
	{
		Connection preparation = ComputerDAO.connexionOpen();
		int value = 0;
		try
		{
			PreparedStatement prepare = preparation.prepareStatement(CREER) ;
			prepare.setString(1, comp.getName());
			if (comp.getIntroduced()!=null)
			{
				prepare.setTimestamp(2, Timestamp.valueOf(comp.getIntroduced().atStartOfDay()));
			}
			if (comp.getDiscontinued() != null)
			{
				prepare.setTimestamp(3, Timestamp.valueOf(comp.getDiscontinued().atStartOfDay()));
			}
			if (comp != null)
			{
				if (comp.getCompany() != null)
				{
					if (comp.getCompany().getId() != null)
					{
						prepare.setLong(4, comp.getCompany().getId());
					}
					else
					{
						prepare.setNull(4, java.sql.Types.BIGINT);
					}
				}
			}
			value = prepare.executeUpdate();
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		ComputerDAO.connexionClose(preparation);
		return value;
	}
}