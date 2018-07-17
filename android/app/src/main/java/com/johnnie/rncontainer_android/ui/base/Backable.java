package com.johnnie.rncontainer_android.ui.base;

/**
 * Created by Johnnie on 09/05/18.
 */

public interface Backable {

    /**
     * if response to back clicked event
     * @return true if consume back event
     */
    boolean onBackClicked();
}
