package com.geekmake.plugin.dialog;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import org.apache.commons.lang3.StringUtils;

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
 * @version $Id: BaseAnActionDialog.java v 0.1 2020/4/1 9:37 下午 pez1420 Exp $$
 */
public class BaseAnActionDialog extends JDialog {

    private JPanel     contentPane;
    /**
     * tt -t 表达式构造
     */
    private JTextField ttTextField;
    /**
     * 获取tt 命令行Button
     */
    private JButton    ttButton;
    /**
     * 常用命令下拉框
     */
    private JComboBox  comboBox;

    /**
     * 获取命令的信息
     */
    private JButton    comboBoxValueGetButton;
    /**
     * 关闭按钮
     */
    private JButton    closeButton;

    /**
     * 帮助链接
     */
    private LinkLabel  helpLink;

    /**
     * 最佳案列
     */
    private LinkLabel  ttBestLink;

    /**
     * 表达式
     */
    private String     timeTunnelExpression;
    /**
     * 工程信息
     */
    private Project    project;

    public BaseAnActionDialog(Project project, String timeTunnelExpression) {
        this.project = project;
        setContentPane( this.getContentPane());
        setModal(true);
        getRootPane().setDefaultButton(closeButton);
        this.timeTunnelExpression = timeTunnelExpression;

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

        init();
    }

    private void init() {
        ttTextField.setText(this.timeTunnelExpression);
        ttButton.addActionListener(e -> {
            String text = ttTextField.getText();
            if (StringUtils.isNotBlank(text)) {
                ClipboardUtils.setClipboardContent(text);
                NotificationUtils.showMessage();
                // ClipboardUtils.setClipboardString(text);
                // NotifyUtils.notifyMessageDefault(project);
            }

        });

        comboBoxValueGetButton.addActionListener(e -> {
            Object selectedItem = comboBox.getSelectedItem();
            String selectedItemStr = selectedItem.toString();
            //            if (selectedItem instanceof TimeTunnelCommandEnum) {
            //                selectedItemStr = ((TimeTunnelCommandEnum) selectedItem).getCode();
            //            }
            //            if (StringUtils.isNotBlank(selectedItemStr)) {
            //                ClipboardUtils.setClipboardString(selectedItemStr);
            //                NotifyUtils.notifyMessage(project, "选中的tt常用命令已经复制");
            //            }
        });
        //值太长展示不全处理 https://www.java-forums.org/awt-swing/16196-item-too-big-jcombobox.html

        //        for (TimeTunnelCommandEnum value : TimeTunnelCommandEnum.values()) {
        //            comboBox.addItem(value);
        //        }

        comboBox.setRenderer(new DefaultListCellRenderer() {

            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index,
                                                          boolean isSelected,
                                                          boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                String tipText = value.toString();
                String codeValue = value.toString();
                //                if (value instanceof TimeTunnelCommandEnum) {
                //                    tipText = ((TimeTunnelCommandEnum) value).getEnumMsg();
                //                    codeValue = ((TimeTunnelCommandEnum) value).getCode();
                //                }
                if (isSelected) {
                    comboBox.setToolTipText(tipText);
                }

                setToolTipText(tipText);
                Rectangle textRect = new Rectangle(comboBox.getSize().width,
                    getPreferredSize().height);
                String shortText = SwingUtilities.layoutCompoundLabel(this,
                    getFontMetrics(getFont()), codeValue, null, getVerticalAlignment(),
                    getHorizontalAlignment(), getHorizontalTextPosition(),
                    getVerticalTextPosition(), textRect, new Rectangle(), textRect,
                    getIconTextGap());
                setText(shortText);
                return this;
            }

        });
        closeButton.addActionListener(e -> onCancel());
    }

    /**
     * 关闭
     */
    private void onCancel() {
        dispose();
    }

    private void createUIComponents() {
        ttBestLink = new ActionLink("", AllIcons.Ide.Link, new AnAction() {
            @Override
            public void actionPerformed(AnActionEvent anActionEvent) {
                BrowserUtil.browse("https://github.com/alibaba/arthas/issues/482");
            }
        });
        ttBestLink.setPaintUnderline(false);

        helpLink = new ActionLink("", AllIcons.Ide.Link, new AnAction() {
            @Override
            public void actionPerformed(AnActionEvent anActionEvent) {
                BrowserUtil.browse("https://alibaba.github.io/arthas/tt.html");
            }
        });
        helpLink.setPaintUnderline(false);
    }

    /**
     * 打开窗口
     */
    public void open(String title) {
        setTitle(title);
        pack();
        //两个屏幕处理出现问题，跳到主屏幕去了
        setLocationRelativeTo(WindowManager.getInstance().getFrame(this.project));
        setVisible(true);
    }

}
