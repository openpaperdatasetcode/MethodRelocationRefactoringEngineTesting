package test;
import java.util.ArrayList;
public record SourceClass(String data) {class MemberInner {void useTarget(TargetClass target) {System.out.println(target.value());}}
public TargetClass varargsMethod(TargetClass... targets) {class LocalInner {TargetClass getFirstTarget(TargetClass[] ts) {return ts[0];}}
LocalInner local = new LocalInner();TargetClass first = local.getFirstTarget(targets);String varCall = first.value();
ArrayList rawList = new ArrayList();rawList.add(first);
static TargetClass returnTarget() {return TargetClass.STATIC_TARGET;}
return returnTarget();}}
record TargetClass(String value) {static final TargetClass STATIC_TARGET = new TargetClass("static");}
class OthersClass {private Object callMethod(SourceClass source, TargetClass... targets) {TargetClass[] array = {new TargetClass("a"), new TargetClass("b")};return ((SourceClass) source).varargsMethod(array);}}