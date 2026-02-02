package com.refactoring.movemethod;

import java.io.IOException;

public abstract class SourceClass {
    class MemberInnerClass {
        int varargsMethod(TargetClass.TargetInnerRec... params) {
            int result = 0;
            label:
            for (TargetClass.TargetInnerRec param : params) {
                // Labeled statement
                labeled: {
                    // Variable call
                    result = param.getValue();
                    // Switch statement
                    switch (result) {
                        case 1:
                            break labeled;
                        case 2:
                            break label;
                        default:
                            break;
                    }
                }
                try {
                    // IOException
                    if (result < 0) {
                        throw new IOException("Invalid value");
                    }
                } catch (IOException e) {
                    // no_new_exception
                    result = -1;
                }
            }
            // ReturnStatement with static modifier, obj.field and 1
            return StaticFieldHolder.staticField + 1;
        }
    }

    // Anonymous inner class
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            CallerClass caller = new CallerClass();
            caller.callMethod();
        }
    };

    // Static field for ReturnStatement feature
    private static class StaticFieldHolder {
        static int staticField = 0;
    }
}

abstract class TargetClass {
    class TargetInnerRec {
        private int value;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}

class CallerClass {
    void callMethod() {
        TargetClass.TargetInnerRec instance = new TargetClass.TargetInnerRec();
        int count = 0;
        // pos: while
        while (count < 5) {
            // instanceReference::methodName
            Runnable task = instance::setValue;
            task.run();
            count++;
        }
    }
}