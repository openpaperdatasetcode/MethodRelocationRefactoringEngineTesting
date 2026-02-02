package test;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;
public class TargetClass implements Runnable {protected String targetField;
public void run() {}
public void createLocalInner() {class LocalInner {public void localMethod() {}}}}
protected class SourceClass extends ParentClass {protected int outerProtected;private TargetClass targetField;
class MemberInner {public void memberMethod() {}}
public void createAnonymousInner() {Runnable r = new Runnable() {public void run() {}};}
private List<String> overloadedMethod() {return new ArrayList<>();}
private List<String> overloadedMethod(int param) {super();List rawList;this.outerProtected = 1;targetField.targetField = "test";rawList = new ArrayList();
try {Method method = getClass().getMethod("overloadedMethod");method.invoke(this);} catch (Exception e) {}
List<String> result = new ArrayList<>();Runnable lambda = () -> result.addAll(overloadedMethod(param - 1));lambda.run();
return result;}}
class ParentClass {}