package test.refactoring.movemethod;
import java.io.IOException;import java.util.List;import java.util.ArrayList;
// Marker annotation for method (has_annotation)@interface RefactorAnnot {}
// Others class for method call (method_feature: others_class)class OthersClass {public List<String> provideData() {List<String> data = new ArrayList<>();data.add("test_data");return data;}}
// Source normal class (private modifier, same package)private class SourceClass {// Target class field (per_condition: source contains target's field)private TargetClass targetField = new TargetClass();
// Static nested class (source_class feature)static class SourceStaticNested {}
// Anonymous inner class (source_class feature)Runnable sourceAnonymous = new Runnable() {@Overridepublic void run() {new SourceStaticNested();}};
// Member inner record (method_position: source_inner_rec)record SourceInnerRec(String id) {// Final instance method to be refactored (method.type: instance, access_modifier: final)@RefactorAnnot // has_annotation (method.features)final TargetClass refactorMethod() throws IOException { // return_type: TargetClas Type, IOException (method.features)// Variable call (method.features)String innerId = this.id();TargetClass.TargetStaticNested targetNested = targetField.new TargetStaticNested();int varCall = targetNested.getCount() + innerId.length();
// Public normal method call in instance code blocks (method.features){List<String> data = new OthersClass().provideData(); // new ClassName().method()varCall += data.size();}
// No new exception (method.features)if (varCall < 0) {throw new IOException("Invalid count"); // IOException}
targetNested.updateCount(varCall);return targetField;}}}
// Target normal class (default modifier, same package)class TargetClass {// Static nested class (target_feature: static nested class)static class TargetStaticNested {private int count = 0;
public int getCount() {return count;}
public void updateCount(int value) {this.count = value;}}
// Target inner class to receive moved method (target class: target_inner)class TargetInner {}
// Factory method for static nested class instancepublic TargetStaticNested new TargetStaticNested() {return new TargetStaticNested();}}