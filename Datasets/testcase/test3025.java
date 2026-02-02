import java.util.List;import java.util.ArrayList;
private class SourceClass {class MemberInner {private String getString() {return "test";}}
TargetClass.Inner process(TargetClass target) {MemberInner member = new MemberInner();List<? extends CharSequence> boundedList = new ArrayList<>();TargetClass.Inner targetInner = null;
try {for (int i = 0; i < 5; i++) {targetInner = target.createInner();boundedList.add(targetInner.getValue());}
do {String val = member::getString;assert targetInner != null;} while (false);} catch (NullPointerException e) {}
abstract class AbstractLocal {abstract void checkNull();}new AbstractLocal() {void checkNull() {if (targetInner == null) {}}}.checkNull();
return targetInner;}}
public class TargetClass {class Inner {String getValue() {class LocalInner {String data = "local";}return new LocalInner().data;}}
Inner createInner() {return new Inner();}}