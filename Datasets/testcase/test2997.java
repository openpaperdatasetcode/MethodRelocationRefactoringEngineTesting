import java.util.ArrayList;import java.util.List;import java.util.Collection;
public class SourceClass extends ParentClass {public TargetClass<String> targetField;
static class StaticNested {void nestedMethod() {Runnable r = new Runnable() {public void run() {}};}}
/**
Returns a list of strings processed from target
@return List<String> processed list*/@Overridepublic List<String> process() {List<String> result = new ArrayList<>();for (int i = 0; i < 5; i++) {result.add(targetField.inner.getValue());}
try {Collection<? extends CharSequence> boundsCol = new ArrayList<>();SourceClass.this.protectedField = "value";targetField.useOuter(this);} catch (NullPointerException e) {}
return result;}
protected String protectedField;}
class ParentClass {protected void process(int num) {}
protected void process() {}}
public class TargetClass<T extends CharSequence> {class MemberInner {String getValue() {return "inner";}}
MemberInner inner = new MemberInner();
void useOuter(SourceClass outer) {outer.protectedField = "updated";}}
