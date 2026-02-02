package test;
import java.util.ArrayList;import java.util.List;
interface MyInterface {}
final class TargetClass {String field = "test";
{new Object() {String getField() {return field;}};}}
class OtherClass {private static void otherMethod(String... args) {}}
class SourceClass implements MyInterface {static class StaticNested {record SourceInnerRec() {protected List<String> instanceMethod(TargetClass target) {List<String> list = new ArrayList<>();assert target != null;
for (int i = 0; i < 3; i++) {OtherClass.otherMethod(target.field);if (i == 1) {break;}; // empty statementlist.add(target.field);}
new Runnable() {@Overridepublic void run() {SourceClass.this.toString();super.toString();}}.run();
return list;}}}}