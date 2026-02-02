```java
package pkg;

import java.util.*;

public enum TargetEnum {
    TARGET_CONST;

    public class MemberInner<T> {
        class InnerMost {}
    }
}

class SourceContainer {
    private enum SourceEnum {
        ENUM_CONST;

        protected int protectedField = 5;
    }

    static class StaticNested {
        Object anon = new Object() {
            @Override
            public String toString() {
                return "Anonymous";
            }
        };

        class Inner implements MyInterface {
            @CustomAnnotation
            public strictfp List<String> methodToMove(TargetEnum.MemberInner<String> targetParam) throws CustomException {
                List<String> result = new ArrayList<>();
                int count = 0;
                
                // Enhanced for statement
                for (String s : Arrays.asList("A", "B")) {
                    result.add(s);
                }
                
                // While statement
                while (count < 3) {
                    count++;
                }
                
                // this.methodName call
                this.overloadedMethod(result.size());
                
                // Access outer protected field
                int value = SourceEnum.this.protectedField;
                
                // Super constructor invocation via anonymous class
                new Base("Test") {
                    {
                        System.out.println("Initialized");
                    }
                };
                
                // Variable call
                int calc = value * 2;
                
                // Conditional exception
                if (calc > 100) {
                    throw new CustomException("Threshold exceeded");
                }
                
                return result;
            }

            // Overloaded method
            void overloadedMethod(int num) {}
            void overloadedMethod(String text) {}
        }
    }
}

interface MyInterface {
    List<String> methodToMove(TargetEnum.MemberInner<String> targetParam) throws CustomException;
}

@interface CustomAnnotation {}

class Base {
    Base(String msg) {}
}

class CustomException extends Exception {
    CustomException(String message) {
        super(message);
    }
}
```