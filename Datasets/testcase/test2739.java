package test.same;
import java.util.ArrayList;import java.util.List;
protected class SourceClass<T> {private T outerPrivateField;
class MemberInner extends ParentClass {@OverrideList<String> method(TargetClass target) {List<String> list = new ArrayList<>();TargetClass.MemberInner targetInner = new TargetClass.MemberInner(1);T var = outerPrivateField;list.add(String.valueOf(targetInner.field));
int val = 5;switch (val) {case 1:list.add("case1");break;default:list.add("default");}
return list;}}
void createLocal() {class LocalInner {}}}
class TargetClass {class MemberInner {final int field;
final MemberInner(int val) {this.field = val;}}}
class ParentClass {List<String> method(TargetClass target) {return new ArrayList<>();}}