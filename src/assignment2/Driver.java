package assignment2;

import java.awt.EventQueue;

import javax.swing.JFrame;

// Driver class that runs the entire program 

public class Driver extends JFrame {


	private static final long serialVersionUID = 1L;

// Launching the program 
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    @SuppressWarnings("unused")
					AdminControlPanel frame = AdminControlPanel.getInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}