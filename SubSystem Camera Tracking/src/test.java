import java.util.Random;
public class test {
public static void main(String[] args) {
	
	Random gen = new Random();
	
	int syncbyte = -1;
	String blocksync = "TEST";
	int setsofdata = 25;
	int amountofbytes = 14;
	String[][] objects = new String[setsofdata][amountofbytes];
	//Converted Data From Pixy	
	String[] data = new String[setsofdata*amountofbytes];
	double[][] targets = new double[setsofdata][7];
	
	for(int i = 0; i < (amountofbytes * setsofdata); i++){
		if(i % 14 == 0){
			data[i] = blocksync;
			//System.out.println("Data["+i+"] = "+data[i]);
		}
		else{
			data[i] = Integer.toString(gen.nextInt(600));
			System.out.println("Data["+i+"] = "+data[i]);
		}
	}
	
	for(int ii = 0; ii < setsofdata; ii++){
		for(int i = 0; i < (amountofbytes); i++){
			if(data[i].equals(blocksync)){
				objects[ii][0] = data[i];
				syncbyte = i;
				break;
			}
		}
		for(int i = 1; i < (amountofbytes); i++){
			objects[ii][i] = data[(syncbyte + i)];
		}
	}

	
	for(int ii = 0; ii < setsofdata; ii++){
		for(int i=0; i< amountofbytes; i++){
			System.out.println(objects[ii][i]);
		}
	}
	
	int iii = 1;
	for(int ii = 0; ii < setsofdata; ii++){
		iii = 1;
		for(int i=2; i < amountofbytes; i=i+2){
			targets[ii][iii] = Integer.parseInt(objects[ii][i]) + Integer.parseInt(objects[ii][i+1]);
			//System.out.println("III "+iii+"  II "+ii+"  I "+i);
			iii++;
		}
	}
	
	for(int ii = 0; ii < setsofdata; ii++){
		for(int i=0; i< 7; i++){
		System.out.println(targets[ii][i]);
		}
	}
	
}
}
