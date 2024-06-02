package proj3_2336;

public class StringHash {
	public int hashSize;
	public int hashInitial;
	public int hashMulti;
	public int hashRelPrime;
	public String[] hashTable;
	
	public StringHash(int size, int initialValue, int hashMultiplier, int relativePrime)
	{
		hashSize = size;
		hashInitial = initialValue;
		hashMulti = hashMultiplier;
		hashRelPrime = relativePrime;
		hashTable = new String[size];
		for (int i = 0; i < size; i++)
		{
			hashTable[i] = "<EMPTY>";
		}
	}

	public int hashMultiplicative(String data) 
	{ 
		int strHash = hashInitial;
		for (int i = 0; i < data.length(); i++) 
		{
			strHash = (strHash * hashMulti) + data.charAt(i);
		}

		return Math.abs(strHash % hashSize);
	}
	
	public boolean contains(String data)
	{
		boolean check = true;
		char quotes = '"';
		int h1 = hashMultiplicative(data);
		int h2 = hashRelPrime - (hashMultiplicative(data) % hashRelPrime);
		int key = h1;
		int originalKey = h1;
		int i = 0;
		System.out.print("Searching " + quotes + data + quotes);
		System.out.print(" -> " + key);
		
		while(check)
		{
			if (key != originalKey)
			{
				System.out.print(" -> " + key);
			}
			if (hashTable[key].equals(data))
			{
				System.out.println();
				System.out.println("TRUE");
				return true;
			}
			if (hashTable[key].equals("<EMPTY>"))
			{
				System.out.println(" -> FAILED");
				System.out.println("FALSE");
				return false;
			}
			if (i > hashSize)
			{
				System.out.println(" -> FAILED");
				System.out.println("FALSE");
				return false;
			}
			
			key = (h1 + (i * h2)) % hashSize;
			i++;
		}
		return false;
	}
	
	public boolean containsNoPrint(String data)
	{
		boolean check = true;
		char quotes = '"';
		int h1 = hashMultiplicative(data);
		int h2 = hashRelPrime - (hashMultiplicative(data) % hashRelPrime);
		int key = h1;
		int i = 0;
		
		while(check)
		{
			if (hashTable[key].equals(data))
			{
				return true;
			}
			if (hashTable[key].equals("<EMPTY>"))
			{
				return false;
			}
			if (i > hashSize)
			{
				return false;
			}
			
			key = (h1 + (i * h2)) % hashSize;
			i++;
		}
		return false;
	}

	public boolean add(String data)
	{
		char quotes = '"';
		int h1 = hashMultiplicative(data);
		int h2 = hashRelPrime - (hashMultiplicative(data) % hashRelPrime);
		int key = h1;
		int originalKey = h1;
		int j = 0;
		boolean check = true;
		
		System.out.print("Adding " + quotes + data + quotes); 
		System.out.print(" -> " + key);
		
		if (containsNoPrint(data) == true)
		{
			System.out.println(" -> FAILED");
			return false;
		}
		
		if (data.isEmpty())
		{
			System.out.println(" -> FAILED");
			return false;
		}
		
		while(check)
		{
			if (key != originalKey)
				{
					System.out.print(" -> " + key);
				}
			
			if (hashTable[key].equals("<EMPTY>") || hashTable[key].equals("<REMOVED>"))
			{
				hashTable[key] = data;
				System.out.println();
				return true;
			}
			if (j > hashSize)
			{
				System.out.println(" -> FAILED");
				return false;
			}
			
			key = (h1 + (j * h2)) % hashSize;
			j++;
		}
		return false;
	}

	public boolean remove(String data)
	{
		char quotes = '"';
		int h1 = hashMultiplicative(data);
		int h2 = hashRelPrime - (hashMultiplicative(data) % hashRelPrime);
		int key = h1;
		int originalKey = h1;
		int j = 0;
		boolean check = true;
		
		System.out.print("Removing " + quotes + data + quotes); 
		System.out.print(" -> " + key);
		
		if (containsNoPrint(data) == false)
		{
			System.out.println(" -> FAILED");
			return false;
		}
		
		if (data.isEmpty())
		{
			System.out.println(" -> FAILED");
			return false;
		}
		
		
		while(check)
		{
			if (key != originalKey)
			{
				System.out.print(" -> " + key);
			}
			
			if (hashTable[key].equals(data))
			{
				hashTable[key] = "<REMOVED>";
				System.out.println();
				return true;
			}
			if (j > hashSize)
			{
				System.out.println(" -> FAILED");
				return false;
			}
			
			key = (h1 + (j * h2)) % hashSize;
			j++;
		}
		return false;
	}
	
	public void displayTable()
	{
		for(int loop = 0; loop < hashSize; loop++)
		{
			System.out.print(loop + " : " + hashTable[loop] + " ");
		}
		System.out.println();
	}

	public boolean rehash(String data)
	{
		char quotes = '"';
		int h1 = hashMultiplicative(data);
		int h2 = hashRelPrime - (hashMultiplicative(data) % hashRelPrime);
		int key = h1;
		int originalKey = h1;
		int j = 0;
		boolean check = true;
		System.out.print("Rehashing " + quotes + data + quotes); 
		System.out.print(" -> " + key);
		
		if (containsNoPrint(data) == true)
		{
			System.out.println(" -> FAILED");
			return false;
		}
		
		if (data.isEmpty())
		{
			System.out.println(" -> FAILED");
			return false;
		}
		
		while(check)
		{
			if (key != originalKey)
				{
					System.out.print(" -> " + key);
				}
			
			if (hashTable[key].equals("<EMPTY>") || hashTable[key].equals("<REMOVED>"))
			{
				hashTable[key] = data;
				System.out.println();
				return true;
			}
			if (j > hashSize)
			{
				System.out.println(" -> FAILED");
				return false;
			}
			
			key = (h1 + (j * h2)) % hashSize;
			j++;
		}
		return false;
	}
	
	public void resize()
	{
		String [] tempArray = new String [hashSize];
		int oldSize = hashSize;
		for (int i = 0; i < oldSize; i++)
		{
			tempArray[i] = hashTable[i];
		}
		hashSize = hashSize * 2;
		hashTable = new String [hashSize];
		
		for (int j = 0; j < hashSize; j++)
		{
			hashTable[j] = "<EMPTY>";
		}
		
		for (int k = 0; k < oldSize; k++)
		{
			if (tempArray[k].equals("<EMPTY>") || tempArray[k].equals("<REMOVED>"))
			{
				
			}
			else
			{
				rehash(tempArray[k]);
			}
		}
		
	}
}

