package test;
import java.lang.reflect.Method;
public class SourceClass {private int outerPrivateField = 100;private TargetClass targetField = new TargetClass();
class SourceInner {public int instanceMethod (int baseTypeParam) {
private TargetClass target = SourceClass.this.targetField;private int field1Val = target.thisField1;private int field2Val = target.thisField2;private int field3Val = target.thisField3;
try {Method reflectMethod = SourceInner.class.getMethod ("instanceMethod", int.class);reflectMethod.invoke (this, baseTypeParam);} catch (Exception e) {e.printStackTrace ();}
synchronized (target) {field1Val += baseTypeParam;field2Val += outerPrivateField;field3Val += SourceClass.this.outerPrivateField;}
Runnable printLambda = () -> System.out.println (this.field1Val);printLambda.run ();
int sum = 0;for (int i = 0; i < 3; i++) {if (i == 2) break;sum += new TargetClass.TargetStaticNested ().calculate (i);}
int varCallResult = field1Val + field2Val + field3Val;return varCallResult + sum;}}}
protected class TargetClass {int thisField1 = 10;int thisField2 = 20;int thisField3 = 30;
static class TargetStaticNested {
public int calculate (int num) {
return num * 5;
}
}
}