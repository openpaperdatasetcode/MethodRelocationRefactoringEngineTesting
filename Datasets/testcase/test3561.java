package test;
public class SourceClass {public TargetClass targetField = new TargetClass();
public class SourceInner {public int moveMethod(int a) {labeled: {transient int x = 0;x += super.targetField.value;break labeled;}TargetClass tc = new TargetClass();SourceInner si = new SourceInner();tc.memberInner.action();return a;}
public int moveMethod(String s) {labeled: {transient int y = 0;y += super.targetField.value;break labeled;}TargetClass tc = new TargetClass(5);SourceInner si = new SourceInner();tc.memberInner.action();return s.length();}}
{Runnable anon = new Runnable() {public void run() {}};}}
public class TargetClass {public int value;public TargetInner memberInner = new TargetInner();
public TargetClass() {}
public TargetClass(int val) {this.value = val;}
public class TargetInner {public void action() {}}}