package a7;

public class ColorFrame extends DirectFrame {

	private static final Pixel DEFAULT_PIXEL_VALUE = new ColorPixel(0.5, 0.5, 0.5);
    	
	public ColorFrame(int width, int height, Pixel init_color, String title) {
		super(width, height, init_color, title);

		pixels = new ColorPixel[height][width];
		for (int y = 0; y<height; y++) {
			for (int x = 0; x<width; x++) {
				pixels[y][x] = init_color;
			}
		}
	}
	
	public ColorFrame(int width, int height) {
		this(width, height, ColorFrame.DEFAULT_PIXEL_VALUE, DirectFrame.DEFAULT_TITLE);
	}


	public void setPixel(int x, int y, Pixel p) {
		if (!(p instanceof ColorPixel)) {
			throw new RuntimeException("ColorFrame can only accept ColorPixel");
		}
		super.setPixel(x, y, p);
	}	
}
