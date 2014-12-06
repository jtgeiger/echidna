package com.alltooeasy.echidna.analysis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.alltooeasy.echidna.domain.Cell;
import com.alltooeasy.echidna.domain.Grid;
import com.alltooeasy.echidna.engine.ControllerI;

public class BacktrackSolver implements SolverI
{

    private final ControllerI controller;

    private boolean isBacktrackFinished;

    public BacktrackSolver( ControllerI controller) {
        this.controller = controller;
    }

    @Override
    public boolean solve( Grid grid )
    {
        isBacktrackFinished = false;
        backtrack( grid, 0, -1 );
        boolean isComplete = Analyzer.isGridComplete( grid );
//        System.out.println(isComplete);
//        System.out.println(grid);
        return isComplete;
    }


    private void backtrack(Grid g, int row, int col) {

        if (row + 1 == g.getSideLen() && col + 1 == g.getSideLen() ) {
            isBacktrackFinished = true;
            controller.draw( g );
        } else {

            col++;
            if ( col >= g.getSideLen()) {
                col = 0;
                row++;
            }

            List<Integer> candidates = generateCandidates(g, row, col);

            for (int i = 0; i < candidates.size(); i++) {

                Cell cell = g.getCell( row, col );
                boolean isLocked = cell.isLocked();

                if (isLocked) {
                    if (cell.getValue() != candidates.get( i ) ) {
                        throw new IllegalStateException("Expected candidate=" + cell.getValue() +
                                ", actual=" + candidates.get(i));
                    }
                } else {
                    cell.setValue( candidates.get(i) );
                }

                controller.draw( g );

                backtrack(g, row, col);

                if (isBacktrackFinished)
                    return;

                if ( ! isLocked )
                    cell.setValue( null );
            }

        }
    }

    private static List<Integer> generateCandidates( Grid g, int row, int col )
    {
        if ( g.getCell( row, col ).getValue() != null ) {
            return Arrays.asList( g.getCell(row, col).getValue() );
        }

        boolean[] hits = new boolean[g.getSideLen()];

        setHits( g.getRow( row ), hits );
        setHits( g.getCol( col ), hits );

        Grid cluster = g.getCluster( row, col );
        for ( int i = 0; i < cluster.getSideLen(); i++) {
            setHits( cluster.getRow( i ), hits );
            setHits( cluster.getCol( i ), hits );
        }

        List<Integer> nonHits = new ArrayList<Integer>(hits.length);
        for (int i = 0; i < hits.length; i++) {
            if (!hits[i]) {
                nonHits.add(i + 1);
            }
        }

        return nonHits;
    }

    private static void setHits( Cell[] run, boolean[] hits )
    {
        for ( Cell c : run ) {
            if (c.getValue() != null) {
                hits[c.getValue() - 1] = true;
            }
        }
    }

}
