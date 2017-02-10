package com.example;

public class ForHumanity {
	private Card[] whiteDeck=new Card[50];
	private Card[] blackDeck=new Card[15];
	public ForHumanity(){
		String[] whiteCards={"Hold hand", "Hand out Candy", "Compliment them", "Mow their lawn", "Kind Stranger", "Soft lamb", "Pick up the trash kindly", "baby's laughter", "the sun's joyful rays", "the old lady down the street", "plank's unending smile", "A good grade from Mr. Eltzroth", "American pride", "Walking the dog", "Holding the door open", "Purchasing the local flowers", "Opening Eltzroth's windows", "Opening a Christmas present", "Going to the local game", "petting a kitten", "Smelling the roses","Mr. Rogers", "Mr. Eltzroth", "Bob Ross", "RESPECT", "Happy Birthday", "go to a art show, and get your healthy dose of culture", "Saturday morning cartoons", "wholesome movies", "an evening with the family", "Friendship games", "tutoring", "friendship", "sending a letter to your grandma", "Donating to charity", "doing your homework", "giving blood for good-meaning uses", "getting a part-time job", "butterflies", "a rainbow", "running through the rain", "Grandpa", "Grandma", "nothing", "Turning in homework on time", "Pushing buttons", "Working at an animal shelter", "Favorite teacher(Mr. Eltzroth)", "Java", "Computers", "A pop quiz"};//50
		String[] blackCards={"An old lady is crossing the street. What do you do?", "Man! That ______ is such a great person!", "My favorite form of community service is _____", "The best thing for a warm summer day is _____", "Good day sir, would you care for ____", "I like to _____ for fun", "When a day seems down, I just like to go ____", "You have no idea how much I love ____", "Where is your favorite place?", "Why is Mr. Eltzroth's class your favorite?", "When are you happiest?", "My vote for kindest thing is ______", "If you could be doing one thing right now, what would it be?", "What is your favorite kind of homework?", "If you were four people, what would all of you be doing?"};//15
		
		
		for(int i=0; i<whiteDeck.length; i++){
			whiteDeck[i]=new Card();
		}
		for(int i=0; i<blackDeck.length; i++){
			blackDeck[i]=new Card();
		}
		for(int i=0; i<whiteDeck.length; i++){
			whiteDeck[i].setType("white");
			whiteDeck[i].setWords(whiteCards[i]);
		}
		for(int i=0; i<blackDeck.length; i++){
			blackDeck[i].setType("black");
			blackDeck[i].setWords(blackCards[i]);
		}
		
		
	}
	public void shuffle(){
		for(int i=0; i<whiteDeck.length; i++){
			Card temp=whiteDeck[i];
			int num=(int)(Math.random()*whiteDeck.length);
			whiteDeck[i]=whiteDeck[num];
			whiteDeck[num]=temp;
		}
		for(int i=0; i<blackDeck.length; i++){
			Card temp=blackDeck[i];
			int num=(int)(Math.random()*blackDeck.length);
			blackDeck[i]=blackDeck[num];
			blackDeck[num]=temp;
		}
	}
	private int currentcard = -1;
	public Card deal(){
		currentcard++;
		return whiteDeck[currentcard];
	}
	private int currentBlackcard=-1;
	public Card dealBlack(){
		currentBlackcard++;
		return blackDeck[currentBlackcard];
	}
}
