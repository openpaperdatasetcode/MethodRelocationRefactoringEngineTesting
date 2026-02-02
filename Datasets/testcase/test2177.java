package test;
import java.util.List;import java.util.ArrayList;
abstract class ParentGenericClass<T> {public abstract List<String> process(T target);}
abstract class SourceClass<T> extends ParentGenericClass<TargetClass<T>> {private String outerPrivateField = "private";
class SourceInner {@Overridepublic final List<String> process(TargetClass<T> target) {// Method parameter is generic (TargetClass<T>)T genericParam = target.genericField;
// Type declaration statementTargetClass<T>.MemberInner inner = target.new MemberInner();List rawList = new ArrayList(); // Raw type
// VariableDeclarationStatement with 2 super.fields in inner classclass NestedInner {private int field1 = target.superField1;private String field2 = target.superField2;}new NestedInner();
// Access outer private fieldrawList.add(SourceClass.this.outerPrivateField);
// Access instance fieldrawList.add(inner.innerField);
// Variable callreturn new ArrayList<>(rawList.stream().map(Object::toString).toList());}}}
class ParentOfTarget {int superField1;String superField2;}
public class TargetClass<V> extends ParentOfTarget {V genericField;
class MemberInner {String innerField = "inner";}}