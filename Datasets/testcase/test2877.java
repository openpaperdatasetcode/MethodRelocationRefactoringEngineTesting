package test;
import java.util.List;import java.util.ArrayList;
public class SourceClass {private int instanceField;
int methodToMove(TargetClass target) {class LocalInner1 {void innerMethod() {// ExpressionStatement with target_feature in inner classtarget.superField = 1;}}class LocalInner2 {}
// Access instance fieldinstanceField = 5;// Variable callint val = instanceField;
new LocalInner1().innerMethod();
do {// Static method with specified features in do-whileList<String> list = StaticMethodHolder.staticMethod();} while (val < 10);
return val;}
public static class StaticMethodHolder {public static List<String> staticMethod() {// super.methodName()super.toString();return new ArrayList<>();}}}
public class TargetClass extends SuperClass {// Field accessed as super.field in ExpressionStatement}
class SuperClass {int superField;}