package test;
import otherpackage.ExternalObj;import java.util.List;import java.util.ArrayList;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
record SourceRecord(int id) {static class StaticNested {class SourceInner {@MyAnnotationpublic Object methodToMove(TargetRecord target) {ExternalObj ext = new ExternalObj();ext.field1 = "val1";ext.field2 = "val2";;
Label: {List<String> list = new ArrayList<>();list = target.nested().accessorMethod(1);if (list.isEmpty()) break Label;}
String expr = "test";target.variableCall();target.accessInstanceMethod();
return new Object();}
public Object methodToMove(TargetRecord target, String arg) {return null;}}}
void createAnonymous() {Runnable r = new Runnable() {public void run() {}};}}
abstract record TargetRecord(String data) {static class Nested {class InnerRecursive {List<String> accessorMethod(int param) {return new ArrayList<>();}}
InnerRecursive nested() {return new InnerRecursive();}}
Nested nested() {return new Nested();}
void variableCall() {}
void accessInstanceMethod() {}}
package otherpackage;
public class ExternalObj {public String field1;public String field2;}