package test;
import java.util.List;import java.util.ArrayList;
public class SourceClass {protected String outerProtectedField = "protectedValue";
public static class StaticNested {public class Inner {public class InnerRec {public List<String> recursiveMethod(int n, PrivateTargetClass target) {List<String> result = new ArrayList<>();if (n <= 0) {result.add(target.innerField);return result;}
try {String val = outerProtectedField;result.add(val);result.addAll(this.recursiveMethod(n - 1, target));} catch (Exception e) {}
return result;}
private List<String> callMethod(int choice, PrivateTargetClass target) {switch (choice) {case 1:return (param) -> new InnerRec().recursiveMethod(param, target);default:return new ArrayList<>();}}
private List<String> callMethod(String unused, PrivateTargetClass target) {return new ArrayList<>();}}}}
void sampleMethod() {class LocalInner {}LocalInner local = new LocalInner();}}
private class PrivateTargetClass extends ParentClass {String innerField = "targetValue";
void targetMethod() {class TargetLocalInner {}TargetLocalInner local = new TargetLocalInner();}}
class ParentClass {}