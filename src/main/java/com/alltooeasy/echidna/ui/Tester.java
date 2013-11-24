package com.alltooeasy.echidna.ui;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alltooeasy.echidna.analysis.Analyzer;
import com.alltooeasy.echidna.domain.Grid;
import com.alltooeasy.echidna.io.GridLoader;

public class Tester
{
    final static private Logger log = LoggerFactory.getLogger( Tester.class );

    static public void main( String[] args )
    {
        long startMs = System.currentTimeMillis();

        log.info( "main() started." );

        //Grid g = new Grid();
        Grid g = GridLoader.load( getClasspathResource( "/test3.txt" ) );
        SudokuPanel panel = new SudokuPanel( g );
        GuiBuilder guiBuilder = new GuiBuilder( panel );
        guiBuilder.show();

        Analyzer.analyze( g, panel );

        long endMs = System.currentTimeMillis();

        log.info( "main() finished; duration={} ms.", endMs - startMs );
    }

    static private InputStream getClasspathResource( String name )
    {
        InputStream ins = Tester.class.getResourceAsStream( name );

        if ( ins == null )
            throw new RuntimeException( "Couldn't find resource=" + name );

        return ins;
    }

}