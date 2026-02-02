package test;
import java.util.List;import java.util.ArrayList;
public class TargetClass {public static class StaticNested {}}
class ParentClass {public List<String> callMethod(TargetClass target) {List<String> list = new ArrayList<>();list.add(target::toString);return list;}}
protected class SourceClass extends ParentClass {private TargetClass targetField;private int outerPrivate;
public class MemberInner {}
public void createAnonymousInner() {Runnable r = new Runnable() {public void run() {}};}
strictfp TargetClass instanceMethod() {outerPrivate = 5;TargetClass.StaticNested nested = new TargetClass.StaticNested();return targetField;}
strictfp TargetClass instanceMethod(int param) {outerPrivate = param;return targetField;}
public void initializeObject() {TargetClass obj = new TargetClass();List<String> result = super.callMethod(obj);}}