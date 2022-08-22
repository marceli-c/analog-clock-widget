package zegar;

//import java.time.temporal.WeekFields;
import java.awt.Color;
//import java.awt.GradientPaint;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.GridBagLayout;
//import java.awt.MouseInfo;
//import java.awt.Paint;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
//import java.time.LocalDateTime;
//import java.time.Month;

import javax.swing.JFrame;
//import javax.swing.JMenu;
//import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
//import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.WindowConstants;

public class okno extends JFrame implements MouseMotionListener,MouseListener,ActionListener

{
	
	//------------------------------DEKLARACJE---------------------------//
	
	
	//LocalDateTime data=LocalDateTime.now();
	//int miesiac=data.getMonthValue();
	//String dzien=String.valueOf(data.getDayOfWeek());
	//String miesiacstring="";
    MyPanel panel;
    Point myszwcis;
    int xPress=0;
    int yPress=0;
    JPopupMenu menu=new JPopupMenu();
	JMenuItem wielkoscplus=new JMenuItem("Zwiększ wielkość");
	JMenuItem wielkosc=new JMenuItem("Zmniejsz wielkość");
	JMenuItem kolory=new JMenuItem("Odwróć kolory");
	JMenuItem wylacz=new JMenuItem("Wyłącz");
	
    
    public okno()
    {
    	
    	
    	
    	//miesiac();
    	//dzien();
    	
    	//---------------------------MENU---------------------//
    	
    	menu.add(wielkosc);
    	menu.add(wielkoscplus);
    	menu.add(kolory);
    	menu.add(wylacz);
    	wylacz.addActionListener(this);
    	wielkosc.addActionListener(this);
    	wielkoscplus.addActionListener(this);
    	kolory.addActionListener(this);
    	
    	//-------------------------JFRAME--------------------//
    	
    	
    	
        panel=new MyPanel();
        this.setSize(410,410);
        this.addMouseMotionListener(this);
        this.setAlwaysOnTop(true);										//ZAWSZE WIDOCZNY
        this.setUndecorated(true);										//POZBAWIA GORNEJ WARSTWY (TYTUL ITD)
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setBackground(new Color(0,0,0,0));							//DODAJE WARSTWE ALFA
        this.addMouseListener(this);
        this.setType(javax.swing.JFrame.Type.UTILITY); 					//NIE WIDOCZNY NA PASKU ZADAN
        this.add(panel);
        this.setVisible(true);
        
        
        //this.pack();
        //this.setTitle("Zegar "+data.getDayOfMonth()+" "+miesiacstring+" "+data.getYear()+" "+dzien);
        //System.out.println(miesiac);
 
        
    }
	
    //---------------------------PRZESUWANIE----------------------------//
    
    
	@Override
	public void mouseDragged(MouseEvent e) 
	{
		drag(e);
		
	}
	@Override
	public void mouseMoved(MouseEvent e) 
	{
			xPress = e.getX();
			yPress = e.getY();
	}

	
	//---------------------POKAZANIE MENU----------------------//
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton()==MouseEvent.BUTTON3)
		{
			popup(e);
		}
		
	}
	
	
	
	//-------------------------------NIC------------------------//
	
	
	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	
	//-----------------------AKCJE MENU---------------------//
	
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		//------------------------------------EXIT--------------------------//
		
		
		if(e.getSource()==wylacz) 
		{
			System.exit(0);
		}
		
		
		//------------------------------------ZMNIEJSZ WIELKOSC--------------------------//
		
		
		if(e.getSource()==wielkosc)
		{
			MyPanel.promien=MyPanel.promien-25;
			int x=this.getWidth()-50;
			int y=this.getHeight()-50;
			this.setSize(x,y);
			panel.rep();
		}
		
		//------------------------------------ZWIEKSZ WIELKOSC--------------------------//
		
		
		if(e.getSource()==wielkoscplus)
		{
			MyPanel.promien=MyPanel.promien+25;
			int x=this.getWidth()+50;
			int y=this.getHeight()+50;
			this.setSize(x,y);
			panel.rep();
		}
		
		//------------------------------------ODWROC KOLORY--------------------------//
		
		if (e.getSource()==kolory)
		{
			panel.kolory();
		}
		
	
	}
	
	public void popup(MouseEvent e)
	{
		menu.show(e.getComponent(),
	    e.getX(), e.getY());
	}
	public void drag(MouseEvent e)
	{
		int xDrag = e.getX();
	    int yDrag = e.getY();

	    JFrame sFrame = (JFrame) e.getSource();
	    sFrame.setLocation(sFrame.getLocation().x+xDrag-xPress, 
	    sFrame.getLocation().y+yDrag-yPress);
	}
	
    
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
    //---------------------BEZ UZYTKU JEZELI UNDECORATED---------------//
    
    /*public void miesiac()
    {
    	switch (miesiac)
    	{
    	case 1: miesiacstring="Styczeń";
    	break;
    	case 2: miesiacstring="Luty";
    	break;
    	case 3: miesiacstring="Marzec";
    	break;
    	case 4: miesiacstring="Kwiecień";
    	break;
    	case 5: miesiacstring="Maj";
    	break;
    	case 6: miesiacstring="Czerwiec";
    	break;
    	case 7: miesiacstring="Lipiec";
    	break;
    	case 8: miesiacstring="Sierpień";
    	break;
    	case 9: miesiacstring="Wrzesień";
    	break;
    	case 10: miesiacstring="Październik";
    	break;
    	case 11: miesiacstring="Listopad";
    	break;
    	case 12: miesiacstring="Grudzień";
    	break;
    	
    	}
    }
    
    public void dzien()
    {
    	switch (dzien)
    	{

    	case "MONDAY": dzien="Poniedziałek";
    	break;
    	case "TUESDAY": dzien="Wtorek";
    	break;
    	case "WEDNESDAY": dzien="Środa";
    	break;
    	case "THURSDAY": dzien="Czwartek";
    	break;
    	case "FRIDAY": dzien="Piątek";
    	break;
    	case "SATURDAY": dzien="Sobota";
    	break;
    	case "SUNDAY": dzien="Niedziela";
    	break;
    	
    	}
    }*/
    

}
