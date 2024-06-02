package proj3_2336;

import java.util.*;

public class Main {
	
	
	static void menu()
	{
		int tableSize, hashValue, hashMulti, relPrime;
		Scanner scnr = new Scanner(System.in);
		
		System.out.println("Enter table size: ");
		tableSize = scnr.nextInt();
		System.out.println("Enter initial hash value: ");
		hashValue = scnr.nextInt();
		System.out.println("Enter hash multiplier: ");
		hashMulti = scnr.nextInt();
		System.out.println("Enter relative prime value: ");
		relPrime = scnr.nextInt();
		
		StringHash hashTable  = new StringHash(tableSize, hashValue, hashMulti, relPrime);
		boolean check = true;
		char menuChoice = 0;
		String choiceStr;
		
		while(check == true)
		{
			System.out.print("1. Search String \n2. Add String \n3. Remove String \n4. Display table \n5. Resize Table \nQ. Quit \nChoice:");
			menuChoice = scnr.next().charAt(0);
			switch(menuChoice)
			{
			case '1':
				System.out.print("String to search for: ");
				choiceStr = scnr.next();
				hashTable.contains(choiceStr);
				break;
			case '2':
				System.out.print("String to add: ");
				choiceStr = scnr.next();
				hashTable.add(choiceStr);
				break;
			case '3':
				System.out.print("String to remove: ");
				choiceStr = scnr.next();
				hashTable.remove(choiceStr);
				break;
			case '4':
				hashTable.displayTable();
				break;
			case '5':
				hashTable.resize();
				break;
			case 'Q':
				check = false;
				break;
			default: 
				break;
			}
		}
		return;
	}

	public static void main(String[] args) {
		menu();
	}

}

