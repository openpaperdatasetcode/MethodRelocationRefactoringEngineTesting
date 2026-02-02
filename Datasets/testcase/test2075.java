package test;
import java.io.IOException;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
class OtherClass {protected void lambdaMethod(Runnable runnable) {runnable.run();}}
public class SourceClass {/**
Final instance method with overloading and exception handling
@param targetParam target class parameter
@return Object instance
@throws IOException if error occurs*/@MyAnnotationpublic final Object methodToMove(TargetClass targetParam) throws IOException {new SuperClass();
int i = 0;do {try {TargetClass result = TargetClass.overloadedMethod(i);i++;} catch (Exception e) {throw new IOException("Error", e);}} while (i < 3);
targetParam.variableCall();
int num = 2;switch (num) {case 1:new OtherClass().lambdaMethod(() -> System.out.println("Case 1"));break;case 2:new OtherClass().lambdaMethod(() -> System.out.println("Case 2"));break;}
return new Object();}}
class SuperClass {public SuperClass() {}}
protected class TargetClass {class MemberInner {}
static TargetClass overloadedMethod(int param) {return new TargetClass();}
void variableCall() {}}