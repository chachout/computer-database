import java.util.ArrayList;

public class Requete 
{
	static String requete;

	public Requete(String requete) 
	{
		super();
		this.requete = requete;
	}
	public static ArrayList<Computer> demande(Requete r)
	{
		Connexion c = new Connexion();
		ArrayList<Computer> listComputer = new ArrayList<Computer>();
		c.conexion();
		return listComputer;
	}
}
