import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
/**
 * Reversi Class
 *
 * @author Nabeel SMADI
 */

public class Reversi extends JPanel implements MouseListener {
    static int[][] data = new int[6][6];
    static int gameSizeInt = 6;
    static JPanel panel = new JPanel() ;
    static int turn = 2;
    static int black = 0;
    static int white = 0;
    static int frei = 0;
    static int blue = 0;
    static int fontX = 10;
    static int fontY = 375;
    static int noblue = 0;
    static boolean noOneWin = false;



    /**
     * Class CONSTRUCTOR (Menu + GUI)
     */   
    public Reversi() {
    	  // Window Properties
        JFrame frame = new JFrame();
        frame.setTitle("Reversi");
        frame.setLocationRelativeTo(null);
        frame.setLocation(450, 150);
        panel.setPreferredSize(new Dimension(361, 385));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setLayout(null);
        frame.setResizable(false);
        frame.getContentPane().setBackground( new Color(18, 199, 24));
        
        // JMenuBar
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem newGame = new JMenuItem("New Game");
        JCheckBoxMenuItem help = new JCheckBoxMenuItem("Enable Help");
        JMenuItem exitGame = new JMenuItem("Close Game");
        menuBar.add(file);
        file.add(newGame);
        file.add(help);
        help.setSelected(true); 
        file.addSeparator();
        file.add(exitGame);
        JMenu gameSize = new JMenu("GameSize");
        JMenuItem x6 = new JMenuItem("6X6");
        JMenuItem x8 = new JMenuItem("8X8");
        JMenuItem x10 = new JMenuItem("10X10");
        menuBar.add(gameSize);
        gameSize.add(x6);
        gameSize.add(x8);
        gameSize.add(x10);
        newGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	data = new int[gameSizeInt][gameSizeInt];
            	turn = 2;
            	start();
            	count();
            	panel.repaint();
            }

        });
        help.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	panel.repaint();
            }

        });
        exitGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	System.exit(0);
            }

        });
        x6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	gameSizeInt = 6;
                fontX = 10;
                fontY = 375;
                frame.setLocation(450, 150);
                frame.setSize(367, 437); 
                frame.setPreferredSize(new Dimension(361, 385));
            	data = new int[6][6];
            	turn = 2;
            	start();
            	count();
            	panel.repaint();
            }

        });
        x8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	gameSizeInt = 8;
            	 fontX = 10;
                 fontY = 498;
                frame.setLocation(390, 80);
                frame.setPreferredSize(new Dimension(481, 505));
                frame.setSize(487, 557);
            	data = new int[8][8];
            	turn = 2;
            	start();
            	count();
            	panel.repaint();
            }

        });
        x10.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	gameSizeInt = 10;
            	 fontX = 10;
                 fontY = 617;
                frame.setLocation(330, 30);
                frame.setSize(607, 677);
                frame.setPreferredSize(new Dimension(601, 625));
            	data = new int[10][10];
            	turn = 2;
            	start();
            	count();
            	panel.repaint();
            }

        });

         panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (int i = 0; i < gameSizeInt; i++)
        			for (int j = 0; j < gameSizeInt; j++) {
        				g.setColor(new Color(18, 199, 24));
        				g.fillRect( j * 60,   i * 60, 60, 60);
        				g.setColor(Color.black);
        				g.drawRect( j * 60,  i * 60, 60, 60);
        			}
                for(int i = 0 ;i < data.length;i++){
                	 for(int j = 0 ;j < data[i].length;j++){
                		 switch (data[i][j]) {
                         case 0:   break;
                                 
                         case 1: 
                         g.setColor(Color.BLACK);
                        g.fillOval(5+i * 60, 5+j * 60, 50, 50);
                                  break;
                         case 2: 
                             g.setColor(Color.WHITE);
                            g.fillOval(5+i * 60, 5+j * 60, 50, 50);
                                      break;
                         case -1: 
                        	 if(help.getState()){
                        		   g.setColor(Color.BLUE);
                                   g.fillOval(20+i * 60, 20+j * 60, 25, 25); 
                        	 }
                                      break;
                         
                     }
                     }
                }
           	 g.setColor(Color.BLACK);
        	 g.setFont(new Font ("Courier New", Font.BOLD, 15));
        	    if(frei == 0){
                	if(black > white){
                		   g.drawString("Black win     Black = " + black + "  White = " + white, fontX, fontY);
                           	}else if(black == white || noOneWin){
                           g.drawString("No one win     Black = " + black + "  White = " + white, fontX, fontY);
                	}else{
                		   g.drawString("White win     Black = " + black + "  White = " + white, fontX, fontY);
                	}
                }else{
                	 if(turn == 1){
                     	   g.drawString("Black Turn     Black = " + black + "  White = " + white, fontX, fontY);
                               }else{
                     	   g.drawString("White Turn     Black = " + black + "  White = " + white, fontX, fontY);
                    }  
                } 
            }
          
            @Override
            public Dimension getPreferredSize() {
            	 if(gameSizeInt == 6){   
                return new Dimension(361, 385);
            }else if(gameSizeInt == 8){
            	  return new Dimension(481, 505);
            }else{
            	 return new Dimension(601, 625);
            }
           }
        };
     
        frame.add(panel);
        frame.setJMenuBar(menuBar);
        panel.addMouseListener(this);
        frame.pack();
        // Display frame after all components added
        frame.setVisible(true);

    }
 
   
    /**
     * MAIN METHOD
     */ 
    public static void main(String[] args) {
    	start();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Reversi();
            }
        });
    }

    /**
     * Start METHOD : execute on start
     */ 
    static void start(){
    	 data[(gameSizeInt/2)-1][(gameSizeInt/2)-1] = 1;
    	    data[gameSizeInt/2][(gameSizeInt/2)-1] = 2;
    	    data[(gameSizeInt/2)-1][gameSizeInt/2] = 2;
    	    data[gameSizeInt/2][gameSizeInt/2] = 1;
    	    help();
    	    count();
    }
    
    /**
     * MouseClicked METHOD : execute after a Mouse Clicked
     */ 
	@Override
	public void mouseClicked(MouseEvent arg0) {
		int x, y, i = 0, j = 0;
		x = arg0.getX();
		y = arg0.getY();
        i = x/60;
        j = y/60;
        if(accepted(i,j)){
        	 if(turn == 1){
                 data[i][j]=turn;
                 fillAll(i, j);
                 turn = 2;
             }else{
                 data[i][j]=turn;
                 fillAll(i, j);
                 turn = 1;
             }
        	 help();
        	
             panel.repaint();
        }
	}
	
	/**
     * Check if the field is accepted
     */ 
	boolean accepted(int i,int j){
		if(i < gameSizeInt && j < gameSizeInt){
			if(data[i][j] == -1){
				return true;
			}
		}
		return false;
	}
	
	/**
     * Check which fields can be chosen and mark it for player help
     */
	static void help(){
		 for(int i = 0 ;i < data.length;i++){
		   	 for(int j = 0 ;j < data[i].length;j++){
		   		if(data[i][j] == -1){
		   			data[i][j] = 0;
		   		}
		   	 } }
		
			 for(int i = 0 ;i < data.length;i++){
			   	 for(int j = 0 ;j < data[i].length;j++){
			   		if(data[i][j] == turn){
			   			check(i,j);
			   		}}}
			 count(); 	 
		if(blue == 0 && frei != 0){
			noblue ++;
			if(turn == 1){
				turn = 2;
			}else{
				turn = 1;
			}
		    if(noblue > 1){
	            	noOneWin = true;
				}
			if(!noOneWin){
				help();
			}
		}else{
			noblue = 0;
		}
	
	}
	
	/**
     * Check if a field can be chosen 
     */
	static void check(int i,int j){
		int notTurn ;
		int oI = i;
		int oJ = j;
		boolean done = false;
		if(turn == 1){
			notTurn = 2;
		}else{
			notTurn = 1;
		}
		
		//up
		while(i >= 0 && i < gameSizeInt && j-1 >= 0 && j-1 < gameSizeInt && !done){
        	if(data[i][j-1] == notTurn){
        		if(i >= 0 && i < gameSizeInt && j-2 >= 0 && j-2 < gameSizeInt){
    				//ln("i1");
    				if(data[i][j-2] == 0){
    					//	ln("i2");
    					data[i][j-2] = -1;
    					done = true;
        			}else if(data[i][j-2] == notTurn){
        				j = j-1;
        			}else{
        				done = true;
        			}
    			}else{
    				done = true;	
    			}
    		}else{
    			done = true;
    		}
		}
		
        i = oI;
        j = oJ;
        done = false;
    	//ln("up-right");
    	
      //up-right
    	while(i+1 >= 0 && i+1 < gameSizeInt && j-1 >= 0 && j-1 < gameSizeInt && !done){
        	//ln("w1");
        	if(data[i+1][j-1] == notTurn){
        		//ln("w2");
    			if(i+2 >= 0 && i+2 < gameSizeInt && j-2 >= 0 && j-2 < gameSizeInt){
    				//ln("i1");
    				if(data[i+2][j-2] == 0){
    					//ln("i2");
    					data[i+2][j-2] = -1;
    					done = true;
        			}else if(data[i+2][j-2] == notTurn){
        				j = j-1;
        				i = i+1;
        			}else{
        				done = true;
        			}
    			}else{
    				done = true;	
    			}
    		}else{
    			done = true;
    		}
		}
    	   i = oI;
           j = oJ;
           done = false;
       	//ln("right");
       	
         //right
       	while(i+1 >= 0 && i+1 < gameSizeInt && j >= 0 && j < gameSizeInt && !done){
           	//ln("w1");
           	if(data[i+1][j] == notTurn){
           		//ln("w2");
       			if(i+2 >= 0 && i+2 < gameSizeInt && j >= 0 && j < gameSizeInt){
       				//ln("i1");
       				if(data[i+2][j] == 0){
       					//ln("i2");
       					data[i+2][j] = -1;
       					done = true;
           			}else if(data[i+2][j] == notTurn){
           				i = i+1;
           			}else{
           				done = true;
           			}
       			}else{
       				done = true;	
       			}
       		}else{
       			done = true;
       		}
   		}
       	
        i = oI;
        j = oJ;
        done = false;
    	//ln("right-down");
    	
      //right-down
    	while(i+1 >= 0 && i+1 < gameSizeInt && j+1 >= 0 && j+1 < gameSizeInt && !done){
        	//ln("w1");
        	if(data[i+1][j+1] == notTurn){
        		//ln("w2");
    			if(i+2 >= 0 && i+2 < gameSizeInt && j+2 >= 0 && j+2 < gameSizeInt){
    				//ln("i1");
    				if(data[i+2][j+2] == 0){
    					//ln("i2");
    					data[i+2][j+2] = -1;
    					done = true;
        			}else if(data[i+2][j+2] == notTurn){
        				i = i+1;
        				j = j+1;
        			}else{
        				done = true;
        			}
    			}else{
    				done = true;	
    			}
    		}else{
    			done = true;
    		}
		}
    	
    	 i = oI;
         j = oJ;
         done = false;
     	//ln("down");
     	
       //down
     	while(i >= 0 && i < gameSizeInt && j+1 >= 0 && j+1 < gameSizeInt && !done){
         	//ln("w1");
         	if(data[i][j+1] == notTurn){
         		//ln("w2");
     			if(i >= 0 && i < gameSizeInt && j+2 >= 0 && j+2 < gameSizeInt){
     				//ln("i1");
     				if(data[i][j+2] == 0){
     					//ln("i2");
     					data[i][j+2] = -1;
     					done = true;
         			}else if(data[i][j+2] == notTurn){
         				j = j+1;
         			}else{
         				done = true;
         			}
     			}else{
     				done = true;	
     			}
     		}else{
     			done = true;
     		}
 		}
     	
     	 i = oI;
         j = oJ;
         done = false;
     	//ln("down-left");
     	
       //down-left
     	while(i-1 >= 0 && i-1 < gameSizeInt && j+1 >= 0 && j+1 < gameSizeInt && !done){
         	//ln("w1");
         	if(data[i-1][j+1] == notTurn){
         		//ln("w2");
     			if(i-2 >= 0 && i-2 < gameSizeInt && j+2 >= 0 && j+2 < gameSizeInt){
     				//ln("i1");
     				if(data[i-2][j+2] == 0){
     					//ln("i2");
     					data[i-2][j+2] = -1;
     					done = true;
         			}else if(data[i-2][j+2] == notTurn){
         				j = j+1;
         				i = i-1;
         			}else{
         				done = true;
         			}
     			}else{
     				done = true;	
     			}
     		}else{
     			done = true;
     		}
 		}
     	
   	 i = oI;
     j = oJ;
     done = false;
 	//ln("left");
 	
   //left
 	while(i-1 >= 0 && i-1  < gameSizeInt && j >= 0 && j < gameSizeInt && !done){
     	//ln("w1");
     	if(data[i-1][j] == notTurn){
     		//ln("w2");
 			if(i-2 >= 0 && i-2 < gameSizeInt && j >= 0 && j < gameSizeInt){
 				//ln("i1");
 				if(data[i-2][j] == 0){
 					//ln("i2");
 					data[i-2][j] = -1;
 					done = true;
     			}else if(data[i-2][j] == notTurn){
     				i = i-1;
     			}else{
     				done = true;
     			}
 			}else{
 				done = true;	
 			}
 		}else{
 			done = true;
 		}
		}
 	
	 i = oI;
     j = oJ;
     done = false;
 	//ln("left-up");
 	
   //left-up
 	while(i-1 >= 0 && i-1 < gameSizeInt && j-1 >= 0 && j-1 < gameSizeInt && !done){
     	//ln("w1");
     	if(data[i-1][j-1] == notTurn){
     		//ln("w2");
 			if(i-2 >= 0 && i-2 < gameSizeInt && j-2 >= 0 && j-2 < gameSizeInt){
 				//ln("i1");
 				if(data[i-2][j-2] == 0){
 					//ln("i2");
 					data[i-2][j-2] = -1;
 					done = true;
     			}else if(data[i-2][j-2] == notTurn){
     				i = i-1;
     				j = j-1;
     			}else{
     				done = true;
     			}
 			}else{
 				done = true;	
 			}
 		}else{
 			done = true;
 		}
		}
   		
	}
	
	/**
     * Change the fields value after a player movement
     */
	static void fillAll(int i,int j){
		int notTurn ;
		int oI = i;
		int oJ = j;
		boolean done = false;
		if(turn == 1){
			notTurn = 2;
		}else{
			notTurn = 1;
		}
		
		//up-fillall
		while(i >= 0 && i < gameSizeInt && j-1 >= 0 && j-1 < gameSizeInt && !done){
        	if(data[i][j-1] == notTurn){
        		if(i >= 0 && i < gameSizeInt && j-2 >= 0 && j-2 < gameSizeInt){
    				if(data[i][j-2] == turn){
    					for(int k = j-1;k <= oJ;k++){
    						data[i][k] = turn;
    						System.out.println("up-fillall" + i + " " + k);
    					}
    					done = true;
        			}else if(data[i][j-2] == notTurn){
        				j = j-1;
        			}else{
        				done = true;
        			}
    			}else{
    				done = true;	
    			}
    		}else{
    			done = true;
    		}
		}
		
        i = oI;
        j = oJ;
        done = false;
    	
      //up-right
    	while(i+1 >= 0 && i+1 < gameSizeInt && j-1 >= 0 && j-1 < gameSizeInt && !done){
        	if(data[i+1][j-1] == notTurn){
        		if(i+2 >= 0 && i+2 < gameSizeInt && j-2 >= 0 && j-2 < gameSizeInt){
    				if(data[i+2][j-2] == turn){
    					int m = i+1;
    					for(int k = j-1;k < oJ;k++){
    						System.out.println("up-right" + m + " " + k);
        					data[m][k] = turn;
        					m--;
    					}
    					done = true;
        			}else if(data[i+2][j-2] == notTurn){
        				j = j-1;
        				i = i+1;
        			}else{
        				done = true;
        			}
    			}else{
    				done = true;	
    			}
    		}else{
    			done = true;
    		}
		}
    	   i = oI;
           j = oJ;
           done = false;
       	
         //right
       	while(i+1 >= 0 && i+1 < gameSizeInt && j >= 0 && j < gameSizeInt && !done){
          	if(data[i+1][j] == notTurn){
        		if(i+2 >= 0 && i+2 < gameSizeInt && j >= 0 && j < gameSizeInt){
       				if(data[i+2][j] == turn){
       				for(int k = i+1;k > oI;k--){
        					data[k][j] = turn;
        					System.out.println("right" + k + " " + j);		
    					}
       					done = true;
           			}else if(data[i+2][j] == notTurn){
           				i = i+1;
           			}else{
           				done = true;
           			}
       			}else{
       				done = true;	
       			}
       		}else{
       			done = true;
       		}
   		}
       	
        i = oI;
        j = oJ;
        done = false;
   	
      //right-down
    	while(i+1 >= 0 && i+1 < gameSizeInt && j+1 >= 0 && j+1 < gameSizeInt && !done){
        	if(data[i+1][j+1] == notTurn){
        	if(i+2 >= 0 && i+2 < gameSizeInt && j+2 >= 0 && j+2 < gameSizeInt){
    				if(data[i+2][j+2] == turn){
    					int m = i+1;
    					for(int k = j+1;k > oJ;k--){
    						System.out.println("right-down" + m + " " + k);		
        					data[m][k] = turn;
        					m--;
    					}
    					done = true;
        			}else if(data[i+2][j+2] == notTurn){
        				i = i+1;
        				j = j+1;
        			}else{
        				done = true;
        			}
    			}else{
    				done = true;	
    			}
    		}else{
    			done = true;
    		}
		}
    	
    	 i = oI;
         j = oJ;
         done = false;
     	
       //down
     	while(i >= 0 && i < gameSizeInt && j+1 >= 0 && j+1 < gameSizeInt && !done){
         	if(data[i][j+1] == notTurn){
       		if(i >= 0 && i < gameSizeInt && j+2 >= 0 && j+2 < gameSizeInt){
     				if(data[i][j+2] == turn){
     					for(int k = j+1;k > oJ;k--){
        					data[i][k] = turn;
        					System.out.println("down" + i + " " + k);					
    					}
     					done = true;
         			}else if(data[i][j+2] == notTurn){
         				j = j+1;
         			}else{
         				done = true;
         			}
     			}else{
     				done = true;	
     			}
     		}else{
     			done = true;
     		}
 		}
     	
     	 i = oI;
         j = oJ;
         done = false;
     	
       //down-left
     	while(i-1 >= 0 && i-1 < gameSizeInt && j+1 >= 0 && j+1 < gameSizeInt && !done){
        	if(data[i-1][j+1] == notTurn){
       		if(i-2 >= 0 && i-2 < gameSizeInt && j+2 >= 0 && j+2 < gameSizeInt){
     				if(data[i-2][j+2] == turn){
     					int m = i-1;
     					for(int k = j+1;k > oJ;k--){
    						System.out.println("down-left" + m + " " + k);						
        					data[m][k] = turn;
        					m++;
    					}
     					done = true;
         			}else if(data[i-2][j+2] == notTurn){
         				j = j+1;
         				i = i-1;
         			}else{
         				done = true;
         			}
     			}else{
     				done = true;	
     			}
     		}else{
     			done = true;
     		}
 		}
     	
   	 i = oI;
     j = oJ;
     done = false;
 	
   //left
 	while(i-1 >= 0 && i-1  < gameSizeInt && j >= 0 && j < gameSizeInt && !done){
     	if(data[i-1][j] == notTurn){
     		if(i-2 >= 0 && i-2 < gameSizeInt && j >= 0 && j < gameSizeInt){
 				if(data[i-2][j] == turn){
 					for(int k = i-2;k < oI;k++){
    					data[k][j] = turn;
    					System.out.println("down-left" + k + " " + j);					
    				}
 					done = true;
     			}else if(data[i-2][j] == notTurn){
     				i = i-1;
     			}else{
     				done = true;
     			}
 			}else{
 				done = true;	
 			}
 		}else{
 			done = true;
 		}
		}
 	
	 i = oI;
     j = oJ;
     done = false;
	
   //left-up
 	while(i-1 >= 0 && i-1 < gameSizeInt && j-1 >= 0 && j-1 < gameSizeInt && !done){
     	if(data[i-1][j-1] == notTurn){
     		if(i-2 >= 0 && i-2 < gameSizeInt && j-2 >= 0 && j-2 < gameSizeInt){
 				if(data[i-2][j-2] == turn){
 					int m = i-1;
 					for(int k = j-1;k < oJ;k++){
						System.out.println("down" + m + " " + k);					
    					data[m][k] = turn;
    					m++;
					}
 					done = true;
     			}else if(data[i-2][j-2] == notTurn){
     				i = i-1;
     				j = j-1;
     			}else{
     				done = true;
     			}
 			}else{
 				done = true;	
 			}
 		}else{
 			done = true;
 		}
		}
   		
	}

	/**
     * Count how much pieces from every kind
     */
	static void count(){
		black = 0;
		white = 0;
		frei = 0;
		blue = 0;
		 for(int i = 0 ;i < data.length;i++){
        	 for(int j = 0 ;j < data[i].length;j++){
        		 if(data[i][j] == 1){
        			 black++;
        		 }
        		 if(data[i][j] == 2){
        			 white++;
        		 }
        		 if(data[i][j] == 0){
        			 frei++;
        		 }
        		 if(data[i][j] == -1){
        			 blue++;
        		 }
        		 }
        	 }
        	 
	}

	
	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		 
		     
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}