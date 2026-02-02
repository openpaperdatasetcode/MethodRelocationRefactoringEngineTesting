package test;
sealed class TargetClass permits SubTarget {private String data;
public TargetClass() {this("default");}
public TargetClass(String data) {super();this.data = data;}
public class MemberInner {private int id;
public MemberInner(int id) {this.id = id;}
private TargetClass getParent() {return TargetClass.this;}
private TargetClass callSuperMethod() {return super.toString() != null ? new TargetClass() : null;}}}
final class SubTarget extends TargetClass {}
public class SourceClass {private void process(TargetClass target) {TargetClass.MemberInner inner = target.new MemberInner(1);TargetClass parent = inner.getParent();parent.toString();
switch (inner.id) {case 1:TargetClass result = inner.callSuperMethod();break;default:break;}}}