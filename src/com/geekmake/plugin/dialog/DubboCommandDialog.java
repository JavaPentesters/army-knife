package com.geekmake.plugin.dialog;

import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import com.geekmake.plugin.config.IdeActionEvent;
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
 * @version $Id: DubboCommandDialog.java v 0.1 2020/3/26 7:02 下午 pez1420 Exp $$
 */
public class DubboCommandDialog extends JDialog {

    /** 项目信息 */
    private Project   project;

    /** 面板 */
    private JPanel    contentPane;

    private LinkLabel link0;

    private JButton   copyButton0;

    private JButton   cancelButton;

    private LinkLabel helpLink;
    private LinkLabel link4;
    private JTextArea textArea1;
    private JLabel    lable1;
    private JTextField statusLTextField;
    private JButton copyButton1;
    private JLabel statusLabel;

    public DubboCommandDialog(Project project, IdeActionEvent actionEvent) {
        this.project = project;

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(cancelButton);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        contentPane.registerKeyboardAction(e -> onCancel(),
            KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
            JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        initComponent(actionEvent);
    }

    private void initComponent(IdeActionEvent actionEvent) {
        copyButton0.addActionListener(e -> {
            String invokeCommand = buildInvokeCommand(actionEvent.getClassName(),
                actionEvent.getMethodName(), textArea1.getText());
            ClipboardUtils.setClipboardContent(invokeCommand);
            NotificationUtils.showMessage();
        });

        cancelButton.addActionListener(e -> onCancel());

    }

    private String buildInvokeCommand(String className, String methodName, String param) {
        StringBuilder sb = new StringBuilder();
        sb.append("invoke ").append(className).append(".").append(methodName).append("(")
            .append(param).append(")");
        return sb.toString();

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

        link4 = new ActionLink("", AllIcons.Ide.Link, new AnAction() {
            @Override
            public void actionPerformed(AnActionEvent anActionEvent) {
                BrowserUtil.browse("https://www.geek-make.com");
            }
        });
        link4.setPaintUnderline(false);
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
