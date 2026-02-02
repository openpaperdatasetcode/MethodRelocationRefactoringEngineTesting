package test.same;
import java.util.ArrayList;import java.util.List;
public class SourceClass {/**
Retrieves list of strings from target inner class fields
@return List<String> containing field values*/strictfp List<String> instanceMethod(TargetClass target) {List<String> result = new ArrayList<>();TargetClass.MemberInner inner = target.new MemberInner();Object var = inner.targetField;
class LocalInner {int process() {return protectedMethod();}}LocalInner local = new LocalInner();
for (int i = 0; i < local.process(); i++) {result.add(inner.targetField.toString());}
Runnable anon = new Runnable() {public void run() {new Object() {{var = protectedMethod();}};}};
return result;}
protected int protectedMethod() {return super.hashCode();}}
public class TargetClass {class MemberInner {String targetField;}}