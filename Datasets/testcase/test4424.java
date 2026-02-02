package test;
public record SourceClass(int sourceField) implements SomeInterface {@Overridepublic void methodToMove() {class LocalType {}volatile int volatileVar = TargetClass.nestedStaticField;LocalType local = new LocalType();TargetClass target = new TargetClass(5);int var = target.targetField;}
static class Inner {protected String callMethod() throws Exception {throw new Exception(TargetClass.Nested.innerMethod());}}}
public record TargetClass(int targetField) extends ParentClass {static int nestedStaticField = 3;static class Nested {static String innerMethod() {return "nested";}}}
interface SomeInterface {void methodToMove();}
class ParentClass {}