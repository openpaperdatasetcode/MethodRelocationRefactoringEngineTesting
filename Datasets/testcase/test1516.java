package test;
import java.util.ArrayList;import java.util.List;
public class Target<T, U> {public T field1;public U field2;public String field3;
public Target(T f1, U f2, String f3) {this.field1 = f1;this.field2 = f2;this.field3 = f3;}
static class Nested<V> {public V nestedField;
public Nested(V value) {this.nestedField = value;}
class InnerRec {private String recData;
public InnerRec(String data) {this.recData = data;}
public String getRecData() {return recData;}}}}
class Source<T> {private String outerPrivate = "outer_private";
static class FirstStaticNested {// Static nested class feature}
static class SecondStaticNested {// Second static nested class feature}
private List<String> process(Target<T, Integer>.Nested<String>.InnerRec targetRec) {List<String> result = new ArrayList<>();
// Constructor invocationTarget<T, Integer> target = new Target<>(null, 0, "init");Target.Nested<String> nested = new Target.Nested<>("nested_data");Target.Nested<String>.InnerRec newRec = nested.new InnerRec("new_rec");
// Variable callresult.add(targetRec.getRecData());
// Access outer private fieldresult.add(outerPrivate);
// Access instance field (target's fields)result.add(target.field3);result.add(String.valueOf(target.field2));
// Depends on inner class (uses Target's nested InnerRec)result.add(newRec.getRecData());
// EmptyStatement with 3 target this.fields; // Empty statementresult.add((String) target.field1); // this.field1 (from Target)result.add(String.valueOf(target.field2)); // this.field2 (from Target)result.add(target.field3); // this.field3 (from Target)
// No new exceptionreturn result;}}