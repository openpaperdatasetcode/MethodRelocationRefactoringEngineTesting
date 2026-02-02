package test.same;
import java.util.List;import java.util.ArrayList;
abstract class SourceClass<T> {class MemberInner {record InnerRec(TargetClass target) {void instanceMethod() {TargetClass.StaticNested.InnerRec rec = new TargetClass.StaticNested.InnerRec();Object var = rec.targetField;
class TypeDecl {String getValue() {return var.toString();}}TypeDecl typeDecl = new TypeDecl();
OtherObject other = new OtherObject();other.process(this);
int count = 0;do {List<String> list = this.overloadMethod(1);var = list;count++;} while (count < 3);
if (var instanceof List) {switch (rec.targetField.toString()) {case "1":String str = rec.innerOverloadMethod();var = str;break;}}}
public List<String> overloadMethod(int val) {return new ArrayList<>(List.of(String.valueOf(val)));}
public List<String> overloadMethod(String str) {return new ArrayList<>(List.of(str));}}}
Runnable anon = new Runnable() {public void run() {}};}
private abstract class TargetClass {static class StaticNested {record InnerRec() {Object targetField;
private String innerOverloadMethod() {return super.toString();}
private String innerOverloadMethod(int val) {return String.valueOf(val);}}}}
class OtherObject {<T> void process(SourceClass<T>.MemberInner.InnerRec innerRec) {}}