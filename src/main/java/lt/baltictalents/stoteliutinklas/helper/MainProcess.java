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
import lt.baltictalents.stoteliutinklas.data.database.DatabaseOperations;
import lt.baltictalents.stoteliutinklas.data.layer.*;
//Susikuriam .jar faila per file>export
//Paleidziam per cmd: java -cp JarFile.jar lt.baltictalents.stoteliutinklas.config.Application

public class MainProcess {
	
	static String dbUrl = null;
	
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
						if(incomingArgs[0].toLowerCase().equals("help") || incomingArgs[0].toLowerCase().equals("?")) HelpMain();
						else previousQueryResults = SelectOperations(incomingArgs, previousQueryResults);
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
		RoutesListFactory routesConnection = new RoutesListFactory();
		
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
		else if (args[0+s].equalsIgnoreCase("SameStationsByMoreRoutes")) 
		{
			SameStationsByMoreRoutes instance = new SameStationsByMoreRoutes(connection);
			List<Station> station = instance.findMoreRoutes(Integer.parseInt(args[1+s]));
			for(Station ss : station) System.out.println(ss.toString());
			ret = station;
		}
		else if (args[0+s].equalsIgnoreCase("SameStationsByCrossingRoutes")) 
		{
			SameStationsByCrossingRoutes instance = new SameStationsByCrossingRoutes(connection);
			List<String> station = instance.get(args[1+s], args[2+s]);
			for(String ss : station) System.out.println(ss);
			//ret = station;
		}
		
		//SPRINT 3 code below----------------------------------------------------------------------------
		else if (args[0+s].equalsIgnoreCase("init")) //Create Stations & Routes tables & insert from list
		{
			dbUrl = DatabaseOperations.initializeDatabase(connection, routesConnection, args[1+s]);
			if(dbUrl!=null)
			{
				connection.setConnectionType(3);// DB AS VIRTUAL
				connection.SetStoteles(DatabaseOperations.getPavilionsByOldestDate(dbUrl));
				//routesConnection.SetRoutes <------ Nothing to do with routes
			}
			ret = null;
		}
		else if (args[0+s].equalsIgnoreCase("SetPavilionDate")) //Stations set (or update) pavilion date
		{
			DatabaseOperations.setPavilionDate(Integer.parseInt(args[1+s]), args[2+s], dbUrl);
			
			ret = null;
		}
		//
		else if (args[0+s].equalsIgnoreCase("_sts_debug_")) //StationsDatabaseTableTogetStotelesList
		{
			DatabaseOperations.StationsDatabaseTableTogetStotelesList(connection);
			
			ret = null;
		}
		else if (args[0+s].equalsIgnoreCase("TouchPavilionDate")) //touchPavilionDate
		{
			DatabaseOperations.touchPavilionDate(Integer.parseInt(args[1+s]), dbUrl);
			
			ret = null;
		}
		else if (args[0+s].equalsIgnoreCase("PavilionsByOldestDate")) //getPavilionsByOldestDate
		{
			ret = DatabaseOperations.getPavilionsByOldestDate(dbUrl);
			for(Station ss : ret) System.out.println(ss.toString());
			
		}
		else if (args[0+s].equalsIgnoreCase("PavilionsByServiceTime")) //getPavilionsByOldestDate
		{
			PavilionsByServiceTime instance = new PavilionsByServiceTime();
			List<Station> stations = instance.get(Integer.parseInt(args[1+s]), dbUrl);
			
			if(args[0].equalsIgnoreCase("filter") && stationList!=null)
			{
				stationList.retainAll(stations);
				stations = stationList;
			}
			
			for(Station ss : stations) System.out.println(ss.toString());
			ret = stations;
		}
		else HelpMain();
		
		return ret;
	}
	
	
	static void HelpMain()
	{
		System.out.println("-----------------BT-STOTELIU-TINKLAS----------------");
		System.out.print("Duombazes statusas: "+DatabaseInitStatus());
		System.out.println(" Prasome programa paleisti taip:");
		ExecInstructions();
		System.out.println("Kur <args> yra:");
		HelpCommandsList();
		System.out.println("Jei tarp <args> pasitaiko zodziai neapskliausti <>, juos taip ir reiktu ivesti.");
		System.out.println("Keli zodziai viename argumente ivedami su kabutemis, pvz.: \"vienas du\".");
	}
	
	private static String DatabaseInitStatus() {
		if(dbUrl!=null) return "VEIKIA -> " + dbUrl;
		else return "NEINICIALIZUOTA. Inicializavimui iveskite: init <direktorija>/<failo_pavadinimas>.db\n"
				+ "Pvz.: init C:/db.db\n"
				+ "DIREKTORIJA VARTOTOJO PRIVALO BUTI SUKURTA PRIES ATLIEKANT SIA OPERACIJA\n";
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
		System.out.println("5. SameStationsByCrossingRoutes <Route1> <Route2>");
		System.out.println("6. SameStationPavilionsByRange <RangeInMeters>");
		System.out.println("7. SameStationsByMoreRoutes <PavilionsSize>");
		System.out.println("---------------------SPRINT 3--------------------------------");
		System.out.println("4. SetPavilionDate <PRIMARY_KEY> <date as YYYY-MM-DD>");
		System.out.println("5. TouchPavilionDate <PRIMARY_KEY>");
		System.out.println("6. PavilionsByOldestDate");
		System.out.println("7. PavilionsByServiceTime <ThresholdDays>");
		System.out.println("-----------------------------------------------------");
		System.out.println("Arba <args> rasykite tiesiai i ivesties laukeli.");
		System.out.println("Noredami apsibrezti regiona, pirma iveskite uzklausa pagal ta regiona, o sekancia");
		System.out.println("uzklausa rasykite pradedami zodeliu filter, pvz:");
		System.out.println("PavilionsByName <StationName>");
		System.out.println("filter PavilionsByRectangle <Longitude0> <Latitude0> <Longitude1> <Latitude1>");
		System.out.println("-----------------------------------------------------");
	}
}
