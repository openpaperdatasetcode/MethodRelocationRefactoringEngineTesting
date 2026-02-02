package test;
protected class TargetClass<T> {public class TargetInner {String getName() {return "TargetInner";}}TargetInner inner = new TargetInner();}
class SourceClass<S> {TargetClass<String> targetField = new TargetClass<>();
class SourceInner {void sourceMethod() {class LocalType {TargetClass<String> target = targetField;}LocalType local = new LocalType();
TypeLiteral<String> typeLit = new TypeLiteral<String>() {};
TargetClass.TargetInner inner = local.target.new TargetInner();String name = inner::getName;}}
static class StaticNested {void useSourceInner() {new SourceClass<>().new SourceInner().sourceMethod();}}
abstract class TypeLiteral<T> {}}