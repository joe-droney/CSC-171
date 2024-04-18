
import java.util.Scanner;
import java.util.InputMismatchException;
public class CalculatorHW {

	public static void main(String[] args) {
	
		Scanner s = new Scanner(System.in);
	
		
		//System.out.println("<number> <unit> to <unit> (nothing to quit):");
		
		while (s.hasNextLine()) {
			String line = s.nextLine();
			
			if (line. length () == 0)
				break;
			
			Scanner lineScanner = new Scanner(line);
		try {
			double value = lineScanner.nextDouble () ;
			String u1 = lineScanner.next().toLowerCase();
			lineScanner.next();
			String u2 = lineScanner.next () . toLowerCase();
			
			if (u1.equals ("f") && u2.equals ("c" )) {
				double result = (value - 32) * 5 / 9.0;
				System.out.printf("%.3f%n" , result);
			
			} else if  (u1.equals ("ft") && u2.equals ("m")){
				double result = (value * 0.3048);
				System.out.printf("%.3f%n" , result);
	
			}else if  (u1.equals ("c") && u2.equals ("f")) {
				double result = (value * 9.0/5.0) + 32;
				System.out.printf("%.3f%n" , result);
			
			}else if  (u1.equals ("m") && u2.equals ("ft")) {
				double result = value * 3.28084 ;
				System.out.printf("%.3f%n" , result);
			
			}else if  (u1.equals ("h") && u2.equals ("min")) {
				double result = value * 60;
				System.out.printf("%.3f%n" , result);
			
			}else if  (u1.equals ("min") && u2.equals ("h")) {
				double result = value/60 ;
				System.out.printf("%.3f%n" , result);
			
			}else if  (u1.equals ("lb") && u2.equals ("kg")) {
				double result = value * 0.4535 ;
				System.out.printf("%.3f%n" , result);
			
			}else if  (u1.equals ("kg") && u2.equals ("lb")) {
				double result = value * 2.20462;
				System.out.printf("%.3f%n" , result);
				
			}else {
				System.out.println("***Unable to convert from " + u1 + " to "  +  u2 + " ***");
				
			}
			
		} catch (InputMismatchException e) {
			
		System.out.println("Invalid input format. Please enter a valid number and units.");
			
		}
		
	}
		System.out.println( "Goodbye.");
}
}