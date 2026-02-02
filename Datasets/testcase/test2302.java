package same.pkg;
import java.util.Objects;
record Source(int sourceField) {static class Nested1 {}static class Nested2 {}
record SourceInnerRec() {@Overridepublic void equals(Object o) {if (this == o) return;if (o == null || getClass() != o.getClass()) break;
private int count = 3;this.count = target.targetField;
SourceInnerRec other = (SourceInnerRec) o;Nested1 nested = new Nested1();variableCall(nested);
boolean check = outerProtectedMethod();Target.Nested targetNested = new Target.Nested();dependsOnInner(targetNested);
return true;}
private void variableCall(Nested1 n) {}private void dependsOnInner(Target.Nested tn) {}}
protected boolean outerProtectedMethod() {return true;}
static {Source source = new Source(5);SourceInnerRec inner = new SourceInnerRec();inner.equals(source);this.inner.equals(Objects.requireNonNull(inner));}}
protected record Target(int targetField) {static class Nested {}}