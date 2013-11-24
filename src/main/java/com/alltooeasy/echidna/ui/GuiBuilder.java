package com.alltooeasy.echidna.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GuiBuilder
{

    private JPanel panel;

    public GuiBuilder( JPanel panel )
    {
        this.panel = panel;
    }

    private void doGui()
    {
        JFrame frame = new JFrame( "Sudokusan" );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
//        JScrollPane jsp = new JScrollPane( panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
//                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
//        frame.add( jsp );
        frame.add( panel );
        frame.pack();
        frame.setVisible( true );
    }

    public void show()
    {
        SwingUtilities.invokeLater( new Runnable()
            {
                @Override
                public void run()
                {
                    doGui();
                }
            } );
    }

}
