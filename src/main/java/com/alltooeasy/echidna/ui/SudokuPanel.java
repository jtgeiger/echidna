package com.alltooeasy.echidna.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.alltooeasy.echidna.domain.Cell;
import com.alltooeasy.echidna.domain.Grid;

public class SudokuPanel extends JPanel
{

    private static final long serialVersionUID = 1L;

    private Grid grid;

    public SudokuPanel() {} //No-op.

    public void draw( Grid grid ) {
        this.grid = grid;
        repaint();
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension( 450, 450 );
    }

    @Override
    protected void paintComponent( Graphics g )
    {
        super.paintComponent( g );

        if ( grid == null ) {
            return;
        }

        int sideLen = grid.getSideLen();

        for ( int i = 0; i < sideLen; i++ )
        {
            int y = i * 20 + 20;

            for ( int j = 0; j < sideLen; j++ )
            {
                int x = j * 20 + 20;

                Cell cell = grid.getCell( i, j );
                Integer value = cell.getValue();
                String s = "-";
                Color fore = g.getColor();
                if ( value != null )
                {
                    Color back = getBackground();

                    g.setColor( back );
                    g.drawString( s, x, y );    //Erase the '-'

                    g.setColor( fore );

                    s = value.toString();
                }

                Font font = g.getFont();

                if (cell.isLocked()) {
                    Font bold = new Font( font.getName(), font.getStyle() | Font.BOLD, font.getSize() );
                    g.setFont( bold );
                    g.setColor( Color.RED );
                }

                g.drawString( s, x, y );

                // Set back to normal for next cell.
                if (cell.isLocked()) {
                    g.setColor( fore );
                    g.setFont( font );
                }
            }
        }
    }


}
