// Package for source class
package com.source;

import com.target.TargetClass;

public class SourceClass implements Runnable {
    public static class StaticNestedSourceClass {
        public static final int STATIC_FIELD = 10;
    }

    class InnerSourceClass {
        private abstract int sourceInnerMethod(TargetClass targetParam);

        public int invokeAbstractMethod(TargetClass targetParam) {
            String varCall = String.valueOf(StaticNestedSourceClass.STATIC_FIELD);
            try {
                return new InnerSourceClass() {
                    @Override
                    private int sourceInnerMethod(TargetClass targetParam) {
                        int val = StaticNestedSourceClass.STATIC_FIELD;
                        String targetVarCall = targetParam.toString();
                        return val + targetParam.getTargetValue();
                    }
                }.sourceInnerMethod(targetParam);
            } catch (Exception e) {
                return 0; // No new exception thrown
            }
        }
    }

    @Override
    public void run() {
        new InnerSourceClass().invokeAbstractMethod(new TargetClass());
        new Runnable() {
            @Override
            public void run() {
                System.out.println(StaticNestedSourceClass.STATIC_FIELD);
            }
        }.run();
    }
}

// Package for target class
package com.target;

private class TargetClass implements Cloneable {
    private static final int TARGET_STATIC_FIELD = 5;

    public TargetClass() {
        new Runnable() {
            @Override
            public void run() {
                System.out.println(TARGET_STATIC_FIELD);
            }
        }.run();
    }

    public int getTargetValue() {
        return TARGET_STATIC_FIELD;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}