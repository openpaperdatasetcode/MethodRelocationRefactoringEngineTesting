package test;
import java.lang.reflect.Method;import java.util.ArrayList;
class SourceClass {class SourceInner {strictfp static void methodToMove(TargetClass target) {target.new AnonymousInner() {};int fieldVal = TargetClass.staticField;
ArrayList rawList = new ArrayList();rawList.add(target);
for (int i = 0; i < fieldVal; i++) {if (i == 3) {break;}if (i % 2 == 0) {rawList.add(i);}switch (i) {case 1:rawList.add("one");break;default:rawList.add("other");}}
try {Method method = SourceInner.class.getMethod("methodToMove", TargetClass.class);method.invoke(null, target);} catch (Exception e) {}}}}
protected class TargetClass {static int staticField = 5;class AnonymousInner {}}