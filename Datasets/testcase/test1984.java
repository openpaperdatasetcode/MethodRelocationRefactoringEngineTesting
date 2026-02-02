package test;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;
private class SourceClass {private int outerField = 10;
protected Object process(TargetClass target) {class LocalInnerOne {}class LocalInnerTwo {}
TargetClass.Inner targetInner = target.new Inner();Object result = null;
for (int val : targetInner.values) {if (val == 1) {result = super.toString() + target.superField;}}
int i = 0;while (i < 2) {List<String> items = targetInner.getData(i, this.outerField);i++;}
try {Method method = TargetClass.Inner.class.getMethod("getData", int.class, int.class);result = method.invoke(targetInner, 0, outerField);} catch (Exception e) {}
return result;}}
public class TargetClass {int superField;
class Inner {int[] values = {1, 2, 3};
List<String> getData(int a, int b) {class LocalInner {}superField = a + b;return new ArrayList<>();}}}
