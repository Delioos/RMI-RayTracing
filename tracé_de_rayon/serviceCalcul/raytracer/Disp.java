package raytracer;
import javax.swing.*;        
import java.awt.*;
import java.awt.image.BufferedImage;


public class Disp {
    private JFrame frame; 
    private ImageRenderComponent renderer;

    public Disp(String title, int width, int height) {
	
	// create the image on which we draw
	BufferedImage image = new BufferedImage(width, height, 
						BufferedImage.TYPE_INT_RGB);
	


        //Create and set up the window.
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(image.getWidth(), image.getHeight());
	
	// create the image 
	renderer = new ImageRenderComponent(image);
	renderer.setOpaque(true);

	// add renderer to the frame
	frame.setContentPane(renderer);
	
        //Display the window.
        frame.setVisible(true);
    }


    public void setImagePixel(int x, int y, Color c){
	renderer.setImagePixel(x, y,  c);
    }
    
    public void repaint(){
	frame.repaint();
    }
    
    public void setImage(Image i, int x0, int y0){
	for(int y=0; y<i.getHeight() ; y++)
	    for(int x=0; x<i.getWidth() ; x++)
		renderer.setImagePixel(x0+x, y0+y, i.getPixel(x,y));
	
	frame.repaint();
    }

    
}


class ImageRenderComponent extends JPanel {
    private BufferedImage image;
    private Dimension size;
 
    public ImageRenderComponent(BufferedImage image) {
        this.image = image;
        size = new Dimension(image.getWidth(), image.getHeight());
	
	Color c = Color.white;
	int white = c.getRGB();
	for(int i=0; i<image.getWidth(); i++)
	    for(int j=0; j<image.getHeight(); j++)
		image.setRGB(i, j, white); 
	
    }
 
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = (getWidth() - size.width)/2;
        int y = (getHeight() - size.height)/2;
        g.drawImage(image, x, y, this);
    }
 
    public Dimension getPreferredSize() {
        return size;
    }

    public void setImagePixel(int x, int y, Color c){
	int rgb = c.getRGB();
	image.setRGB(x, y, rgb); 
    }

    
  
    

}











