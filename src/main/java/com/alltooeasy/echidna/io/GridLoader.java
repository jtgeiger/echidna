package com.alltooeasy.echidna.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import com.alltooeasy.echidna.domain.Cell;
import com.alltooeasy.echidna.domain.Grid;

public class GridLoader
{

    static public Grid load( Reader reader )
    {
        Grid g = null;

        try ( BufferedReader r = new BufferedReader( reader ) )
        {
            String line;

            for ( int i = 0; ( line = r.readLine() ) != null; i++ )
            {
                String[] tokens = line.trim().split( "\\s+" );

                if ( i == 0 )
                {
                    int sideLen = tokens.length;
                    g = new Grid( sideLen );
                }

                for ( int j = 0; j < tokens.length; j++ )
                {
                    String token = tokens[j];
                    try
                    {
                        int value = Integer.parseInt( token );
                        Cell cell = g.getCell( i, j );
                        cell.setValue( value );
                        cell.setLocked(true);
                    }
                    catch ( NumberFormatException e )
                    {
                        //No-op.  It's a non-number so leave the cell's value as null.
                    }
                }
            }
        }
        catch ( IOException e )
        {
            throw new RuntimeException( "Trouble processing input stream:", e );
        }

        return g;
    }

}
