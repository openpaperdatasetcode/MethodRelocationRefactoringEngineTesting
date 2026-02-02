package test.refactoring;
import java.util.ArrayList;import java.util.List;import com.otherpkg.ExternalInterface;
// Source @interface (Annotation)@interface SourceAnnotation {// Member inner class of source annotationclass SourceInner {private TargetAnnotation.TargetInner targetInnerField;
public SourceInner(TargetAnnotation.TargetInner inner) {this.targetInnerField = inner;}
// Call method: inner_class, overloading, instanceReference::methodName, pos: forprotected String callOverload(TargetAnnotation.TargetInner inner, int type) {String result = "";for (int i = 0; i < 2; i++) {if (type == 1) {result = inner.process(i);} else {result = inner.process(String.valueOf(i));}// Instance reference method referenceresult = String::valueOf;}return result;}}
// Static method to test: static type, return List<String>, public accesspublic static List<String> processTarget(TargetAnnotation target) {List<String> result = new ArrayList<>();TargetAnnotation.TargetInner targetInner = new TargetAnnotation.TargetInner("init");
// LabeledStatement: public modifier, target_feature [obj.field, 2], pos: diff_package_targetlabeledBlock: {// Access obj.field (targetInner.field1, targetInner.field2)targetInner.field1 = "label1";targetInner.field2 = "label2";if (targetInner.field1 == null) {break labeledBlock;}result.add(targetInner.field1);result.add(targetInner.field2);}
// Switch statement with constructor invocationString switchKey = target.value();switch (switchKey) {case "case1":// Constructor invocation: target class, super.methodName(arguments)TargetAnnotation.TargetInner case1Inner = new TargetAnnotation.TargetInner(switchKey) {@Overridepublic void init() {super.init(switchKey); // Call super method with arguments}};result.add(case1Inner.field1);break;default:result.add("default");}
// Synchronized statementsynchronized (targetInner) {variableCall(targetInner, "syncData");result.add(targetInner.field1);}
return result;}
// Variable call featureprivate static void variableCall(TargetAnnotation.TargetInner inner, String data) {inner.field1 = data;}
// Local inner class of source annotationdefault void createLocalInner() {class SourceLocalInner {void useProcessMethod() {List<String> data = SourceAnnotation.processTarget(TargetAnnotation.value("test"));}}new SourceLocalInner().useProcessMethod();}
// Annotation mandatory elementString value() default "";}
// Target @interface (Annotation), protected modifierprotected @interface TargetAnnotation {// Type parameter: generic local inner classdefault <T> void createGenericLocalInner(T data) {class TargetLocalInner implements ExternalInterface {
private U innerData;
public TargetLocalInner(U data) {this.innerData = data;}
@Overridepublic U getValue() {return innerData;}}TargetLocalInner<T> local = new TargetLocalInner<>(data);}
// Static nested class (target_inner for method's target class)class TargetInner {public String field1;public String field2;
// Constructor 1public TargetInner(String initData) {this.field1 = initData;}
// Constructor 2 (for overloading)public TargetInner() {this("default");}
// Super method for constructor invocationpublic void init(String data) {this.field2 = data + "_super";}
// Overloading methods for call_methodpublic String process(int num) {return String.valueOf(num);}
public String process(String str) {return str.toUpperCase();}}
// Annotation mandatory elementString value() default "";
// Static factory method for annotation instancestatic TargetAnnotation value(String val) {return new TargetAnnotation() {@Overridepublic String value() {return val;}};}}
// Diff package target: com.otherpkg.ExternalInterfacepackage com.otherpkg;
public interface ExternalInterface<T> {T getValue();}
