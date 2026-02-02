package com.refactoring.test;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Method;

strictfp enum SourceClass {
    INSTANCE;

    // Member inner class
    protected class MemberInnerClass {
        private int value;

        public MemberInnerClass(int value) {
            this.value = value;
        }

        // Target method to be refactored (inner recursive position)
        protected int calculate(TargetClass targetParam) {
            int result = 0;
            // If statement
            if (targetParam != null) {
                // This(arguments) call
                this(10);
                // Variable call
                result = this.value + targetParam.getCode();
                // With bounds
                for (int i = 0; i < 5; i++) {
                    result += i;
                }
                // Uses outer this
                SourceClass outerThis = SourceClass.this;
                // Switch statement with constructor
                switch (targetParam) {
                    case FIRST:
                        // Constructor with default modifier and instance reference call
                        AnonymousInnerClass anon = new AnonymousInnerClass() {
                            @Override
                            List<String> process() {
                                List<String> list = new ArrayList<>();
                                list.add(outerThis.toString());
                                // Instance reference method call
                                list.add(targetParam.name());
                                return list;
                            }
                        };
                        result += anon.process().size();
                        break;
                    case SECOND:
                        result *= 2;
                        break;
                }
                // Used by reflection
                try {
                    Method method = TargetClass.class.getMethod("getCode");
                    result += (int) method.invoke(targetParam);
                } catch (Exception e) {
                    // No new exception thrown
                    result = 0;
                }
            }
            return result;
        }

        // Default constructor for this(arguments) call
        MemberInnerClass() {
            this(0);
        }
    }

    // Anonymous inner class
    interface AnonymousInnerClass {
        List<String> process();
    }
}

protected enum TargetClass {
    FIRST(1), SECOND(2);

    private final int code;

    TargetClass(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}