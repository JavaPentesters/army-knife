package com.geekmake.plugin.dialog;

import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import com.geekmake.plugin.utils.ClipboardUtils;
import com.geekmake.plugin.utils.NotificationUtils;
import com.intellij.icons.AllIcons;
import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.WindowManager;
import com.intellij.ui.components.labels.ActionLink;
import com.intellij.ui.components.labels.LinkLabel;

/**
 * @author pez1420@gmail.com
 * @version $Id: MysqlCommandDialog.java v 0.1 2020/3/26 7:02 下午 pez1420 Exp $$
 */
public class MysqlCommandDialog extends JDialog {

    /** 项目信息 */
    private Project    project;

    /** 面板 */
    private JPanel     contentPane;

    private LinkLabel  link0;

    private JTextField textField0;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;

    private JButton    copyButton0;
    private JButton    copyButton1;
    private JButton    copyButton2;
    private JButton    copyButton3;

    private JButton    cancelButton;

    private LinkLabel  helpLink;
    private LinkLabel  link1;
    private LinkLabel  link2;
    private LinkLabel  link3;
    private LinkLabel  cpu100Link;
    private LinkLabel  deadLockLink;
    private LinkLabel  link4;
    private JButton    copyButton4;
    private JTextField textField4;

    public MysqlCommandDialog(Project project) {
        this.project = project;

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(cancelButton);

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(),
            KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
            JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        initComponent();
    }

    private void initComponent() {
        copyButton0.addActionListener(e -> {
            ClipboardUtils.setClipboardContent(textField0.getText());
            NotificationUtils.showMessage();
        });

        copyButton1.addActionListener(e -> {
            ClipboardUtils.setClipboardContent(textField1.getText());
            NotificationUtils.showMessage();
        });

        copyButton2.addActionListener(e -> {
            ClipboardUtils.setClipboardContent(textField2.getText());
            NotificationUtils.showMessage();
        });

        copyButton3.addActionListener(e -> {
            ClipboardUtils.setClipboardContent(textField3.getText());
            NotificationUtils.showMessage();
        });

        copyButton4.addActionListener(e -> {
            ClipboardUtils.setClipboardContent(textField4.getText());
            NotificationUtils.showMessage();
        });

        cancelButton.addActionListener(e -> onCancel());

    }

    private void createUIComponents() {
        helpLink = new ActionLink("", AllIcons.Ide.Link, new AnAction() {
            @Override
            public void actionPerformed(AnActionEvent anActionEvent) {
                BrowserUtil.browse("https://www.geek-make.com");
            }
        });
        helpLink.setPaintUnderline(false);

        link0 = new ActionLink("", AllIcons.Ide.Link, new AnAction() {
            @Override
            public void actionPerformed(AnActionEvent anActionEvent) {
                BrowserUtil.browse("https://www.geek-make.com");
            }
        });
        link0.setPaintUnderline(false);

        link1 = new ActionLink("", AllIcons.Ide.Link, new AnAction() {
            @Override
            public void actionPerformed(AnActionEvent anActionEvent) {
                BrowserUtil.browse("https://www.geek-make.com");
            }
        });
        link1.setPaintUnderline(false);

        link2 = new ActionLink("", AllIcons.Ide.Link, new AnAction() {
            @Override
            public void actionPerformed(AnActionEvent anActionEvent) {
                BrowserUtil.browse("https://www.geek-make.com");
            }
        });
        link2.setPaintUnderline(false);

        link3 = new ActionLink("", AllIcons.Ide.Link, new AnAction() {
            @Override
            public void actionPerformed(AnActionEvent anActionEvent) {
                BrowserUtil.browse("https://www.geek-make.com");
            }
        });
        link3.setPaintUnderline(false);

        link4 = new ActionLink("", AllIcons.Ide.Link, new AnAction() {
            @Override
            public void actionPerformed(AnActionEvent anActionEvent) {
                BrowserUtil.browse("https://www.geek-make.com");
            }
        });
        link4.setPaintUnderline(false);

        cpu100Link = new ActionLink("", AllIcons.Ide.Link, new AnAction() {
            @Override
            public void actionPerformed(AnActionEvent anActionEvent) {
                BrowserUtil.browse("https://www.geek-make.com");
            }
        });
        cpu100Link.setPaintUnderline(false);

        deadLockLink = new ActionLink("", AllIcons.Ide.Link, new AnAction() {
            @Override
            public void actionPerformed(AnActionEvent anActionEvent) {
                BrowserUtil.browse("https://www.geek-make.com");
            }
        });
        deadLockLink.setPaintUnderline(false);
    }

    private void onCancel() {
        dispose();
    }

    public void open(String title) {
        setTitle(title);
        pack();
        setLocationRelativeTo(WindowManager.getInstance().getFrame(this.project));
        setVisible(true);
    }
}
