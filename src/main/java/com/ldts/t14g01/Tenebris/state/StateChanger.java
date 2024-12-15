package com.ldts.t14g01.Tenebris.state;

import java.io.IOException;

public interface StateChanger {
    void setState(State state) throws IOException;

    boolean stateChanged();
}
