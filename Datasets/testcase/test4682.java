package test;
sealed class SourceClass permits SourceSubClass {protected String outerProtectedField = "protected_data";private TargetClass targetField;
public class SourceInner1 {}public class SourceInner2 {}
public Object overloadedMethod(int val) {return val * 2;}
public Object overloadedMethod(String str) {return str + outerProtectedField;}
public SourceClass(TargetClass target) {this.targetField = target;
try {SourceInner1 inner1 = new SourceInner1();Object result = this.overloadedMethod(1);
if (targetField.field < 0) {return;}
targetField.field = (int) result;} catch (Exception e) {}}
public SourceClass(TargetClass target, String extra) {this(target);Object strResult = this.overloadedMethod(extra);targetField.field = strResult.toString().length();}}
class SourceSubClass extends SourceClass {public SourceSubClass(TargetClass target) {super(target);}
public SourceSubClass(TargetClass target, String extra) {super(target, extra);}}
public class TargetClass {int field;
void targetMethod() {class TargetLocalInner {}TargetLocalInner local = new TargetLocalInner();}}