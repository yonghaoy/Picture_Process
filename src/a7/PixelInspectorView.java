package a7;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PixelInspectorView {
	public static void main(String[] args) throws IOException {
		Frame f = A7Helper.readFromURL("http://www.cs.unc.edu/~kmp/kmp.jpg");
		f.setTitle("Image Inspector frame");
		PixelInspectorViewWidget Inspector_widget = new PixelInspectorViewWidget(f);
		
		JFrame main_frame = new JFrame();
		main_frame.setTitle("Assignment 7 Pixel Inspector");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel top_panel = new JPanel();
		top_panel.setLayout(new BorderLayout());
		top_panel.add(Inspector_widget, BorderLayout.CENTER);
		main_frame.setContentPane(top_panel);

		main_frame.pack();
		main_frame.setVisible(true);
	}

}
