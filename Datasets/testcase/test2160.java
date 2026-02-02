package test;
import java.util.function.Runnable;
public abstract class SourceClass {class MemberInner {}
Runnable anonymous = new Runnable() {public void run() {}};
void methodToMove(TargetClass... targets) {for (TargetClass target : targets) {// Constructor invocationTargetClass.SubClass sub = new TargetClass.SubClass(target.field);
// Expression statementtarget.counter++;
// Variable calltarget.variableCall();
// Assert statementassert target.field != null : "Field must not be null";}}}
abstract class TargetClass extends SuperClass {Object field;int counter;
class SubClass {SubClass(Object val) {this.val = val;}Object val;}
void variableCall() {Runnable r = new Runnable() {public void run() {}};}}
class SuperClass {}