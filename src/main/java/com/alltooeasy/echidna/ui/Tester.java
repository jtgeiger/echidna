package com.alltooeasy.echidna.ui;

import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alltooeasy.echidna.analysis.BacktrackSolver2;
import com.alltooeasy.echidna.analysis.SolverI;
import com.alltooeasy.echidna.domain.Grid;
import com.alltooeasy.echidna.engine.Controller;
import com.alltooeasy.echidna.io.GridLoader;

public class Tester
{
    final static private Logger log = LoggerFactory.getLogger( Tester.class );

    static public void main( String[] args )
    {
        long startMs = System.currentTimeMillis();

        log.info( "main() started." );

        //Grid g = new Grid();
        Grid g = GridLoader.load( new InputStreamReader( getClasspathResource( "/test5.txt" ) ) );
        SudokuPanel panel = new SudokuPanel();
        GuiBuilder guiBuilder = new GuiBuilder( panel );
        guiBuilder.show();

        Controller controller = new Controller( panel );

//        SolverI solver = new LoopingSolver( controller );
//        SolverI solver = new BacktrackSolver( controller );
        SolverI solver = new BacktrackSolver2( controller );

        boolean isComplete = solver.solve( g );
        System.out.println(isComplete);
        System.out.println(g);

        long endMs = System.currentTimeMillis();

        log.info( "main() finished; duration={} ms.", endMs - startMs );
    }

    static public InputStream getClasspathResource( String name )
    {
        InputStream ins = Tester.class.getResourceAsStream( name );

        if ( ins == null )
            throw new RuntimeException( "Couldn't find resource=" + name );

        return ins;
    }

}
