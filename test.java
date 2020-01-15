import java.util.ArrayList;

public class test 
{
	public static void main(String[] args) 
	{
		ArrayList<Computer> listComputer = new ArrayList<Computer>();
		Requete r =new Requete("SELECT * FROM computer WHERE company_id=4");
		Requete m =new Requete("SELECT * FROM computer WHERE company_id=1");
		listComputer=Requete.demande(m);
		listComputer=Requete.demande(r);
		
	}

}
