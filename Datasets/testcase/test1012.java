import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class SourceClass<T> {
    public static int staticField = 10;

    int moveCandidateMethod(TargetClass<String>... targetParams) {
        // IfStatement with protected modifier semantics, diff_package_others reference
        protectedIfBlock: {
            if (targetParams.length > 0 && targetParams[0].innerField != null) {
                targetParams[0].innerField = staticField + 1;
            }
        }

        // Instance method call with collection operations
        List<String> resultList = new ArrayList<>();
        for (TargetClass<String> target : targetParams) {
            resultList.addAll(target.localInnerInstance.getStringList(2));
        }

        // Do statement
        int count = 0;
        do {
            count++;
            targetParams[0].localInnerInstance.updateCount(count);
        } while (count < staticField);

        // Type declaration statement
        String typeDeclaredVar = targetParams[0].toString();
        // Variable call
        int localVar = targetParams[0].innerField;
        // Depends on static field
        localVar += SourceClass.staticField;
        // Used by reflection
        try {
            Method method = TargetClass.class.getDeclaredMethod("innerMethod", int.class);
            method.setAccessible(true);
            method.invoke(targetParams[0], localVar);
        } catch (Exception e) {
            // No new exception thrown
        }

        return localVar;
    }

    private String callMethod() {
        TargetClass<String> target = new TargetClass<>();
        // Instance code blocks, this.methodName(arguments)
        {
            int value = this.moveCandidateMethod(target);
            return String.valueOf(value);
        }
    }
}

private class TargetClass<E> {
    String innerField;

    TargetClass() {
        class LocalInnerClass {
            int count;

            List<String> getStringList(int num) {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < num; i++) {
                    list.add("item" + i);
                }
                return list;
            }

            void updateCount(int newCount) {
                this.count = newCount;
            }
        }
        this.localInnerInstance = new LocalInnerClass();
    }

    LocalInnerClass localInnerInstance;

    void innerMethod(int param) {
        this.innerField = String.valueOf(param);
    }
}