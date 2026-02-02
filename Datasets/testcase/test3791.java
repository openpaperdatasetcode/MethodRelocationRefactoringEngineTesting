import java.util.ArrayList;import java.util.List;
// Target interface for implementation (target_class: implements)interface DataHandler {int handleData(String data);}
// TargetClass: normal, package-private, implements DataHandler, with member inner class (meets target_class specs)class TargetClass implements DataHandler {// Member inner class (target_inner: method's target class)public class TargetInner {private List<String> innerList;
// Constructor for call_method (target_feature: constructor)public TargetInner(List<String> list) {this.innerList = list;}
// Method for variable callpublic void addData(String data) {innerList.add(data);}
// Method for variable callpublic int getListSize() {return innerList.size();}}
private String targetField;
// Super constructor invocation (implicitly calls Object super constructor)public TargetClass() {this.targetField = "target_default";}
@Overridepublic int handleData(String data) {return data.length();}}
// SourceClass: normal, public, same package as target, no extra features (meets source_class specs)public class SourceClass {// Inner class for method_position: source_inner_recprotected class SourceInner {/**
Varargs method to process TargetClass.Inner instances
Handles data collection and inner class interaction
@param inners TargetClass.TargetInner instances (per_condition: contains target parameter)
@return Object Combined result of processing*/// Method: varargs, Object return, protected, source_inner_rec (meets method specs)protected Object processTargetInners(TargetClass.TargetInner... inners) {List<Integer> sizes = new ArrayList<>();if (inners == null) {return sizes;}
for (TargetClass.TargetInner inner : inners) {// Super keywords: access outer class (SourceInner) via this (implicit super context)SourceClass outer = SourceClass.this;
// Expression statement: use target inner class methodinner.addData("processed_by_source");
// Variable call: invoke target inner class methodsizes.add(inner.getListSize());
// Variable call: invoke target class (outer) method via inner's enclosing instanceTargetClass target = inner.new TargetClass();sizes.add(target.handleData(inner.getListSize() + "_data"));}
return sizes; // no_new_exception}
// Instance code block: call_method pos=instance code blocks{// Call_method: inner_class, private, constructor, lambda (parameters)->methodBodyTargetClass target = new TargetClass();List<String> initList = new ArrayList<>();// Lambda expression (parameters)->methodBody, uses TargetInner constructorjava.util.function.Function<List<String>, TargetClass.TargetInner> innerCreator = (list) -> new TargetClass.TargetInner(list);TargetClass.TargetInner inner = innerCreator.apply(initList);
// Private inner class for call_method (modifier=private)class PrivateCaller {private int callInnerMethod(TargetClass.TargetInner inner) {inner.addData("from_private_caller");return inner.getListSize();}}
int result = new PrivateCaller().callInnerMethod(inner);System.out.println("Instance block call result: " + result);}}
// Test entrypublic static void main(String[] args) {SourceClass source = new SourceClass();SourceInner inner = source.new SourceInner();
TargetClass target = new TargetClass();TargetClass.TargetInner t1 = target.new TargetInner(new ArrayList<>());TargetClass.TargetInner t2 = target.new TargetInner(new ArrayList<>());
Object processResult = inner.processTargetInners(t1, t2);System.out.println("Process result: " + processResult);}}