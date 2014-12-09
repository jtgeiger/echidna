package com.alltooeasy.echidna.analysis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alltooeasy.echidna.domain.Cell;
import com.alltooeasy.echidna.domain.Grid;
import com.alltooeasy.echidna.engine.ControllerI;

public class BacktrackSolver2 implements SolverI
{

    private final static Logger log = LoggerFactory.getLogger( BacktrackSolver2.class );

    private final ControllerI controller;

    private boolean isBacktrackComplete;

    public BacktrackSolver2(ControllerI controller) {
        this.controller = controller;
    }

    @Override
    public boolean solve( Grid grid )
    {
        backtrack(new ArrayList<Point>(), 0, grid);

        return Analyzer.isGridComplete( grid );
    }

    private void backtrack(List<Point> moves, int k, Grid grid) {

        if ( Analyzer.isGridComplete( grid )) {
            log.info( "solution:\n{}", grid );
            isBacktrackComplete = true;
        } else {

            k++;

            Collection<Point> candidates = generateCandidates(moves, k, grid);

            log.info( "k={}: {}", k, candidates);

            for ( Point candidate : candidates ) {

                moves.add(candidate);

                Cell cell = grid.getCell( candidate.row, candidate.col );
                if ( ! cell.isLocked() )
                    cell.setValue( candidate.val );

                controller.draw( grid );

                backtrack(moves, k, grid);

                if (isBacktrackComplete)
                    return;

                moves.remove( moves.size() - 1 );

                if ( ! cell.isLocked() )
                    cell.setValue( null );

                controller.draw( grid );
            }
        }
    }

    private Collection<Point> generateCandidates( List<Point> moves, int k, Grid grid )
    {
        int sideLen = grid.getSideLen();

        //index: number of candidates for each point, value: each point (possibly repeated) with
        //its unique candidate val
        List<List<Point>> points = new ArrayList<List<Point>>(sideLen);
        for (int i = 0; i < sideLen; i++) {
            points.add(new ArrayList<Point>());
        }

        for (int row = 0; row < sideLen; row++) {
            for (int col = 0; col < sideLen; col++) {
                Integer value = grid.getCell( row, col ).getValue();
                if ( value == null ) {
                    List<Integer> candidates = getCandidates( row, col, grid );
                    if (candidates.size() == 0) {
                        return Collections.emptyList();
                    }

                    int count = candidates.size();
                    for (int i = 0; i < count; i++) {
                        Point point = new Point(row, col, candidates.get( i ));
                        List<Point> list = points.get( count );

                        list.add( point );
                    }
                }
            }
        }

        // sort the cells by fewest number of candidates
        List<Point> candidates = new ArrayList<Point>();
        for(int i = 0; i < points.size() && candidates.size() == 0; i++) {
            List<Point> list = points.get( i );
            for (int j = 0; j < list.size(); j++) {
                candidates.add( list.get( j ) );
            }
        }

        return candidates;
    }


    private List<Integer> getCandidates( int row, int col, Grid grid )
    {
        boolean[] candidates = new boolean[grid.getSideLen()];
        Arrays.fill( candidates, true );

        Cell[] r = grid.getRow( row );
        Cell[] c = grid.getCol( col );

        setCandidates(r, candidates);
        setCandidates(c, candidates);

        Grid cluster = grid.getCluster( row, col );
        for (int i = 0; i < cluster.getSideLen(); i++) {
            r = cluster.getRow( i );
            c = cluster.getCol( i );

            setCandidates(r, candidates);
            setCandidates(c, candidates);
        }

        List<Integer> vals = new ArrayList<Integer>(candidates.length);
        for (int i = 0; i < candidates.length; i++) {
            if (candidates[i]) {
                vals.add( i + 1 );
            }
        }

        return vals;
    }

    private void setCandidates( Cell[] run, boolean[] candidates ) {
        for (int i = 0; i < run.length; i++) {
            Integer value = run[i].getValue();
            if ( value != null ) {
                int val = value;
                candidates[val - 1] = false;
            }
        }
    }

    private static class Point {
        private final int row;
        private final int col;
        private final int val;

        public Point(int row, int col, int val) {
            this.row = row;
            this.col = col;
            this.val = val;
        }

        @Override
        public String toString() {
            return "(" + row + ", " + col + "): " + val;
        }
    }

}
