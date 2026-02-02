package test;
import java.util.function.Function;
public class TargetClass {private int targetField;
public void createLocalInner() {class TargetLocalInner {int localField;
int recursiveLocalMethod(int depth) {if (depth <= 0) {return localField;}localField += depth;return recursiveLocalMethod(depth - 1);}}}
int overloadedMethod(int val) {return val * 2;}
int overloadedMethod(String val) {return Integer.parseInt(val) * 2;}}
class SourceClass {private TargetClass targetField = new TargetClass();
class SourceMemberInner {}
public void createLocalInner() {class SourceLocalInner {}}
protected int instanceMethod() {TargetClass.TargetLocalInner targetLocal = new TargetClass() {{createLocalInner();}}.new TargetLocalInner();
Function<Integer, Integer> methodRef = targetField::overloadedMethod;int result = 0;int depth = 2;
do {synchronized (targetField) {result += methodRef.apply(depth);result += targetLocal.recursiveLocalMethod(depth);}depth--;} while (depth > 0);
return result;}}
class TargetClass {class TargetLocalInner {int localField;
int recursiveLocalMethod(int depth) {if (depth <= 0) {return localField;}localField += depth;return recursiveLocalMethod(depth - 1);}}}