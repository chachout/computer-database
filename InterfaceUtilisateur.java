package com.excilys.cbd.utilisateur;
import java.util.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import com.excilys.cbd.dao.CompanyDAO;
import com.excilys.cbd.dao.ComputerDAO;

public class InterfaceUtilisateur 
{
	public static void AfficherInterface() throws ParseException
	{
		System.out.println("\t\t\t\t\t\tBonjour");
		System.out.println("\n\t\t\t\t\tQue souhaitez-vous faire ?");
		System.out.println("\t\t\t\tEntrez le chiffre correspondant à votre demande");
		System.out.println("\n\t\t\t1.Add a computer");
		System.out.println("\t\t\t2.Update computer by id");
		System.out.println("\t\t\t3.Find a computer by id");
		System.out.println("\t\t\t4.Find a computer by name");
		System.out.println("\t\t\t5.Display all computers");
		System.out.println("\t\t\t6.Display all companies");
		System.out.println("\t\t\t7.Find a company by id");
		System.out.println("\t\t\t8.Find a company by name");
		System.out.println("\t\t\t9.Delete a computer");
		System.out.println("\t\t\t10.Exit\n");
		
		Scanner clavier = new Scanner(System.in);
		int i = clavier.nextInt();
		if (i==1)
		{
			System.out.println("\n\t\t\tEntrez le nom du computeur, sa date d'introduction, sa date de disparition ainsi que l'id de sa company. \n\t\t\tSi vous n'avez pas toutes ces informations entrez le nom et \"null\" dans les autres champs ");
			System.out.println("\n\t\t\t\tnom : ");
			String name = clavier.next();
			System.out.println("\n\t\t\t\tdate d'introduction : \n\t\t\t\tsous la forme jj-mm-yyyy");
			String introducedString = clavier.next();
			
			Date dateIntro= new SimpleDateFormat("dd-MM-yyyy").parse(introducedString);
			Timestamp introduced = new Timestamp(dateIntro.getTime());
			System.out.println("\n\t\t\t\tdate de disparition : \n\t\t\t\tsous la forme jj-mm-yyyy");
			String discontinuedString = clavier.next();
			Date dateDisco= new SimpleDateFormat("dd-MM-yyyy").parse(discontinuedString);
			Timestamp discontinued = new Timestamp(dateDisco.getTime());
			System.out.println(discontinued);
			System.out.println("\n\t\t\t\tid de la company : ");
			Long company_id = clavier.nextLong();
			ComputerDAO.creer(name,introduced,discontinued,company_id);
		}
		else
		{
			if (i==2)
			{
				System.out.println("\n\t\t\tEntrez l'id du computer à modifier ainsi que le nouveau nom du computeur, sa nouvelle date d'introduction, sa nouvelle date de disparition ainsi que l'id de sa company. \n\t\t\tSi vous n'avez pas toutes ces informations entrez le nom et \"null\" dans les autres champs ");
				System.out.println("\n\t\t\t\tid du computer : ");
				Long computer_id = clavier.nextLong();
				System.out.println("\n\t\t\t\tnom : ");
				String name = clavier.next();
				System.out.println("\n\t\t\t\tdate d'introduction : \n\t\t\t\tsous la forme jj-mm-yyyy");
				String introducedString = clavier.next();
				Date dateIntro= new SimpleDateFormat("dd-MM-yyyy").parse(introducedString);
				Timestamp introduced = new Timestamp(dateIntro.getTime());
				System.out.println("\n\t\t\t\tdate de disparition : \n\t\t\t\tsous la forme jj-mm-yyyy");
				String discontinuedString = clavier.next();
				Date dateDisco= new SimpleDateFormat("dd-MM-yyyy").parse(discontinuedString);
				Timestamp discontinued = new Timestamp(dateDisco.getTime());
				System.out.println(discontinued);
				System.out.println("\n\t\t\t\tid de la company : ");
				Long company_id = clavier.nextLong();
				ComputerDAO.modifier(computer_id, name, introduced, discontinued, company_id);
			}
			else
			{
				if (i==3)
				{
					System.out.println("\n\t\t\tEntrez l'id du computer que vous voulez trouvez : ");
					Long computer_id= clavier.nextLong();
					ComputerDAO.trouverid(computer_id);
				}
				else
				{
					if (i==4)
					{
						System.out.println("\n\t\t\tEntrez le nom du computer que vous voulez trouvez : ");
						String name= clavier.next();
						ComputerDAO.trouvernom(name);
					}
					else
					{
						if (i==5)
						{
							ComputerDAO.toutComputer();
						}
						else
						{
							if (i==6)
							{
								CompanyDAO.toutCompany();
							}
							else
							{
								if (i==7)
								{
									System.out.println("\n\t\t\tEntrez l'id de la company que vous voulez trouvez : ");
									Long company_id= clavier.nextLong();
									CompanyDAO.trouverCompany(company_id);
								}
								else
								{
									if (i==8)
									{
										System.out.println("\n\t\t\tEntrez le nom de la company que vous voulez trouvez : ");
										String name = clavier.next();
										CompanyDAO.trouverCompany(name);
									}
									else
									{
										if (i==9)
										{
											System.out.println("\n\t\t\tEntrez l'id du computer que vous voulez supprimer.");
											Long computer_id = clavier.nextLong();
											ComputerDAO.effacer(computer_id);
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
