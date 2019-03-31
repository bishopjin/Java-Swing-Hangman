import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;

public class hangman extends JFrame implements ActionListener{
	private JPanel centerpanel;
	private JPanel southpanel;
	private JPanel inside_south;
	private JButton new_game_btn;
	private JPanel inside;
	private JFrame frame;

	public hangman(){
		setSize(800, 600);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Hangman");
		setLayout(new BorderLayout());
		setBackground(Color.GRAY);

		southpanel = new JPanel();
		southpanel.setPreferredSize(new Dimension(800, 30));
		southpanel.setBackground(Color.GRAY);

		centerpanel = new JPanel(new BorderLayout());
		centerpanel.setBorder(new LineBorder(Color.GRAY, 5));
		
		inside = new cJpanel();
		inside.setBorder(new BevelBorder(BevelBorder.LOWERED));
		
		new_game_btn = new JButton("New");
		new_game_btn.setMnemonic(KeyEvent.VK_N);
		new_game_btn.addActionListener(this);

		inside_south = new JPanel();
		inside_south.setBorder(new BevelBorder(BevelBorder.LOWERED));
		inside_south.add(new_game_btn);
		inside_south.setBackground(Color.GRAY);

		centerpanel.add(inside, BorderLayout.CENTER);
		centerpanel.add(inside_south, BorderLayout.SOUTH);

		add(centerpanel, BorderLayout.CENTER);
		add(southpanel, BorderLayout.SOUTH);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent event){		
		String s = event.getActionCommand();
		if (s == "New"){
			cJpanel c = new cJpanel();//create object
			c.wrong_guess = 0;//re-initialize
			c.guess_count = 0;//
			c.correct = new String[8];//
			c.guess_word = "";//
			inside.requestFocusInWindow();//request for refocusing of jpanel for keylistener
			inside.setFocusable(true);//set the focus
			repaint();//redraw all
		}
	}
	//main
	public static void main(String[] args){
		hangman gui = new hangman();
	}
}
//Custom JPanel
class cJpanel extends JPanel implements KeyListener{
	private JPanel panel;
	static String[] array;
	static String[] correct;
	static int guess_count;
	static int wrong_guess;
	static String guess_word;
	static String word;

	public cJpanel(){
		guess_count = 0;
		wrong_guess = 0;
		word = "ragnarok";
		array = word.split("");
		correct = new String[8];
		panel = new JPanel(new BorderLayout());
		setFocusable(true);
		addKeyListener(this);
	}
	
	public void paintComponent(Graphics g){  
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(5));  
		g2.drawLine(450, 50, 450, 300);//pole
		g2.drawLine(450, 300, 400, 350);//stand left
		g2.drawLine(450, 300, 500, 350);// stand right
		g2.drawLine(450, 50, 350, 50);// top
		g2.drawLine(350, 50, 350, 80);//rope

		Font f = new Font("Comic Sans MS", Font.BOLD, 20);
		g.setFont(f);
		g.setColor(Color.GRAY);
		g.drawString("a", (getWidth()/2) -195, 470);
		g.drawString("b", (getWidth()/2) -180, 470);
		g.drawString("c", (getWidth()/2) -165, 470);
		g.drawString("d", (getWidth()/2) -150, 470);
		g.drawString("e", (getWidth()/2) -135, 470);
		g.drawString("f", (getWidth()/2) -120, 470);
		g.drawString("g", (getWidth()/2) -105, 470);
		g.drawString("h", (getWidth()/2) -90, 470);
		g.drawString("i", (getWidth()/2) -75, 470);
		g.drawString("j", (getWidth()/2) -60, 470);
		g.drawString("k", (getWidth()/2) -45, 470);
		g.drawString("l", (getWidth()/2) -30, 470);
		g.drawString("m", (getWidth()/2) -15, 470);
		g.drawString("n", (getWidth()/2) +15, 470);
		g.drawString("o", (getWidth()/2) +30, 470);
		g.drawString("p", (getWidth()/2) +45, 470);
		g.drawString("q", (getWidth()/2) +60, 470);
		g.drawString("r", (getWidth()/2) +75, 470);
		g.drawString("s", (getWidth()/2) +90, 470);
		g.drawString("t", (getWidth()/2) +105, 470);
		g.drawString("u", (getWidth()/2) +120, 470);
		g.drawString("v", (getWidth()/2) +135, 470);
		g.drawString("w", (getWidth()/2) +150, 470);
		g.drawString("x", (getWidth()/2) +165, 470);
		g.drawString("y", (getWidth()/2) +180, 470);
		g.drawString("z", (getWidth()/2) +195, 470);
    }

    public int guess(String[] hidden, String guess){
    	int loop_count = 0;
    	int index_pos;
    	int s = 0;
    	guess_word = "";

    	boolean contains = Arrays.stream(hidden).anyMatch(guess::equals);
 
    	if (contains == true){
			while(loop_count < 8){
				if (hidden[loop_count].equals(guess)){
					index_pos = loop_count;
					correct[index_pos] = guess;
				}
				guess_word += correct[loop_count];
				loop_count += 1;
			}
			guess_count += 1;
			s = 1;
    	}
    	else{
    		guess_count += 1;
    		s = 0;
    	}
    	return s;
    }

    public void check(char guess, int x){
    	Graphics g = getGraphics();
		Graphics2D g2 = (Graphics2D) g;
		Font f = new Font("Comic Sans MS", Font.BOLD, 20);
		g2.setFont(f);

		if (guess(array, String.valueOf(guess)) == 1){
			g2.setColor(Color.BLACK);
			if ((int)guess < 110){
				g2.drawString(String.valueOf(guess), (getWidth()/2) - x, 470);
			}
			else{
				g2.drawString(String.valueOf(guess), (getWidth()/2) + x, 470);
			}
		}
		else{
			wrong_guess += 1;
		}
    }
   
    public void keyTyped(KeyEvent e) {}
    
	public void keyReleased(KeyEvent e) {}
	
	public void keyPressed(KeyEvent e){
		Graphics g = getGraphics();
		Graphics2D g2 = (Graphics2D) g;
		Font f = new Font("Comic Sans MS", Font.BOLD, 20);
		g2.setFont(f);

		g2.setStroke(new BasicStroke(5));

    	if (e.getKeyCode() == KeyEvent.VK_A) {
    		check('a', 195);
		}
		else if (e.getKeyCode() == KeyEvent.VK_B){
			check('b', 180);
		}
		else if (e.getKeyCode() == KeyEvent.VK_C){
			check('c', 165);
		}
		else if (e.getKeyCode() == KeyEvent.VK_D){
			check('d', 150);
		}
		else if (e.getKeyCode() == KeyEvent.VK_E){
			check('e', 135);
		}
		else if (e.getKeyCode() == KeyEvent.VK_F){
			check('f', 120);
		}
		else if (e.getKeyCode() == KeyEvent.VK_G){
			check('g', 105);
		}
		else if (e.getKeyCode() == KeyEvent.VK_H){
			check('h', 90);
		}
		else if (e.getKeyCode() == KeyEvent.VK_I){
			check('i', 75);
		}
		else if (e.getKeyCode() == KeyEvent.VK_J){
			check('j', 60);
		}
		else if (e.getKeyCode() == KeyEvent.VK_K){
			check('k', 45);
		}
		else if (e.getKeyCode() == KeyEvent.VK_L){
			check('l', 30);
		}
		else if (e.getKeyCode() == KeyEvent.VK_M){
			check('m', 15);
		}
		else if (e.getKeyCode() == KeyEvent.VK_N){
			check('n', 15);
		}
		else if (e.getKeyCode() == KeyEvent.VK_O){
			check('o', 30);
		}
		else if (e.getKeyCode() == KeyEvent.VK_P){
			check('p', 45);
		}
		else if (e.getKeyCode() == KeyEvent.VK_Q){
			check('q', 60);
		}
		else if (e.getKeyCode() == KeyEvent.VK_R){
			check('r', 75);
		}
		else if (e.getKeyCode() == KeyEvent.VK_S){
			check('s', 90);
		}
		else if (e.getKeyCode() == KeyEvent.VK_T){
			check('t', 105);
		}
		else if (e.getKeyCode() == KeyEvent.VK_U){
			check('u', 120);
		}
		else if (e.getKeyCode() == KeyEvent.VK_V){
			check('v', 135);
		}
		else if (e.getKeyCode() == KeyEvent.VK_W){
			check('w', 150);
		}
		else if (e.getKeyCode() == KeyEvent.VK_X){
			check('x', 165);
		}
		else if (e.getKeyCode() == KeyEvent.VK_Y){
			check('y', 180);
		}
		else if (e.getKeyCode() == KeyEvent.VK_Z){
			check('z', 195);
		}
		else if (e.getKeyCode() == KeyEvent.VK_SPACE){}

		//
		if (wrong_guess == 2){
			g2.drawOval(325, 85, 50, 50); //head	
		}  
		else if (wrong_guess == 4){
			g2.drawLine(350, 135, 350, 230);//body	
		}
		else if (wrong_guess == 6){
			g2.drawLine(350, 150, 310, 150);//left hand	
			g2.drawLine(350, 150, 390, 150);//right hand
		}
		else if (wrong_guess == 8 && !guess_word.equals(word)){
			g2.drawLine(350, 230, 310, 280);//left leg
			g2.drawLine(350, 230, 390, 280);//right leg
			g2.drawString(word, (getWidth()/2) - 50, 400);//
			g2.drawString("You lose...RIP man.", (getWidth()/2) - 105, 440);//you lose
			setFocusable(false);
		}
		else if (guess_word.equals(word)){
			g2.drawString(word, (getWidth()/2) - 50, 400);//
			g2.drawString("You won with "+ String.valueOf(8 - wrong_guess) + " guesses left!", (getWidth()/2) - 125, 440);//you won
			setFocusable(false); 
		}
	}
}