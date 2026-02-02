package sourcepkg;
import targetpkg.TargetClass;import java.io.IOException;
public record SourceClass(int sourceField, TargetClass targetField) extends ParentClass implements SomeInterface {public class MemberInnerClass {public record SourceInnerRec() {public int getValue() {int result = 0;try {for (int i = 0; i < 2; i++) {TargetClass tc = new TargetClass();result += ParentClass.superTypeMethod(targetField.value());result += tc.value();}} catch (IOException e) {result = -1;}return result;}}}
class LocalInnerClass {void localMethod() {}}}
package targetpkg;
import java.io.IOException;
non-sealed record TargetClass<T>(int value) {static class NestedStaticClass {}}
class ParentClass {protected static int superTypeMethod(int arg) {return arg * 2;}}
interface SomeInterface {}