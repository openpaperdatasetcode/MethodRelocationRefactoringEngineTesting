import java.util.ArrayList;
import java.util.List;

public class SourceClass {
    class MemberInnerSourceClass {
        private int counter = 1;
        
        public void sourceInnerRecMethod(TargetClass targetParam, Object... varargs) {
            super();
            String varCall = String.valueOf(counter);
            List<Object> boundedList = new ArrayList<>() {
                @Override
                public boolean add(Object e) {
                    return super.add(e);
                }
            };
            
            do {
                try {
                    private EnhancedForStatement: {
                        for (Object obj : boundedList) {
                            String fieldVal = targetParam.memberInnerTargetClass.innerField;
                            boundedList.add(fieldVal + counter);
                        }
                    }
                    counter--;
                    if (counter > 0) {
                        sourceInnerRecMethod(targetParam); // Recursive call (source_inner_rec)
                    }
                } catch (Exception e) {
                    // Required try-catch block
                }
            } while (counter > 0);
        }
    }
    
    public SourceClass() {
        new Runnable() {
            @Override
            public void run() {
                new MemberInnerSourceClass().sourceInnerRecMethod(new TargetClass());
            }
        }.run();
    }
}

final class TargetClass {
    class MemberInnerTargetClass {
        String innerField = "targetField";
    }
    
    MemberInnerTargetClass memberInnerTargetClass = new MemberInnerTargetClass();
}