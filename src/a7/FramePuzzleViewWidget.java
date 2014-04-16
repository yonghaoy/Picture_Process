package a7;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class FramePuzzleViewWidget extends JPanel implements MouseListener,KeyListener {

	private FrameView[][] frame_views;
	private JPanel[][] panels;
	
	//the position of solid color
	private int x_pos = 4;
	private int y_pos = 4;
	
	public FramePuzzleViewWidget(Frame f) {
		frame_views = new FrameView[5][5];
		IndirectFrame[][] puzzles = f.makeTiles(5, 5);
		panels = new JPanel[5][5];
		setLayout(new GridLayout(5,5));
		for(int j = 0 ; j < 5; j++){
			for(int i = 0; i < 5; i++){
				frame_views[i][j] = new FrameView(puzzles[i][j]);
				frame_views[i][j].addMouseListener(this);
				panels[i][j] = new JPanel();
				panels[i][j].setLayout(new BorderLayout());
				panels[i][j].add(frame_views[i][j],BorderLayout.CENTER);
				add(panels[i][j]);
				
			}
		}
		
		//set right corner indriectframe to solid color
		puzzles[4][4].suspendNotifications();
		for(int i = 0; i < puzzles[4][4].getWidth(); i++){
			for(int j = 0; j < puzzles[4][4].getHeight(); j++){
				puzzles[4][4].setPixel(i, j,new ColorPixel(49,130,33));
			}
		}
		puzzles[4][4].resumeNotifications();
		
		addKeyListener(this);
		setFocusable(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() != frame_views[x_pos][y_pos]){
			for(int i = 0; i< 5; i++){
				
				//clicked position in the same row as solid color frame
				if(e.getSource() == frame_views[i][y_pos]){
					
					//clicked position in the left of solid color frame
					if(i < x_pos){
						FrameView tem = frame_views[x_pos][y_pos];
						//frame_views[i][y_pos] = frame_views[x_pos][y_pos];
						for(int j = x_pos; j > i; j--){
							frame_views[j][y_pos] = frame_views[j-1][y_pos];
							panels[j][y_pos].add(frame_views[j][y_pos]);
						}
						frame_views[i][y_pos] = tem;
						panels[i][y_pos].add(frame_views[i][y_pos]);
						x_pos = i;
						this.revalidate();
						return;
					}
					
					//clicked position in the right of solid color frame
					else if(i > x_pos){
						
						FrameView tem = frame_views[x_pos][y_pos];
						for(int j = x_pos; j < i; j++){
							frame_views[j][y_pos] = frame_views[j+1][y_pos];
							panels[j][y_pos].add(frame_views[j][y_pos]);
						}
						frame_views[i][y_pos] = tem;
						panels[i][y_pos].add(frame_views[i][y_pos]);
						x_pos =i;
						this.revalidate();
						return;
					}
				}
				
				//clicked position in the same colomn as solid color frame
				if(e.getSource() == frame_views[x_pos][i]){
					
					//clicked position in the up of solid color frame
					if(i < y_pos){
						FrameView tem = frame_views[x_pos][y_pos];
						//frame_views[i][y_pos] = frame_views[x_pos][y_pos];
						for(int j = y_pos; j > i; j--){
							frame_views[x_pos][j] = frame_views[x_pos][j-1];
							panels[x_pos][j].add(frame_views[x_pos][j]);
						}
						frame_views[x_pos][i] = tem;
						panels[x_pos][i].add(frame_views[x_pos][i]);
						y_pos = i;
						this.revalidate();
						return;
					}
					
					//clicked position in the down of solid color frame
					else if(i > y_pos){
						FrameView tem = frame_views[x_pos][y_pos];
						for(int j = y_pos; j < i; j++){
							frame_views[x_pos][j] = frame_views[x_pos][j+1];
							panels[x_pos][j].add(frame_views[x_pos][j]);
						}
						frame_views[x_pos][i] = tem;
						panels[x_pos][i].add(frame_views[x_pos][i]);
						y_pos =i;
						this.revalidate();
						return;
					}
				}
			}	
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		 if(e.getKeyCode() ==KeyEvent.VK_UP && y_pos > 0){
			 	FrameView tem = frame_views[x_pos][y_pos];
			 	frame_views[x_pos][y_pos] = frame_views[x_pos][y_pos-1];
			 	frame_views[x_pos][y_pos-1] = tem;
			 	panels[x_pos][y_pos].add(frame_views[x_pos][y_pos]);
				panels[x_pos][y_pos-1].add(frame_views[x_pos][y_pos-1]);
				y_pos --;
				this.revalidate();
		 }
		 if(e.getKeyCode() ==KeyEvent.VK_DOWN && y_pos < 4){
				FrameView tem = frame_views[x_pos][y_pos];
				frame_views[x_pos][y_pos] = frame_views[x_pos][y_pos+1];
				frame_views[x_pos][y_pos+1] = tem;
				panels[x_pos][y_pos].add(frame_views[x_pos][y_pos]);
				panels[x_pos][y_pos+1].add(frame_views[x_pos][y_pos+1]);
				y_pos ++;
				this.revalidate();
		}
		 if(e.getKeyCode() ==KeyEvent.VK_LEFT && x_pos > 0){
				FrameView tem = frame_views[x_pos][y_pos];
				frame_views[x_pos][y_pos] = frame_views[x_pos-1][y_pos];
				frame_views[x_pos-1][y_pos] = tem;
				panels[x_pos][y_pos].add(frame_views[x_pos][y_pos]);
				panels[x_pos-1][y_pos].add(frame_views[x_pos-1][y_pos]);
				x_pos --;
		}
		 if(e.getKeyCode() ==KeyEvent.VK_RIGHT && x_pos < 4){
				FrameView tem = frame_views[x_pos][y_pos];
				frame_views[x_pos][y_pos] = frame_views[x_pos+1][y_pos];
				frame_views[x_pos+1][y_pos] = tem;
				panels[x_pos][y_pos].add(frame_views[x_pos][y_pos]);
				panels[x_pos+1][y_pos].add(frame_views[x_pos+1][y_pos]);
				x_pos ++;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}