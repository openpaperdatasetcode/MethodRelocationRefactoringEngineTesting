package refactoring.test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

// Parent class for overriding feature in call_method
class ParentCallClass {
    public Object callParentMethod(TargetClass targetParam) {
        return targetParam;
    }
}

// Source normal class (protected modifier, same package as target, two member inner classes)
protected class SourceClass extends ParentCallClass {
    // First member inner class
    public class FirstMemberInnerClass {
        // Second member inner class (source_inner for method position)
        public class SecondMemberInnerClass {
            // Target normal method (final access, List<String> return, source_inner)
            // Precondition: method contains target class parameter
            public final List<String> targetMethod(TargetClass targetParam) {
                // Variable call feature
                String varCall = targetParam.memberInner.innerField;
                List<String> resultList = new ArrayList<>();
                resultList.add(varCall);

                // Constructor invocation feature
                TargetClass.MemberInnerClass innerInstance = targetParam.new MemberInnerClass();
                resultList.add(innerInstance.innerValue);

                // Depends_on_inner_class feature (relies on inner class logic)
                InnerDependency innerDep = new InnerDependency();
                resultList.addAll(innerDep.processData(targetParam));

                // NullPointerException (no_new_exception - no explicit new exception instance)
                if (targetParam == null) {
                    throw new NullPointerException(); // Reuse standard NPE, no custom new
                }

                return resultList;
            }

            // Inner class for depends_on_inner_class feature
            private class InnerDependency {
                List<String> processData(TargetClass target) {
                    List<String> data = new ArrayList<>();
                    data.add(target.toString());
                    return data;
                }
            }
        }
    }

    // Call method (source type, synchronized modifier, Lambda expressions pos, Object return)
    @Override // Overriding target_feature
    public synchronized Object callParentMethod(TargetClass targetParam) {
        // Lambda expressions pos requirement
        Function<TargetClass, List<String>> lambdaFunc = param -> 
            new FirstMemberInnerClass().new SecondMemberInnerClass().targetMethod(param);
        
        // ClassName::methodName target_feature
        Function<TargetClass, List<String>> methodRef = 
            new FirstMemberInnerClass().new SecondMemberInnerClass()::targetMethod;

        List<String> result = methodRef.apply(targetParam);
        return lambdaFunc.apply(targetParam);
    }
}

// Parent class for TargetClass's extends feature
class TargetParentClass {}

// Target normal class (public modifier, extends + member inner class target_feature)
public class TargetClass extends TargetParentClass {
    // Member inner class target_feature
    public class MemberInnerClass {
        String innerField = "target_inner_field";
        String innerValue = "processed_inner_value";
    }

    // Instance of member inner class for access
    public MemberInnerClass memberInner = new MemberInnerClass();
}