# Chinese Chess
A Chinese-chess game for 1 vs 1 online battle.
## Build & Run
Import source folder to Eclipse, then click "Run" button.   You can refer to this tutorial:  
[How to import an existing Java Project in Eclipse](https://www.youtube.com/watch?v=REViWzhUfag)
## Usage
The two computers must be in the same local network.

**Step 1.** One (Client) need to specify the IP address of another (Server).  
Line number 21, in Client.java  
```
	public boolean creatClient() {
		
		try {
			// specify IP here
			c1 = new Socket("localhost",1111);
			in = new DataInputStream(c1.getInputStream());
			out = new DataOutputStream(c1.getOutputStream());
	
		}catch(IOException e) {return false;}
		
		return true;
		
	}
```
**Step 2.** The server computer run game first, then the client computer. Both of them need to type user name.

<img src="https://github.com/Msiciots/chinese-chess/raw/master/doc-img/enter-user-name.png" width="30%">


**Step 3.** You will see the game window, the color of your name represents the part where you are.

![](https://github.com/Msiciots/chinese-chess/raw/master/doc-img/game.png)

**Step 4.** Red go first. Enjoy the game! You could chat with your enemy, and make some actions through buttons.