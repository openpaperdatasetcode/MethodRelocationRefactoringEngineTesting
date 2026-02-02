package test;
final class SourceClass extends ParentClass {@SuppressWarnings("unused")public final void process(TargetClass targetParam) {super();TargetClass.StaticNested targetStatic = new TargetClass.StaticNested();
targetParam.instanceField = 20;targetStatic.staticField = super.parentField;
Runnable anon1 = new Runnable() {@Overridepublic void run() {targetParam.instanceField++;}};anon1.run();
Runnable anon2 = new Runnable() {@Overridepublic void run() {targetStatic.update();}};anon2.run();
targetParam.action(targetStatic::print);
;}}
class ParentClass {protected int parentField = 10;}
class TargetClass {public int instanceField;
public static class StaticNested {public int staticField;
public void update() {staticField *= 2;}
public void print() {System.out.println(staticField);}}
public void action(Runnable task) {task.run();}}