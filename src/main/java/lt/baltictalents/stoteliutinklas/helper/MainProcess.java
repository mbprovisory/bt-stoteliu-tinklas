package lt.baltictalents.stoteliutinklas.helper;
import lt.baltictalents.stoteliutinklas.operations.*;
import lt.baltictalents.stoteliutinklas.operations.PavilionsByRadius.Measurement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import lt.baltictalents.stoteliutinklas.data.beans.Station;
import lt.baltictalents.stoteliutinklas.data.layer.*;
//Susikuriam .jar faila per file>export
//Paleidziam per cmd: java -cp JarFile.jar lt.baltictalents.stoteliutinklas.config.Application

public class MainProcess {
	public static void HandleArgs(String[] args)
	{
		boolean bOuterCycle = true;
		while(bOuterCycle)
		{
			
			try
			{
				//HelpMain();
				
				List<Station> previousQueryResults = null, queryResults = null;
				String[] incomingArgs;
				
				if(args.length!=0) {
					if (args[0].toLowerCase().equals("help") || args[0].toLowerCase().equals("?"))
					{
						HelpMain();
					}
					else
					{
						SelectOperations(args, null);	
						HelpMain();
					}
				}
				else HelpMain();
				
				
				boolean bCycle = true;
				while (bCycle)
				{
					
					System.out.println("Iveskite uzklausa (pagalbai gauti rasykite ? arba help, isejimui - exit");
					Scanner scanner = new Scanner(System.in);
					String s = scanner.nextLine();
					if (s.equalsIgnoreCase("exit")||s.equalsIgnoreCase("quit"))
					{
						bCycle = false;
						bOuterCycle = false;
					}
					else
					{
						incomingArgs = Helper.RegexParser(s);
						//shift logic
						previousQueryResults = SelectOperations(incomingArgs, previousQueryResults);
					}
					
					
				}
				
			}
			catch (Exception e)
			{
				System.out.println("Neteisinga uzklausa.");
			}
		}
	}
	
	private static List<Station> SelectOperations(String []args, List<Station> stationList)
	{
		List<Station> ret = new LinkedList<Station>();
		int s; //shifter
		DataListFactory connection = new DataListFactory();
		if(args[0].toLowerCase().equals("filter")) 
		{ 
			connection.SetStoteles(stationList);
			connection.setConnectionType(DataListFactory.VIRTUALDB);
			s = 1;
		}
		else s = 0;
		
		
		if (args[0+s].equalsIgnoreCase("PavilionsByRectangle")) //OK
		{
			PavilionsByRectangle  instance = new PavilionsByRectangle(connection);
			List<Station> station = instance.get(args[1+s], args[2+s], args[3+s], args[4+s]);
			for(Station ss : station) System.out.println(ss.toString());
			ret = station;	
		}
		else if (args[0+s].equalsIgnoreCase("NearestPavilionByCoords"))//OK
		{
			NearestPavilionByCoords npc = new NearestPavilionByCoords(connection);
			ret.add(new NearestPavilionByCoords(connection).get(args[1+s], args[2+s]));
			System.out.println(ret.get(0));
		}
		else if (args[0+s].equalsIgnoreCase("BusiestPavilionByRectangle"))//OK
		{
			BusiestPavilionByRectangle instance = new BusiestPavilionByRectangle(connection);
			List<Station> station = instance.get(args[1+s], args[2+s], args[3+s], args[4+s]);
			for(Station ss : station) System.out.println(ss.toString());
			ret = station;
		}
		else if (args[0+s].equalsIgnoreCase("PavilionsByName")) //OK
		{
			PavilionsByName instance = new PavilionsByName(connection);
			List<Station> station = instance.get(args[1+s]);
			for(Station ss : station) System.out.println(ss.toString());
			ret = station;
		}
		else if (args[0+s].equalsIgnoreCase("PavilionsByRadius")) //OK
		{
			PavilionsByRadius instance = new PavilionsByRadius(connection);
			
			Measurement m= Measurement.METERS; 
			if(args[3+s].toLowerCase().equals("angle")) m = Measurement.RADIUS;
			
			List<Station> station = instance.get(args[1+s],args[2+s], Double.parseDouble(args[3+s]), m);
			for(Station ss : station) System.out.println(ss.toString());
			ret = station;
		}
		else if (args[0+s].equalsIgnoreCase("RoutesByStationName"))//OK
		{
			RoutesNumbersByStationName instance = new RoutesNumbersByStationName(connection);
			List<String[]> station = instance.get(args[1+s]);
			for(String[] ss : station) 
			{
				for (String sss: ss)
				{
					System.out.print(sss + " | ");
				}
				System.out.println();
			}
			//ret = station; invalid logic
		}
		else if (args[0+s].equalsIgnoreCase("RoutesByPavilionCoords"))//OK
		{
			RoutesByPavilionCoords instance = new RoutesByPavilionCoords(connection);
			ret = instance.get(Double.parseDouble(args[1+s]), Double.parseDouble(args[2+s]));
			
		}
		else if (args[0+s].equalsIgnoreCase("StationsAndPavilionsByRouteNumber")) //OK?
		{
			StationsAndPavilionsByRouteNumber instance = new StationsAndPavilionsByRouteNumber(connection);
			List<Station> station = instance.get(args[1+s]);
			for(Station ss : station) System.out.println(ss.toString());
			ret = station;
		}
		/*
		 * bugs with Troleibusas 16: Pašilaičiai - Stotis  => Pašilaiciai - Stotis
		 */
		else if (args[0+s].equalsIgnoreCase("PavilionsByRouteNumberAndDirection")) //BUGS
		{
			PavilionsByRouteNumberAndDirection instance = new PavilionsByRouteNumberAndDirection(connection);
			List<Station> station = instance.get(args[1+s]);
			for(Station ss : station) System.out.println(ss.toString());
			ret = station;
		}
		else if (args[0+s].equalsIgnoreCase("SameStationPavilionsByRange")) //TESTING
		{
			SameStationPavilionsByRange instance = new SameStationPavilionsByRange(connection);
			Set<Station> station = instance.get(Integer.parseInt(args[1+s]));
			
			ret = new ArrayList<Station>();
			ret.addAll(station);
			
			Comparator<Station> comp = (Station a, Station b) -> {
			    return a.getName().compareTo(b.getName());
			};
			
			Collections.sort(ret, comp);
			
			for(Station ss : ret) System.out.println(ss.toString());
		}
		else if (args[0+s].equalsIgnoreCase("SameStationsByMoreRoutes")) //BUGS
		{
			SameStationsByMoreRoutes instance = new SameStationsByMoreRoutes(connection);
			List<Station> station = instance.findMoreRoutes(Integer.parseInt(args[1+s]));
			for(Station ss : station) System.out.println(ss.toString());
			ret = station;
		}
		else HelpMain();
		
		return ret;
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
		System.out.println("---------------------SPRINT 1--------------------------------");
		System.out.println("1. PavilionsByRectangle <Longitude0> <Latitude0> <Longitude1> <Latitude1>");
		System.out.println("2. NearestPavilionByCoords <Longitude> <Latitude>");
		System.out.println("3. BusiestPavilionByRectangle <Longitude0> <Latitude0> <Longitude1> <Latitude1>");
		System.out.println("4. PavilionsByName <StationName>");
		System.out.println("5. PavilionsByRadius <Longitude> <Latitude> <angle/meters> angle/meters");
		System.out.println("6. RoutesByStationName <StationName>");
		System.out.println("7. RoutesByPavilionCoords <Longitude> <Latitude>");
		System.out.println("8. StationsAndPavilionsByRouteNumber <RouteNumber>");
		System.out.println("9. PavilionsByRouteNumberAndDirection <Route number and direction>");
		System.out.println("---------------------SPRINT 2--------------------------------");
		System.out.println("5. Genadijaus");
		System.out.println("6. SameStationPavilionsByRange <RangeInMeters>");
		System.out.println("7. SameStationsByMoreRoutes <PavilionsSize>");
		System.out.println("-----------------------------------------------------");
		System.out.println("Arba <args> rasykite tiesiai i ivesties laukeli.");
		System.out.println("Noredami apsibrezti regiona, pirma iveskite uzklausa pagal ta regiona, o sekancia");
		System.out.println("uzklausa rasykite pradedami zodeliu filter, pvz:");
		System.out.println("PavilionsByName <StationName>");
		System.out.println("filter PavilionsByRectangle <Longitude0> <Latitude0> <Longitude1> <Latitude1>");
		System.out.println("-----------------------------------------------------");
	}
}
