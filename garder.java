

/*
public class garder {}

ArrayList<Computer> listComputer=new ArrayList<Computer>();
		try
		{
			connexion = DriverManager.getConnection(url,user,password);
			System.out.println("La connexion est établi");
			
			Statement statement=connexion.createStatement();
			ResultSet resultat=statement.executeQuery(Requete.requete);
			while (resultat.next())
			{ 
				String name =resultat.getString("name");
				Timestamp introduced = resultat.getTimestamp("introduced");
				Timestamp discontinued = resultat.getTimestamp("discontinued");
				Long company_id=resultat.getLong("company_id");
				Computer comp = new Computer(name);
				comp.setIntroduced(introduced);
				comp.setDiscontinued(discontinued);
				comp.setCompany_id(company_id);				
				
				System.out.println(comp.getName() + " " + comp.getIntroduced() + " " + comp.getDiscontinued() + " " + comp.getCompany_id());
				if (comp.getName()!=null)
				{
					listComputer.add(comp);
				}
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if ( connexion != null )
			{
				try 
				{
					connexion.close();
				}
				catch ( SQLException ignore ) 
				{ 
				}
			}
			else
			{
				System.out.print("La connexion à échouer");
			}
		}
		return listComputer;
	}
*/
