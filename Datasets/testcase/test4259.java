package test;
import java.lang.reflect.Type;
abstract class SourceClass<T extends ParentClass> {private TargetClass<String> targetField = new TargetClass<>();private int outerPrivateField = 10;
class SourceMemberInner {}
void createAnonymous() {Runnable anon = new Runnable() {public void run() {}};}
@Deprecatedprotected TargetClass<String> processTarget() {Type typeLiteral = new TypeLiteral<TargetClass<String>>() {}.getType();
TargetClass<String>.TargetInner inner = targetField.new TargetInner();inner.varargsMethod("arg1", "arg2");
int result = super.parentMethod() > 5 ? targetField.value : outerPrivateField;targetField.value = result;
return targetField;}}
class TargetClass {
int value;
class TargetInner {public void varargsMethod(String... args) {Runnable lambda = () -> System.out.println(args.length);lambda.run();}}}
abstract class ParentClass {protected int parentMethod() {SourceClass<?> source = new SourceClass<ParentClass>() {};return source.targetField.new TargetInner().varargsMethod() ? 10 : 5;}}
abstract class TypeLiteral<V> {public Type getType() {return getClass().getGenericSuperclass();}}