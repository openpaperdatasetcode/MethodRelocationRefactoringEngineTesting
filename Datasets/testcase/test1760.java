package test;
class SuperSource {protected String protectedField = "protected_value";}
public class Source<T> extends SuperSource {T x;
static class StaticNested {int id;}
class MemberInner {T value;}
protected void process(FinalTarget target) {FinalTarget.InnerTarget inner = target.new InnerTarget();MemberInner sourceInner = new MemberInner();sourceInner.value = Source.this.x;
do {inner.setValue(sourceInner.value.toString() + protectedField);System.out.println(inner.getValue());} while (inner.getCount() < 3);}
protected void process(String str) {System.out.println(str);}}
final class FinalTarget {int count;
class InnerTarget {private String value;
void setValue(String val) {this.value = val;count++;}
String getValue() {return value;}
int getCount() {class LocalCounter {int get() {return count;}}return new LocalCounter().get();}}}