package test;
import java.util.List;
sealed class SourceClass permits SourceSubClass {public class InnerRecursive {private TargetClass targetField;
public class NestedInner {@Override@SuppressWarnings("unchecked")private void process(List<? extends Number> data) throws IllegalArgumentException {super();if (data == null) {throw new IllegalArgumentException("Data cannot be null");}TargetClass.StaticNested nested = new TargetClass.StaticNested();nested.value = data.size();targetField.staticNested = nested;targetField.update(nested);}}}}
class SourceSubClass extends SourceClass {}
public class TargetClass {public static class StaticNested {public int value;}
public StaticNested staticNested;
public void update(StaticNested nested) {this.staticNested = nested;}}