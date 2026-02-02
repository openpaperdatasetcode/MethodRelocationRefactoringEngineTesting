package refactoring.test;

class SourceClass {
    static class StaticNestedOne {
        static int staticField = 10;
    }

    static class StaticNestedTwo {
        private TargetClass target = new TargetClass();
        
        private abstract int abstractMethod(List<String> param);
        
        private int concreteMethod(List<String> param) {
            private int val1 = target.innerField;
            private int val2 = StaticNestedOne.staticField;
            private int val3 = target.memberInner.field;
            
            TargetClass result = OtherClass.callMethod(param);
            return val1 + val2 + val3 + result.baseValue;
        }
    }
}

public class TargetClass {
    int innerField = 5;
    public int baseValue = 0;
    
    class MemberInner {
        int field = 3;
    }
}

class OtherClass {
    public static <T extends TargetClass> T callMethod(List<String> params) {
        return (T) new TargetClass() {
            @Override
            public int baseValue() {
                return super.baseValue + params.size();
            }
        };
    }
}
    