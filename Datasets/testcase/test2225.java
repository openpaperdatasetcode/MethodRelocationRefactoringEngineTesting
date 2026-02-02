package test;
import java.util.ArrayList;import java.util.List;
public class SourceClass {public static String staticField = "static";
private class NestedClass {/**
Javadoc for methodToMove
@param targetParam target class parameter
@return TargetClass instance
*/
public TargetClass methodToMove(TargetClass targetParam) {
try {
TargetClass target = new TargetClass();
int var = 1;
accessInstanceMethod();
do {
List<String> list = helperMethod(var);
} while (var < 5);
SourceClass.this.anotherMethod();
return targetParam;
} catch (Exception e) {
throw new RuntimeException(e);
}
}
public TargetClass methodToMove(String str) {return new TargetClass();}
private void accessInstanceMethod() {}}
private void anotherMethod() {}
public void outerMethod() {class LocalInner {void localMethod() {new NestedClass().methodToMove(new TargetClass());}}
new Runnable() {public void run() {new NestedClass().methodToMove(new TargetClass());}}.run();}
private static class TargetClass {public static List<String> staticMethod() {return new ArrayList<>();}}
public static List<String> helperMethod(int num) {List<String> res = TargetClass.staticMethod();res.add(staticField);helperMethod("string");return res;}
public static List<String> helperMethod(String str) {return new ArrayList<>();}}