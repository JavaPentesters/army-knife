package com.geekmake.plugin.utils;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author pez1420@gmail.com
 * @version $Id: ClipboardUtils.java v 0.1 2020/3/26 7:16 下午 pez1420 Exp $$
 */
public final class ClipboardUtils {

    /** 日志记录器 */
    private static final Logger logger = LoggerFactory.getLogger(ClipboardUtils.class);

    public static String getClipboardContent() {
        try {
            return (String) Toolkit.getDefaultToolkit().getSystemClipboard()
                .getData(DataFlavor.stringFlavor);
        } catch (IllegalStateException e) {
            e.printStackTrace();
            logger.error("Caught and recovered from IllegalStateException: " + e.getMessage());
        } catch (HeadlessException | IOException | UnsupportedFlavorException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * set the clipboard contents
     *
     * @param content String 待填充的内容
     */
    public static void setClipboardContent(String content) {
        try {
            Toolkit.getDefaultToolkit().getSystemClipboard()
                .setContents(new StringSelection(content), null);
        } catch (HeadlessException e) {
            logger.error("Set Clipboard Content: " + e.getMessage());
        }
    }

}
