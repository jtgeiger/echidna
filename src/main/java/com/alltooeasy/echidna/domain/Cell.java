package com.alltooeasy.echidna.domain;

public class Cell
{
    private Integer value;

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
        this.value = value;
    }

}
