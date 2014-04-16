package a7;
import java.awt.BorderLayout;
import java.io.IOException;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
public class ImageAdjusterViewWidget extends JPanel implements ChangeListener {

	private Frame view_f;
	
	//reference to the original image
	private Frame original;
	
	private FrameView frame_view;
	private JSlider blur_slider;
	private JSlider satura_slider;
	private JSlider bright_slider;
	public ImageAdjusterViewWidget(Frame f) {
		original = f;
		
		view_f = new ColorFrame(original.getWidth(), original.getHeight());
		
		//copy origin image to view frame
		for(int i = 0; i < original.getWidth(); i++){
			for(int j = 0; j < original.getHeight(); j++){
				view_f.setPixel(i, j, original.getPixel(i, j));
			}
		}
		setLayout(new BorderLayout());
		frame_view = new FrameView(view_f);
		add(frame_view, BorderLayout.NORTH);
		
		JPanel slider_area = new JPanel();
		slider_area.setLayout(new GridLayout(3,1));
		
		//add blur panel and slider
		JPanel blur_panel = new JPanel();
		blur_panel.setLayout(new BorderLayout());
		JLabel blur_text = new JLabel("Blur: ");
		blur_slider = new JSlider(0, 5, 0);
		blur_slider.setPaintTicks(true);
		blur_slider.setSnapToTicks(true);
		blur_slider.setPaintLabels(true);
		blur_slider.setMajorTickSpacing(1);
		blur_panel.add(blur_text, BorderLayout.WEST);
		blur_panel.add(blur_slider);
		slider_area.add(blur_panel);
		
		
		//add saturation panel and slider
		JPanel satura_panel = new JPanel();
		satura_panel.setLayout(new BorderLayout());
		JLabel satura_text = new JLabel("Saturation: ");
		satura_slider = new JSlider(-100, 100, 0);
		satura_slider.setMajorTickSpacing(25);
		satura_slider.setPaintTicks(true);
		satura_slider.setSnapToTicks(false);
		satura_slider.setPaintLabels(true);
		satura_panel.add(satura_text, BorderLayout.WEST);
		satura_panel.add(satura_slider);
		slider_area.add(satura_panel);	
		
		//add brightness panel and slider
		JPanel bright_panel = new JPanel();
		bright_panel.setLayout(new BorderLayout());
		JLabel bright_text = new JLabel("Brightness: ");
		bright_slider = new JSlider(-100, 100, 0);
		bright_slider.setMajorTickSpacing(25);
		bright_slider.setPaintTicks(true);
		bright_slider.setSnapToTicks(false);
		bright_slider.setPaintLabels(true);
		bright_panel.add(bright_text, BorderLayout.WEST);
		bright_panel.add(bright_slider);
		slider_area.add(bright_panel);
		
		blur_slider.addChangeListener(this);
		satura_slider.addChangeListener(this);
		bright_slider.addChangeListener(this);
		add(slider_area, BorderLayout.SOUTH);
		
	}
	@Override
	public void stateChanged(ChangeEvent e) {
		view_f.suspendNotifications();
		if (!((JSlider) e.getSource()).getValueIsAdjusting()) {
			int blur_size = blur_slider.getValue();
			int bright_value = bright_slider.getValue();
			int satura_value = satura_slider.getValue();	
			set_blur(view_f, blur_size);
			set_bright(view_f, bright_value);
			set_satura(view_f, satura_value);
			view_f.resumeNotifications();
		}		
	}
	
	private void set_blur(Frame blur_f, int blur_size) {
		
		for(int i = 0; i < blur_f.getWidth(); i++){
			for(int j = 0; j < blur_f.getHeight(); j++){
				Frame result = getAround(original, i, j, blur_size);
				blur_f.setPixel(i, j, result.getAverage());
			}
		}
		
	}
	
	private void set_bright(Frame bright_f, int value){
		for(int i = 0; i < bright_f.getWidth(); i++){
			for(int j = 0; j < bright_f.getHeight(); j++){
				
				//when adjust slider between 0 - 100
				if(value > 0){
					double new_red = original.getPixel(i, j).getRed() + (1.0 - original.getPixel(i, j).getRed()) * value/100.0;
					double new_green = original.getPixel(i, j).getGreen() + (1.0 - original.getPixel(i, j).getGreen()) * value/100.0;
					double new_blue = original.getPixel(i, j).getBlue() + (1.0 - original.getPixel(i, j).getBlue()) * value/100.0;
					bright_f.setPixel(i, j, new ColorPixel(new_red, new_green, new_blue));
				}
				
				//when adjust slider between -100 - 0
				else if(value < 0){
					double new_red = original.getPixel(i, j).getRed() + original.getPixel(i, j).getRed() * value/100.0;
					double new_green = original.getPixel(i, j).getGreen() + original.getPixel(i, j).getGreen() * value/100.0;
					double new_blue = original.getPixel(i, j).getBlue() + original.getPixel(i, j).getBlue() * value/100.0;
					bright_f.setPixel(i, j, new ColorPixel(new_red, new_green, new_blue));
				}			
			}
		}
	}
	
	private void set_satura(Frame satura_f, int satura_value){
		
		for(int i = 0; i < satura_f.getWidth(); i++){
			for(int j = 0; j < satura_f.getHeight(); j++){
				
				//when adjust slider between 0 - 100
				if(satura_value > 0){
					double large = getLarge(original.getPixel(i, j));
					double gain = (large + (1.0 -large) * satura_value/100.0)/large;
					double new_red = original.getPixel(i, j).getRed() * gain;
					double new_green = original.getPixel(i, j).getGreen() * gain;
					double new_blue = original.getPixel(i, j).getBlue() * gain;
					satura_f.setPixel(i, j, new ColorPixel(new_red, new_green, new_blue));
				}
				
				//when adjust slider between -100 - 0
				else if(satura_value < 0){
					double new_red = original.getPixel(i, j).getRed() * (1.0 + satura_value/100.0) - (original.getPixel(i, j).getBrightness() * satura_value/100.0);
					double new_green = original.getPixel(i, j).getGreen() * (1.0 + satura_value/100.0) - (original.getPixel(i, j).getBrightness() * satura_value/100.0);
					double new_blue = original.getPixel(i, j).getBlue() * (1.0 + satura_value/100.0) - (original.getPixel(i, j).getBrightness() * satura_value/100.0);
					satura_f.setPixel(i, j, new ColorPixel(new_red, new_green, new_blue));
				}
				
				
			}
		}
	}
	/**
	 * 
	 * @param pixel : the pixel to get largest compoment.
	 * @return : the largest value of a Red, Green, or Blue
	 */
	private double getLarge(Pixel pixel){
		double large = pixel.getRed();
		if(pixel.getBlue() > large){
			large = pixel.getBlue();
			if(pixel.getBlue() > large){
				large = pixel.getBlue();
				if(pixel.getBrightness() > large){
					large = pixel.getBrightness();
				}
			}
		}
		return large;
	}
	
	/**
	 * This gets a Indirect Frame centered a given pixel.
	 * @param blur_f
	 * @param x_pos : the position of the given pixel
	 * @param y_pos : the position of the given pixel
	 * @param size : the length of the edge to the center pixel
	 * @return :this returns a IndirectFrame surround a given pixel in a Frame.
	 */
	private Frame getAround(Frame blur_f, int x_pos, int y_pos, int size){
		
		//the length of sub IndirectFrame square area
		int range = 2*size + 1;	
		
		//the width of sub IndirectFrame
		int width = range;
		
		//the height of sub IndirectFrame area
		int height = range;
		
		//the start position of sub IndirectFrame
		int start_x = x_pos - size;
		
		//the start position of sub IndirectFrame
		int start_y = y_pos - size;
		
		
		if(x_pos < size){
			start_x = 0;
			width -= (size - x_pos);
		}
		if((blur_f.getWidth() - x_pos) <= size){
			width -= (size - (blur_f.getWidth() - x_pos -1));
		}
		if(y_pos < size){
			start_y = 0;
			height -= (size - y_pos);
		}
		if((blur_f.getHeight() - y_pos) <= size){
			height -= (size - (blur_f.getHeight() - y_pos -1));
		}
		
		IndirectFrame sub_frame = blur_f.crop(start_x, start_y, width, height);
		
		//blur_f.setPixel(x_pos, y_pos, sub_frame.getAverage());
		return sub_frame;
	}
	
}
