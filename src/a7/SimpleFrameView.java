package a7;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SimpleFrameView {
	public static void main(String[] args) throws IOException {
		Frame f = A7Helper.readFromURL("http://www.cs.unc.edu/~kmp/kmp.jpg");
		f.setTitle("K to the M to the P");
		SimpleFrameViewWidget simple_widget = new SimpleFrameViewWidget(f);
		
		JFrame main_frame = new JFrame();
		main_frame.setTitle("Assignment 7 Simple Frame View");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel top_panel = new JPanel();
		top_panel.setLayout(new BorderLayout());
		top_panel.add(simple_widget, BorderLayout.CENTER);
		main_frame.setContentPane(top_panel);

		main_frame.pack();
		main_frame.setVisible(true);
	}
}