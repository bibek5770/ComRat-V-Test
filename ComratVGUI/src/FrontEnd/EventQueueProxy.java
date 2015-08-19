package FrontEnd;

import java.awt.AWTEvent;
import java.awt.EventQueue;

import javax.swing.JOptionPane;

public class EventQueueProxy extends EventQueue {

    protected void dispatchEvent(AWTEvent newEvent) {
        try {
            super.dispatchEvent(newEvent);
        } catch (Throwable t) {
        	//System.out.println("sdfasdf");
        	JOptionPane.showMessageDialog(null, t.toString());   
        	t.printStackTrace();
        }
    }
}