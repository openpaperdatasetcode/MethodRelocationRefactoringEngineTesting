package test;
import java.util.ArrayList;import java.util.List;
class Parent<T> {protected String parentMethod() {return super.toString();}}
protected class SourceClass<T> extends Parent<T> {class MemberInner {class InnerRec extends Parent<T> {@Overrideprivate TargetClass<T>.InnerRec moveMethod(TargetClass<T>.InnerRec param) {Object anon1 = new Object() {};Object anon2 = new Object() {};
TargetClass<T>.InnerRec target = new TargetClass<T>.InnerRec(1);target.superField = 1;
List rawList = new ArrayList();rawList.add(param);
variableCall(target);
switch (1) {case 1:parentMethod();break;}
return target;}
private void variableCall(TargetClass<T>.InnerRec t) {}}}}
class TargetClass<T> {class InnerRec {int superField;
private InnerRec(int val) {this.superField = val;}
class LocalInner {}}}
