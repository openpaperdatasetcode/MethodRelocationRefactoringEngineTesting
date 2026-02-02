package test;
import java.util.List;import java.util.ArrayList;import java.io.IOException;
protected class TargetClass {String targetField;class TargetInner {} // target_feature: member inner class}
public class SourceClass<T> { // source_feature: type parameterstatic class StaticNested {} // source_feature: static nested class
class SourceInner {private List<String> methodToMove(TargetClass target, int depth) throws IOException {// Variable callString var = target.targetField;List<String> result = new ArrayList<>();
// Constructor invocationTargetClass newTarget = new TargetClass();SourceInner inner = new SourceInner();StaticNested nested = new StaticNested();
// Super constructor invocationsuper();
// Switch statementswitch (depth) {case 0:result.add("base: " + var);break;case 1:result.add("level1: " + var);break;default:result.add("default: " + var);}
// If/else conditions with recursion (method_feature)if (depth > 0) {Object recursiveResult = new SourceInner().methodToMove(target, depth - 1); // 1 as requiredresult.add(recursiveResult.toString());}
// IOException (checked exception declaration)if (var == null) {throw new IOException("Target field is null");}
// Source_feature: local inner classclass LocalInner {void addToResult() {result.add("local: " + var);}}new LocalInner().addToResult();
return result;}}}