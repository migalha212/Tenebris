package com.ldts.t14g01.Tenebris.gui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFrame;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

import static org.mockito.ArgumentMatchers.any;

public class LanternaGUITest {
    private LanternaGUI lanternaGUI;
    private TerminalScreen mockedScreen;

    @BeforeEach
    void setup() throws IOException {
        this.mockedScreen = Mockito.mock(TerminalScreen.class);
        this.lanternaGUI = (LanternaGUI) LanternaGUI.getGUI();
        this.lanternaGUI.recreateScreen();
        this.lanternaGUI.close();
        this.lanternaGUI.setScreen(mockedScreen);
    }

    @Test
    void TestInput1() {
        System.out.println("LANTERNAGUI TEST");
        Set<Action> actions = new TreeSet<>();
        KeyEvent keyEvent = Mockito.mock(KeyEvent.class);
        this.lanternaGUI.keyTyped(keyEvent);

        // Add Actions
        actions.add(Action.LOOK_LEFT);
        Mockito.when(keyEvent.getKeyCode()).thenReturn(37);
        this.lanternaGUI.keyPressed(keyEvent);
        Assertions.assertEquals(actions, this.lanternaGUI.getActiveActions());

        actions.add(Action.LOOK_UP);
        Mockito.when(keyEvent.getKeyCode()).thenReturn(38);
        this.lanternaGUI.keyPressed(keyEvent);
        Assertions.assertEquals(actions, this.lanternaGUI.getActiveActions());

        actions.add(Action.LOOK_RIGHT);
        Mockito.when(keyEvent.getKeyCode()).thenReturn(39);
        this.lanternaGUI.keyPressed(keyEvent);
        Assertions.assertEquals(actions, this.lanternaGUI.getActiveActions());

        actions.add(Action.LOOK_DOWN);
        Mockito.when(keyEvent.getKeyCode()).thenReturn(40);
        this.lanternaGUI.keyPressed(keyEvent);
        Assertions.assertEquals(actions, this.lanternaGUI.getActiveActions());

        actions.add(Action.MOVE_LEFT);
        Mockito.when(keyEvent.getKeyCode()).thenReturn(65);
        this.lanternaGUI.keyPressed(keyEvent);
        Assertions.assertEquals(actions, this.lanternaGUI.getActiveActions());

        actions.add(Action.MOVE_RIGHT);
        Mockito.when(keyEvent.getKeyCode()).thenReturn(68);
        this.lanternaGUI.keyPressed(keyEvent);
        Assertions.assertEquals(actions, this.lanternaGUI.getActiveActions());

        actions.add(Action.MOVE_DOWN);
        Mockito.when(keyEvent.getKeyCode()).thenReturn(83);
        this.lanternaGUI.keyPressed(keyEvent);
        Assertions.assertEquals(actions, this.lanternaGUI.getActiveActions());

        actions.add(Action.MOVE_UP);
        Mockito.when(keyEvent.getKeyCode()).thenReturn(87);
        this.lanternaGUI.keyPressed(keyEvent);
        Assertions.assertEquals(actions, this.lanternaGUI.getActiveActions());


        // Remove Actions
        SwingTerminalFrame mockSTF = Mockito.mock(SwingTerminalFrame.class);
        Mockito.when(this.mockedScreen.getTerminal()).thenReturn(mockSTF);

        actions.remove(Action.LOOK_LEFT);
        Mockito.when(keyEvent.getKeyCode()).thenReturn(37);
        this.lanternaGUI.keyReleased(keyEvent);
        Assertions.assertEquals(actions, this.lanternaGUI.getActiveActions());

        actions.remove(Action.LOOK_UP);
        Mockito.when(keyEvent.getKeyCode()).thenReturn(38);
        this.lanternaGUI.keyReleased(keyEvent);
        Assertions.assertEquals(actions, this.lanternaGUI.getActiveActions());

        actions.remove(Action.LOOK_RIGHT);
        Mockito.when(keyEvent.getKeyCode()).thenReturn(39);
        this.lanternaGUI.keyReleased(keyEvent);
        Assertions.assertEquals(actions, this.lanternaGUI.getActiveActions());

        actions.remove(Action.LOOK_DOWN);
        Mockito.when(keyEvent.getKeyCode()).thenReturn(40);
        this.lanternaGUI.keyReleased(keyEvent);
        Assertions.assertEquals(actions, this.lanternaGUI.getActiveActions());

        actions.remove(Action.MOVE_LEFT);
        Mockito.when(keyEvent.getKeyCode()).thenReturn(65);
        this.lanternaGUI.keyReleased(keyEvent);
        Assertions.assertEquals(actions, this.lanternaGUI.getActiveActions());

        actions.remove(Action.MOVE_RIGHT);
        Mockito.when(keyEvent.getKeyCode()).thenReturn(68);
        this.lanternaGUI.keyReleased(keyEvent);
        Assertions.assertEquals(actions, this.lanternaGUI.getActiveActions());

        actions.remove(Action.MOVE_DOWN);
        Mockito.when(keyEvent.getKeyCode()).thenReturn(83);
        this.lanternaGUI.keyReleased(keyEvent);
        Assertions.assertEquals(actions, this.lanternaGUI.getActiveActions());

        actions.remove(Action.MOVE_UP);
        Mockito.when(keyEvent.getKeyCode()).thenReturn(87);
        this.lanternaGUI.keyReleased(keyEvent);
        Assertions.assertEquals(actions, this.lanternaGUI.getActiveActions());

        // Non Registered Key
        Mockito.when(keyEvent.getKeyCode()).thenReturn(12);
        this.lanternaGUI.keyPressed(keyEvent);
        Assertions.assertEquals(actions, this.lanternaGUI.getActiveActions());

        Mockito.when(keyEvent.getKeyCode()).thenReturn(12);
        this.lanternaGUI.keyReleased(keyEvent);
        Assertions.assertEquals(actions, this.lanternaGUI.getActiveActions());

        // Execute
        actions.add(Action.EXEC);
        Mockito.when(keyEvent.getKeyCode()).thenReturn(10);
        this.lanternaGUI.keyPressed(keyEvent);
        Assertions.assertEquals(actions, this.lanternaGUI.getActiveActions());

        actions.remove(Action.EXEC);
        Mockito.when(keyEvent.getKeyCode()).thenReturn(10);
        this.lanternaGUI.keyReleased(keyEvent);
        Assertions.assertEquals(actions, this.lanternaGUI.getActiveActions());

        actions.add(Action.EXEC);
        Mockito.when(keyEvent.getKeyCode()).thenReturn(32);
        this.lanternaGUI.keyPressed(keyEvent);
        Assertions.assertEquals(actions, this.lanternaGUI.getActiveActions());

        actions.remove(Action.EXEC);
        Mockito.when(keyEvent.getKeyCode()).thenReturn(32);
        this.lanternaGUI.keyReleased(keyEvent);
        Assertions.assertEquals(actions, this.lanternaGUI.getActiveActions());

        actions.add(Action.EXEC);
        Mockito.when(keyEvent.getKeyCode()).thenReturn(69);
        this.lanternaGUI.keyPressed(keyEvent);
        Assertions.assertEquals(actions, this.lanternaGUI.getActiveActions());

        actions.remove(Action.EXEC);
        Mockito.when(keyEvent.getKeyCode()).thenReturn(69);
        this.lanternaGUI.keyReleased(keyEvent);
        Assertions.assertEquals(actions, this.lanternaGUI.getActiveActions());
    }

    @Test
    void TestInput2() throws IOException, InterruptedException {
        // Recreate Screen
        KeyEvent keyEvent;

        SwingTerminalFrame mockSTF = Mockito.mock(SwingTerminalFrame.class);
        Mockito.when(this.mockedScreen.getTerminal()).thenReturn(mockSTF);

        Mockito.doAnswer(invocationOnMock -> {
            Mockito.when(this.mockedScreen.pollInput()).thenReturn(invocationOnMock.getArgument(0), (KeyStroke) null);
            return null;
        }).when(mockSTF).addInput(any(KeyStroke.class));

        keyEvent = Mockito.mock(KeyEvent.class);
        Mockito.when(keyEvent.getKeyCode()).thenReturn(37);
        this.lanternaGUI.keyReleased(keyEvent);
        Assertions.assertEquals(Action.LOOK_LEFT, this.lanternaGUI.getAction());

        keyEvent = Mockito.mock(KeyEvent.class);
        Mockito.when(keyEvent.getKeyCode()).thenReturn(38);
        this.lanternaGUI.keyReleased(keyEvent);
        Assertions.assertEquals(Action.LOOK_UP, this.lanternaGUI.getAction());

        keyEvent = Mockito.mock(KeyEvent.class);
        Mockito.when(keyEvent.getKeyCode()).thenReturn(39);
        this.lanternaGUI.keyReleased(keyEvent);
        Assertions.assertEquals(Action.LOOK_RIGHT, this.lanternaGUI.getAction());

        keyEvent = Mockito.mock(KeyEvent.class);
        Mockito.when(keyEvent.getKeyCode()).thenReturn(40);
        this.lanternaGUI.keyReleased(keyEvent);
        Assertions.assertEquals(Action.LOOK_DOWN, this.lanternaGUI.getAction());

        keyEvent = Mockito.mock(KeyEvent.class);
        Mockito.when(keyEvent.getKeyCode()).thenReturn(27);
        this.lanternaGUI.keyReleased(keyEvent);
        Assertions.assertEquals(Action.ESC, this.lanternaGUI.getAction());

        keyEvent = Mockito.mock(KeyEvent.class);
        Mockito.when(keyEvent.getKeyCode()).thenReturn(10);
        this.lanternaGUI.keyReleased(keyEvent);
        Assertions.assertEquals(Action.EXEC, this.lanternaGUI.getAction());

        keyEvent = Mockito.mock(KeyEvent.class);
        Mockito.when(keyEvent.getKeyCode()).thenReturn(32);
        this.lanternaGUI.keyReleased(keyEvent);
        Assertions.assertEquals(Action.EXEC, this.lanternaGUI.getAction());

        keyEvent = Mockito.mock(KeyEvent.class);
        Mockito.when(keyEvent.getKeyCode()).thenReturn(69);
        this.lanternaGUI.keyReleased(keyEvent);
        Assertions.assertEquals(Action.EXEC, this.lanternaGUI.getAction());

        keyEvent = Mockito.mock(KeyEvent.class);
        Mockito.when(keyEvent.getKeyCode()).thenReturn(81);
        this.lanternaGUI.keyReleased(keyEvent);
        Assertions.assertEquals(Action.QUIT, this.lanternaGUI.getAction());

        keyEvent = Mockito.mock(KeyEvent.class);
        Mockito.when(keyEvent.getKeyCode()).thenReturn(87);
        this.lanternaGUI.keyReleased(keyEvent);
        Assertions.assertEquals(Action.MOVE_UP, this.lanternaGUI.getAction());

        keyEvent = Mockito.mock(KeyEvent.class);
        Mockito.when(keyEvent.getKeyCode()).thenReturn(83);
        this.lanternaGUI.keyReleased(keyEvent);
        Assertions.assertEquals(Action.MOVE_DOWN, this.lanternaGUI.getAction());

        keyEvent = Mockito.mock(KeyEvent.class);
        Mockito.when(keyEvent.getKeyCode()).thenReturn(65);
        this.lanternaGUI.keyReleased(keyEvent);
        Assertions.assertEquals(Action.MOVE_LEFT, this.lanternaGUI.getAction());

        keyEvent = Mockito.mock(KeyEvent.class);
        Mockito.when(keyEvent.getKeyCode()).thenReturn(68);
        this.lanternaGUI.keyReleased(keyEvent);
        Assertions.assertEquals(Action.MOVE_RIGHT, this.lanternaGUI.getAction());

        keyEvent = Mockito.mock(KeyEvent.class);
        Mockito.when(keyEvent.getKeyCode()).thenReturn(49);
        this.lanternaGUI.keyReleased(keyEvent);
        Assertions.assertEquals(Action.SELECT_1, this.lanternaGUI.getAction());

        keyEvent = Mockito.mock(KeyEvent.class);
        Mockito.when(keyEvent.getKeyCode()).thenReturn(50);
        this.lanternaGUI.keyReleased(keyEvent);
        Assertions.assertEquals(Action.SELECT_2, this.lanternaGUI.getAction());

        keyEvent = Mockito.mock(KeyEvent.class);
        Mockito.when(keyEvent.getKeyCode()).thenReturn(82);
        this.lanternaGUI.keyReleased(keyEvent);
        Assertions.assertEquals(Action.RELOAD, this.lanternaGUI.getAction());

        // Not registered input
        keyEvent = Mockito.mock(KeyEvent.class);
        Mockito.when(keyEvent.getKeyCode()).thenReturn(85);
        this.lanternaGUI.keyReleased(keyEvent);
        Assertions.assertNull(this.lanternaGUI.getAction());

        // No input
        keyEvent = Mockito.mock(KeyEvent.class);
        Assertions.assertNull(this.lanternaGUI.getAction());
    }

    @Test
    void TestInput3() throws IOException, InterruptedException {
        // CLOSE TEST

        // To cover the focus request
        SwingTerminalFrame mockSTF = Mockito.mock(SwingTerminalFrame.class);
        Mockito.when(this.mockedScreen.getTerminal()).thenReturn(mockSTF);

        // Create EOF KeyStroke
        KeyStroke mockedKeyStroke = Mockito.mock(KeyStroke.class);
        Mockito.when(mockedKeyStroke.getKeyType()).thenReturn(KeyType.EOF);
        Mockito.when(this.mockedScreen.pollInput()).thenReturn(mockedKeyStroke, mockedKeyStroke);

        Assertions.assertNull(this.lanternaGUI.getAction());
        Thread.sleep(300);
        Assertions.assertEquals(Action.QUIT, this.lanternaGUI.getAction());
        Mockito.verify(this.mockedScreen, Mockito.times(2)).pollInput();
    }

    @Test
    void TestInput4() throws IOException, InterruptedException {
        // CLOSE Test however not fully

        // To cover the focus request
        SwingTerminalFrame mockSTF = Mockito.mock(SwingTerminalFrame.class);
        Mockito.when(this.mockedScreen.getTerminal()).thenReturn(mockSTF);

        // Create EOF KeyStroke
        KeyStroke mockedKeyStroke = Mockito.mock(KeyStroke.class);
        Mockito.when(mockedKeyStroke.getKeyType()).thenReturn(KeyType.EOF);
        Mockito.when(this.mockedScreen.pollInput()).thenReturn(mockedKeyStroke, (KeyStroke) null);

        Assertions.assertNull(this.lanternaGUI.getAction());
        Thread.sleep(300);
        Assertions.assertNull(this.lanternaGUI.getAction());
        Mockito.verify(this.mockedScreen, Mockito.times(3)).pollInput();
    }

    @Test
    void TestInput5() throws IOException, InterruptedException {
        // CLOSE Test however not fully

        // To cover the focus request
        SwingTerminalFrame mockSTF = Mockito.mock(SwingTerminalFrame.class);
        Mockito.when(this.mockedScreen.getTerminal()).thenReturn(mockSTF);

        // Create EOF KeyStroke
        KeyStroke mockedKeyStroke = Mockito.mock(KeyStroke.class);
        Mockito.when(mockedKeyStroke.getKeyType()).thenReturn(KeyType.EOF, KeyType.Enter);
        Mockito.when(this.mockedScreen.pollInput()).thenReturn(mockedKeyStroke);

        Assertions.assertNull(this.lanternaGUI.getAction());
        Thread.sleep(300);
        Assertions.assertEquals(Action.EXEC, this.lanternaGUI.getAction());
        Mockito.verify(this.mockedScreen, Mockito.times(3)).pollInput();
    }

    @Test
    void TestOnResized() throws IOException, InterruptedException {
        this.lanternaGUI.recreateScreen();
        Screen oldScreen = this.lanternaGUI.getScreen();
        this.lanternaGUI.onResized(null, new TerminalSize(10, 10));
        Thread.sleep(500);
        Assertions.assertNotSame(oldScreen, this.lanternaGUI.getScreen());
    }

    @Test
    void TestGetWindowSize() throws IOException {
        TerminalSize mockedTerminalSize = Mockito.mock(TerminalSize.class);
        Mockito.when(mockedTerminalSize.getColumns()).thenReturn(100);
        Mockito.when(mockedTerminalSize.getRows()).thenReturn(100);

        Mockito.when(this.mockedScreen.getTerminalSize()).thenReturn(mockedTerminalSize);
        Assertions.assertEquals(new Vector2D(100, 100), this.lanternaGUI.getWindowSize());

        this.lanternaGUI.setScreen(null);
        Assertions.assertEquals(new Vector2D(0,0), this.lanternaGUI.getWindowSize());
    }
}
