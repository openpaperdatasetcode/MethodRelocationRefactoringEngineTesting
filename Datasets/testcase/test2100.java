package test;
import java.util.List;import java.util.ArrayList;
class SuperClass {protected int superField;
public int methodToOverride() {return 0;}}
class TargetSubClass extends TargetClass {@Overridepublic Object subMethod(String arg) {return arg;}}
protected class SourceClass extends SuperClass {class FirstMemberInner {}class SecondMemberInner {}
@Overridepublic final int methodToOverride() {TargetClass target = new TargetClass();int fieldVal = target.instanceField;
synchronized (target) {if (super.superField < 0) {// Do nothing}}
int i = 0;do {List<String> list1 = new TargetClass().overloadMethod(1);List<String> list2 = new TargetClass().overloadMethod("str");List<String> list3 = new TargetClass().overloadMethod(1.0);i++;} while (i < 3);
for (int j = 0; j < 5; j++) {target.variableCall();}
class LocalType {}LocalType local = new LocalType();
TargetSubClass sub = new TargetSubClass();Object result;if (fieldVal > 0) {result = sub.subMethod("test");} else {result = null;}
return fieldVal;}}
/**
Javadoc for TargetClass*/class TargetClass {int instanceField;
class MemberInner {}
void variableCall() {}
private List<String> overloadMethod(int num) {return new ArrayList<>();}
private List<String> overloadMethod(String str) {return new ArrayList<>();}
private List<String> overloadMethod(double d) {return new ArrayList<>();}
public Object subMethod(String arg) {return null;}}