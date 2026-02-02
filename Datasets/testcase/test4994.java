package refactoring.test;

strictfp class SourceClass<T> {
    protected void instanceMethod(TargetClass<T> targetParam) {
        super();
        
        class LocalInnerOne {
            void process() {
                private try {
                    String val1 = targetParam.innerField;
                    String val2 = targetParam.memberInner.fieldOne;
                    String val3 = targetParam.memberInner.fieldTwo;
                    variable call;
                } catch (Exception e) {}
            }
        }
        
        class LocalInnerTwo {
            LocalInnerOne getInner() {
                return new LocalInnerOne();
            }
        }
        
        @SuppressWarnings("unused")
        LocalInnerTwo inner = new LocalInnerTwo();
        inner.getInner().process();
    }
}

/**
 * Target generic class with member inner class
 */
private class TargetClass<T> {
    String innerField;
    
    class MemberInner {
        String fieldOne;
        String fieldTwo;
    }
}
