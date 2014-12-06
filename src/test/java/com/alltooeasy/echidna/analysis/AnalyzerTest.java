package com.alltooeasy.echidna.analysis;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.InputStreamReader;

import org.junit.Test;

import com.alltooeasy.echidna.domain.Grid;
import com.alltooeasy.echidna.io.GridLoader;
import com.alltooeasy.echidna.ui.Tester;

public class AnalyzerTest
{

    @Test
    public void testIsGridComplete()
    {
        Grid g = GridLoader.load( new InputStreamReader( Tester.getClasspathResource( "/test3.txt" )) );
        assertFalse( Analyzer.isGridComplete( g ));

        g = GridLoader.load( new InputStreamReader( Tester.getClasspathResource( "/test5-solution.txt" )) );
        assertTrue( Analyzer.isGridComplete( g ));
    }

}
