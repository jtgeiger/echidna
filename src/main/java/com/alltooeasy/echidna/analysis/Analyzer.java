package com.alltooeasy.echidna.analysis;

import java.util.Arrays;

import com.alltooeasy.echidna.domain.Cell;
import com.alltooeasy.echidna.domain.CellComparator;
import com.alltooeasy.echidna.domain.Grid;

public class Analyzer
{
    //final static private Logger log = LoggerFactory.getLogger( Analyzer.class );

    public static boolean isGridComplete( Grid g ) {
        for (int i = 0; i < g.getSideLen(); i++) {
            if ( ! isRunComplete( g.getRow( i )) ) {
                return false;
            }

            if ( ! isRunComplete( g.getCol( i )) ) {
                return false;
            }

                //TODO: Really only need to check each cluster once but this does every cluster sideLen times.
            for (int col = 0; col < g.getSideLen(); col++) {

                if ( ! isClusterComplete( g.getCluster( i, col )) ) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean isRunComplete( Cell[] run ) // Row or col
    {
        Cell[] copy = Arrays.copyOf( run, run.length );
        Arrays.sort( copy, new CellComparator());

        for (int i = 0; i < copy.length; i++) {
            Cell cell = copy[i];
            if ( cell.getValue() == null || cell.getValue() != i + 1 ) {
                return false;
            }
        }

        return true;
    }

    private static boolean isClusterComplete( Grid g ) {
        int[] a = new int[g.getSideLen() * g.getSideLen()];
        for(int row = 0; row < g.getSideLen(); row++) {
            for (int col = 0; col < g.getSideLen(); col++) {
                Cell c = g.getCell( row, col );
                if ( c.getValue() == null )
                    return false;

                int offset = row * g.getSideLen() + col;
                a[offset] = c.getValue();
            }
        }
        Arrays.sort( a );
        for (int i = 0; i < a.length; i++) {
            if ( a[i] != i + 1 ) {
                return false;
            }
        }

        return true;
    }

}
