package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnn {}
class ParentTarget {}
abstract class SourceClass {class FirstInner {}
class SourceInner {@MyAnnint instanceMethod(TargetClass target) {super();
private void privateForLoop() {for (int i = 0; i < target.this.field; i++) {if (i == 1) break;}}privateForLoop();
variableCall = target.field;return variableCall;}
int variableCall;}}
class TargetClass extends ParentTarget {int field;
{new Runnable() {};}}
