package com.refactoring.test;

enum SourceEnum extends BaseEnum {
    INSTANCE;

    private final InnerAnonymous inner = new InnerAnonymous() {
        @Override
        protected void innerMethod() {
            SourceEnum.this.moveMethod();
        }
    };

    private final AnotherAnonymous anotherInner = new AnotherAnonymous() {
        @Override
        public void anotherMethod() {
            // Empty implementation for anonymous inner class feature
        }
    };

    protected void moveMethod(TargetEnum targetParam) {
        int count = 0;
        switch (targetParam) {
            case VALUE1:
                Object obj = new Object() {
                    protected Object constructor() {
                        super.toString();
                        return this;
                    }
                }.constructor();
                break;
            default:
                break;
        }

        do {
            StaticNested nested = new StaticNested();
            nested.nestedField = count++;
            targetParam.callReflectionMethod();
        } while (count < 5);

        try {
            int result = callMethod(targetParam);
        } catch (Exception e) {
            // Exception handling for callMethod position
        }
    }

    protected int callMethod(TargetEnum param) {
        return param.ordinal() + (() -> 10).get();
    }

    private abstract static class InnerAnonymous {
        protected abstract void innerMethod();
    }

    private abstract static class AnotherAnonymous {
        public abstract void anotherMethod();
    }
}

strictfp enum TargetEnum {
    VALUE1, VALUE2;

    static class StaticNested {
        int nestedField;
    }

    void callReflectionMethod() {
        try {
            java.lang.reflect.Method method = SourceEnum.class.getDeclaredMethod("moveMethod", TargetEnum.class);
            method.setAccessible(true);
            method.invoke(SourceEnum.INSTANCE, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class BaseEnum {}