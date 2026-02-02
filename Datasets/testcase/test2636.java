package test.same;
import java.util.ArrayList;import java.util.List;
abstract class SourceClass {List<String> overloadingMethod(TargetClass target) throws IllegalStateException {@Deprecatedclass TypeDecl {transient TargetClass fieldHolder;}TypeDecl typeDecl = new TypeDecl();typeDecl.fieldHolder = new TargetClass() {};
List<String> result = new ArrayList<>();int count = 0;while (count < 2) {Object var1 = target.field1;Object var2 = typeDecl.fieldHolder.field2;
if (var1 == null) {throw new IllegalStateException("field1 is null");}if (var2 == null) {count++;continue;}
result.add(var1.toString());result.add(var2.toString());; // Empty statementcount++;}
return result;}
List<String> overloadingMethod(TargetClass target, int limit) throws IllegalStateException {return overloadingMethod(target);}}
/**
Javadoc for TargetClass
Abstract class with fields used in overloading methods
*/
public abstract class TargetClass {
Object field1;
Object field2;
}