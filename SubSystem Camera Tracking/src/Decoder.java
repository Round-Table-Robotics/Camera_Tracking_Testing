
public class Decoder{
	public String[][] decode(int setsofdata, int amountofbytes, String blocksync, String[] data, int syncbyte, String[][] objects){
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
		System.out.println("Done Decode");
		return objects;
	}
	
	
	
	public double[][] convert(int amountofbytes, int setsofdata, int amountofvariables, String[][] objects, double[][] targets){
		int iii = 1;
		for(int ii = 0; ii < setsofdata; ii++){
			iii = 1;
			for(int i=2; i < amountofbytes; i=i+2){
				if((i+1) > setsofdata){
					break;
				}
				targets[ii][iii] = Integer.parseInt(objects[ii][i]) + Integer.parseInt(objects[ii][i+1]);
				iii++;
			}
		}
		System.out.println("Done Convert");
		return targets;
	}
	
	
	
	public static double convertBytes(int msb, int lsb)
    {
        if (msb < 0)
            msb += 256;
        double value = msb * 256;

        if (lsb < 0)
        {
            // lsb should be unsigned
            value += 256;
        }
        value += lsb;
        return value;
    }
	
}
