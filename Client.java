package Scenarios_Outline;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Client {

	public static void main(String[] args) throws IOException {
		
		
		InputStream is = Client.class.getClassLoader().getResourceAsStream("Formater/Names.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
		String currLine = new String();
		int size = 0;
		int count =0;
		int nameIndex =0;
		
		List<List<String>> nameLists =new ArrayList<>();
		nameLists.add(new ArrayList<String>());
		nameLists.add(new ArrayList<String>());
		nameLists.add(new ArrayList<String>());
		nameLists.add(new ArrayList<String>());
		
		
		while((currLine = br.readLine()) !=null) {
			System.out.println(currLine);
			
			StringTokenizer st = new StringTokenizer(currLine);
			
			if(!st.hasMoreTokens()){
			continue;	
		}
			
			String firstName = st.nextToken();
			String lastName = st.nextToken();
			nameLists.get(nameIndex % nameLists.size()).add(firstName);
			nameLists.get((nameIndex+1)% nameLists.size()).add(lastName);
			
			if(count % 2 == 0) {
				size++;
				}
			count++;
			nameIndex +=2;
			}
		System.out.println("Size:  " + size);
		
		String out = generateOutput(nameLists, size);
		System.out.println(out);
	}

	public static String generateOutput(List<List<String>> nameLists, int size) {
		int i = 0;
		String out = "";
		int count =1;
		int index =0;
		int nameIndex =0;
		
		int maxNameLength1 = maxLength(nameLists.get(0));
		int maxNameLength2 = maxLength(nameLists.get(1));
		int maxNameLength3 = maxLength(nameLists.get(2));
		int maxNameLength4 = maxLength(nameLists.get(3));
		int[]maxLengths = {maxNameLength1, maxNameLength2, maxNameLength3, maxNameLength4};
		
		//Applicant Filed 
		
		String [] appTypes = {"self", "Custodial Mother"," Custodial Father", "Court Appointed Legal Guardian",
				"Administrator of Estate", "Relative with Custody of Child", 
				"State Agency or State Licensed Agency with Legal Custody"};
		
		
		// ApplicantType
		
		out += "|applicantType | firstName | lastName | nhfirstName | nhlastName | \n\r";
		while (i < size) {
			if((i% 2)==0){
				System.out.println("Excuting! " + i + "," + padding(appTypes[index % (appTypes.length)], 28 ));
				out += "|" + padding(appTypes[index % (appTypes.length)], 31);
				index++;
			}
			
			String firstName = nameLists.get(nameIndex % nameLists.size()).get(i);
			String lastName = nameLists.get(nameIndex % nameLists.size()).get(i);
			
			String paddedFirstName = padding(firstName, maxLengths[nameIndex % nameLists.size()]);
			String paddedLastName = padding(lastName, maxLengths[(nameIndex+1) % nameLists.size()]);
			
			out += "|" + paddedFirstName + "|" + paddedLastName; 
			
			if(count % 2== 0) {
				out += "     |    \n";
			}
			i++;
			count++;
			nameIndex += 2;
		}
		return out;
		
	}

	public static String padding(String str, int maxLength) {
		int delta = maxLength - str.length();
		for(int i = 0; i < delta; i++) {
			
		}
		
		return str;
	}

	public static int maxLength(List<String> l) {
		int maxLength =0;
		for(String s : l) {
			if(s.length() > maxLength) {
				maxLength = s.length();
			}
		}
		return maxLength;
	}
	public static void dumpList(List<String> l ) {
		for(String s : l) {
			System.out.println(s);
		}
	
		
	}

}
