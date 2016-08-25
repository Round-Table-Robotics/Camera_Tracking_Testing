import java.util.Random;
public class CameraTrackingMain {
public static void main(String[] args) {
	
	//Instances
	Random gen=new Random();
	Decoder decoder=new Decoder();
	TargetFilter filter=new TargetFilter();
	AligntoTarget align=new AligntoTarget();
	
	//Variables
	int syncbyte = -1;
	String blocksync = "0xaa55";
	//Pixy Data
		//How Much Data
			int setsofdata = 25;
			int amountofbytes = 14;
			int amountofvariables = 7;
		//Data From Pixy
			String[] data = new String[(amountofbytes * setsofdata)];
		//Sorted Data From Pixy	
			String[][] objects = new String[setsofdata][amountofbytes];
		//Converted Data From Pixy	
		double[][] targets = new double[setsofdata][amountofbytes];
	//Find Best Target
		double numdist = -1; //Distance between numbers and closest
		int[] best = new int[4]; //x, y, height, width
		int[] bestcount = new int[4];
		int besttarget = -1;
		double[] target = new double[amountofvariables];
	//Targeting System Variables
		boolean fixed = false,xgood = false,ygood = false,hgood = false,wgood = false;
		boolean strafe = false;
	//Robot Variables
		double LeftMotor = 0,RightMotor = 0;
		boolean auto = false;
		double oldwidth = 0;
		boolean shot = false, trigger = true;
	//Target What is Looking For
		double[] want = new double[amountofvariables];
		double[] highrange = new double[amountofvariables];
		double[] lowrange = new double[amountofvariables];
		
		//Goal Values Hard Coded
			//View is 640 x 400
			want[3] = 200; //X Center
			want[4] = 320; //Y Center
			want[6] = 80; //Height
			want[5] = 40; //Width		
		
		//Range That Goal Can Be within
		//For Example
		//(want x-axis) + (low range) > Targets X < (want x-axis) + (high range)
		//If above is true Goal is Okay. /\
			//X Center
			highrange[3] = 5; //Must Be Positive or 0
			lowrange[3] = -5; //Must Be Negative or 0
			//Y Center
			highrange[4] = 5; //Must Be Positive or 0
			lowrange[4] = -5; //Must Be Negative or 0
			//Height
			highrange[6] = 5; //Must Be Positive or 0
			lowrange[6] = -5; //Must Be Negative or 0
			//Width
			highrange[5] = 5; //Must Be Positive or 0
			lowrange[5] = -5; //Must Be Negative or 0
	
		//Note: Want, High Range and Low Range should probably be able to be changed from the SmartDash Board	
			
			//Init Should Not Run More than Once
				//Set the Ranges to actual Ranges
				for(int i=3; i < amountofvariables; i++){
					highrange[i] = highrange[i] + want[i];
					lowrange[i] = lowrange[i] + want[i];
				}
	
	/* How Data is Sent
	 * Bytes    16-bit word    Description
		----------------------------------------------------------------
		0, 1     y              sync: 0xaa55=normal object, 0xaa56=color code object
		2, 3     y              checksum (sum of all 16-bit words 2-6)
		4, 5     y              signature number
		6, 7     y              x center of object
		8, 9     y              y center of object
		10, 11   y              width of object
		12, 13   y              height of object
	 */
	/* How Data is Stored
	 * Bytes    Description
		----------------------------------------------------------------
		0      sync: 0xaa55=normal object, 0xaa56=color code object
		1      checksum (sum of all 16-bit words 2-6)
		2      signature number
		3      x center of object
		4      y center of object
		5      width of object
		6      height of object
	 */
		for(int i = 0; i < (amountofbytes * setsofdata); i++){
			if(i % 14 == 0 || i == 0){
				data[i] = blocksync;
				//System.out.println("Data["+i+"] = "+data[i]);
			}
			else{
				data[i] = Integer.toString(gen.nextInt(600));
				//System.out.println("Data["+i+"] = "+data[i]);
			}
		}
		
	//while(!shot && trigger){ //Runs until the trigger is let go or ball is shot 
		//Decoder
		//Takes in all Data From Pixy and Organizes it for Conversion
		objects = decoder.decode(amountofbytes, blocksync, data, syncbyte, objects); 
		//Convert
		//Takes in the objects and converts them from strings to bytes to ints into usable numbers 
		targets = decoder.convert(amountofbytes, besttarget, objects, targets); 
		for(int ii = 0; ii < setsofdata; ii++){
			for(int i=0; i< amountofbytes; i++){
				System.out.println(targets[ii][i]);
			}
		}
		//Filter Targets
		// Takes in the Use able numbers and outputs the correct target will also sort out other targets
		target = filter.filter(numdist, target, targets, want, setsofdata, best, bestcount, besttarget, amountofvariables); 
		//Align to Target
		//Takes in the Correct Target and Lines the robot up to shoot and shoots
		shot = align.align(shot, fixed, xgood, ygood, hgood, wgood, auto, strafe, oldwidth, target, want, highrange, lowrange, LeftMotor, RightMotor);  
	//}	
			
		
}
}
