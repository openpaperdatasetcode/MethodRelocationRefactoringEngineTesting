package test;
import java.util.List;import java.util.ArrayList;
interface SomeInterface {}
record SourceRecord(int field) implements SomeInterface {private static int staticField;class MemberInner {}
private int methodToMove(TargetRecord... targets) throws Exception {// Type declaration statementRawType raw;// Empty statement;// PrefixExpressionint num = +1;// Super keywordssuper.toString();// Variable callSourceRecord.staticField = 0;// Anonymous inner classSomeInterface anon = new SomeInterface() {};// ConstructorInvocation with target featuresTargetRecord tr = new TargetRecord(new int[0]);tr.data[0] = 1;// Instance method feature in annotation (simulated)List<String> list = getList();return num;}
// Overload existsprivate int methodToMove(String s) { return 0; }
// Instance method for annotation attributedefault List<String> getList() {return new ArrayList<>();}}
public record TargetRecord(int[] data) {{// Call method in instance code blockParentClass::protectedMethod;}
private TargetRecord() {this(new int[0]);}
class LocalInner {}}
class ParentClass {protected String protectedMethod() {return "";}}
class RawType {}