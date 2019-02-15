//Elliott Goldstein
/**My MlbPlayer class
implements the Comparable
interface, since I will
want to use the Collections.sort
algorithm later on, which requires
the objects that I have sorted
to have implemented the interface
for Comparable. It contains the 
class variables playername of 
type String, and two integer 
variables called obp and wrc
*/

public class MlbPlayer implements Comparable<MlbPlayer>{
	String playername;
	int obp;
	int wrc;
	
/*My MlbPlayer constructor 
creates an instance of my 
MlbPlayer object by taking
 in 3 variables: a String 
 (the player's name) and then two
ints (the obp and wrc attributes)
*/
public MlbPlayer(String n, int o, int w){
	playername=n;
	wrc=w;
	obp=o;
}
/**
@param m MlbPlayer object
This method compares the 
values of two MlbPlayer's obp 
attribute. If the two players 
have the same obp, it compares
their name strings
@return an integer value that 
has either compared the player's
 \name or on base percentage
*/ 
public int compareTo(MlbPlayer m){
	if (m.getobp()==this.getobp()){
		return (this.getname().compareTo(m.getname()));

	}
	else {
		return (m.getobp()-(this.getobp()));
	}
}


/**
@param null
Returns the player's playername variable
@return the playername variable
*/
public String getname(){
	return playername;
}
/**
Returns the player's wrc variable
@return the wrc variable
*/
public int getwrc(){
	return wrc;
}
/**
Returns the player's obp variable
@return rhe player's obp attribute
*/
public int getobp(){
	return obp;
}
/**
My overloaded toString method
@return the string that will 
represent my player object
*/
public String toString(){
	return ("Player name:" + " " + getname() + " " + " Weighted Runs Created:" + getwrc() + " " + " " + "On-Base Percentage:" + getobp());
}


}


