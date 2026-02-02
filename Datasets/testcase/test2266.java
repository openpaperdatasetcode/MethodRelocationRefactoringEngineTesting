package test;
import java.util.List;
sealed class SourceClass permits SubClass1, SubClass2 {/**
Method Javadoc
*/
abstract List<String> abstractMethod(TargetClass target);
}
final class SubClass1 extends SourceClass {List<String> abstractMethod(TargetClass target) {label: {String var = target.field;variableCall = var;if (var.isEmpty()) {throw new RuntimeException();}break label;}return null;}String variableCall;}
final class SubClass2 extends SourceClass {List<String> abstractMethod(TargetClass target) {return null;}}
class TargetClass {String field;{new Runnable() {};}}