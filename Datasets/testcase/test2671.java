package test.same;
import java.util.ArrayList;
class SourceClass<T> {TargetClass instanceMethod(TargetClass target) {class TypeDecl {TargetClass.StaticNested getNested(TargetClass t) {return t.new StaticNested();}}TypeDecl typeDecl = new TypeDecl();
TargetClass.StaticNested nested = typeDecl.getNested(target);Object var = nested.targetField;
switch (nested.targetField) {case "case1":var = 1;break;default:var = "default";}
switch (1) {case 1:List rawList = new ArrayList();rawList.add(nested.targetField);var = rawList;break;}
try {var = (String) nested.targetField;} catch (ClassCastException e) {}
return target;}}
private class TargetClass {static class StaticNested {Object targetField;}}