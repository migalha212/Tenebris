package com.ldts.t14g01.Tenebris.state;

import com.ldts.t14g01.Tenebris.controller.Controller;
import com.ldts.t14g01.Tenebris.savedata.SaveDataProvider;
import com.ldts.t14g01.Tenebris.view.View;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class StateTest {
    @Test
    void Test1() throws IOException, InterruptedException {
        Object model = new Object();

        View view = Mockito.mock(View.class);
        Controller controller = Mockito.mock(Controller.class);

        State state = new State(model) {
            @Override
            protected View getView() {
                return view;
            }

            @Override
            protected Controller getController() {
                return controller;
            }
        };

        Assertions.assertSame(model, state.getModel());
        Assertions.assertSame(view, state.getView());
        Assertions.assertSame(controller, state.getController());

        StateChanger stateChanger = Mockito.mock(StateChanger.class);
        SaveDataProvider saveDataProvider = Mockito.mock(SaveDataProvider.class);

        Mockito.when(stateChanger.stateChanged()).thenReturn(false);
        state.tick(stateChanger, saveDataProvider);
        Mockito.verify(controller, Mockito.times(1)).tick(stateChanger, saveDataProvider);
        Mockito.verify(view, Mockito.times(1)).draw();

        Mockito.when(stateChanger.stateChanged()).thenReturn(true);
        state.tick(stateChanger, saveDataProvider);
        Mockito.verify(controller, Mockito.times(2)).tick(stateChanger, saveDataProvider);
        Mockito.verify(view, Mockito.times(1)).draw();
    }
}
