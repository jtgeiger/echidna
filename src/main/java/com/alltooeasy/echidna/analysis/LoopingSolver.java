package com.alltooeasy.echidna.analysis;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alltooeasy.echidna.domain.Cell;
import com.alltooeasy.echidna.domain.Grid;
import com.alltooeasy.echidna.engine.ControllerI;

public class LoopingSolver implements SolverI
{
    final static private Logger log = LoggerFactory.getLogger( LoopingSolver.class );

    private final ControllerI controller;

    public LoopingSolver( ControllerI controller) {
        this.controller = controller;
    }

    @Override
    public boolean solve( Grid grid )
    {
        doAnalysis( grid );
        boolean isComplete = Analyzer.isGridComplete( grid );
        return isComplete;
    }

    private void doAnalysis( Grid g )
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

}
