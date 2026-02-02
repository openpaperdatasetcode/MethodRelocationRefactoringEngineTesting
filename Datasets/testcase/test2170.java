package test;
import java.util.List;import java.util.ArrayList;
public class SourceClass<T> {protected T outerProtectedField;
record SourceInnerRec() {private int methodToMove(TargetClass.InnerRec innerRec) {// Anonymous inner classnew Object() {};
// Local inner classclass LocalInner {}new LocalInner();
// Super constructor invocation (implicit in record)super.toString();
// Access outer protected fieldT val = SourceClass.this.outerProtectedField;
// Expression statementint count = innerRec.items().size();
// Instance method chain in ternary operatorList<String> result = (count > 0)? innerRec.innerClass().m1().m2().m3(): new ArrayList<>();
// Overload exists (demonstrated with another method)return result.size();}
private int methodToMove(String str) {return str.length();}}
static class NestedInner {NestedInner m1() { return this; }NestedInner m2() { return this; }List<String> m3() { return new ArrayList<>(); }}}
protected class TargetClass {void someMethod() {class LocalInner {}new LocalInner();}
record InnerRec(List<String> items) {SourceClass.NestedInner innerClass() {return new SourceClass.NestedInner();}}}
