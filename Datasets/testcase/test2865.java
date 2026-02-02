package test;
import java.io.IOException;import java.util.List;
sealed abstract class SourceClass permits SourceSubClass {static {// Varargs method in static code blocksTargetClass.StaticNested.varargsMethod(1, new TargetClass());}
// Method with List type parameterpublic TargetClass methodToMove(List<String> list, TargetClass target) throws IOException {// Constructor invocationTargetClass.StaticNested staticNested = new TargetClass.StaticNested();// Variable call (source contains target's field)int val = target.targetField;// Expression statementval *= 2;
synchronized (this) {target.targetField = val;}
// Requires throws IOExceptionif (list.isEmpty()) throw new IOException();
return target;}
// Overload existspublic TargetClass methodToMove(List<Integer> list) {return new TargetClass();}}
final class SourceSubClass extends SourceClass {}
public class TargetClass {int targetField = 1; // Target field used in source
static class StaticNested {// Varargs method with specified featuresprivate static void varargsMethod(int num, TargetClass... targets) {super.toString(); // super.methodName()}}}