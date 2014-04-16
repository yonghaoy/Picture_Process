package a7;

import java.awt.BorderLayout;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ImageAdjusterView {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		Frame f = A7Helper.readFromURL("http://www.cs.unc.edu/~kmp/kmp.jpg");
		f.setTitle("Image Adjuster");
		ImageAdjusterViewWidget adjuster_widget = new ImageAdjusterViewWidget(f);
		
		JFrame main_frame = new JFrame();
		main_frame.setTitle("Assignment 7 Image Adjuster View");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel top_panel = new JPanel();
		top_panel.setLayout(new BorderLayout());
		top_panel.add(adjuster_widget, BorderLayout.CENTER);
		main_frame.setContentPane(top_panel);
		
		main_frame.pack();
		main_frame.setVisible(true);
	}

}
