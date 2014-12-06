package com.alltooeasy.echidna.analysis;

import static org.junit.Assert.assertTrue;

import java.io.InputStreamReader;
import java.io.Reader;

import org.junit.Test;

import com.alltooeasy.echidna.domain.Grid;
import com.alltooeasy.echidna.engine.ControllerI;
import com.alltooeasy.echidna.io.GridLoader;
import com.alltooeasy.echidna.ui.Tester;

public class BacktrackSolverTest
{

    private void solve( String resourceName ) {

        solve(new InputStreamReader( Tester.getClasspathResource( resourceName )));
    }

    private void solve( Reader reader ) {

        ControllerI controller = new ControllerI() {

            @Override
            public void draw( Grid grid )
            {
                //No-op.
            }

        };

        BacktrackSolver solver = new BacktrackSolver( controller );

        Grid g;

        g = GridLoader.load( reader );
        assertTrue( solver.solve( g ));

    }

    @Test
    public void testSolve01() {
        solve( "/test2.txt" );
    }

    @Test
    public void testSolve02() {
        solve( "/test3.txt" );
    }

    @Test
    public void testSolve03() {
        solve( "/test4.txt" );
    }

    @Test
    public void testSolve04() {
        solve( "/test5.txt" );
    }

    @Test
    public void testSolve05() {
        solve( "/test6.txt" );
    }

    @Test
    public void testSolve06() {
        solve( "/test7.txt" );
    }

}
