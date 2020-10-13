package com.geekmake.plugin.dialog;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import org.apache.commons.lang3.StringUtils;

import com.geekmake.plugin.action.http.enums.FormatEnum;
import com.geekmake.plugin.config.IdeActionEvent;
import com.geekmake.plugin.utils.GsonUtil;
import com.geekmake.plugin.utils.NotificationUtils;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;

/**
 * @author pez1420@gmail.com
 * @version $Id: DubboCommandDialog.java v 0.1 2020/3/26 7:02 下午 pez1420 Exp $$
 */
public class FormatJsonDialog extends JDialog {

    public JPanel     contentPanel;
    private Project   project;
    private Editor    editor;
    private JButton   formatBtn;
    private JComboBox comboBox;
    private JTextPane textPanel;
    private JPanel    centerPanel;

    private String    selectedType = FormatEnum.JSON.getValue();

    public FormatJsonDialog(IdeActionEvent actionEvent) {
        this.project = actionEvent.getProject();
        this.editor = actionEvent.getEditor();
        textPanel.setFont(new Font("Microsoft YaHei", Font.BOLD, 14));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setContentPane(contentPanel);
        setModal(true);
        getRootPane().setDefaultButton(formatBtn);
        comboBox.addItem(FormatEnum.JSON.getValue());

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        initComponent();
    }

    public void initComponent() {
        comboBox.addItemListener(e -> selectedType = comboBox.getSelectedItem().toString());

        formatBtn.addActionListener(e -> {
            if (StringUtils.isEmpty(textPanel.getText())) {
                NotificationUtils.showMessage("请输入数据");
                return;
            }

            String formattedString;
            if (FormatEnum.JSON.getValue().equals(selectedType)) {
                formattedString = formatJson(textPanel.getText());
            } else {
                NotificationUtils.showMessage("正在开发中...");
                return;
            }

            if (StringUtils.EMPTY.equals(formattedString)) {
                return;
            }
            textPanel.setText(formattedString);
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        contentPanel.registerKeyboardAction(e -> onCancel(),
            KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
            JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private String formatJson(String text) {
        String str = "";
        try {
            str = GsonUtil.toPrettyFormat(text);
        } catch (Exception e) {
            NotificationUtils.showMessage("JSON格式有误");

        }
        return str;
    }

    private void onCancel() {
        dispose();
    }

    public void open(String title) {
        this.setSize(900, 520);
        setTitle(title);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
