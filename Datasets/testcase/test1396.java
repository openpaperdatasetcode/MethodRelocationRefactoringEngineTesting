package test;
import java.util.List;import java.util.ArrayList;import java.io.IOException;
sealed record SourceRecord(String data) permits ExtendedSourceRecord {// Static nested class (source feature)static class StaticNested {}
// Anonymous inner class (source feature)Runnable anon = new Runnable() {@Overridepublic void run() {}};
// Static field for depends_on_static_fieldprivate static final String STATIC_FIELD = "static_dep";
private List<String> methodToMove() throws IOException { // requires_throws// Source contains target's field (per_condition)TargetRecord target = new TargetRecord("target_data");Object targetFieldVal = target.targetField;
// expression statementString expr = data + STATIC_FIELD;
// variable callStaticNested nested = new StaticNested();target.localInnerAction();
// with_boundsclass BoundedType<T extends Number> {T value;}BoundedType<Integer> bounded = new BoundedType<>();bounded.value = 3;
// depends_on_static_fieldString staticDep = STATIC_FIELD;
// ConstructorInvocation (protected, super.field, 3, pos:same_package_target)SuperClass superObj = new SuperClass(3);int superFieldVal = superObj.superField;
return new ArrayList<>();}}
non-sealed record ExtendedSourceRecord(String data) extends SourceRecord {public ExtendedSourceRecord(String data) {super(data);}}
record TargetRecord(String value) {// Target field (per_condition)public String targetField = "target_field";
// Local inner class (target feature)public void localInnerAction() {class LocalInner {void useTargetField() {System.out.println(targetField);}}new LocalInner().useTargetField();}}
class SuperClass {protected int superField;
// Protected constructor (ConstructorInvocation target)protected SuperClass(int num) {this.superField = num; // super.field, 3}}