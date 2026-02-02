package test;
import java.io.IOException;import java.util.List;
interface MyInterface<T> {void process(T data);}
class ParentClass<T> {protected T parentField;
public ParentClass(T field) {this.parentField = field;}}
protected class SourceClass<T> {protected T outerField;
public SourceClass(T field) {this.outerField = field;}
static class StaticNestedOne {}static class StaticNestedTwo {}
void varargsMethod(TargetClass<String>... targets) throws IOException {// Constructor invocation of target classTargetClass<String> newTarget = new TargetClass<>("initial", List.of("a", "b"));
// Super constructor invocation in anonymous subclassParentClass<T> parent = new ParentClass<>(outerField) {};
// Variable call using target fieldsfor (TargetClass<String> target : targets) {System.out.println("Target data: " + target.data);System.out.println("Target list size: " + target.list.size());}
// Uses outer this (access outer class instance from nested context)StaticNestedOne nested = new StaticNestedOne() {void printOuterField() {System.out.println("Outer field via outer this: " + SourceClass.this.outerField);}};nested.printOuterField();}}
protected class TargetClass<T> extends ParentClass<T> implements MyInterface<T> {T data;List<T> list;
public TargetClass(T data, List<T> list) {super(data);this.data = data;this.list = list;
// Anonymous inner class in targetMyInterface<T> processor = new MyInterface<>() {@Overridepublic void process(T item) {System.out.println("Processing: " + item);}};processor.process(data);}
@Overridepublic void process(T data) {System.out.println("Target processing: " + data);}}