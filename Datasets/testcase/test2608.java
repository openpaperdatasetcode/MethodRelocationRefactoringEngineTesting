package test.same;
import java.io.IOException;
protected class SourceClass {public int varargsMethod(TargetClass... targets) throws IOException {class LocalInner {private int val = targets[0].nested.field;}LocalInner local = new LocalInner();Object var = local.val;
switch (3) {case 3:new SourceClass(3);break;}
TargetClass.StaticNested nestedObj = new TargetClass.StaticNested();return (int) var;}
public SourceClass(int num) {super();}
Runnable anon = new Runnable() {public void run() {}};}
abstract class TargetClass {StaticNested nested = new StaticNested();
static class StaticNested {int field;}}