package com.alltooeasy.echidna.ui;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.alltooeasy.echidna.domain.Cell;
import com.alltooeasy.echidna.domain.Grid;

public class SudokuPanel extends JPanel
{

    private static final long serialVersionUID = 1L;

    private Grid grid;

    public SudokuPanel( Grid grid )
    {
        this.grid = grid;
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension( 450, 450 );
    }

    @Override
    protected void paintComponent( Graphics g )
    {
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
                if ( value != null )
                {
                    s = value.toString();
                }

                g.drawString( s, x, y );
            }
        }
    }


}
