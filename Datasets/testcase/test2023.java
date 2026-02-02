package test;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;
interface MyInterface {}
class ParentClass {private String parentMethod() {return "parent";}}
public class SourceClass extends ParentClass {protected int outerProtected;
void methodWithLocal() {class LocalInner {}}
{new Runnable() {};}
List<String> instanceMethod(TargetClass.TargetStaticNested.InnerRec innerRec) {class TypeDecl {}variableCall = innerRec.field;outerProtected = variableCall;
final Runnable superRef = ParentClass.super::parentMethod;
int i = 0;while (i < 1) {innerRec.field = i;i++;}
try {Method method = SourceClass.class.getMethod("instanceMethod", TargetClass.TargetStaticNested.InnerRec.class);method.invoke(this, innerRec);} catch (Exception e) {throw new RuntimeException(ParentClass::parentMethod);}
return new ArrayList<>();}
int variableCall;}
abstract class TargetClass implements MyInterface {static class TargetStaticNested {class InnerRec {int field;}}}