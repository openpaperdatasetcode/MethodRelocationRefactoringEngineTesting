package source;
import target.TargetClass;
strictfp class SourceClass<T> extends SuperClass<T> {private String outerPrivate = "private";class MemberInner1 {}class MemberInner2 {}
private Object methodToMove(TargetClass<T> target) {int i = 0;do {if (TargetClass.staticField == null) {break;}target.variableCall();i++;} while (i < 3);
outerPrivate = "modified";target.value = i;
if (i < 1) {throw new IllegalArgumentException();}
return target;}}
class SuperClass<T> {}
package target;
/**
Javadoc for TargetClass*/final class TargetClass<T> {static Object staticField;T value;
void variableCall() {class LocalInner {}}}