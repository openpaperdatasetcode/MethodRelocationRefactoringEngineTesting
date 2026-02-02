package test;
non-sealed abstract class SourceClass {public TargetClass targetField;
public void outerMethod() {class LocalInner {public class SourceInnerRec {<T> void innerMethod(T param) {do {targetField.innerClass.innerMethodRec(param);param.toString();} while (param != null);}}}
Runnable r = new Runnable() {public void run() {new LocalInner().new SourceInnerRec().innerMethod("test");}};}}
abstract class TargetClass {public class TargetInnerRec {void innerMethodRec(U param) {
param.hashCode();
}
}
}