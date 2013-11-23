package com.alltooeasy.echidna.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class GridTest
{

    private Grid g;

    @Before
    public void setUp()
    {
        g = new Grid();
        int sideLen = g.getSideLen();
        for ( int i = 0; i < sideLen; i++ )
        {
            for ( int j = 0; j < sideLen; j++ )
            {
                int value = i * sideLen + j;
                g.getCell( i, j ).setValue( value );
            }
        }

        assertEquals( 9, g.getSideLen() );
    }

    @Test
    public void test()
    {
        assertEquals( 0, (int)g.getCell( 0, 0 ).getValue() );
        assertEquals( 8, (int)g.getCell( 0, 8 ).getValue() );
        assertEquals( 9, (int)g.getCell( 1, 0 ).getValue() );
        assertEquals( 72, (int)g.getCell( 8, 0 ).getValue() );
        assertEquals( 80, (int)g.getCell( 8, 8 ).getValue() );
    }

    @Test
    public void testGetCluster()
    {
        checkC1( g.getCluster( 0, 0 ) );
        checkC1( g.getCluster( 0, 1 ) );
        checkC1( g.getCluster( 0, 2 ) );
        checkC1( g.getCluster( 1, 0 ) );
        checkC1( g.getCluster( 1, 1 ) );
        checkC1( g.getCluster( 1, 2 ) );
        checkC1( g.getCluster( 2, 0 ) );
        checkC1( g.getCluster( 2, 1 ) );
        checkC1( g.getCluster( 2, 2 ) );

        checkC2( g.getCluster( 0, 3 ) );
        checkC2( g.getCluster( 0, 4 ) );
        checkC2( g.getCluster( 0, 5 ) );
        checkC2( g.getCluster( 1, 3 ) );
        checkC2( g.getCluster( 1, 4 ) );
        checkC2( g.getCluster( 1, 5 ) );
        checkC2( g.getCluster( 2, 3 ) );
        checkC2( g.getCluster( 2, 4 ) );
        checkC2( g.getCluster( 2, 5 ) );

        checkC9( g.getCluster( 6, 6 ) );
        checkC9( g.getCluster( 6, 7 ) );
        checkC9( g.getCluster( 6, 8 ) );
        checkC9( g.getCluster( 7, 6 ) );
        checkC9( g.getCluster( 7, 7 ) );
        checkC9( g.getCluster( 7, 8 ) );
        checkC9( g.getCluster( 8, 6 ) );
        checkC9( g.getCluster( 8, 7 ) );
        checkC9( g.getCluster( 8, 8 ) );

    }

    private void checkC1( Grid c1 )
    {
        assertEquals( 3, c1.getSideLen() );
        assertEquals( 0, (int)c1.getCell( 0, 0 ).getValue() );
        assertEquals( 1, (int)c1.getCell( 0, 1 ).getValue() );
        assertEquals( 2, (int)c1.getCell( 0, 2 ).getValue() );
        assertEquals( 9, (int)c1.getCell( 1, 0 ).getValue() );
        assertEquals( 10, (int)c1.getCell( 1, 1 ).getValue() );
        assertEquals( 11, (int)c1.getCell( 1, 2 ).getValue() );
        assertEquals( 18, (int)c1.getCell( 2, 0 ).getValue() );
        assertEquals( 19, (int)c1.getCell( 2, 1 ).getValue() );
        assertEquals( 20, (int)c1.getCell( 2, 2 ).getValue() );
    }

    private void checkC2( Grid c2 )
    {
        assertEquals( 3, c2.getSideLen() );
        assertEquals( 3, (int)c2.getCell( 0, 0 ).getValue() );
        assertEquals( 4, (int)c2.getCell( 0, 1 ).getValue() );
        assertEquals( 5, (int)c2.getCell( 0, 2 ).getValue() );
        assertEquals( 12, (int)c2.getCell( 1, 0 ).getValue() );
        assertEquals( 13, (int)c2.getCell( 1, 1 ).getValue() );
        assertEquals( 14, (int)c2.getCell( 1, 2 ).getValue() );
        assertEquals( 21, (int)c2.getCell( 2, 0 ).getValue() );
        assertEquals( 22, (int)c2.getCell( 2, 1 ).getValue() );
        assertEquals( 23, (int)c2.getCell( 2, 2 ).getValue() );
    }

    private void checkC9( Grid c9 )
    {
        assertEquals( 3, c9.getSideLen() );
        assertEquals( 60, (int)c9.getCell( 0, 0 ).getValue() );
        assertEquals( 61, (int)c9.getCell( 0, 1 ).getValue() );
        assertEquals( 62, (int)c9.getCell( 0, 2 ).getValue() );
        assertEquals( 69, (int)c9.getCell( 1, 0 ).getValue() );
        assertEquals( 70, (int)c9.getCell( 1, 1 ).getValue() );
        assertEquals( 71, (int)c9.getCell( 1, 2 ).getValue() );
        assertEquals( 78, (int)c9.getCell( 2, 0 ).getValue() );
        assertEquals( 79, (int)c9.getCell( 2, 1 ).getValue() );
        assertEquals( 80, (int)c9.getCell( 2, 2 ).getValue() );
    }

}
