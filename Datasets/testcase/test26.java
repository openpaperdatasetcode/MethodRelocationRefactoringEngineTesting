```java
p1/SourceClass.java:
package p1;

import p2.TargetClass;
import java.util.List;
import java.util.ArrayList;

public sealed class SourceClass permits AnotherClass {
    public static int staticField = 10;
    
    public class MemberInner {
        public int nonStaticField = 5;
        
        public class DeepInner {
            @FunctionalInterface
            interface IntTriFunction {
                int apply(int a, int b, int c);
            }
            
            TargetClass methodToMove(TargetClass param, int... varargs) {
                int x = this.nonStaticField + SourceClass.staticField + 1;
                
                class Base {
                    public int compute(int a, int b, int c) { return a + b + c; }
                }
                class Sub extends Base {
                    @Override
                    public int compute(int a, int b, int c) { return a * b * c; }
                }
                Sub sub = new Sub();
                
                List list = new ArrayList();
                list.add(1);
                
                for (int i = 0; i < varargs.length; i++) {
                    int result = sub.compute(varargs[i], 2, 3);
                    IntTriFunction func = Sub::compute;
                    int result2 = func.apply(varargs[i], 2, 3);
                    list.add(result);
                    
                    if (i == 1) {
                        continue;
                    }
                }
                
                param.execute();
                
                return new TargetClass() {
                    @Override
                    public void execute() {
                        System.out.println("Anonymous inner class");
                    }
                };
            }
        }
    }
    
    public void demoMethod() {
        class LocalInner {}
        new LocalInner();
    }
}

class AnotherClass extends SourceClass {}

p2/TargetClass.java:
package p2;

public class TargetClass {
    public void execute() {}
}
```