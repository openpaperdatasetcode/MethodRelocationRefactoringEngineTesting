package test;
import java.util.List;
public class SourceClass {class MemberInner {class RecursiveInner {TargetClass process(TargetClass target) {TargetClass newTarget = new TargetClass();int count = 0;
try {while (count < 5) {target.instanceField = count;target.inner.method();count++;}} finally {super.toString();}
new Runnable() {@Overridepublic void run() {}};
return newTarget;}}}}
strictfp class TargetClass {int instanceField;Inner inner = new Inner();
class Inner {void method() {}}}