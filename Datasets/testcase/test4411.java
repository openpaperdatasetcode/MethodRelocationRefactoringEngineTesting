package test;
import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface RefactorAnnotation {}
protected class Source<T> {class Inner {class InnerRec {@RefactorAnnotation@RefactorAnnotationprotected Object varargsMethod(Target<String>.Inner targetInner, T... params) {int i = 0;while (i < params.length) {variableCall(targetInner);i++;}
DependedInner depended = new DependedInner();return depended.useTargetInner(targetInner);}
private void variableCall(Target<String>.Inner targetInner) {String typeParamVal = targetInner.typeParamField;targetInner.createAnonymous();}
class DependedInner {Object useTargetInner(Target<String>.Inner targetInner) {return targetInner.typeParamField;}}}}
void methodWithLocalInner() {class LocalInner {void localMethod(Target<Integer>.Inner targetInner) {Inner inner = new Inner();InnerRec innerRec = inner.new InnerRec();innerRec.varargsMethod(targetInner, 1, 2);}}LocalInner local = new LocalInner();}}
class Target {
class Inner {
U typeParamField;
void createAnonymous() {Runnable r = new Runnable() {@Overridepublic void run() {System.out.println(typeParamField);}};}}}
