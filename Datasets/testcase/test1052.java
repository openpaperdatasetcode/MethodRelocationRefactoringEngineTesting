package com.refactoring.test;

public record SourceClass() {
    public static record InnerSourceRec1() {}
    
    public static record InnerSourceRec2() {
        public final int targetMethod(TargetClass.InnerTargetRec param) throws IllegalArgumentException {
            super();
            if (param == null) {
                throw new IllegalArgumentException("Parameter cannot be null");
            }
            int localVar = 10;
            int superValue = super.hashCode();
            int result = localVar + param.hashCode();
            return result;
        }
    }
}

protected record TargetClass() {
    public static record InnerTargetRec() {}
}