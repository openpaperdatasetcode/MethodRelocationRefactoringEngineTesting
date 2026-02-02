package test;
import java.util.List;import java.util.ArrayList;
public class TargetClass {public String targetField;class TargetInner {}}
public class SourceClass {class Inner1 {}class Inner2 {private List<String> innerHelper(SourceClass outer, TargetClass target) {List<String> result = new ArrayList<>();result.add(target.targetField);return result;}}
/**
Static method to test Move Method refactoring
Depends on TargetClass's field and inner class*/@RefactorTestAnnotationpublic static List<String> methodToMove(TargetClass target) {try {SourceClass outer = new SourceClass();List<String> data = outer.new Inner2().innerHelper(outer, target);
switch (target.targetField) {case "test":data.add("matched");break;default:break;}
TargetClass.TargetInner targetInner = target.new TargetInner();return data;} catch (Exception e) {return new ArrayList<>();}}}
@interface RefactorTestAnnotation {}