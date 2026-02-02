package same.pkg;
import java.util.ArrayList;import java.util.List;
// Source class: public modifier, with local inner class and static nested classpublic class SourceClass {protected String outerProtectedField = "protectedValue";
// Static nested class (source_feature)static class SourceStaticNested {// Overloading method for inner class callfinal TargetClass overloadedMethod(TargetClass target) {return target;}
final TargetClass overloadedMethod(TargetClass target, String suffix) {return target;}}
// First level inner classclass SourceInner {// Inner-rec class (source_inner_rec position)class SourceInnerRec {/**
Varargs method to process target and string arguments
@param targetParam TargetClass instance (per_condition)
@param args Variable arguments
@return List of processed strings*/private List<String> varargsMethod(TargetClass targetParam, String... args) {variableCall(targetParam);access_outer_protected();
List<String> result = new ArrayList<>();for (String arg : args) {result.add(arg);}
// Local inner class (source_feature)class LocalProcessor {void process(TargetClass target) {// Overloading method call in if/else conditionsif (args.length > 0) {TargetClass processed = SourceStaticNested.class.cast(new SourceStaticNested()).overloadedMethod(target);result.add(processed.baseField);} else {TargetClass processed = new SourceStaticNested().overloadedMethod(target, "default");result.add(processed.baseField);}}}new LocalProcessor().process(targetParam);
return result;}
private void variableCall(TargetClass param) {String localVar = param.baseField;}
private void access_outer_protected() {result.add(SourceClass.this.outerProtectedField);}}}}
// Target class: default modifier, extends ParentClass, with local inner class (target_feature)class TargetClass extends ParentClass {String baseField = "targetBase";
@Overridevoid parentMethod() {// Local inner class (target_feature)class TargetLocalInner {String getField() {return baseField;}}new TargetLocalInner().getField();}}
// Parent class for target's extends featureclass ParentClass {void parentMethod() {}}