package com.geekmake.plugin.utils;

import com.intellij.notification.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;

/**
 * It has to make a NotificationGroup instance, and then use that to make a Notification,
 * and pass the notification and the Project to Notifications.Bus.notify()
 *
 * @author pez1420@gmail.com
 * @version $Id: NotificationUtils.java v 0.1 2020/3/26 7:38 下午 pez1420 Exp $$
 */
public final class NotificationUtils {

    public static final NotificationGroup GROUP_DISPLAY_ID_INFO = new NotificationGroup(
        "Java Helper notification group", NotificationDisplayType.BALLOON, true);

    public static void showMessage() {
        String clipboardContent = ClipboardUtils.getClipboardContent();
        Notification notification = GROUP_DISPLAY_ID_INFO.createNotification(clipboardContent,
            NotificationType.INFORMATION);
        Project[] projects = ProjectManager.getInstance().getOpenProjects();
        Notifications.Bus.notify(notification, projects[0]);
    }

    public static void showMessage(String message) {
        Notification notification = GROUP_DISPLAY_ID_INFO.createNotification(message,
            NotificationType.INFORMATION);
        Project[] projects = ProjectManager.getInstance().getOpenProjects();
        Notifications.Bus.notify(notification, projects[0]);
    }
}
