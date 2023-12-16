import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

 public class PaintApplet extends Applet  
 {
	 int x1, x2, y1, y2 ;
	 
	 Oval oval = new Oval();
	 Rectangle rect = new Rectangle();
	 Line line =new Line();
	 FreeHandDrawing freeHand = new FreeHandDrawing();
	 Eraser eraser = new Eraser();
	 
	 Button btnRect, btnOval, btnLine, btnEraser, btnFreeHand, btnRED, btnGreen , btnBlue ,btnFilled, btnClear, btnUndo;
	 Checkbox Filled;
	 ArrayList<Shape> shapes; 
	 
	 boolean firstDraw = true;
	 boolean isFilled = false;
	 int shapeMode = LINE_MODE;
	 Color currentColor = Color.BLACK;
	 
	 protected static final int RECT_MODE = 0;
	 protected static final int OVAL_MODE = 1;
	 protected static final int LINE_MODE = 2;
	 protected static final int FREE_HAND_MODE = 3;
	 protected static final int ERASER_MODE = 4;
	 protected static final int CLEAR_ALL_MODE = 5;
	 protected static final int UNDO_MODE = 6;
	 
	 
	 public void init(){
		shapes = new ArrayList<Shape>();
			 
		btnRect = new Button("Draw Rectangle");
		add(btnRect);
		btnRect.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				shapeMode = PaintApplet.RECT_MODE;
			}
		});
			 
		btnOval = new Button("Draw Oval");
		add(btnOval);
		btnOval.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				shapeMode =  PaintApplet.OVAL_MODE;
			}
		});
			 
		btnLine = new Button("Draw Line");
		add(btnLine);
		btnLine.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				shapeMode =  PaintApplet.LINE_MODE;
			}
		});
			 
			 
		btnFreeHand = new Button("FreeHand");
		add(btnFreeHand);
		btnFreeHand.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				shapeMode = FREE_HAND_MODE;
			}
		});	
			 
		btnRED = new Button("red color");
		add(btnRED);
		btnRED.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				currentColor = Color.RED;
			}
		});
			
		btnGreen = new Button("Green color");
		add(btnGreen);
		btnGreen.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				currentColor = Color.GREEN;
			}
		});
			
		btnBlue = new Button("BLUE color");
		add(btnBlue);
		btnBlue.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				currentColor = Color.BLUE;
			}
		});
			
		btnEraser = new Button("Eraser");
		add(btnEraser);
		btnEraser.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				shapeMode = ERASER_MODE;
			}
		});

		btnClear = new Button("Clear All");
		add(btnClear);
		btnClear.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				shapes.clear();
				repaint();
			}
		});
				
		btnUndo = new Button("Undo");
		add(btnUndo);
		btnUndo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (shapes.size()!=0)
					shapes.remove(shapes.size() -1);
					repaint();
								
				}
			});
				
		Filled = new Checkbox("Fill",isFilled);
				add(Filled);
					Filled.addItemListener(new ItemListener(){
						public void itemStateChanged(ItemEvent e){
						isFilled = Filled.getState();		
						}
					});
			
		this.addMouseListener(new MouseAdapter(){
			public void mousePressed (MouseEvent e){ 
				switch (shapeMode) {
						
					case PaintApplet.RECT_MODE:
						rect.color = currentColor;
						rect.isFilled = isFilled;
						rect.x1 = e.getX();
						rect.y1 = e.getY();
					break;
						
					case PaintApplet.OVAL_MODE:
						oval.color = currentColor;
						oval.isFilled = isFilled;
						oval.x1 = e.getX();
						oval.y1 = e.getY();
					break;
						
					case PaintApplet.LINE_MODE:
						line.color = currentColor;
						line.x1 = e.getX();
						line.y1 = e.getY();
				    break;
						
					case PaintApplet.ERASER_MODE:
						eraser.x1 = e.getX();
						eraser.y1 = e.getY();
					break;
						
					case PaintApplet.FREE_HAND_MODE:
						freeHand.x1 = e.getX();
						freeHand.y1 = e.getY();
					break;	
				}
			 }
		});
		
		this.addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent e) {
			
			switch (shapeMode)
				{
					case PaintApplet.RECT_MODE:
						rect.color = currentColor;
						rect.x2 = e.getX();
						rect.y2 = e.getY();
						shapes.add(rect);
						rect = new Rectangle();
						repaint();
					break;
					
					case PaintApplet.OVAL_MODE:
						oval.x2 = e.getX();
						oval.y2 = e.getY();
						shapes.add(oval);
						oval = new Oval();
						repaint();
					break;
					
					case PaintApplet.LINE_MODE:
						line.x2 = e.getX();
						line.y2 = e.getY();
						shapes.add(line);
						line = new Line();
						repaint();
					break;
					
					case PaintApplet.FREE_HAND_MODE:
						freeHand.color = currentColor;
						freeHand.x2 = e.getX();
						freeHand.y2 = e.getY();
						firstDraw = true;
						repaint();
					

					break;
					
					case PaintApplet.ERASER_MODE:
						freeHand.x2 = e.getX();
						freeHand.y2 = e.getY();
						shapes.add(new Eraser(eraser.x1, eraser.y1, 50,50, Color.WHITE, true));
						repaint();
					break; 

				}

		}
		});
		
		this.addMouseMotionListener(new MouseMotionAdapter() {
				public void mouseDragged(MouseEvent e){	
					switch (shapeMode)
					{
						case PaintApplet.RECT_MODE:
							rect.color = currentColor;
							rect.x2 = e.getX();
							rect.y2 = e.getY();
							repaint();
						break;
						
						case PaintApplet.OVAL_MODE:
							oval.color = currentColor;
							oval.x2 = e.getX();
							oval.y2 = e.getY();
							repaint();
						break;
						
						case PaintApplet.LINE_MODE:
							line.x2 = e.getX();
							line.y2 = e.getY();
							repaint();
						break;
						
						case PaintApplet.FREE_HAND_MODE:
						
								freeHand.color =currentColor;
								if (firstDraw == false) 
								{	
									freeHand.x1 = freeHand.x2; 
									freeHand.y1 = freeHand.y2;
								}
									freeHand.x2 = e.getX();
									freeHand.y2 = e.getY();
									shapes.add(freeHand);
									freeHand = new FreeHandDrawing(freeHand.x1, freeHand.y1,freeHand.x2,freeHand.y2, currentColor);
									firstDraw = false;		
									repaint();
						break;
						
						case PaintApplet.ERASER_MODE:
							eraser.x1 = e.getX();
							eraser.y1 = e.getY();
							shapes.add(new Eraser(eraser.x1, eraser.y1, 50,50, Color.WHITE, true));
							repaint();
						break; 
			
					}
				}
			});			
		}
	
	
	 public void paint(Graphics g)
	{
		switch (shapeMode){
			
			case PaintApplet.RECT_MODE:
				rect.draw(g);
			break;
			
			case PaintApplet.OVAL_MODE:
				oval.draw(g);
			break;
				
			case PaintApplet.LINE_MODE:
				line.draw(g); 
			break;
				
			case PaintApplet.FREE_HAND_MODE:
				freeHand.draw(g);
			break;
			
			case PaintApplet.ERASER_MODE:
				g.setColor(Color.WHITE);
				g.fillRect (x1,y1,20,20);
			break;	

		}
		
		for (int j =0; j< shapes.size(); j++ )
			{shapes.get(j).draw(g);}
	
	}

 }
	 
  abstract class Shape{
	int x1, y1, x2, y2;
	Color color;
	boolean isFilled;
	
	 Shape(){}
	 Shape (int xSt,int ySt, int xEn,int yEn, Color c1, boolean isFilled)
	{
		x1 = xSt;
		y1 = ySt;
		x2 = xEn;
		y2 = yEn;
		c1 = Color.BLUE;
		isFilled = false;
	}
	
	 Shape (int xSt,int ySt, int xEn,int yEn, Color c1)
	{
		x1 = xSt;
		y1 = ySt;
		x2 = xEn;
		y2 = yEn;
		c1 = Color.BLUE;
		isFilled = false;
	}
		
	public abstract void draw(Graphics g);
}

class Rectangle extends Shape{
	
	Rectangle() {}
	 Rectangle (int xSt,int ySt, int xEn,int yEn, Color c1, boolean isFilled)
	{
		x1 = xSt;
		y1 = ySt;
		x2 = xEn;
		y2 = yEn;
		c1 = Color.BLUE;
		isFilled = false;
	}
	
	public void draw (Graphics g)
	{
		g.setColor(color);
		
		if (isFilled == true)
			g.fillRect(Math.min(x2,x1),Math.min(y2,y1),
					Math.abs(x1 - x2), Math.abs(y1 - y2));
		else 
			
		g.drawRect(Math.min(this.x2,this.x1),Math.min(this.y2,this.y1),
					Math.abs(this.x1 - this.x2), Math.abs(this.y1 - this.y2));
	}
}

class Eraser extends Rectangle{
	
	Eraser () {}
	Eraser (int xSt,int ySt, int xEn,int yEn, Color c1, boolean isFilled)
	{
		super(xSt,ySt, xEn, yEn, Color.WHITE, true);
	}
	
	 public void draw (Graphics g)
	{
		g.setColor(Color.WHITE);
		
		g.fillRect(x1,y1, 20,20);
		
	}
}

class Oval extends Shape {
	
	Oval(){}
	public Oval (int xSt,int ySt, int xEn,int yEn, Color c1, boolean isFilled)
	{
		super(xSt,ySt, xEn, yEn, c1, isFilled);
	}
	public void draw (Graphics g)
	{
		g.setColor(color);
		
		if (isFilled == true)
			g.fillOval (Math.min(x2,x1),Math.min(y2,y1),
					Math.abs(x1 - x2), Math.abs(y1 - y2));
		else 
			
		g.drawOval (Math.min(x2,x1),Math.min(y2,y1),
					Math.abs(x1 - x2), Math.abs(y1 - y2));
}
}

class Line extends Shape {
	Line(){}
	
	public Line (int xSt,int ySt, int xEn,int yEn, Color c1)
	{
		super(xSt,ySt, xEn, yEn, c1);
	}
	
	public void draw (Graphics g){
		g.setColor(color);
		g.drawLine(x1, y1, x2, y2);
	}
}

class FreeHandDrawing extends Shape{
	FreeHandDrawing() {}
	public FreeHandDrawing (int xSt,int ySt, int xEn,int yEn, Color c1)
	{
		super(xSt,ySt, xEn, yEn, c1);
	}
	
	public void draw (Graphics g)
	{
		g.setColor(color);
		g.drawLine(x1, y1, x2, y2);
	}
}

 