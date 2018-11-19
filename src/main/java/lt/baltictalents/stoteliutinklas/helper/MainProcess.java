package lt.baltictalents.stoteliutinklas.helper;

//Susikuriam .jar faila per file>export
//Paleidziam per cmd: java -cp JarFile.jar lt.baltictalents.stoteliutinklas.config.Application

public class MainProcess {
	public static void HandleArgs(String[] args)
	{
		if (args.length==0 || args[0].toLowerCase().equals("help") || args[0].toLowerCase().equals("?"))
		{
			HelpMain();
		}
	}
	
	static void HelpMain()
	{
		System.out.println("-----------------BT-STOTELIU-TINKLAS----------------");
		System.out.println("Neteisinga uzklausa.");
		System.out.println("Prasome programa paleisti taip:");
		ExecInstructions();
		System.out.println("Kur <args> yra:");
		HelpCommandsList();
		System.out.println("Jei tarp <args> pasitaiko zodziai neapskliausti <>, juos taip ir reiktu ivesti.");
	}
	
	static void ExecInstructions()
	{
		System.out.println("java -cp JarFile.jar lt.baltictalents.stoteliutinklas.config.Application <args>");
	}
	
	static void HelpCommandsList()
	{
		System.out.println("PavilionsByRectangle <Longitude0> <Latitude0> <Longitude1> <Latitude1>");
		System.out.println("NearestPavilionByCoords");
		System.out.println("BusiestPavilionByRectangle <Longitude0> <Latitude0> <Longitude1> <Latitude1>");
		System.out.println("PavilionsByName");
		System.out.println("PavilionsByRadius <Longitude> <Latitude> <radius> angle/meters");
		System.out.println("RoutesByStationName");
		System.out.println("RoutesByPavilionCoords");
		System.out.println("StationsAndPavilionsByRouteNumber");
		System.out.println("PavilionsByRouteNumberAndDirection <Route number and direction>");
		System.out.println("-----------------------------------------------------");
	}
}
