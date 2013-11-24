package com.alltooeasy.echidna.domain;

public class Grid
{

    private Cell[][] cells;

    final static public int STANDARD_SIZE = 9;

    public Grid()
    {
        this( STANDARD_SIZE );
    }

    public Grid( int sideLen )
    {
        cells = new Cell[sideLen][sideLen];

        for ( int i = 0; i < sideLen; i++ )
        {
            for ( int j = 0; j < sideLen; j++ )
            {
                cells[i][j] = new Cell();
            }
        }
    }

    private Grid( Cell[][] cells )
    {
        this.cells = cells;
    }

    public Cell getCell( int row, int col )
    {
        return cells[row][col];
    }

    public Grid getCluster( int row, int col )
    {
        int clusterSideLen = (int)Math.sqrt( getSideLen() );

        int rO = row / clusterSideLen;
        int cO = col / clusterSideLen;

        Cell[][] cluster = new Cell[clusterSideLen][clusterSideLen];

        for ( int i = 0; i < clusterSideLen; i++ )
        {
            for ( int j = 0; j < clusterSideLen; j++ )
            {
                cluster[i][j] = cells[rO * clusterSideLen + i][cO * clusterSideLen + j];
            }
        }

        return new Grid( cluster );
    }

    public Cell[] getCol( int col )
    {
        Cell[] c = new Cell[getSideLen()];
        for ( int i = 0; i < c.length; i++ )
        {
            c[i] = cells[i][col];
        }

        return c;
    }

    public Cell[] getRow( int row )
    {
        Cell[] r = new Cell[getSideLen()];
        for ( int i = 0; i < r.length; i++ )
        {
            r[i] = cells[row][i];
        }

        return r;
    }

    public int getSideLen()
    {
        return cells[0].length;
    }

    @Override
    public String toString()
    {
        StringBuilder buf = new StringBuilder();

        //int clusterSideLen = (int)Math.sqrt( getSideLen() );

        for ( int i = 0; i < getSideLen(); i++ )
        {
            for ( int j = 0; j < getSideLen(); j++ )
            {
                if ( j > 0 )
                    buf.append( ' ' );

//                if ( j % clusterSideLen == 0 )
//                {
//                    buf.append( '|' );
//                }

                Cell cell = getCell( i, j );
                Integer value = cell.getValue();
                if ( value == null )
                {
                    buf.append( " -" );
                }
                else
                {
                    if ( value < 10 )
                    {
                        buf.append( ' ' );
                    }

                    buf.append( value );
                }
            }

            buf.append( '\n' );
        }

        return buf.toString();
    }

}
