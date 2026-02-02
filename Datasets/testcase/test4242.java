package test;
import java.lang.reflect.Type;
class SourceClass extends ParentClass {// Static nested class (source_class feature)static class SourceStaticNested {}
// Local inner class (source_class feature)void createLocalInner() {class SourceLocalInner {}new SourceLocalInner();}
// Instance field (for variable call/access_instance_field)private int sourceInstanceField;// Volatile TypeLiteral (matches "numbers": "1", "modifier": "volatile", "exp": "TypeLiteral")private volatile Type typeLiteral = new TypeLiteral<List<String>>() {}.getType();
// Abstract method to be refactored (matches method structure)public final abstract TargetClass getTargetInstance(TargetClass targetParam);
// Constructor with SuperConstructorInvocation (matches nested feature)public SourceClass() {super(targetParam -> targetParam.targetField); // "obj.field" reference}
// Method containing required statements (for method features)void helperMethod(TargetClass targetParam) {// NullPointerException handling contextif (targetParam == null) {throw new NullPointerException("Target cannot be null");}
// Synchronized statementsynchronized (this) {// While statement + Break statementint i = 0;while (i < 5) {if (targetParam.targetField > 3) {break;}i++;}}
// Variable call + Access instance fieldsourceInstanceField = targetParam.targetField;// Super keywords + Raw type (raw List)List rawList = super.getRawList();}}
// Parent class for super constructor/keywordsclass ParentClass {private List rawList; // For raw_type feature
public ParentClass(java.util.function.Function<TargetClass, Integer> fieldAccessor) {}
public List getRawList() {return rawList;}}
// TypeLiteral helper class (for TypeLiteral feature)abstract class TypeLiteral<T> {public Type getType() {return getClass().getGenericSuperclass();}}
// Target class (matches target_class structure)abstract class TargetClass {// Static nested class (target_class target_feature)static class TargetStaticNested {}// Instance field (for "obj.field" and variable call)int targetField;}
