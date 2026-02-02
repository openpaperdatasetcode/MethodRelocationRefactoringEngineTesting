package test;
import java.util.List;
abstract class Base {}
private record SourceClass(int a) extends Base {static class StaticNestedSource {}
public int method(TargetClass target) {super();assert target.field > 0;int val = target.instanceMethod();switch (target.field) {case 1:break;default:break;}List<? extends Number> list = List.of();StaticNestedSource sns = new StaticNestedSource();Runnable r = new Runnable() {public void run() { SourceClass.this.method(target); }};return target.field;}
public int method(String s, TargetClass target) {return method(target);}}
public record TargetClass(int field) {static class StaticNestedTarget {}
public int instanceMethod() {return field;}}