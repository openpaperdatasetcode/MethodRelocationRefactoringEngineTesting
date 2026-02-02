package test;
interface Action {void execute();}
class ParentClass {protected String value;
public ParentClass(String value) {this.value = value;}}
public class SourceClass extends ParentClass implements Action {public SourceClass(String value) {super(value);}
@Overridepublic void execute() {}
// Overloading methodsprivate int process(TargetClass target) {return process(target, 0);}
private int process(TargetClass target, int offset) {// Type declaration statementSubTarget subTarget = new SubTarget("sub_target");
// Super constructor invocation in subclassclass SubTarget extends TargetClass {public SubTarget(String data) {super(data);}}
// Protected normal method from sub_class in do-while (1 occurrence)int count = 0;do {TargetClass processed = subTarget.formatData(target);count++;} while (count < 3);
// Lambda expression: () -> System.out.println(this.value)Runnable printer = () -> System.out.println(this.value);printer.run();
// Variable call - access target's fieldreturn target.data.length() + offset;}}
abstract class TargetClass extends ParentClass implements Action {public String data;
public TargetClass(String data) {super(data);this.data = data;}
// Protected normal method for sub_class implementationprotected TargetClass formatData(TargetClass target) {target.data = target.data.toUpperCase();return target;}}