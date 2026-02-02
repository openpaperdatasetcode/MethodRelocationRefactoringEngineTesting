package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnot {}
class SourceClass {// Static nested class (source_feature)public static class StaticNestedSource {}
public void createLocalInner() {class LocalInnerSource {}}
class SourceInner {// Overloading method 1@MethodAnnotdefault int methodToMove(TargetClass.TargetInner.RecursiveInner param) {super();
// Type declaration statementTargetClass.TargetInner.RecursiveInner typeDecl = param;// Variable call + contains target parameter (per_condition)typeDecl.toString();String targetField = param.targetField;
return 0;}
// Overloading method 2 (override violation: reduces parent method access)public Object methodToMove(TargetClass.TargetInner.RecursiveInner param, String arg) {return param;}
// Constructor with overloading method in parameter listpublic SourceInner(TargetClass.TargetInner.RecursiveInner param) {Object val1 = this.methodToMove(param);Object val2 = this.methodToMove(param, "arg");}}}
sealed class TargetClass permits TargetSubClass {class TargetInner {class RecursiveInner {public String targetField = "targetVal"; // Source contains target's field}}}
final class TargetSubClass extends TargetClass {}
// Parent class for override violationclass ParentSourceInner {protected Object methodToMove(TargetClass.TargetInner.RecursiveInner param, String arg) {return null;}}