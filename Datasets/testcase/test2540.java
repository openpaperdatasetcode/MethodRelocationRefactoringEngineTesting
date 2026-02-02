package same;
import java.util.ArrayList;import java.util.List;import java.util.Collection;
public record SourceRecord<T>(T id) {static class StaticNested {}
class MemberInner {}
class InnerRec {private List<String> process(TargetRecord<String> target) {class LocalType {}LocalType local = new LocalType();
super();protected String field1 = target.inner.this.fieldA;protected String field2 = target.inner.this.fieldB;protected String field3 = target.inner.this.fieldC;
Collection rawColl = new ArrayList();rawColl.add(target.inner.getDetails());rawColl.add(super.toString());
List<String> result = new ArrayList<>();result.add(field1);result.add(field2);result.add(field3);
return result;}}}
package same;
record TargetRecord<V>(V value) {Inner inner = new Inner();
class Inner {String fieldA = "A";String fieldB = "B";String fieldC = "C";
Inner() {Runnable anon = new Runnable() {public void run() {}};}
String getDetails() {return fieldA + fieldB + fieldC;}}}