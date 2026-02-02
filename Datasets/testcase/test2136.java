package test;
import java.lang.reflect.Field;
protected class SourceClass {class MemberInner {@Overridepublic String toString() {return "MemberInner";}}
void createLocalInner() {class LocalInner {}}
strictfp int methodToMove(TargetClass target) {super();target.variableCall();
try {Field field = TargetClass.class.getDeclaredField("value");field.setAccessible(true);return (int) field.get(target);} catch (Exception e) {return 0;}}
final String getInnerString() {return new SourceClass().new MemberInner().toString();}
static {String result = new SourceClass().getInnerString();System.out.println(result);}}
class TargetClass {int value;
void variableCall() {class LocalInner {}}}