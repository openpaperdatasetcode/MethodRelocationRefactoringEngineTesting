package test;
import java.lang.reflect.Method;
class ParentClass {}
protected class SourceClass extends ParentClass {public class SourceInner {public class SourceInnerRec {public void varargsMethod(TargetClass.TargetInnerRec... params) {class LocalType<T extends CharSequence> {T value;LocalType(T val) { this.value = val; }}
LocalType<String> localVar = new LocalType<>("test");localVar.value = "updated";
try {for (TargetClass.TargetInnerRec param : params) {Object result = TargetClass.staticFinalMethod(param);System.out.println(result);}
Method refMethod = getClass().getMethod("varargsMethod", TargetClass.TargetInnerRec[].class);refMethod.invoke(this, (Object) new TargetClass.TargetInnerRec[]{});} catch (Exception e) {}}
public void varargsMethod(String... strs) {}}}}
public class TargetClass {public static class TargetInnerRec {int field = 5;}
public static final Object staticFinalMethod(TargetInnerRec param) {return param.field;}}
