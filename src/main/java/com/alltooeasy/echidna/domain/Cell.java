package com.alltooeasy.echidna.domain;

public class Cell
{
    private Integer value;

    private boolean isLocked;

    public Cell() {}    //No-op.

    public Cell( Integer value )
    {
        this.value = value;
    }

    public Integer getValue()
    {
        return value;
    }

    public void setValue( Integer value )
    {
        if (isLocked)
            throw new IllegalStateException( "Cell is locked" );

        this.value = value;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked( boolean isLocked )
    {
        this.isLocked = isLocked;
    }

}
