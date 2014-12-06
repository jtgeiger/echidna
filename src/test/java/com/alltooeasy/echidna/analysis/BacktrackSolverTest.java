package com.alltooeasy.echidna.analysis;

import static org.junit.Assert.assertTrue;

import java.io.InputStreamReader;

import org.junit.Test;

import com.alltooeasy.echidna.domain.Grid;
import com.alltooeasy.echidna.engine.ControllerI;
import com.alltooeasy.echidna.io.GridLoader;
import com.alltooeasy.echidna.ui.Tester;

public class BacktrackSolverTest
{

    @Test
    public void testSolve() {

        ControllerI controller = new ControllerI() {

            @Override
            public void draw( Grid grid )
            {
                //No-op.
            }

        };

        BacktrackSolver solver = new BacktrackSolver( controller );

        Grid g;

        g = GridLoader.load( new InputStreamReader( Tester.getClasspathResource( "/test2.txt" )) );
        assertTrue( solver.solve( g ));

        g = GridLoader.load( new InputStreamReader( Tester.getClasspathResource( "/test3.txt" )) );
        assertTrue( solver.solve( g ));

        g = GridLoader.load( new InputStreamReader( Tester.getClasspathResource( "/test4.txt" )) );
        assertTrue( solver.solve( g ));

        g = GridLoader.load( new InputStreamReader( Tester.getClasspathResource( "/test5.txt" )) );
        assertTrue( solver.solve( g ));

    }

}
