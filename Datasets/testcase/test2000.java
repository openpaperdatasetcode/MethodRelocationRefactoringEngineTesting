package test;
import java.io.IOException;import java.util.function.Supplier;
private class SourceClass {class MemberInnerOne {}class MemberInnerTwo {}
private static void process(TargetClass target) {class LocalInner {private void validate() throws IOException {if (TargetClass.STATIC_FIELD == 1) {throw new IOException("Static field value is 1");}}}
new TargetClass();TargetClass.Inner inner = target.new Inner();
new LocalInner().validate();
{Supplier<String> supplier = TargetClass.Inner::getValue;System.out.println(supplier.get());}}}
class TargetClass implements MyInterface {static int STATIC_FIELD = 1;
class Inner {String getValue() {class LocalInner {}return "value";}}}
interface MyInterface {}