package com.geekmake.plugin.dialog;

import static com.geekmake.plugin.Constants.GITHUB_DOCS_PATH;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import org.apache.commons.lang3.StringUtils;

import com.geekmake.plugin.Constants;
import com.geekmake.plugin.action.jvm.enums.ArthasTtCommandEnum;
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

public class ArthasTimeTunelCommandDialog extends JDialog {

    /** 工程信息 */
    private Project        project;

    private IdeActionEvent ideActionEvent;

    /** 显示Lable*/
    private JLabel         commonCmdLabel;

    /** 面板 */
    private JPanel         contentPane;

    /** 下拉列表 */
    private JComboBox      comboBox;

    /** 拷贝命令 */
    private JButton        copyCmdButton2;

    /** 跳转连接 */
    private LinkLabel      helpLink;

    /** 关闭按钮 */
    private JButton        closeButton;
    private JTextField     textField1;
    private JButton        copyCmdButton1;
    private JLabel         label1;
    /** 最佳实践按钮 */
    private LinkLabel      bestExample;

    public ArthasTimeTunelCommandDialog(IdeActionEvent ideActionEvent) {
        this.ideActionEvent = ideActionEvent;
        this.project = ideActionEvent.getProject();

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(closeButton);

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
        initComponent();
    }

    private void initComponent() {
        // 参数里-n 3，表示只执行3次
        String ttCommand = String.format("tt -t %s %s -n 5 '1==1'",
            this.ideActionEvent.getClassName(), this.ideActionEvent.getMethodName());
        textField1.setText(ttCommand);
        // tt -t
        copyCmdButton1.addActionListener(e -> {
            ClipboardUtils.setClipboardContent(ttCommand);
            NotificationUtils.showMessage();
        });

        copyCmdButton2.addActionListener(e -> {
            Object selectedItem = comboBox.getSelectedItem();
            String selectedItemStr = selectedItem.toString();
            if (selectedItem instanceof ArthasTtCommandEnum) {
                selectedItemStr = ((ArthasTtCommandEnum) selectedItem).getCode();
            }

            if (StringUtils.isNotBlank(selectedItemStr)) {
                ClipboardUtils.setClipboardContent(selectedItemStr);
                NotificationUtils.showMessage();
            }
        });

        for (ArthasTtCommandEnum value : ArthasTtCommandEnum.values()) {
            comboBox.addItem(value);
        }
        comboBox.setRenderer(new DefaultListCellRenderer() {

            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index,
                                                          boolean isSelected,
                                                          boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                String tipText = value.toString();
                String codeValue = value.toString();
                if (value instanceof ArthasTtCommandEnum) {
                    tipText = ((ArthasTtCommandEnum) value).getMsg();
                    codeValue = ((ArthasTtCommandEnum) value).getCode();
                }
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

    private void onCancel() {
        dispose();
    }

    private void createUIComponents() {
        helpLink = new ActionLink("", AllIcons.Ide.Link, new AnAction() {
            @Override
            public void actionPerformed(AnActionEvent anActionEvent) {
                BrowserUtil.browse(Constants.ARTHAS_URL);
            }
        });
        helpLink.setPaintUnderline(false);

        bestExample = new ActionLink("", AllIcons.Ide.Link, new AnAction() {
            @Override
            public void actionPerformed(AnActionEvent anActionEvent) {
                BrowserUtil.browse(GITHUB_DOCS_PATH + "arthas.md");
            }
        });
        bestExample.setPaintUnderline(false);
    }

    public void open(String title) {
        setTitle(title);
        pack();
        setLocationRelativeTo(WindowManager.getInstance().getFrame(this.project));
        setVisible(true);
    }
}
