
public class TargetFilter {
public double[] filter(double numdist, double[] target, double[][] targets, double[] want, int setsofdata, int[] bestcount, int besttarget, int amountofvariables){
	//Closest to Want
		//X-Axis
			numdist = Math.abs(targets[0][3] - want[3]);
			for(int i = 1; i < setsofdata; i++){
			    if(Math.abs(targets[i][3] - want[3]) < numdist){
			        bestcount[i]++;
			        numdist = Math.abs(targets[i][3] - want[3]);
			    }
			}
		//Y-Axis	
			numdist = Math.abs(targets[0][4] - want[4]);
			for(int i = 1; i < setsofdata; i++){
			    if(Math.abs(targets[i][4] - want[4]) < numdist){
			        bestcount[i]++;
			        numdist = Math.abs(targets[i][4] - want[4]);
			    }
			}
		//Height
			numdist = Math.abs(targets[0][6] - want[6]);
			for(int i = 1; i < setsofdata; i++){
			    if(Math.abs(targets[i][6] - want[6]) < numdist){
			        bestcount[i]++;
			        numdist = Math.abs(targets[i][6] - want[6]);
			    }
			}
		//Width	
			numdist = Math.abs(targets[0][5] - want[5]);
			for(int i = 1; i < setsofdata; i++){
			    if(Math.abs(targets[i][5] - want[5]) < numdist){
			        bestcount[i]++;
			        numdist = Math.abs(targets[i][5] - want[5]);
			    }
			}
	//Closest to Want End	
	//Find the best Target
		numdist = bestcount[0];
		for(int i = 1; i < setsofdata; i++){
		    if(bestcount[i] >= numdist){
		        besttarget = i;
		        if(besttarget < 0){
					System.out.println("I is less than 0");
				}
		        numdist = bestcount[i];
		    }
		}
	//Find the best Target End		
	//Set the Best Target
		for(int i=0; i < amountofvariables; i++){
			target[i] = targets[besttarget][i];
		}
	//Set the Best Target End
	System.out.println("Done TargetFilter");
	return target;	
}
}
