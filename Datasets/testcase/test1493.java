package test;
import java.util.ArrayList;import java.util.List;
public class Source {private String outerPrivateField = "outer_private";
class MemberInner {String getInnerData() {return outerPrivateField + "_inner";}}
private List<String> process(Target target) {List<String> result = new ArrayList<>();MemberInner inner = new MemberInner();
// Access outer private fieldresult.add(outerPrivateField);
// Local inner classclass LocalProcessor {void validate(Target t) {// ThrowStatement with 3 target fieldsif (t.field1 == null) {throw new IllegalArgumentException("field1 is null: " + t.field1);}if (t.field2.isEmpty()) {throw new IllegalArgumentException("field2 is empty: " + t.field2);}if (t.field3 < 0) {throw new IllegalArgumentException("field3 is negative: " + t.field3);}}}
new LocalProcessor().validate(target);result.add(target.field2);result.add(inner.getInnerData());
return result;}}
protected class Target {String field1;String field2;int field3;}