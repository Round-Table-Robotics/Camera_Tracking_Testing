public class Main {
public static void main(String[] args) {
	
	//Variables
	int amountofbytes = 14;
	int syncbyte = -1;
	int sort = 1;
	String blocksync = "0xaa55";
	String[] data = new String[(amountofbytes * 25)];
	String[] object = new String[amountofbytes];
	
	//Fake Data Creator
	for(int i = 0; i < (amountofbytes); i++){
		data[i] = "no";
	}
	
	//Swap the Sync Byte for the start
	data[4] = "0xaa55";
	data[1] = "1";
	data[2] = "2";
	data[3] = "3";
	data[0] = "4";
	data[5] = "5";
	data[6] = "6";
	data[7] = "7";
	data[8] = "8";
	data[9] = "9";
	data[10] = "10";
	data[11] = "11";
	data[12] = "12";
	data[13] = "13";
	
	
	//Decoder
		for(int i = 0; i < (amountofbytes); i++){
			if(data[i].equals(blocksync)){
				object[0] = data[i];
				syncbyte = i;
				break;
			}
		}
		for(int i = 1; i < (amountofbytes); i++){
			object[i] = data[(syncbyte + i)];
		}
	//Decoder End
		
	for(int i=0; i < (amountofbytes); i++){
		System.out.println("Object["+ i +"]: "+object[i]+"      Data["+ i + "]: "+ data[i]);
	}
		

}
}
