package sourcepkg;
import java.util.List;import java.util.ArrayList;import targetpkg.TargetAnnotation;
// Source annotation (type: @interface, different package with target, features: anonymous inner class + static nested class)public @interface SourceAnnotation {private String outerPrivateField = "private_data"; // For access_outer_private
// Static nested class (source feature)static class SourceStaticNested {public String nestedField = "nested_field"; // obj.field for AssertStatement}
// Anonymous inner class (source feature)static class AnonymousHolder {static final Runnable ANON_RUNNABLE = new Runnable() {@Overridepublic void run() {}};}
// Accessor method feature (3, source, accessor, instanceReference.methodName(arguments), pos: instance code blocks)default Object accessorFeature(SourceStaticNested nested) {return nested.nestedField;}
// Varargs method (public access modifier, return_type: void)@TargetAnnotation.FeatureAnno // has_annotationvoid varargsMethod(TargetAnnotation... targets); // per_condition
// Inner class for AssertStatement positionclass MethodInnerClass {public void execute(TargetAnnotation target) {// AssertStatement (public, target_feature: obj.field x1, pos: inner class)public SourceStaticNested sourceNested = new SourceStaticNested();assert sourceNested.nestedField != null : "Nested field cannot be null";
// Type declaration statementTargetAnnotation.TargetInnerRec targetInner = TargetAnnotation.TargetInnerRec.INSTANCE;
// Variable calltargetInner.recursiveMethod();String fieldVal = sourceNested.nestedField;
// Access outer privateString privateVal = SourceAnnotation.outerPrivateField;
// Depends on inner classTargetAnnotation.TargetStaticNested targetStaticNested = new TargetAnnotation.TargetStaticNested();targetStaticNested.staticMethod();
// Accessor method feature in instance code blocksSourceAnnotation annoInstance = SourceAnnotation.class.getAnnotation(SourceAnnotation.class);Object featureResult = annoInstance.accessorFeature(sourceNested);Object featureResult2 = annoInstance.accessorFeature(sourceNested);Object featureResult3 = annoInstance.accessorFeature(sourceNested);
// Call method (others_class, protected, instance, super.methodName(arguments), pos: property assignment)OtherClass other = new OtherClass();List<String> callResult = other.instanceMethod(sourceNested, privateVal);}}
// Others class for call_methodclass OtherClass extends ParentOtherClass {protected List<String> instanceMethod(SourceStaticNested nested, String arg) {// Super.methodName(arguments) in property assignmentList<String> result = super.parentMethod(arg, nested.nestedField);return result;}}
// Parent class for call_method's super invocationabstract class ParentOtherClass {protected List<String> parentMethod(String... args) {return new ArrayList<>(List.of(args));}}}
package targetpkg;
import java.util.List;
// Target annotation (type: @interface, modifier: strictfp, target_feature: static nested class)strictfp public @interface TargetAnnotation {String targetField = "target_field"; // obj.field for AssertStatement
// Static nested class (target_feature)static class TargetStaticNested {public void staticMethod() {}}
// Target inner recursive class (target_inner_rec)enum TargetInnerRec {INSTANCE;
public void recursiveMethod() {}}
// Nested annotation for has_annotation@interface FeatureAnno {}}
