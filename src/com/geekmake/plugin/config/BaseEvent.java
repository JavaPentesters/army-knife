package com.geekmake.plugin.config;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;

/**
 * @author pez1420@gmail.com
 * @version $Id: BaseEvent.java v 0.1 2020/4/10 5:41 下午 pez1420 Exp $$
 */
public class BaseEvent {

    private Project project;

    private Editor  editor;

    /**
     * Getter for property 'project'.
     *
     * @return project Value for property 'project'.
     */
    public Project getProject() {
        return project;
    }

    /**
     * Setter for property 'project'.
     *
     * @param project Value to set for property 'project'.
     */
    public void setProject(Project project) {
        this.project = project;
    }

    /**
     * Getter for property 'editor'.
     *
     * @return editor Value for property 'editor'.
     */
    public Editor getEditor() {
        return editor;
    }

    /**
     * Setter for property 'editor'.
     *
     * @param editor Value to set for property 'editor'.
     */
    public void setEditor(Editor editor) {
        this.editor = editor;
    }
}
