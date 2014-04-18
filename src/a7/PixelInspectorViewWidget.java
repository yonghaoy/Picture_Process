package a7;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class PixelInspectorViewWidget extends JPanel implements MouseListener {

	private FrameView frame_view;
	private Frame f;
	private JLabel x_pos;
	private JLabel y_pos;
	private JLabel red;
	private JLabel green;
	private JLabel blue;
	private JLabel brightness;
	public PixelInspectorViewWidget(Frame f) {
		setLayout(new BorderLayout());
		this.f = f;
		frame_view = new FrameView(f);
		frame_view.addMouseListener(this);
		add(frame_view, BorderLayout.EAST);
		
		JPanel display_panel = new JPanel();
		display_panel.setLayout(new GridLayout(0,1));
		display_panel.setPreferredSize(new Dimension(115,100));
		
		JPanel x_display = new JPanel();
		x_display.setLayout(new BorderLayout());
		JLabel x_text = new JLabel("X: ");
		x_pos = new JLabel();
		x_display.add(x_text, BorderLayout.WEST);
		x_display.add(x_pos, BorderLayout.EAST);
		display_panel.add(x_display);
		
		JPanel y_display = new JPanel();
		y_display.setLayout(new BorderLayout());
		JLabel y_text = new JLabel("Y: ");
		y_pos = new JLabel();
		y_display.add(y_text, BorderLayout.WEST);
		y_display.add(y_pos, BorderLayout.EAST);
		display_panel.add(y_display);
		
		JPanel red_display = new JPanel();
		red_display.setLayout(new BorderLayout());
		JLabel red_text = new JLabel("Red: ");
		red = new JLabel();
		red_display.add(red_text, BorderLayout.WEST);
		red_display.add(red, BorderLayout.EAST);
		display_panel.add(red_display);
		
		JPanel green_display = new JPanel();
		green_display.setLayout(new BorderLayout());
		JLabel green_text = new JLabel("Green: ");
		green = new JLabel();
		green_display.add(green_text, BorderLayout.WEST);
		green_display.add(green, BorderLayout.EAST);
		display_panel.add(green_display);
		
		JPanel blue_display = new JPanel();
		blue_display.setLayout(new BorderLayout());
		JLabel blue_text = new JLabel("Blue: ");
		blue = new JLabel();
		blue_display.add(blue_text, BorderLayout.WEST);
		blue_display.add(blue, BorderLayout.EAST);
		display_panel.add(blue_display);
		
		JPanel brightness_display = new JPanel();
		brightness_display.setLayout(new BorderLayout());
		JLabel brightness_text = new JLabel("Brightness: ");
		brightness = new JLabel();
		brightness_display.add(brightness_text, BorderLayout.WEST);
		brightness_display.add(brightness, BorderLayout.EAST);
		display_panel.add(brightness_display);
		
		add(display_panel, BorderLayout.WEST);
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		double red_num = f.getPixel(e.getX(), e.getY()).getRed();
		double green_num = f.getPixel(e.getX(), e.getY()).getGreen();
		double blue_num = f.getPixel(e.getX(), e.getY()).getBlue();
		double brightness_num = f.getPixel(e.getX(), e.getY()).getBrightness();
				
		String RED= String.format("%03.2f", red_num);
		String GREEN = String.format("%03.2f", green_num);
		String BLUE = String.format("%03.2f", blue_num);
		String BRIGHTNESS = String.format("%03.2f", brightness_num);
		
		x_pos.setText(String.valueOf(e.getX()));
		y_pos.setText(String.valueOf(e.getY()));
		red.setText(RED);
		green.setText(GREEN);
		blue.setText(BLUE);
		brightness.setText(BRIGHTNESS);
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

}