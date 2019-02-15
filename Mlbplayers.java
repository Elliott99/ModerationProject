//Elliott Goldstein
import java.util.*;
import java.io.*;
import java.util.Scanner;
import java.util.regex.*;


/**My MlbPlayers class contains the class variable count, the arraylist which will be populated by my MlbPlayer objects
the nested Players2 class which implements a comparator and it's respective compare() method*/

public class MlbPlayers{
	int count;
	ArrayList<MlbPlayer> players=new ArrayList<MlbPlayer>();
	class Players2 implements Comparator<MlbPlayer>{
		/**
		@param mlb1,mlb2 player objects to be compared
		The compare method compares the two player objects' wrc attribute
		@return the integer value that is gotten when subtracting one player's wrc attribute from another
		*/
		public int compare(MlbPlayer mlb1, MlbPlayer mlb2){
			return mlb2.getwrc()-mlb1.getwrc();
		}
	}
	/**
	*@param name the name to be searched for
	This methods loops through the arraylist comparing the String name parameter to the names of the players in the arraylist
	@return The player object with the corresponding name if a match is found, or a NullPlayer if no match is found
	*/
	public  MlbPlayer playerSearch(String name){
		for (int a=0;a<players.size();a++){
			if (name.equals(players.get(a).getname())){
				MlbPlayer found=players.get(a);
				return found;
			}
		}
		MlbPlayer NullPlayer=new MlbPlayer("Player not found", 0,0);
		return NullPlayer;
	}
	
	/**
	@param Mlb5 name to be searched for
	This method prints a message that evaluates a player's offensive ability based on their obp and wrc attributes
	*/
	public void hittingability(String mlb5){
		MlbPlayer hitter=playerSearch(mlb5);
		if (hitter.getname().equals("Player not found")){
			System.out.println("This player does not exist");
		}
		else if (hitter.getwrc()>=100 && hitter.getobp()<330){
			System.out.println(hitter.getname()+ " " + "has good power but weak on base skills");
		}
		else if (hitter.getwrc()<100 && hitter.getobp()>=330){
			System.out.println(hitter.getname()+ " " + "has good on base skills but weak power");
		}
		else if (hitter.getwrc()>=100 && hitter.getobp()>=330){
			System.out.println(hitter.getname()+ " " + "is an all around above average offensive player");
		}
		else if (hitter.getwrc()<100 && hitter.getobp()<330){
			System.out.println(hitter.getname()+ " " + "is a below average offensive player in terms of both power and on base skills");
		}
		else if (hitter.getwrc()==100 && hitter.getobp()==330){
			System.out.println(hitter.getname()+ "is league average in both power and on base skills");

		}
		
	}

	//Calls my quicksort algorithm
	void qsort(){
		quicksort(players,0,players.size()-1);
	}

	
	/**
	@param parsedLine String that will be parsed by the .split method using the comma as a delimiter
	This method creates a token array based on splitting along commas, and then loads the various attributes into the MlbPlayer's constructor
	*/
	void addplayers(String parsedline){
		String[] tokens=parsedline.split(",");
		String name=tokens[0];
		String obp=tokens[13];
		String wrc=tokens[16];
		obp=obp.substring(1,obp.length()-1);
		wrc=wrc.substring(1,wrc.length()-1);
		name=name.substring(1,name.length()-1);
		float obptobemultiplied=Float.parseFloat(obp);
		obptobemultiplied=obptobemultiplied * 1000;
		int obptobeadded=(int)obptobemultiplied;
		int wrctobeadded=Integer.parseInt(wrc);
		MlbPlayer mlb3=new MlbPlayer(name,obptobeadded,wrctobeadded);
		players.add(mlb3);
		}
	
	/**Reads the file csv file that contains my the data I wish to parse using a BufferedReader and File object, then 
	using a while loop with the condition the String L, set equal to the BufferedReader's readLine() method is not null, I 
	call the addplayers method so I can populate the entire arraylist with all of my data
	*/
	void getplayers(){
		try{
			File file=new File("displayedlist.csv");
			BufferedReader reader=new BufferedReader(new FileReader(file));
			String l=null;
			while ((l=reader.readLine())!=null){
						addplayers(l);
						count++;
				}
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
		

	}	

	public String getcount(){
		return ("The number of players is"+ count);
	}




	/**
	@param alist,lo,hi arraylist to be sorted, the lo
	and hi values of the arraylist to be used in sorting
	In my quicksort algorithm I get a partiton value by 
	finding the median of the lo and hi parameters. I 
	then loop forward from where lo is and back from where
	is hi is. If the player's obp attrbute for lo is greater
	than the obp attribute of the median player object's obp attribute,
	I increment forward. If the player attribute for hi is less than
	the partitioning player object's obp attribute I go back.
	If i (lo's incrementeed value) is less than or equal to j's value
	(hi's descending value) I swap them and then increment i forward
	and j backwards. Once i is less than or 
	equal to j I call my method recursively from 
	i to hi and lo to j which will sort both halves of the arraylist
	*/
	private void quicksort(ArrayList<MlbPlayer> alist,int lo, int hi){ 
		int partition=partition(lo,hi);
		int i=lo;
		int j=hi;
		while(i<=j){
			while (players.get(i).getobp()>players.get(partition).getobp()){
				i++;
			}
			while (players.get(j).getobp()<players.get(partition).getobp()){
				j--;
			}
			if(i<=j){
				Collections.swap(players,j,i);
				i++;
				j--;
			}
		}
         if (i<hi){
        	quicksort(players,i,hi);
        }
         if (lo<j){
        	quicksort(players,lo,j);
        }
    }
       /**
       @param inslist arraylist to be sorted
       A simple bubble sort algorithm. I loop
        through the arraylist, if the number 
        is less than the number that comes 
       after I swap them so it's
       in descending order
       */


    public void bubbleSort(ArrayList<MlbPlayer> inslist){
    	int b=inslist.size();
       	for (int i=0;i<b;i++){
       		for (int j=0;j<b-i-1;j++){
       			if (inslist.get(j).getobp()<inslist.get(j+1).getobp()){
       				Collections.swap(inslist,j+1,j);
       			}
       		}
       	}
	}
        
        
        /**
        @param lo,hi are the two 
        values from which the median 
        value will be found
        I take the two lo and hi 
        value parameter and then get the median value by
        adding them and dividing by 2
        @return the median value of lo and hi
        */

        private int partition(int lo, int hi){
        	int i=lo;
        	int j=hi;
        	int med=(hi+lo)/2;
        	return med;
        }


       /*dosorts is my caller method. I could have put 
       these methods in my main using as instances of
       a class, but I'd prefer to have my main method
       not look so cluttered so I just executed everything
       in this method. This method populates my arraylist
       using getplayers(), prints out the arraylists that
       are sorted using my the quicksort algorithm, the 
       bubble sort algorithm, and the Collection.sort() 
       algorithm which uses the compareTo() method from 
       my Comparable interface. Also prints out the original
       unsorted playerlist
       */ 

    private  void dosorts(){

    getplayers();
	String amount=getcount();
	System.out.println(amount);

	System.out.println("Unsorted Player List" + "\n");
	for (int i=0;i<players.size();i++){
		System.out.println( players.get(i)+"\n");
	}
	
	Collections.shuffle(players);
    Collections.sort(players);
	System.out.println("Sorted by Timsort" + "\n");
    for (int k=0;k<players.size();k++){
        System.out.println(players.get(k)+"\n");
     }
     
     Collections.shuffle(players);
	System.out.println("Sorted by Quicksort" + "\n");
	qsort();
	for (int p=0;p<players.size();p++){
		System.out.println(players.get(p)+"\n");
	}
	
	Collections.shuffle(players);
    System.out.println("Sorted by Bubble Sort");
	bubbleSort(players);
    for (int h=0;h<players.size();h++){
        System.out.println(players.get(h)+"\n");
    }
	MlbPlayers.Players2 p2=new MlbPlayers.Players2();
	Collections.sort(players,p2);
	System.out.println("Rankings by Weighted Runs Created"+ "\n");
	for (int j=0;j<players.size();j++){
		System.out.println(players.get(j)+ "\n");
	}
	
}


/**
@param args arguments to be inputted by the client
My main method calls the dosorts 
method using an instance of my MlbPlayers class, 
otherwise I wouldn't have been able to have simply
called dosorts() since it is not a static method. 
I also implement a Scanner. The input from this 
scanner will return a playerSearch 
of the entered playera nd returns 
the player's hitting ability. It 
also returns the player's stats by creating an MlbPlayer
object and then printing it
*/

	public static void main(String [] args){
		MlbPlayers testing=new MlbPlayers();
		testing.dosorts();
		System.out.println("Enter a Player Here:");
		Scanner in=new Scanner(System.in);
		String enteredplayer=in.nextLine();
		testing.hittingability(enteredplayer);
		testing.playerSearch(enteredplayer);
		MlbPlayer playerslashline=testing.playerSearch(enteredplayer);
		System.out.println(playerslashline.toString());
	
	}



}





