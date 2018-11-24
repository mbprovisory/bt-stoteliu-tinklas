package lt.baltictalents.stoteliutinklas.helper;
import lt.baltictalents.stoteliutinklas.operations.*;
import lt.baltictalents.stoteliutinklas.operations.PavilionsByRadius.Measurement;

import java.util.List;
import java.util.Scanner;

import lt.baltictalents.stoteliutinklas.data.beans.Station;
import lt.baltictalents.stoteliutinklas.data.layer.*;
//Susikuriam .jar faila per file>export
//Paleidziam per cmd: java -cp JarFile.jar lt.baltictalents.stoteliutinklas.config.Application

public class MainProcess {
	public static void HandleArgs(String[] args)
	{
		try
		{
			HelpMain();
			if (args.length==0 || args[0].toLowerCase().equals("help") || args[0].toLowerCase().equals("?"))
			{
				//HelpMain();
			}
			else
			{
				SelectOperations(args);	
			}
			while (true)
			{
				
				System.out.println("Iveskite uzklausa (pagalbai gauti rasykite ? arba help).");
				Scanner scanner = new Scanner(System.in);
				String s = scanner.nextLine();
				SelectOperations(Helper.RegexParser(s));
				
			}
			//SelectOperations(args);
		}
		catch (Exception e)
		{
			System.out.println("Neteisinga uzklausa.");
		}
	}
	
	static void SelectOperations(String []args)
	{
		DataListFactory connection = new DataListFactory();
		if (args[0].equalsIgnoreCase("PavilionsByRectangle")) //OK
		{
			PavilionsByRectangle  instance = new PavilionsByRectangle(connection);
			List<Station> station = instance.get(args[1], args[2], args[3], args[4]);
			for(Station s : station) System.out.println(s.toString());
				
			
			
		}
		else if (args[0].equalsIgnoreCase("NearestPavilionByCoords"))//OK
		{
			System.out.println(new NearestPavilionByCoords(connection).get(args[1], args[2]));
		}
		else if (args[0].equalsIgnoreCase("BusiestPavilionByRectangle"))//OK
		{
			BusiestPavilionByRectangle instance = new BusiestPavilionByRectangle(connection);
			List<Station> station = instance.get(args[1], args[2], args[3], args[4]);
			for(Station s : station) System.out.println(s.toString());
			
		}
		else if (args[0].equalsIgnoreCase("PavilionsByName")) //OK
		{
			PavilionsByName instance = new PavilionsByName(connection);
			List<Station> station = instance.get(args[1]);
			for(Station s : station) System.out.println(s.toString());
		}
		else if (args[0].equalsIgnoreCase("PavilionsByRadius")) //OK
		{
			PavilionsByRadius instance = new PavilionsByRadius(connection);
			
			Measurement m= Measurement.METERS; 
			if(args[3].toLowerCase().equals("angle")) m = Measurement.RADIUS;
			
			List<Station> station = instance.get(args[1],args[2], Double.parseDouble(args[3]), m);
			for(Station s : station) System.out.println(s.toString());
		}
		else if (args[0].equalsIgnoreCase("RoutesByStationName"))//OK
		{
			RoutesNumbersByStationName instance = new RoutesNumbersByStationName(connection);
			List<String[]> station = instance.get(args[1]);
			for(String[] s : station) 
			{
				for (String ss: s)
				{
					System.out.print(ss + " | ");
				}
				System.out.println();
			}
		}
		else if (args[0].equalsIgnoreCase("RoutesByPavilionCoords"))//OK
		{
			RoutesByPavilionCoords instance = new RoutesByPavilionCoords(connection);
			instance.get(Double.parseDouble(args[1]), Double.parseDouble(args[2]));
		}
		else if (args[0].equalsIgnoreCase("StationsAndPavilionsByRouteNumber")) //OK?
		{
			StationsAndPavilionsByRouteNumber instance = new StationsAndPavilionsByRouteNumber(connection);
			List<Station> station = instance.get(args[1]);
			for(Station s : station) System.out.println(s.toString());
		}
		/*
		 * bugs with Troleibusas 16: Pašilaičiai - Stotis  => Pašilaiciai - Stotis
		 */
		else if (args[0].equalsIgnoreCase("PavilionsByRouteNumberAndDirection")) //BUGS
		{
			PavilionsByRouteNumberAndDirection instance = new PavilionsByRouteNumberAndDirection(connection);
			List<Station> station = instance.get(args[1]);
			for(Station s : station) System.out.println(s.toString());
		}
		else HelpMain();
	}
	static void ExecuteOperations()
	{
		
	}
	
	static void HelpMain()
	{
		System.out.println("-----------------BT-STOTELIU-TINKLAS----------------");

		System.out.println("Prasome programa paleisti taip:");
		ExecInstructions();
		System.out.println("Kur <args> yra:");
		HelpCommandsList();
		System.out.println("Jei tarp <args> pasitaiko zodziai neapskliausti <>, juos taip ir reiktu ivesti.");
		System.out.println("Keli zodziai viename argumente ivedami su kabutemis, pvz.: \"vienas du\".");
	}
	
	static void ExecInstructions()
	{
		System.out.println("java -cp JarFile.jar lt.baltictalents.stoteliutinklas.config.Application <args>");
	}
	
	static void HelpCommandsList()
	{
		System.out.println("PavilionsByRectangle <Longitude0> <Latitude0> <Longitude1> <Latitude1>");
		System.out.println("NearestPavilionByCoords <Longitude> <Latitude>");
		System.out.println("BusiestPavilionByRectangle <Longitude0> <Latitude0> <Longitude1> <Latitude1>");
		System.out.println("PavilionsByName <StationName>");
		System.out.println("PavilionsByRadius <Longitude> <Latitude> <angle/meters> angle/meters");
		System.out.println("RoutesByStationName <StationName>");
		System.out.println("RoutesByPavilionCoords <Longitude> <Latitude>");
		System.out.println("StationsAndPavilionsByRouteNumber <RouteNumber>");
		System.out.println("PavilionsByRouteNumberAndDirection <Route number and direction>");
		System.out.println("-----------------------------------------------------");
		System.out.println("Arba <args> rasykite tiesiai i ivesties laukeli.");
		System.out.println("-----------------------------------------------------");
	}
}
