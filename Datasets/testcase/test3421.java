package test;
@ClassAnnotationpublic abstract class SourceClass<T> implements Runnable {// Member inner class (source feature)public class SourceMemberInner {public Object innerVarargsMethod(Object... params) {return params.length;}}
@Overridepublic void run() {}
// Overloading method 1 (no parameters)int overloadedMethod() {return 0;}
// Overloading method 2 (with target parameter, per condition)int overloadedMethod(TargetClass<T> targetParam) {SourceMemberInner inner = new SourceMemberInner();int count = 0;
// Do statementdo {// Constructor invocation: target's inner recursive classTargetClass<T>.TargetInnerRec innerRec = targetParam.new TargetInnerRec();
// Synchronized statementsynchronized (innerRec) {// Variable call + depends_on_inner_classinnerRec.variableCall();SourceClass.this.helperMethod(); // uses_outer_this
// Varargs method feature (1, inner_class, varargs, ClassName.methodName(arguments), pos: Lambda expressions)Runnable lambda = () -> inner.innerVarargsMethod("param");lambda.run();
// Empty statement;}
count++;// Break statementif (count == 1) break;} while (count < 2);
return count;}
private void helperMethod() {}
// Local inner class (source feature)public void createLocalInner() {class SourceLocalInner {public void innerMethod() {}}new SourceLocalInner().innerMethod();}}
// Target parent class (for target's extends feature)abstract class TargetParentClass {}
// Target class (public abstract, extends TargetParentClass + member inner class)public abstract class TargetClass extends TargetParentClass {
// Target inner recursive class (target_inner_rec)
public class TargetInnerRec {
public void variableCall() {}
}
}
// Annotation for has_annotation feature@interface ClassAnnotation {}