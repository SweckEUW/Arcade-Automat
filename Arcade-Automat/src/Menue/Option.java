package Menue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import Pacman.Sound;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
Menue class contains the ui of the option menue.
You control settings of the window and game in this class.
 */
public class Option {
	
	private static Pane root;

	private static ArrayList<Text> allOptions; // List with every text object of the specific menu (needed for the switchOption())
	private static ImageView arrowView; //arrow
	private static Boolean windowSize=false,fullscreen=false,muteAudio=false; //game settings
	private static Rectangle blackBoxWindowSize,blackBoxFullscreen,blackBoxMuteAudio; //show which setting is selected
	private static KeyCode upK = KeyCode.UP; //move up Key
	private static KeyCode downK = KeyCode.DOWN; //move down Key
	private static KeyCode leftK = KeyCode.LEFT; //move left Key
	private static KeyCode rightK = KeyCode.RIGHT; //move right Key
	private static Text upKey,downKey,rightKey,leftKey; //Text for the option menue
	private static int i; //needed for the switch (blinkingText and arrow)
	
	/**
	Shows option menue of the Arcade-Automat.
	A UI with multiple selections(exit(to main menue),window size ,fullscreen , mute audio and selection which keys you use during a game)
	 */
	public static void optionMenue(Scene scene) throws FileNotFoundException {
		i=0; //reset switch
		
		optionSetOnKeyPressed(scene);
		
		//set text
		Text up=new Text(700,650, "UP");
    	up.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 20));
    	up.setFill(Color.WHITE);
    	
    	upKey=new Text(1000,650, upK.toString());
    	upKey.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 20));
    	upKey.setFill(Color.WHITE);
    	
    	Text down=new Text(700,700, "DOWN");
    	down.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 20));
    	down.setFill(Color.WHITE);
    	
    	downKey=new Text(1000,700, downK.toString());
    	downKey.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 20));
    	downKey.setFill(Color.WHITE);
    	
    	Text left=new Text(700,750, "LEFT");
    	left.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 20));
    	left.setFill(Color.WHITE);
    	
    	leftKey=new Text(1000,750, leftK.toString());
    	leftKey.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 20));
    	leftKey.setFill(Color.WHITE);
    	
    	Text right=new Text(700,800, "RIGHT");
    	right.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 20));
    	right.setFill(Color.WHITE);
    	
    	rightKey=new Text(1000,800, rightK.toString());
    	rightKey.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 20));
    	rightKey.setFill(Color.WHITE);
    	
    	Text back=new Text(700,850,"BACK");
    	back.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 20));
    	back.setFill(Color.WHITE);	
    	
    	//add arrow
    	Image arrow = new Image(new File("recources/Pictures/Menue/arrow.png").toURI().toString());
    	arrowView =new ImageView(arrow);
    	arrowView.setScaleX(0.2);
    	arrowView.setScaleY(0.2);
    	arrowView.relocate(550, 387);
   
    	//720p
    	if(windowSize) {
    		arrowView.setScaleX(0.1);
        	arrowView.setScaleY(0.1);
        	arrowView.relocate(370, 243);
        	up.relocate(500, 420);
        	up.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 13));
        	upKey.relocate(660, 420);
        	upKey.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 13));
        	down.relocate(500, 450);
        	down.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 13));
        	downKey.relocate(660, 450);
        	downKey.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 13));
        	left.relocate(500, 480);
        	left.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 13));
        	leftKey.relocate(660, 480);
        	leftKey.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 13));
        	right.relocate(500, 510);
        	right.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 13));
        	rightKey.relocate(660, 510);
        	rightKey.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 13));
        	back.relocate(500, 540);
        	back.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 13));
    	}
    	
    	addBlackBoxes();
		    	
    	root=new Pane();
		root.getChildren().add(Menue.getBackgroundVideo());
    	root.getChildren().add(arrowView);
    	root.getChildren().add(blackBoxWindowSize);
    	root.getChildren().add(blackBoxFullscreen);
    	root.getChildren().add(blackBoxMuteAudio);
    	
    	allOptions=new ArrayList<Text>(); //reset list for switchOption()
    	addWindowSize();
    	addFullScreen();
    	addMute();
    	allOptions.add(up);
    	allOptions.add(down);
    	allOptions.add(left);
    	allOptions.add(right);
    	allOptions.add(back);
    	
    	root.getChildren().add(upKey);
    	root.getChildren().add(downKey);
    	root.getChildren().add(leftKey);
    	root.getChildren().add(rightKey);
    	root.getChildren().add(up);
    	root.getChildren().add(down);
    	root.getChildren().add(left);
    	root.getChildren().add(right);
    	root.getChildren().add(back);
    	
    	Menue.addOverlay(root);
    	scene.setRoot(root);
	}
	
	/**
	action event when a key is pressed for the option menue
	 */
	public static void optionSetOnKeyPressed(Scene scene) {
		scene.setOnKeyPressed(e->{
    		Stage stage=(Stage)scene.getWindow(); 
    		switch(e.getCode()) {
    			case UP:
    				switchOption(-1);
    				break;
    			case DOWN:
    				switchOption(1);
    				break;
    			case ENTER:
    				switch(i) {
    					case 0://change window size to 1920x180 or 1280x720
    						if(windowSize) {
    							windowSize=false;   							
    							stage.setWidth(1920);
        						stage.setHeight(1080);	
        						stage.setX(0);
        						stage.setY(0);
    						}else {
    							windowSize=true;
        						stage.setWidth(1280);
        						stage.setHeight(720);
        						stage.setX(320);
        						stage.setY(180);
    						}
    						stage.setFullScreen(false);
    						stage.setFullScreen(fullscreen);
						try {
							optionMenue(scene); //open option menue again with changed window size
						} catch (FileNotFoundException e1) {
							System.err.println("Datei wurde nicht gefunden.");
							e1.printStackTrace();
						}
    						break;
    					case 1: //switch fullscreen on or off
    						if(fullscreen) {
    							fullscreen=false;
    							blackBoxFullscreen.setTranslateX(0);  //translate blackbox to show which option is selected  								
    						}else {
    							fullscreen=true;			
    							blackBoxFullscreen.setTranslateX(-150);
    							//720p
    							if(windowSize)
    								blackBoxFullscreen.setTranslateX(-100);
    						}    						    				
    						stage.setFullScreen(fullscreen);
    						break;
    					case 2: //switch audio on or off
    						if(muteAudio) {
    							muteAudio=false; 					
    							blackBoxMuteAudio.setTranslateX(0);
    							Snake.Sound.turnOnAudio();
    						}else {
    							muteAudio=true;
    							blackBoxMuteAudio.setTranslateX(-150);
    							Snake.Sound.turnOfAudio();
    							//720p
    							if(windowSize)
    								blackBoxMuteAudio.setTranslateX(-100);
    						}
    						Menue.getMainMusic().setMute(muteAudio); //mute main theme
							Sound.setAllSoundsMute(muteAudio); //mute audio pacman
    						break;
    					case 3: //change up key 
    						upKey.setText(""); //delete text
    						scene.setOnKeyPressed((a) -> { //get next key entered
    							if(a.getCode().toString()!="ENTER" && a.getCode()!=upK && a.getCode()!=downK && a.getCode()!=rightK && a.getCode()!=leftK) { //new key should not be enter or any of the other keys 
	    							upKey.setText(a.getCode().toString()); //set new key text
	    							upK=a.getCode(); //set new key
	    							optionSetOnKeyPressed(scene); //activate set on key pressed option menue again
    							}
    						});
    						break;
    					case 4: //change down key
    						downKey.setText("");
    						scene.setOnKeyPressed((a) -> {
    							if(a.getCode().toString()!="ENTER" && a.getCode()!=upK && a.getCode()!=downK && a.getCode()!=rightK && a.getCode()!=leftK) {
	    							downKey.setText(a.getCode().toString());
	    							downK=a.getCode();
	    							optionSetOnKeyPressed(scene);
    							}
    						});
    						break;
    					case 5: //change left key
    						leftKey.setText("");
    						scene.setOnKeyPressed((a) -> {
    							if(a.getCode().toString()!="ENTER" && a.getCode()!=upK && a.getCode()!=downK && a.getCode()!=rightK && a.getCode()!=leftK) {
	    							leftKey.setText(a.getCode().toString());
	    							leftK=a.getCode();
	    							optionSetOnKeyPressed(scene);
    							}
    						});
    						break;
    					case 6: //change right key
    						rightKey.setText("");
    						scene.setOnKeyPressed((a) -> {
    							if(a.getCode().toString()!="ENTER" && a.getCode()!=upK && a.getCode()!=downK && a.getCode()!=rightK && a.getCode()!=leftK) {
	    							rightKey.setText(a.getCode().toString());
	    							rightK=a.getCode();
	    							optionSetOnKeyPressed(scene);
    							}
    						});
    						break;
    					case 7: //back to main menue
						try {
							Menue.mainMenue();
						} catch (FileNotFoundException e1) {
							System.err.println("Datei wurde nicht gefunden.");
							e1.printStackTrace();
						}
    						break;
    				}
    			default:
    				break;
    		}
    	});
	}

	public static KeyCode getUpK() {
		return upK;
	}
	
	public static KeyCode getDownK() {
		return downK;
	}

	public static KeyCode getLeftK() {
		return leftK;
	}

	public static KeyCode getRightK() {
		return rightK;
	}

	/**
	switch for the option menue (same as switchmenue())
	 */
	public static void switchOption(int s) {
    	Menue.getBlinkText().setVisible(true);
    	i+=s;
    	if(i==allOptions.size())
    		i=0;
    	if(i==-1)
    		i=allOptions.size()-1;
    	Menue.setBlinkText(allOptions.get(i));
    	Menue.getBlinkText().setVisible(false);
    	arrowView.setTranslateY(50*i);
    	if(windowSize)
    		arrowView.setTranslateY(30*i);
    	Menue.resetBlinkAnimation();
    }
	
	/**
	adds text of the window size option
	 */
	public static void addWindowSize() throws FileNotFoundException {
    	Text windowSizeT=new Text(700,500,"WINDOW SIZE");
    	windowSizeT.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 20));
    	windowSizeT.setFill(Color.WHITE);
    	allOptions.add(windowSizeT);
    	root.getChildren().add(windowSizeT);
    	Menue.setBlinkText(windowSizeT);
    	
		Text fullhd=new Text(1000,500,"1080P");
		fullhd.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 20));
		fullhd.setFill(Color.WHITE);
		root.getChildren().add(fullhd);
		
		Text hdReady=new Text(1150,500,"720P");
		hdReady.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 20));
		hdReady.setFill(Color.WHITE);
		root.getChildren().add(hdReady);
		
		//720p
		if(windowSize) {
			windowSizeT.relocate(500, 330);
			windowSizeT.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 13));
			fullhd.relocate(660, 330);
			fullhd.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 13));
			hdReady.relocate(760, 330);
			hdReady.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 13));     
		}
	}
	
	/**
	adds text of the mute audio option
	 */
	public static void addMute() throws FileNotFoundException{
    	Text muteAudio=new Text(700,600,"MUTE AUDIO");
    	muteAudio.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 20));
    	muteAudio.setFill(Color.WHITE);
    	allOptions.add(muteAudio);
    	root.getChildren().add(muteAudio);
    	
		Text yes=new Text(1000,600,"YES");
		yes.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 20));
		yes.setFill(Color.WHITE);
		root.getChildren().add(yes);
		
		Text no=new Text(1150,600,"NO");
		no.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 20));
		no.setFill(Color.WHITE);
		root.getChildren().add(no);
		
		//720p
		if(windowSize) {
			muteAudio.relocate(500, 390);
			muteAudio.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 13));
			yes.relocate(660, 390);
			yes.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 13));
			no.relocate(760, 390);
			no.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 13));
		}
	}
	
	/**
	adds text of the fullscreen option
	 */
	public static void addFullScreen() throws FileNotFoundException {
		Text fullscreen=new Text(700,550,"FULL SCREEN");
    	fullscreen.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 20));
    	fullscreen.setFill(Color.WHITE);
    	allOptions.add(fullscreen);
    	root.getChildren().add(fullscreen);
    	
    	Text yes=new Text(1000,550,"YES");
    	yes.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 20));
    	yes.setFill(Color.WHITE);
		root.getChildren().add(yes);
		
		Text no=new Text(1150,550,"NO");
		no.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 20));
		no.setFill(Color.WHITE);
		root.getChildren().add(no);
		
		//720p
		if(windowSize) {
			fullscreen.relocate(500, 360);
			fullscreen.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 13));
			yes.relocate(660, 360);
			yes.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 13));
			no.relocate(760, 360);
			no.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 13));
		}
	}
	
	/**
	adds blackboxes for the given options and translates them to the specific setting
	 */
	public static void addBlackBoxes() {
		//window size
		blackBoxWindowSize=new Rectangle(1000,470,100,40);
		blackBoxWindowSize.setOpacity(0.5);
		//720p
		if(windowSize) {
			blackBoxWindowSize=new Rectangle(760,332,70,20);
			blackBoxWindowSize.setOpacity(0.35);
		}
		
		//fullscreen
		blackBoxFullscreen=new Rectangle(1150,520,100,40);
		blackBoxFullscreen.setOpacity(0.5);
		if(fullscreen)
			blackBoxFullscreen.setTranslateX(-150);
		//720p
		if(windowSize) {
			blackBoxFullscreen=new Rectangle(760,362,70,20);
			blackBoxFullscreen.setOpacity(0.35);
		}
		if(windowSize&&fullscreen)
			blackBoxFullscreen.setTranslateX(-100);
		
		
		//mute audio
		blackBoxMuteAudio=new Rectangle(1150,570,100,40);	
		blackBoxMuteAudio.setOpacity(0.5);
		if(muteAudio)
			blackBoxMuteAudio.setTranslateX(-150);
		//720p
		if(windowSize) {
			blackBoxMuteAudio=new Rectangle(760,392,70,20);
			blackBoxMuteAudio.setOpacity(0.35);
		}
		if(windowSize&&muteAudio)
			blackBoxMuteAudio.setTranslateX(-100);
		
	}
	
	public static Boolean getMuteAudio() {
		return muteAudio;
	}
	
	public static Boolean getFullscreen() {
    	return fullscreen;
    }
	
	public static Boolean getWindowSize() {
    	return windowSize;
    }
	
}
