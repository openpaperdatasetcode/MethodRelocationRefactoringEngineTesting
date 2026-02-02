package test;
import java.util.List;import java.util.ArrayList;
class ParentClass {public void process(TargetClass target) {}}
private class SourceClass extends ParentClass {private String sourcePrivateField = "private_outer_data";
public class MemberInner extends ParentClass {@Overridepublic void process(TargetClass target) {class LocalInner {void useOuterPrivate() {System.out.println(SourceClass.this.sourcePrivateField);}}
LocalInner local = new LocalInner();local.useOuterPrivate();
try {TargetClass.InnerRecursive innerRec = target.new InnerRecursive();List<String> result = innerRec.m1().m2().m3();System.out.println("Processed list size: " + result.size());} catch (Exception e) {e.printStackTrace();}
TargetClass.AnonymousHost host = new TargetClass.AnonymousHost();host.createAnonymous();}}}
public class TargetClass {public class InnerRecursive {public InnerRecursive m1() {return this;}
public InnerRecursive m2() {return this;}
protected List<String> m3() {return new ArrayList<>(List.of("rec_item1", "rec_item2"));}}
public class AnonymousHost {public void createAnonymous() {Runnable anonTask = new Runnable() {@Overridepublic void run() {System.out.println("anonymous_inner_executed");}};anonTask.run();}}}