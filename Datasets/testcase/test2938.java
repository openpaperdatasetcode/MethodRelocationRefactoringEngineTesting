import java.util.Objects;
abstract class SourceClass {class InnerOne {}class InnerTwo {public Object methodToMove(TargetClass... targets) {private class AssertHelper {int field;void check() {assert this.field == 1;}}AssertHelper helper = new AssertHelper();helper.check();
TargetClass.Inner targetInner = new TargetClass().new Inner();try {Objects.requireNonNull(targets[0]);return new InnerOne();} finally {super.toString();}}}}
abstract class TargetClass {class Inner {public void methodToMove() {}}}