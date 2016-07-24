package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.getExtendedKeyCodeForChar;

public class KeyPresser {

    private static final int DELAY = 10;
    private static final Logger LOG = LoggerFactory.getLogger(KeyPresser.class);
    private static Robot rbt;

    static {
        try {
            rbt = new Robot();
            rbt.setAutoDelay(DELAY);
        } catch (AWTException e) {
            LOG.error("AWTException={}", e);
        }
    }

    private KeyPresser() {
    }

    public static void press(final String str) {
        char[] chars = str.toCharArray();
        for (char c : chars) {
            // TODO Add more special char if needed
            if (c == ':') {
                pressWithShift(KeyEvent.VK_SEMICOLON);
            } else {
                rbt.keyPress(getExtendedKeyCodeForChar(c));
                rbt.keyRelease(getExtendedKeyCodeForChar(c));
            }
        }
        LOG.info("Press '{}'", str);
    }

    public static void pressEnter() {
        rbt.keyPress(KeyEvent.VK_ENTER);
        rbt.keyRelease(KeyEvent.VK_ENTER);
        LOG.info("Press 'Enter'");
    }

    private static void pressWithShift(int keyEvent) {
        rbt.keyPress(KeyEvent.VK_SHIFT);
        rbt.keyPress(keyEvent);
        rbt.keyRelease(keyEvent);
        rbt.keyRelease(KeyEvent.VK_SHIFT);
    }
}
