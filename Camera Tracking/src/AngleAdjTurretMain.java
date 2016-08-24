import java.util.Scanner;
public class AngleAdjTurretMain {
public static void main(String[] args) {

	//Instances
	Scanner scan=new Scanner(System.in);
	
	//Variables
		//Robot Has
		int targetnumb, x_axis, y_axis, height, width; //Output from Pixy
		//View is 640 x 400
		int wantx, wanty, wanth, wantw; //Target From Testing
		boolean xgood, ygood, hgood, wgood; //Make Sure All are good
		int oldwidth; //For Checking if Strafing is better or worse 
		int LeftMotor, RightMotor, hoodangle; //Robot Variables
		boolean SeesTarget, fixed; //SeesTarget is for alerting Driver and Causes Experiment Code to Run //Fixed Causes the Correction to not Fight other Correction
		//For Sake of Experiment
		boolean strafe = true; //If Robot Can Strafe or Not.
		boolean angleadj = true; //If Robot Can adjust its shooting angle or not.
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
			//Add Alert Driver Here like Virate Controller
		}
		fixed = false;
		xgood = false;
		ygood = false;
		hgood = false;
		wgood = false;
		//Height of Target First
			if(height > wanth && fixed == false){
				LeftMotor = (1 - ((height * -1)/wanth));
				RightMotor = (1 - ((height * -1)/wanth));
				fixed = true;
			}
			else if(height < wanth && fixed == false){
				LeftMotor = (height/wanth);
				RightMotor = (height/wanth);
				fixed = true;
			}
			else{
				//Size Good
				LeftMotor = 0;
				RightMotor = 0;
				hgood = true;
			}
			
		//X-Axis
			if(x_axis > wantx && fixed == false){
				//Turn Left
				LeftMotor = (1 - (wantx/x_axis));
				RightMotor = (1 + ((wantx/x_axis) * -1));
			}
			else if(x_axis < wantx && fixed == false){
				//Turn Right
				LeftMotor = (1 + ((x_axis/wantx) * -1));
				RightMotor = (1 - (x_axis/wantx));	
			}
			else{
				//Placement Good
				LeftMotor = 0;
				RightMotor = 0;
				xgood = true;
			}
		//Y-Axis of the Target Third Can only change if Robot has Adjustable Angle //Works same as Height if No Adjustable Angle
		//Angle Adjust
			if(angleadj == true){ 
				//Change Hood Angle
				//7 feet High Goal
				//Height of Robot
				//Angle to Goal from Base of Shooter // Hope is Good
				//Distance is % size 
				//Not Needed if Adjusted Based on Close to Goal
			}
			//NO Angle Adjust
			else{
				if(y_axis > wanth && fixed == false){
					LeftMotor = (1 - ((y_axis * -1)/wanty));
					RightMotor = (1 - ((y_axis * -1)/wanty));
					fixed = true;
				}
				else if(y_axis < wanty && fixed == false){
					LeftMotor = (y_axis/wanty);
					RightMotor = (y_axis/wanty);
					fixed = true;
				}
				else{
					//Placement Good
					LeftMotor = 0;
					RightMotor = 0;
					ygood = true;
				}
			}
			
		//Width of Target Last (HAS TO STRAFE SHOULD THROW ERROR IF ROBOT CANNOT STRAFE) //Shouldn't really matter with turning of robot But here anyways
			if(width > wantw && strafe == true && fixed == false){
				//Nothing to Do if Width too big Should Reverse because of Error
				System.out.println("TOO CLOSE CAMERA ERROR! Trying to fix Anyway...");
				LeftMotor = (width/wantw);
				RightMotor = (width/wantw);
				fixed = true;
			}
			else if(width < wantw && strafe == true && fixed == false){
				oldwidth = width;
				//Strafe Left
				if(oldwidth > width){
					//Strafe Right
					System.out.println("Strafing Right was Better");
				}
				else if(oldwidth < width){
					System.out.println("Strafing Left was Better");
				}
				else{
					System.out.println("STRAFING DIDNT WORK ERROR!");
				}
				fixed = true;
			}
			else if(strafe == false && fixed == false){
				System.out.println("CANNOT STRAFE MAY NEED TO RECENTER ROBOT ON TARGET!");
			}
			else{
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
				}
				if(auto == false){
					System.out.println("Wait For Trigger.");
				}
			}
	//End Tracking Code
	}	
}
}
