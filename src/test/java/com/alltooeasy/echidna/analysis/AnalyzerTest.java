package com.alltooeasy.echidna.analysis;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.InputStreamReader;

import org.junit.Test;

import com.alltooeasy.echidna.domain.Grid;
import com.alltooeasy.echidna.engine.ControllerI;
import com.alltooeasy.echidna.io.GridLoader;
import com.alltooeasy.echidna.ui.Tester;

public class AnalyzerTest
{

    @Test
    public void testAnalyze() {

        ControllerI controller = new ControllerI() {

            @Override
            public void draw( Grid grid )
            {
                //No-op.
            }

        };

        Grid g;

        g = GridLoader.load( new InputStreamReader( Tester.getClasspathResource( "/test1.txt" )) );
        assertTrue( Analyzer.analyze( g, controller ));

        g = GridLoader.load( new InputStreamReader( Tester.getClasspathResource( "/test2.txt" )) );
        assertTrue( Analyzer.analyze( g, controller ));

        g = GridLoader.load( new InputStreamReader( Tester.getClasspathResource( "/test3.txt" )) );
        assertTrue( Analyzer.analyze( g, controller ));

        g = GridLoader.load( new InputStreamReader( Tester.getClasspathResource( "/test4.txt" )) );
        assertTrue( Analyzer.analyze( g, controller ));

//        g = GridLoader.load( new InputStreamReader( Tester.getClasspathResource( "/test5.txt" )) );
//        assertTrue( Analyzer.analyze( g, controller ));

    }

    @Test
    public void testIsGridComplete()
    {
        Grid g;

        g = GridLoader.load( new InputStreamReader( Tester.getClasspathResource( "/test3.txt" )) );
        assertFalse( Analyzer.isGridComplete( g ));

        g = GridLoader.load( new InputStreamReader( Tester.getClasspathResource( "/test3-solution.txt" )) );
        assertTrue( Analyzer.isGridComplete( g ));

        g = GridLoader.load( new InputStreamReader( Tester.getClasspathResource( "/test5-solution.txt" )) );
        assertTrue( Analyzer.isGridComplete( g ));
    }

}
