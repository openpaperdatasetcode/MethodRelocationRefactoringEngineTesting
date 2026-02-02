package com.source;
import com.target.TargetEnum;import java.lang.reflect.Method;
public enum SourceEnum extends Number {INSTANCE;
protected int targetField = TargetEnum.INSTANCE.targetField;private volatile Object obj1 = null;private volatile Object obj2 = null;
protected static class NestedStaticClass {}
@Overrideprotected int method() {super.toString();
switch (this) {case INSTANCE:break;}
class LocalInnerClass {}LocalInnerClass lic = new LocalInnerClass();
int i = 0;while (i < 1) {;i++;}
TargetEnum.INSTANCE.accessInstanceField();variableCall();
try {Method m = getClass().getMethod("method");m.invoke(this);} catch (Exception e) {}
Runnable r = () -> {System.out.println(2);NestedStaticClass nsc = new NestedStaticClass();SourceEnum.INSTANCE.method();};r.run();
return 0;}
private void variableCall() {}}
package com.target;
enum TargetEnum<T> {INSTANCE;
int targetField = 0;
void accessInstanceField() {new Runnable() {@Overridepublic void run() {}};}}
