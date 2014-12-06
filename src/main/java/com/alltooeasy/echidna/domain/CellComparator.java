package com.alltooeasy.echidna.domain;

import java.util.Comparator;

public class CellComparator implements Comparator<Cell>
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
