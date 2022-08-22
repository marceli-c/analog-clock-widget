package zegar;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
//import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
//import java.awt.Paint;
//import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
import java.time.LocalDateTime;
//import java.util.Calendar;
//import  java.util.Date;
//import javax.sound.sampled.Line;
import javax.swing.JPanel;
//import javax.swing.SwingUtilities;
import javax.swing.Timer;



public class MyPanel extends JPanel implements ActionListener

{
	//---------------DEKLARACJE----------------//
	
	double kat=4.71;
	double katminuty=0.0;
	Timer timer;
	double katsekunda=0.0;
	
	static int promien=200;
    int xstart=5+promien;
    int ystart=5+promien;
	Color bialy=Color.white;
	Color czarny=Color.black;
    
    
    MyPanel()
    {
    	
        
        
    	
     this.setPreferredSize(new Dimension(promien*2,promien*2));
     godziny();
     this.setBackground(new Color(0,0,0,0));
     this.setOpaque(false);
     
    }
    
    

    
    //----------------------------TIMER---------------------------//
    
    
    public void godziny()
    {
    	
    	timer=new Timer(1000,this);
    	//LocalDateTime data=LocalDateTime.now();
    	timer.start();
    	
    }

    
    

    
    //------------------------------------RYSOWANIE-------------------//
    
    public void paint(Graphics g)
    {
    	
        super.paint(g);
        Graphics2D g2d=(Graphics2D) g;
        
        g2d.setRenderingHint
        (
        	RenderingHints.KEY_ANTIALIASING,
        	RenderingHints.VALUE_ANTIALIAS_ON
        );
        liniasekundy(g);
        tarcza(g);
        liniaminuty(g);
        liniagodziny(g); 
        liniagodzinynachodzaca(g);
        napisy(g);
        g2d.setBackground(new Color(0,0,0,0));
        
        
            
        
    }
    
    
    //----------------------------STRZAŁKA MINUTY--------------------//
    
    
    public void liniaminuty(Graphics g)
    
    {
    	int xm=(int) Math.round(xstart+Math.cos(katminuty)*(promien-20));
        int ym=(int) Math.round(xstart+Math.sin(katminuty)*(promien-20));
        Graphics2D g2d=(Graphics2D) g;
        g2d.setStroke(new BasicStroke(4,BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.setColor(bialy);
    	g2d.drawLine(xstart,ystart,xm,ym);
    	
    }
    
    
    //---------------------STRZAŁKA SEKUNDY------------------------------//
    
    
    public void liniasekundy(Graphics g)
    {
    	int xs=(int) Math.round(xstart+Math.cos(katsekunda)*(promien-2));
        int ys=(int) Math.round(xstart+Math.sin(katsekunda)*(promien-2));
        Graphics2D g2d=(Graphics2D) g;
        g2d.setStroke(new BasicStroke(2,BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.setColor(Color.red);
    	g2d.drawLine(xstart,ystart,xs,ys);
    }
    
    
    //---------------------STRZAŁKA GODZINY-----------------------------------//
    
    
    public void liniagodziny(Graphics g)
    {
    	int minus=55;
    	if(promien<=50)
    	{
    		minus=promien-10;
    	}
    	int x= (int) Math.round(xstart+Math.cos(kat)*(promien-minus));
        int y= (int) Math.round(ystart+Math.sin(kat)*(promien-minus));
        Graphics2D g2d=(Graphics2D) g;
        g2d.setColor(bialy);
        g2d.setStroke(new BasicStroke(7,BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.drawLine(xstart, ystart, x, y);
    }
    
    
    
    public void liniagodzinynachodzaca(Graphics g)
    {
    	int minus=70;
    	if(promien<=75)
    	{
    		minus=promien-10;
    	}
    	int x= (int) Math.round(xstart+Math.cos(kat)*(promien-minus));
        int y= (int) Math.round(ystart+Math.sin(kat)*(promien-minus));
        Graphics2D g2d=(Graphics2D) g;
        g2d.setStroke(new BasicStroke(2,BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.setColor(czarny);
        g2d.drawLine(xstart, ystart, x, y);
        
    }
    
    
    //-------------------------------TARCZA-------------------------------------//
    
    
    public void tarcza(Graphics g)
    {
    	Graphics2D g2d=(Graphics2D) g;
    	g2d.setColor(bialy);
    	g2d.drawOval(5, 5,promien*2, promien*2);
    }
    
    
    
    //----------------------------KĄT GODZINY----------------------------------//
    
    
    
    public void katgodzina(double czas)
    {
    	kat=(double)czas*(0.52333/60)+4.71;
    }
    
    
    
  //----------------------------KĄT MINUTY----------------------------------//
    
    
    public void katminuty(double minuty)
    {
    	katminuty=(double) minuty*(0.01666666667)*(6.285)+4.71;
    	
    }
    
    
  //----------------------------KĄT SEKUNDY----------------------------------//
    
    
    public void katsekunda(double sekundy)
    {
 
    	katsekunda=(double)sekundy*(6.285/60)+4.71;
    }

    
    //-------------------GODZINY NAPISY NA TARCZY---------------------------//
    
    
    public void napisy(Graphics g)
	{
    	
    	
    	Graphics2D g2d=(Graphics2D) g;
    	
	
		g2d.setColor(bialy);
		g2d.setFont(new Font("Courier", Font.PLAIN, 28));
		
		g2d.drawString("12", this.getWidth()/2-17, 30);
		g2d.drawString("3", this.getWidth()-25, this.getHeight()/2+10);
		g2d.drawString("6", this.getWidth()/2-7, this.getHeight()-10);
		g2d.drawString("9", 10, this.getHeight()/2+10);
	}
	
	
    
    //-----------------------STRZALKI NA TARCZY(OZNACZAJACE GODZINY INNE NIZ WYZEJ)---------------//--------------TODO!!!!!------------//
	public void szczalki(Graphics g)
	{
		
		

		
	}

	//------------------------------ODŚWIEŻANIE--------------------------//
	
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		
		LocalDateTime data=LocalDateTime.now();
    	double godzina=data.getHour();
    	double minuta=data.getMinute();
    	double sekundy=data.getSecond();
    	
    	double czas=godzina*60+minuta;
		if(czas>720)
    	{
    		czas=czas-720;
    	}
		katgodzina(czas);
    	katminuty(minuta);
    	katsekunda(sekundy);
    	repaint();
		
	}

	//----------------------------ZMIANA WIELKOSCI---------------------------//
	
	
	public  void rep()
	{
		this.setPreferredSize(new Dimension(promien*2,promien*2));
		
		xstart=5+promien;
	    ystart=5+promien;
	    repaint();
	    validate();
	}
	
	
	//--------------------------ZMIANA KOLORU--------------------------------//
	
	
	public void kolory()
	{
		Color swap=bialy;
		bialy=czarny;
		czarny=swap;
		repaint();
		
	}



	
	
	
	
	
    
    
}
