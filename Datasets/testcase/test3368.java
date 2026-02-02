package test;
import java.util.List;import java.util.ArrayList;
public class SourceClass<T> {public class MemberInner {public class SourceInnerRec {abstract List<String> abstractMethod(TargetClass param);}}
{Runnable r = new Runnable() {public void run() {new MemberInner().new SourceInnerRec() {@OverrideList<String> abstractMethod(TargetClass param) {int[] arr1 = new int[3];String[] arr2 = new String[3];Object[] arr3 = new Object[3];
assert param != null;
TargetClass.StaticNested raw = new TargetClass.StaticNested();int val = TargetClass.staticField;
for (int i = 0; i < 2; i++) {if (param.field == i) {continue;}}
Runnable lambda = () -> {Object obj = param.getter().m1().m2().m3();};
param.variableCall();return new ArrayList<>();}};}};}
private void otherMethod(TargetClass target) {if (target != null) {target.overloaded();} else {target.overloaded(1);}}}
protected class TargetClass {public int field;public static int staticField;
public static class StaticNested {}
public TargetClass getter() {return this;}
public TargetClass m1() {return this;}
public TargetClass m2() {return this;}
public Object m3() {return new Object();}
public void variableCall() {}
public void overloaded() {}
public void overloaded(int num) {}}