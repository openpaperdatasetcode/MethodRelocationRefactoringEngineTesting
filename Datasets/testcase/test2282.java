package test;
import java.util.List;import java.util.ArrayList;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnn {}
class ParentGeneric<T> {}
sealed class SourceClass<T extends Number> extends ParentGeneric<T> permits SourceSubClass {private int outerPrivate;
class MemberInner {}
void localInnerMethod() {class LocalInner {}}
@MyAnnprotected List<String> instanceMethod(TargetClass<?>.TargetInner targetInner) throws Exception {class TypeDecl {}
if (targetInner == null) {throw new NullPointerException();}
variableCall = targetInner.field;outerPrivate = variableCall;
new TargetClass<String>() {{super("3");if (targetInner.field != 3) {}}};
for (int i = 0; i < 3; i++) {targetInner.field = i;}
return new ArrayList<>();}
int variableCall;}
final class SourceSubClass extends SourceClass<Integer> {}
protected class TargetClass<S extends CharSequence> extends ParentGeneric<S> {class TargetInner {int field;}
public TargetClass(S s) {super();}
{new Runnable() {};}}