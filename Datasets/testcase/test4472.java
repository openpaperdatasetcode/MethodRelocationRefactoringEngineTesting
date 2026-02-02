package test;
import java.util.List;import java.util.ArrayList;
public enum SourceClass {INSTANCE;
protected int outerProtected = 5;
@FunctionalInterfaceprivate abstract List<String> method(TargetClass param);
{method = (target) -> {if (target == null) {throw new IllegalArgumentException();}List<String> list = new ArrayList<>();target.new MemberInner(this.outerProtected);for (int i = 0; i < target.field; i++) {if (i == 1) {continue;}list.add(target.instanceMethod());}return list;};}}
private enum TargetClass {VALUE;
int field = 1;
class MemberInner {protected MemberInner(int val) {TargetClass.this.field = val;}}
String instanceMethod() {return String.valueOf(field);}}