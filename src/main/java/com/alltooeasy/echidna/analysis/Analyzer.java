package com.alltooeasy.echidna.analysis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alltooeasy.echidna.domain.Cell;
import com.alltooeasy.echidna.domain.Grid;
import com.alltooeasy.echidna.engine.Controller;

public class Analyzer
{
    private static final class CellComparator implements Comparator<Cell>
    {
        @Override
        public int compare( Cell o1, Cell o2 )
        {
            if (o1.getValue() == null && o2.getValue() == null)
                return 0;

            if (o1.getValue() == null)
                return -1;

            if (o2.getValue() == null)
                return 1;

            return new Integer(o1.getValue()).compareTo( o2.getValue() );
        }
    }

    final static private Logger log = LoggerFactory.getLogger( Analyzer.class );

    public static void analyze( Grid g, Controller controller )
    {
        Analyzer a = new Analyzer();
        a.doAnalysis( g, controller );
    }

    private void doAnalysis( Grid g, Controller controller )
    {

        //loop through each cell
        //get the row, col, and cluster
        //make a list of candidates by removing any values already set
        //if only one candidate left then we got it
        //TODO:
        //keep looping until solved or until no changes to grid or candidate list (algorithm fail)


        int sideLen = g.getSideLen();

        boolean runLoop = true;

        for ( int loopCount = 0; runLoop; loopCount++ )
        {
            boolean isComplete = true;
            boolean foundAValue = false;

            for ( int i = 0; i < sideLen; i++ )
            {
                for ( int j = 0; j < sideLen; j++ )
                {
                    Cell cell = g.getCell( i, j );
                    Integer value = cell.getValue();
                    if ( value == null )
                    {
                        isComplete = false;

                        Cell[] row = g.getRow( i );
                        Cell[] col = g.getCol( j );
                        Grid cluster = g.getCluster( i, j );
                        boolean[] candidates = new boolean[sideLen];
                        for ( int k = 0; k < candidates.length; k++ )
                        {
                            candidates[k] = true;
                        }

                        for ( int k = 0; k < row.length; k++ )
                        {
                            Integer v = row[k].getValue();

                            if ( v != null )
                                candidates[v - 1] = false;
                        }


                        for ( int k = 0; k < col.length; k++ )
                        {
                            Integer v = col[k].getValue();

                            if ( v != null )
                                candidates[v - 1] = false;
                        }

                        int clusterSideLen = cluster.getSideLen();
                        for ( int k = 0; k < clusterSideLen; k++ )
                        {
                            for ( int l = 0; l < clusterSideLen; l++ )
                            {
                                Integer v = cluster.getCell( k, l ).getValue();

                                if ( v != null )
                                    candidates[v - 1] = false;
                            }
                        }

                        List<Integer> validCandidates = new ArrayList<Integer>( candidates.length );

                        for ( int k = 0; validCandidates.size() <= 1 && k < candidates.length; k++ )
                        {
                            if ( candidates[k] )
                                validCandidates.add( k );
                        }

                        if ( validCandidates.size() == 1 )
                        {
                            int v = validCandidates.get( 0 ) + 1;
                            log.info( "Found value for ({}, {})={}.", i+1, j+1, v );
                            cell.setValue( v );
                            foundAValue = true;
//                            try
//                            {
//                                Thread.sleep( 300 );
//                            }
//                            catch ( InterruptedException e )
//                            {
//                                //No-op.
//                            }
                            controller.draw( g );
                        }
                    }
                }
            }

            log.info( "Loop {}: isComplete={}, foundAValue={}.", loopCount + 1, isComplete, foundAValue );

            if ( isComplete )
                runLoop = false;
            else
            {
                runLoop = foundAValue;
            }
        }

        controller.draw( g );
    }

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
