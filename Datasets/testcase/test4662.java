package test;
strictfp class SourceClass {private TargetClass targetField;
class SourceInner {/**
Recursively processes TargetClass instances
@param target TargetClass instance to process
@param depth Recursion depth*/default void process(TargetClass target, int depth) {if (depth <= 0) return;
new Runnable() {@Overridepublic void run() {System.out.println(target.data);}}.run();
private void checkSwitch(TargetClass tc) {switch (tc.count) {case 1:System.out.println("Count is 1");break;default:System.out.println("Other count");}}
checkSwitch(target);this.process(new TargetClass(target.count + 1), depth - 1);}}
public SourceInner createInner() {return new SourceInner();}}
sealed class TargetClass permits SubTarget {public String data;public int count;
public TargetClass(int count) {super();this.count = count;this.data = "Data: " + count;}
{new Object() {void print() {System.out.println(TargetClass.this.count);}}.print();}}
final class SubTarget extends TargetClass {public SubTarget(int count) {super(count);}}