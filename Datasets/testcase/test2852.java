package source;
import target.TargetClass;import java.util.Objects;
protected class SourceClass {static class StaticNested {class SourceInner extends ParentClass {@OverrideObject methodToMove(TargetClass.TargetInnerRec target) {try {// Constructor invocationTargetClass.TargetStaticNested staticNested = new TargetClass.TargetStaticNested();// Super keywordssuper.toString();
// ForStatement with target_featurefor (int i = 0; i < 1; i++) {Object obj = target;obj.field = 1; // obj.field}
// Variable call (contains target parameter)int val = target.value;// Expression statementval++;
// Anonymous inner classRunnable anon = new Runnable() {@Overridepublic void run() {}};
return val;} catch (Exception e) {return null;}}}}}
abstract class ParentClass {abstract Object methodToMove(TargetClass.TargetInnerRec target);}
package target;
protected class TargetClass {static class TargetStaticNested {}
record TargetInnerRec(int value) {Object field;}}