package com.alltooeasy.echidna.engine;

import com.alltooeasy.echidna.domain.Grid;
import com.alltooeasy.echidna.ui.SudokuPanel;

public class Controller implements ControllerI
{

    private final SudokuPanel panel;

    public Controller( SudokuPanel panel ) {
        this.panel = panel;
    }

    @Override
    public void draw( Grid grid ) {

        panel.draw( grid );
    }

}
