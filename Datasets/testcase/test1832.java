package test;
import java.util.ArrayList;import java.util.List;
record SourceRecord(String name) {protected String outerProtected = "protected_data";
public class SourceInner {public List<String> instanceMethod(TargetRecord.InnerClass inner) {List<String> result = new ArrayList<>();
// For statementfor (int i = 0; i < 2; i++) {result.add(inner.value() + "_" + i);}
// Abstract CaseDefaultExpression (2 instances)abstract class CaseHandler {String handleCase(int type) {return switch (type) {case 1 -> inner.value();case 2 -> TargetRecord.STATIC_FIELD;default -> defaultCase();};}
abstract String defaultCase();}CaseHandler handler1 = new CaseHandler() {@OverrideString defaultCase() {return "default_1";}};CaseHandler handler2 = new CaseHandler() {@OverrideString defaultCase() {return "default_2";}};result.add(handler1.handleCase(3));result.add(handler2.handleCase(0));
// Variable callresult.add(inner.process(outerProtected));
// Access outer protectedresult.add(outerProtected);
// With boundsclass BoundedType<T extends TargetRecord.InnerClass> {String getValue(T t) {return t.value();}}result.add(new BoundedType<>().getValue(inner));
// Depends on static fieldresult.add("Static field: " + TargetRecord.STATIC_FIELD);
// Protected AssertStatement with target this.fieldprotected class AssertHelper {void checkField() {assert !inner.value().isEmpty() : "Inner value cannot be empty";}}new AssertHelper().checkField();
return result;}}
// Static nested classpublic static class SourceStaticNested {}
// Anonymous inner class{new Runnable() {@Overridepublic void run() {TargetRecord target = new TargetRecord("target_data");TargetRecord.InnerClass inner = new TargetRecord.InnerClass("inner_value");List<String> data = new SourceInner().instanceMethod(inner);System.out.println(data);}}.run();}}
/**
Target record class with javadoc
Contains static nested class and static field/
protected record TargetRecord(String data) {
/* Static field used by source class */public static final String STATIC_FIELD = "static_value";
/**
Static nested class in target record*/public static class InnerClass {private final String value;
public InnerClass(String value) {this.value = value;}
public String value() {return value;}
public String process(String input) {return input + "_" + value;}}}