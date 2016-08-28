
public class AligntoTarget {
public boolean align(boolean shot, boolean fixed, boolean xgood, boolean ygood,boolean hgood, boolean wgood, boolean auto, boolean strafe, double oldwidth, double[] target, double[] want, double[] highrange, double[] lowrange, double LeftMotor, double RightMotor){
	fixed = false;
	xgood = false;
	ygood = false;
	hgood = false;
	wgood = false;
	//Height of Target First
		if(target[6] >= lowrange[6] && fixed == false){
			System.out.println("Too Close");
			LeftMotor = (1 - ((target[6] * -1)/want[6]));
			RightMotor = (1 - ((target[6] * -1)/want[6]));
			fixed = true;
		}
		else if(target[6] <= highrange[6] && fixed == false){
			System.out.println("Too Far");
			LeftMotor = (target[6]/want[6]);
			RightMotor = (target[6]/want[6]);
			fixed = true;
		}
		else if(target[6] >= lowrange[6] && target[6] <= highrange[6]){
			//Size Good
			LeftMotor = 0;
			RightMotor = 0;
			hgood = true;
		}
		
	//X-Axis
		if(target[3] >= lowrange[3] && fixed == false){
			//Turn Left
			System.out.println("More than X Fix");
			LeftMotor = (1 - (want[3]/target[3]));
			RightMotor = ((1 - (want[3]/target[3])) * -1.0);
			fixed = true;
		}
		else if(target[3] <= highrange[3] && fixed == false){
			//Turn Right
			System.out.println("Less than X Fix");
			LeftMotor = ((1 - (target[3]/want[3])) * -1.0);
			RightMotor = (1 - (target[3]/want[3]));	
			fixed = true;
		}
		else if(fixed == false && target[3] >= lowrange[3] && target[3] <= highrange[3]){
			//Placement Good
			LeftMotor = 0;
			RightMotor = 0;
			xgood = true;
		}
	//Y-Axis of the Target Third Can only change if Robot has Adjustable Angle //Works same as Height if No Adjustable Angle
	//Angle Adjust
		if(fixed == false && hgood == true && target[4] < lowrange[4] && target[4] > highrange[4]){
			System.out.println("ERROR Target Not in Range! UNABLE TO FIX!");
		}
		else if(target[4] >= lowrange[4] && target[4] <= highrange[4]){
			ygood = true;
		}
		else if(fixed == false){
			System.out.println("ERROR Y-AXIS UNABLE TO FIX AND HEIGHT NO FIX");
		}
		
	//Width of Target Last (HAS TO STRAFE SHOULD THROW ERROR IF ROBOT CANNOT STRAFE) //Shouldn't really matter with turning of robot But here anyways
		if(target[5] > highrange[5] && strafe == true && fixed == false){
			//Nothing to Do if Width too big Should Reverse because of Error
			System.out.println("TOO CLOSE CAMERA ERROR!");
			//LeftMotor = (width/want[5]);
			//RightMotor = (width/want[5]);
			//fixed = true;
		}
		else if(target[5] < lowrange[5] && strafe == true && fixed == false){
			oldwidth = target[5];
			//Strafe Left
			if(oldwidth > target[5]){
				//Strafe Right
				System.out.println("Strafing Right was Better");
			}
			else if(oldwidth < target[5]){
				System.out.println("Strafing Left was Better");
				//Strafe untill
			}
			else{
				System.out.println("STRAFING DIDNT WORK ERROR!");
			}
			fixed = true;
		}
		else if(strafe == false && fixed == false && (target[5] < lowrange[5] || target[5] > highrange[5])){
			System.out.println("CANNOT STRAFE MAY NEED TO RECENTER ROBOT ON TARGET!");
		}
		else if(fixed == false && target[5] >= lowrange[5] && target[5] <= highrange[5]){
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
				shot = true;
			}
			if(auto == false){
				System.out.println("Wait For Trigger.");
				shot = true;
			}
		}
	System.out.println("LeftMotor: "+LeftMotor+" RightMotor: "+RightMotor);	
	return shot;	
}
}
