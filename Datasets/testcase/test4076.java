package test;
import java.io.IOException;import java.io.Serializable;
@interface CustomAnnotation {}
interface TestInterface {}
private class SourceClass implements TestInterface, Serializable {private TargetClass targetField = new TargetClass();private static int outerPrivateField = 1;
static class StaticNested {}
@CustomAnnotationsynchronized static Object staticMethod() throws IOException {for (int i = 0; i < 5; i++) {if (i == outerPrivateField) {continue;}}
class LocalInner extends StaticNested {LocalInner() {super();}}LocalInner inner = new LocalInner();
Runnable r = SourceClass::superMethod;r.run();
Object var = targetField.memberInner.field;return var;}
private static void superMethod() {}}
sealed class TargetClass permits SubTarget {class MemberInner {int field = 1;}}
final class SubTarget extends TargetClass {}
class OthersClass {default Object callMethod() {return (Math.random() > 0.5) ? SourceClass.staticMethod() : new TargetClass().superMethod();}
void superMethod() {}}
