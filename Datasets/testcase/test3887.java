package test;
import java.util.List;import java.util.ArrayList;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface RefactorAnnotation {}
record SourceRecord(String value) {// Static nested class (source feature)static class SourceStaticNested {}
// Member inner class (source feature)class SourceInner {}
@RefactorAnnotation // has_annotation (method feature)public List<String> recursiveMethod(TargetRecord targetParam, int depth) {// Base case for recursionif (depth <= 0) {return new ArrayList<>();}
// DoStatement (method feature)int count = 0;do {if (targetParam.nested.field == 1) { // obj.field and 1 (target_feature)count++;}} while (count < 2);
// Synchronized statement (method feature)synchronized (this) {variableCall(); // variable call (method feature)}
// Recursive call (method type)List<String> result = recursiveMethod(targetParam, depth - 1);result.add(targetParam.value());
// Return statement (method feature)return result;}
private void variableCall() {}}
record TargetRecord(String value) {// Static nested class (target feature)static class Nested {int field; // Target field for "obj.field"}
// Nested instance for access in methodNested nested = new Nested();}