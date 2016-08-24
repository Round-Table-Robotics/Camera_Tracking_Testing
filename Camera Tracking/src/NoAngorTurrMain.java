import java.util.Scanner;
public class NoAngorTurrMain {
public static void main(String[] args) {

	//Instances
	Scanner scan=new Scanner(System.in);
	
	//Variables
		//Robot Has
		double targetnumb, x_axis, y_axis, height, width; //Output from Pixy
		//View is 640 x 400
		//Variables to Decide which Goal is Correct
			double numdist;
			int best = 0,bestx = 0,besty = 0,besth = 0,bestw = 0;
			int done = 0;
			int waitfortargets = 15;
			double[] backtargetnumb = new double[waitfortargets];
			double[] backx_axis = new double[waitfortargets];
			double[] backy_axis = new double[waitfortargets];
			double[] backheight = new double[waitfortargets];
			double[] backwidth = new double[waitfortargets];
		//Selected Target Variables
			double xp = 0,yp = 0,hp = 0,wp = 0;
		double wantx, wanty, wanth, wantw; //Target From Testing
		boolean xgood, ygood, hgood, wgood; //Make Sure All are good
		double oldwidth; //For Checking if Strafing is better or worse 
		double LeftMotor = 0, RightMotor = 0; //Robot Variables
		boolean SeesTarget, fixed = false; //SeesTarget is for alerting Driver and Causes Experiment Code to Run //Fixed Causes the Correction to not Fight other Correction
		//For Sake of Experiment
		boolean strafe = false; //If Robot Can Strafe or Not.
		boolean auto = false; //If in Autonomous
	
	while(true){
	//User input
		//What Robot Sees
		System.out.println("Enter Target Number:");
		targetnumb = scan.nextInt();
		System.out.println("Enter X Axis:");
		x_axis = scan.nextInt();
		System.out.println("Enter Y Axis:");
		y_axis = scan.nextInt();
		System.out.println("Enter Height:");
		height = scan.nextInt();
		System.out.println("Enter Width:");
		width = scan.nextInt();
		//Correct Points
		wantx = 200;
		wanty = 320;
		wanth = 80;
		wantw = 40;
		
		
	
	//Start Tracking Code
		if(targetnumb > 0){
			SeesTarget = true;
			//Collect waitfortargets amount of data
			for(int i=0; i < waitfortargets; i++){
				backx_axis[i] = x_axis;
				backy_axis[i] = y_axis;
				backheight[i] = height;
				backwidth[i] = width;
			}
			//Find Which Data is the Closest to the Right.
			//X-Axis
				numdist = Math.abs(backx_axis[0] - wantx);
				for(int i = 1; i < waitfortargets; i++){
				    if(Math.abs(backx_axis[i] - wantx) < numdist){
				        bestx = i;
				        numdist = Math.abs(backx_axis[i] - wantx);
				    }
				}
			//Y-Axis	
				numdist = Math.abs(backy_axis[0] - wanty);
				for(int i = 1; i < waitfortargets; i++){
				    if(Math.abs(backy_axis[i] - wanty) < numdist){
				        besty = i;
				        numdist = Math.abs(backy_axis[i] - wanty);
				    }
				}
			//Height
				numdist = Math.abs(backheight[0] - wanth);
				for(int i = 1; i < waitfortargets; i++){
				    if(Math.abs(backheight[i] - wanth) < numdist){
				        besth = i;
				        numdist = Math.abs(backheight[i] - wanth);
				    }
				}
			//Width	
				numdist = Math.abs(backwidth[0] - wantw);
				for(int i = 1; i < waitfortargets; i++){
				    if(Math.abs(backwidth[i] - wantw) < numdist){
				        bestw = i;
				        numdist = Math.abs(backwidth[i] - wantw);
				    }
				}
			//Find which is Best
				best = (bestx + besty + besth + bestw)/4;
		}
	while(fixed == false){
		if(targetnumb == best){
			xp = x_axis;
			yp = y_axis;
			hp = height;
			wp = width;
		}
		fixed = false;
		xgood = false;
		ygood = false;
		hgood = false;
		wgood = false;
		//Height of Target First
			if(hp > wanth && fixed == false){
				System.out.println("Too Close");
				LeftMotor = (1 - ((hp * -1)/wanth));
				RightMotor = (1 - ((hp * -1)/wanth));
				fixed = true;
			}
			else if(hp < wanth && fixed == false){
				System.out.println("Too Far");
				LeftMotor = (hp/wanth);
				RightMotor = (hp/wanth);
				fixed = true;
			}
			else{
				//Size Good
				LeftMotor = 0;
				RightMotor = 0;
				hgood = true;
			}
			
		//X-Axis
			if(xp > wantx && fixed == false){
				//Turn Left
				System.out.println("More than X Fix");
				LeftMotor = (1 - (wantx/xp));
				RightMotor = ((1 - (wantx/xp)) * -1.0);
				fixed = true;
			}
			else if(xp < wantx && fixed == false){
				//Turn Right
				System.out.println("Less than X Fix");
				LeftMotor = ((1 - (xp/wantx)) * -1.0);
				RightMotor = (1 - (xp/wantx));	
				fixed = true;
			}
			else if(fixed == false){
				//Placement Good
				LeftMotor = 0;
				RightMotor = 0;
				xgood = true;
			}
		//Y-Axis of the Target Third Can only change if Robot has Adjustable Angle //Works same as Height if No Adjustable Angle
		//Angle Adjust
			if(fixed == false && hgood == true && yp != wanty){
				System.out.println("ERROR Target Too High! UNABLE TO FIX!");
			}
			else if(yp == wanty){
				ygood = true;
			}
			else if(fixed == false){
				System.out.println("ERROR Y-AXIS UNABLE TO FIX AND HEIGHT NO FIX");
			}
			
		//Width of Target Last (HAS TO STRAFE SHOULD THROW ERROR IF ROBOT CANNOT STRAFE) //Shouldn't really matter with turning of robot But here anyways
			if(wp > wantw && strafe == true && fixed == false){
				//Nothing to Do if Width too big Should Reverse because of Error
				System.out.println("TOO CLOSE CAMERA ERROR!");
				//LeftMotor = (width/wantw);
				//RightMotor = (width/wantw);
				//fixed = true;
			}
			else if(wp < wantw && strafe == true && fixed == false){
				oldwidth = wp;
				//Strafe Left
				if(oldwidth > wp){
					//Strafe Right
					System.out.println("Strafing Right was Better");
				}
				else if(oldwidth < wp){
					System.out.println("Strafing Left was Better");
					//Strafe untill
				}
				else{
					System.out.println("STRAFING DIDNT WORK ERROR!");
				}
				fixed = true;
			}
			else if(strafe == false && fixed == false && wp != wantw){
				System.out.println("CANNOT STRAFE MAY NEED TO RECENTER ROBOT ON TARGET!");
			}
			else if(fixed == false){
				//Size Good
				LeftMotor = 0;
				RightMotor = 0;
				wgood = true;
			}	
		//Check if Shooting is Good! Absolutely
			if(wgood && hgood && xgood && ygood && fixed == false){
				System.out.println("Everything Good!");
				if(auto){
					System.out.println("Shot Ball.");
					done = 1;
				}
				if(auto == false){
					System.out.println("Wait For Trigger.");
					done = 1;
				}
			}
	}		
	//End Tracking Code
			
	System.out.println("Left Motor: " + LeftMotor);
	System.out.println("Right Motor: " + RightMotor);
			
	}
	
}
}
