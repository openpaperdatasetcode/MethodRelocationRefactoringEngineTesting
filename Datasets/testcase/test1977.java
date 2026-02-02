package test;
import java.lang.reflect.Method;import java.util.List;
interface MyInterface {}
public class SourceClass implements MyInterface {static class StaticNested {}
class MemberInner {}
public Object process(TargetClass target) {class LocalInner {private LocalInner() {int val = TargetClass.STATIC_FIELD;if (val == 1) {System.out.println("Static field value is 1");}}}
new LocalInner();TargetClass typeDecl = new TargetClass();Object result = null;labeledBlock: {for (int i = 0; i < 3; i++) {typeDecl.field = i;if (i == 2) {break labeledBlock;}}}
try {Method method = TargetClass.class.getMethod("toString");result = method.invoke(typeDecl);} catch (Exception e) {result = e.getMessage();}
return result;}
@Overridepublic String toString() {return super.toString();}}
public class TargetClass {public static int STATIC_FIELD = 1;public int field;}