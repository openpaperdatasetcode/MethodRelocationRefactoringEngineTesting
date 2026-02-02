package test;
class ParentSource {}
public abstract class SourceClass extends ParentSource {void methodWithLocal1() {class LocalInner1 {}}
void methodWithLocal2() {class LocalInner2 {}}
protected int instanceMethod(TargetClass target) {super();TargetClass newTarget = new TargetClass();variableCall = target.field;
synchronized (this) {int i = 0;while (i < 5) {String result = TargetInnerClass.protectedMethod(i);i++;}}
return variableCall;}
@Overridepublic boolean equals(Object obj) {return false;}
int variableCall;
static class TargetInnerClass {protected static String protectedMethod(int num) {return "Value: " + num;}
protected static String protectedMethod(String str) {return str;}}}
public class TargetClass {int field;
class MemberInner {}}