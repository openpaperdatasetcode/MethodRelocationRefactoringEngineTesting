package source;
import target.TargetAnnotation;
public @interface SourceAnnotation {private TargetAnnotation targetField = new TargetAnnotation() {};
static class SourceStaticNested {int recursiveMethod(int count) {if (count >= 2) return count;return new SourceStaticNested().recursiveMethod(++count);}}
class SourceInner {default void instanceMethod () {
for (int i = 0; i < 1; i++) {int baseResult = new SourceStaticNested ().recursiveMethod (0);System.out.println (baseResult);}
volatile int volatileVar = 1;
TargetAnnotation varCall = SourceAnnotation.targetField;varCall.innerClass ().print ();}}
TargetAnnotation value() default @TargetAnnotation;}
package target;
public @interface TargetAnnotation {class TargetInnerClass {void print () {System.out.println ("TargetInnerClass");}}
TargetInnerClass innerClass () default @TargetInnerClass;}
