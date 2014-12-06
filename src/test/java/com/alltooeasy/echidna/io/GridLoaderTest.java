package com.alltooeasy.echidna.io;

import static org.junit.Assert.assertEquals;

import java.io.InputStreamReader;

import org.junit.Test;

import com.alltooeasy.echidna.domain.Grid;
import com.alltooeasy.echidna.ui.Tester;

public class GridLoaderTest
{

    @Test
    public void testLoad()
    {
        Grid g = GridLoader.load( new InputStreamReader( Tester.getClasspathResource( "/gridPlain.txt" ) ) );

        assertEquals( 9, g.getSideLen() );
    }

}
