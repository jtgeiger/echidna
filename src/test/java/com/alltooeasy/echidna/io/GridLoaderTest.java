package com.alltooeasy.echidna.io;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Test;

import com.alltooeasy.echidna.domain.Grid;

public class GridLoaderTest
{

    @Test
    public void testLoad()
    {
        Grid g = GridLoader.load( new InputStreamReader( getClasspathResource( "/gridPlain.txt" ) ) );

        assertEquals( 9, g.getSideLen() );
    }

    private InputStream getClasspathResource( String name )
    {
        InputStream ins = getClass().getResourceAsStream( name );

        if ( ins == null )
            throw new RuntimeException( "Couldn't find resource=" + name );

        return ins;
    }

}
