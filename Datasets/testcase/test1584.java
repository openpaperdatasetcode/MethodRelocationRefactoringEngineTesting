package test;
public class SourceClass {public class MemberInner {public class InnerRec {private String data = "inner_rec_data";
public final Object process(TargetClass target) {// Anonymous inner classRunnable task = new Runnable() {@Overridepublic void run() {System.out.println("Processing in anonymous class");}};task.run();
// Do statementint count = 0;do {// Variable call - access target's static nested class fieldTargetClass.StaticNested nested = target.new StaticNested();nested.value = count;
// Check target's field for break conditionif (target.counter == 1) {// Static BreakStatement (labeled)static break loop;}
count++;} while (count < 3);
// otherObject.process(this)target.process(this);
return target.counter;}}}}
public class TargetClass extends ParentClass {public int counter;
public static class StaticNested {public int value;}
public void process(SourceClass.MemberInner.InnerRec innerRec) {// Access source's inner rec fieldSystem.out.println("Processing inner rec data: " + innerRec.data);this.counter++;}}
class ParentClass {}