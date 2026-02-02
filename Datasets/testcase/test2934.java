import java.util.ArrayList;import java.util.List;
sealed enum SourceEnum implements Runnable permits SourceEnum.SubEnum {INSTANCE;
static class NestedStatic1 {}static class NestedStatic2 {}
class SourceInner {public TargetEnum methodToMove(TargetEnum... targets) {private class EnhancedForHelper {void process() {for (TargetEnum t : targets) {if (TargetEnum.STATIC_FIELD == 1) {break;}}}}new EnhancedForHelper().process();
try {GenericHelper<String> helper = new GenericHelper<>();helper.execute(new TargetEnum.StaticNested());} catch (Exception e) {}
Runnable r1 = SourceEnum.super::toString;volatile Runnable r2 = TargetEnum.super::toString;
protectedField = 5;return targets[0];}}
protected int protectedField;
static final class SubEnum extends SourceEnum {@Overridepublic void run() {}}
@Overridepublic void run() {}}
private enum TargetEnum {VALUE;
static int STATIC_FIELD = 1;static class StaticNested {void nestedMethod() {}}
class GenericHelper<T> {void execute(StaticNested nested) {new TargetEnum().nestedMethod();}}}
