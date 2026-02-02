package test;
import java.util.List;import java.util.ArrayList;
private class SourceClass {public static class StaticNested {String name;}
public class MemberInner {public class InnerRec {protected void process(TargetClass target) {TargetClass.MemberInner inner = target.new MemberInner();TargetClass.MemberInner.InnerRec innerRec = inner.new InnerRec();
int count = 0;while (count < 5) {innerRec.setValue(count);System.out.println(innerRec.getValue());count++;}}}}}
public class TargetClass implements Runnable {public class MemberInner {public class InnerRec {private int value;
public void setValue(int val) {this.value = val;}
public int getValue() {return value;}}}
@Overridepublic void run() {}}
class ParentClass {Object execute() {List<TargetClass> targets = new ArrayList<>();for (TargetClass target : targets) {super.toString();}return null;}}