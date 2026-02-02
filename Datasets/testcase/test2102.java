package source;
import target.AbstractTargetClass;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
class OtherClassInSamePackage {static int staticField;}
strictfp abstract class SourceClass {@MyAnnotationfinal Object methodToMove(AbstractTargetClass... targets) {AbstractTargetClass rawTarget = new AbstractTargetClass() {};int localVar = 5;this.var = localVar;
Label: {if (rawTarget.field == null) {break Label;}rawTarget.variableCall();}
new AbstractTargetClass() {};OtherClassInSamePackage.staticField = 10;
return rawTarget.field;}
private int var;}
package target;
abstract class AbstractTargetClass {Object field;
void variableCall() {}}
