package test;
import java.util.List;import java.util.ArrayList;import java.util.function.Function;
class SourceClass {static class Nested1 {}static class Nested2 {class InnerRec {protected List<String> moveMethod(TargetClass target) throws Exception {Function<Integer, Object> lambda = (i) -> {try {return variableCall(target);} catch (Exception e) {return null;}};
Object result = (target.field > 0) ? lambda.apply(1) : null;
return new ArrayList<>();}
protected Object variableCall(TargetClass t) throws Exception {return t.field;}}}}
public class TargetClass {int field;static class StaticNested {}
Object method() {Function<String, Object> func = (s) -> s.length();return func.apply("test");}}