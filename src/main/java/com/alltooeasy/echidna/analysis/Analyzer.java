package com.alltooeasy.echidna.analysis;

import java.util.ArrayList;
import java.util.List;

import com.alltooeasy.echidna.domain.Cell;
import com.alltooeasy.echidna.domain.Grid;

public class Analyzer
{

    public static void analyze( Grid g )
    {
        Analyzer a = new Analyzer();
        a.doAnalysis( g );
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

        for ( int i = 0; i < sideLen; i++ )
        {
            for ( int j = 0; j < sideLen; j++ )
            {
                Cell cell = g.getCell( i, j );
                Integer value = cell.getValue();
                if ( value == null )
                {
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
                        cell.setValue( validCandidates.get( 0 ) + 1 );
                    }
                }
            }
        }
    }

}
