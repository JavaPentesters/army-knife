package com.geekmake.plugin.security;

import com.intellij.psi.PsiRecursiveElementWalkingVisitor;

public abstract class BaseFixElementWalkingVisitor extends PsiRecursiveElementWalkingVisitor {

    private boolean fix = false;

    public boolean isFix() {
        return fix;
    }

    public void setFix(boolean fix) {
        this.fix = fix;
    }
}
