
public class TargetFilter {
public double[] filter(double numdist, double[] target, double[][] targets, double[] want, int setsofdata, int[] best, int[] bestcount, int besttarget, int amountofvariables){
	//Closest to Want
		//X-Axis
			numdist = Math.abs(targets[0][3] - want[3]);
			for(int i = 1; i < setsofdata; i++){
			    if(Math.abs(targets[i][3] - want[3]) < numdist){
			        best[0] = i;
			        numdist = Math.abs(targets[i][3] - want[3]);
			    }
			}
		//Y-Axis	
			numdist = Math.abs(targets[0][4] - want[4]);
			for(int i = 1; i < setsofdata; i++){
			    if(Math.abs(targets[i][4] - want[4]) < numdist){
			        best[1] = i;
			        numdist = Math.abs(targets[i][4] - want[4]);
			    }
			}
		//Height
			numdist = Math.abs(targets[0][6] - want[6]);
			for(int i = 1; i < setsofdata; i++){
			    if(Math.abs(targets[i][6] - want[6]) < numdist){
			        best[2] = i;
			        numdist = Math.abs(targets[i][6] - want[6]);
			    }
			}
		//Width	
			numdist = Math.abs(targets[0][5] - want[5]);
			for(int i = 1; i < setsofdata; i++){
			    if(Math.abs(targets[i][5] - want[5]) < numdist){
			        best[3] = i;
			        numdist = Math.abs(targets[i][5] - want[5]);
			    }
			}
	//Closest to Want End		
	//Find which is Best
		for(int i=0; i < best.length; i++){
			bestcount[i] = 0;
		}
		for(int i=0; i < best.length; i++){
			for(int ii=0; ii < best.length; i++){
				if(i > 3){
					break;
				}
				if(best[i] == best[ii]){
					bestcount[i]++;
				}
			}
			if(i>3){
				break;
			}
		}
		besttarget = best[0];
		for(int i = 1; i < best.length; i++){
		    if(bestcount[i] > besttarget){
		        besttarget = best[i];
		    }
		}
	//Find which is Best End
	//Set the Best Target
		for(int i=0; i < amountofvariables; i++){
			target[i] = targets[besttarget][i];
		}
	//Set the Best Target End
	System.out.println("Done TargetFilter");
	for(int i=0; i < 6; i++){
		System.out.println("Target["+i+"] = "+target[i]);
	}
	return target;	
}
}
