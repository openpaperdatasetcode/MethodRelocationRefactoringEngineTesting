package test;
import java.util.ArrayList;import java.util.List;
class SourceClass implements Processable {// First local inner classpublic void firstLocal() {class LocalOne {void process(TargetClass target) {varargsMethod(target);}}new LocalOne().process(new TargetClass());}
// Second local inner classpublic void secondLocal() {class LocalTwo {Object handle(TargetClass target) {return varargsMethod(target, new TargetClass());}}new LocalTwo().process(new TargetClass());}
@MyAnnotationObject varargsMethod(TargetClass... targets) {// Type declaration statementList<Object> results = new ArrayList<>();int count = 0;
// Expression statementcount += targets.length;
// Private VariableDeclarationStatement with super.fieldclass FieldProcessor {private void process(TargetClass target) {int parentField = target.superField;results.add("Parent field: " + parentField);}}new FieldProcessor().process(targets[0]);
// Access instance fieldresults.add(targets[0].instanceField);
// Variable callresults.add(TargetClass.StaticNested.process(targets));
// Exception handling with recursive methodtry {RecursiveHandler handler = new RecursiveHandler();results.addAll(handler.recurse(targets, 0));} catch (IndexOutOfBoundsException e) {results.add("Recursion error");}
// return this;return this;}
// Inner class with recursionpublic class RecursiveHandler {public List<String> recurse(TargetClass[] targets, int index) {List<String> list = new ArrayList<>();if (index >= targets.length) {return list;}list.add(targets[index].toString());list.addAll(SourceClass.this.new RecursiveHandler().recurse(targets, index + 1));return list;}}
@Overridepublic void process() {}}
interface Processable {void process();}
@interface MyAnnotation {}
protected class TargetClass extends TargetParent {public String instanceField = "target_field";
// Static nested classpublic static class StaticNested {public static String process(TargetClass[] targets) {return "Count: " + targets.length;}}}
class TargetParent {protected int superField = 100;}