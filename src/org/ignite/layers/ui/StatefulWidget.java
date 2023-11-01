package org.ignite.layers.ui;

public abstract class StatefulWidget extends Widget {

    protected State<? extends Widget> state;
    abstract State<? extends Widget> createState();

    protected void setState(Runnable fn){
        fn.run();
        createState();
    }
}
