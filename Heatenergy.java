import java.util.Scanner;
public class Heatenergy {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
	
		System.out.println("Enter the value of the mass in grams.");
		double mass = reader.nextDouble();
		System.out.println("Enter the value of the starting temperture in Celsius.");
		double starttemp = reader.nextDouble();
		if(starttemp < -273.14)
			starttemp = -273.14;
		System.out.println("Enter the value of the ending temperture in Celsius.");
			double endtemp = reader.nextDouble();		
			if(endtemp < -273.14)
				endtemp = -273.14;
		
			String startphase = "Liquid";
			//assume Liquid is default
			if(starttemp > 100)
				startphase = "Gas";
			if(starttemp < 0)
				startphase = "Solid";
		
			String endphase = "Liquid";
			//Liquid is default
			if(endtemp > 100)
				endphase = "Gas";
			if(endtemp < 0)
				endphase = "Solid";
			
			System.out.println("Mass: " + mass + " grams");
			System.out.println("Starting Temperture: " + starttemp + " C‹" + " that's a " + startphase);
			System.out.println("Ending Temperture: " + endtemp + " C‹" + " that's a " + endphase + "\n"); 

			boolean endothermic = false;
			if(starttemp < endtemp)
				endothermic = true;
			
			double heatNRG = 0;
		
		//start with Solid
			if(startphase.equals("Solid")) {
				heatNRG += phasesolid(mass, starttemp, endtemp, endphase, endothermic);
			
				if(!endphase.equals("Solid")) {
					heatNRG += fuse(mass, endothermic);
					heatNRG += phaseliquid(mass, 0, endtemp, endphase, endothermic);
		}
			
				if(endphase.equals("Gas")) {
					heatNRG += vaporise(mass, endothermic);
					heatNRG += phasegas(mass, 100, endtemp, endphase, endothermic);
		}
}
		
		//start with Liquid
	if(startphase.equals("Liquid"))  {
			heatNRG += phaseliquid(mass , starttemp, endtemp, endphase, endothermic);
		
		if(endphase.equals("Solid")) {
			heatNRG += fuse(mass, endothermic);
			heatNRG += phasesolid(mass, 0, endtemp, endphase, endothermic);
			}			
		
		if(endphase.equals("Gas")) {
			heatNRG += vaporise(mass, endothermic);
			heatNRG += phasegas(mass, 100, endtemp, endphase, endothermic);
		}
}
		
		//start with Gas
	if(startphase.equals("Gas")) {
		heatNRG += phasegas(mass, starttemp, endtemp, endphase, endothermic);
			
		if(!endphase.equals("Gas")) {
			heatNRG += vaporise(mass, endothermic);
			heatNRG += phaseliquid(mass, 100, endtemp, endphase, endothermic);
	}
		
		if(endphase.equals("Solid")) {
			heatNRG += fuse(mass, endothermic);
			heatNRG += phasesolid(mass, 0, endtemp, endphase, endothermic);
		}
}	
				
	System.out.println("Total heat energy: " + round(heatNRG) + " kJ");
		
	
}//main stop
		
public static double phasesolid(double mass, double starttemp,double endtemp, String endphase, boolean endothermic) {
	if(!endphase.equals("Solid"))
		endtemp = 0;
	double NRGchange = round(mass*0.002108*(endtemp - starttemp));
	if(endothermic)
		System.out.println("Heating (Solid): " + NRGchange + (" kJ"));
	else 
		System.out.println("Cooling (Solid): " + NRGchange + (" kJ"));
			
	return NRGchange;
	}
		
public static double phasegas(double mass, double starttemp, double endtemp, String endphase, boolean endothermic) {
	if(!endphase.equals("Gas"))
		endtemp = 100;
	double NRGchange = round(mass*0.001996*(endtemp - starttemp));
	if(endothermic) 
			System.out.println("Heating (Gas): " + NRGchange + "kJ");	
	else 
			System.out.println("Cooling (Gas): " + NRGchange + "kJ");	
	return NRGchange;
}
	
public static double phaseliquid(double mass, double starttemp, double endtemp, String endphase, boolean endothermic) {
	if(endphase.equals("Solid"))
		endtemp = 0;
	if(endphase.equals("Gas")) 
		endtemp = 100;
	double NRGchange = round(mass*0.004184*(endtemp - starttemp));
	if(endothermic) 
			System.out.println("Heating (Liquid): " + NRGchange + "kJ");
	else 
			System.out.println("Cooling (Liquid): " + NRGchange + "kJ");
	return NRGchange;
}
	
public static double fuse(double mass, boolean endothermic){				
	double NRGchange;
	if(endothermic) {
		NRGchange = round(0.333*mass);
		System.out.println("Melting: " + NRGchange + "kJ");
	}
	else { 
		NRGchange = round(-0.333*mass);
		System.out.println("Freezing: " + NRGchange + "kJ");
		}
	return NRGchange;
	}
	
public static double vaporise(double mass, boolean endothermic){				
	double NRGchange;
	if(endothermic) {
		NRGchange = round(2.257*mass);
		System.out.println("Evaporation: " + NRGchange + "kJ");
	}
	else {
		NRGchange = round(-2.257*mass);
		System.out.println("Condensaiton: " + NRGchange + "kJ");
	}
		
	return NRGchange;
		
}
	
public static double round(double x) {
	x *= 1000;
	if(x > 0) 
		return (int)((x + 0.5)/1000.0);
	else 
		return (int)((x - 0.5)/1000.0);
	}
	
}
