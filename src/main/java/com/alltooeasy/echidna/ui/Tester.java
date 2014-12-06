package com.alltooeasy.echidna.ui;

import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alltooeasy.echidna.analysis.Analyzer;
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

        Analyzer.analyze( g, controller );

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
