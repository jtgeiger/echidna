package com.alltooeasy.echidna.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.alltooeasy.echidna.domain.Grid;

public class GridLoader
{

    static public Grid load( InputStream ins )
    {
        Grid g = null;

        try ( BufferedReader r = new BufferedReader( new InputStreamReader( ins ) ) )
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
                        g.getCell( i, j ).setValue( value );
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
